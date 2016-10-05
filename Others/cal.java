import java.io.*;
import java.util.Scanner.*;
import java.util.*;

public class cal{
	public static void main(String[] args){
        cal c = new cal();
        
		String point_cloud_1 = "pointcloud1.fuse";
		String point_cloud_2 = "pointcloud2.fuse";
        
        ArrayList<Double> x_1 = new ArrayList<Double>();
        ArrayList<Double> y_1 = new ArrayList<Double>();
        ArrayList<Double> z_1 = new ArrayList<Double>();
        ArrayList<Integer> d_1 = new ArrayList<Integer>();
        
        ArrayList<Double> x_2 = new ArrayList<Double>();
        ArrayList<Double> y_2 = new ArrayList<Double>();
        ArrayList<Double> z_2 = new ArrayList<Double>();
        ArrayList<Integer> d_2 = new ArrayList<Integer>();
        
        ArrayList<Double> x_delta = new ArrayList<Double>();
        ArrayList<Double> y_delta = new ArrayList<Double>();
        ArrayList<Double> z_delta = new ArrayList<Double>();
        
        int i = 0;
        int j = 0;
        
        double x_sum = 0;
        double y_sum = 0;
        double z_sum = 0;
        
        try{
            File file1 = new File(point_cloud_1);
            File file2 = new File(point_cloud_2);
            
            Scanner s_1 = new Scanner(file1);
            Scanner s_2 = new Scanner(file2);
            
            String[] token;
            
            while(s_1.hasNextLine()){
                String line = s_1.nextLine();
                if(line.equals("")) break;
                token = line.split(" ");
                double t1 = Double.parseDouble(token[0]);
                x_1.add(t1);
                double t2 = Double.parseDouble(token[1]);
                y_1.add(t2);
                double t3 = Double.parseDouble(token[2]);
                z_1.add(t3);
                d_1.add(Integer.parseInt(token[3]));
                i++;
            }
            s_1.close();                
            
            while(s_2.hasNextLine()){
                String line = s_2.nextLine();
                if(line.equals("")) break;
                token = line.split(" ");
                double t1 = Double.parseDouble(token[0]);
                x_2.add(t1);
                double t2 = Double.parseDouble(token[1]);
                y_2.add(t2);
                double t3 = Double.parseDouble(token[2]);
                z_2.add(t3);
                d_2.add(Integer.parseInt(token[3]));
                j++;
            }
            s_2.close();
        }
        catch(IOException ex) {
            System.out.println("Error reading file");
        }
        
        if(i == j){ //for easy pairing, we checked whether i is equal to j, though it's actually not necessary
            //get the difference between two point clouds and the sum of the difference
            for(int k = 0; k < i; k++){
                double delta = 0;
                delta = x_1.get(k) - x_2.get(k);
                x_delta.add(delta);
                x_sum += delta;
                delta = y_1.get(k) - y_2.get(k);
                y_delta.add(delta);
                y_sum += delta;
                delta = z_1.get(k) - z_2.get(k);
                z_delta.add(delta);
                z_sum += delta;
            }
            //calculate the mean for later use
            double x_mean = x_sum / i;
            double y_mean = y_sum / i;
            double z_mean = z_sum / i;
            
            //just for nicer and useful matrix, round the mean to 0.000001
            double x_mean_o = Math.round(x_sum / i * 1000000) / 1000000.0;
            double y_mean_o = Math.round(y_sum / i * 1000000) / 1000000.0;
            double z_mean_o = Math.round(z_sum / i * 1000000) / 1000000.0;
            
            //calculate the variance
            double x_var = c.getVariance(x_mean, x_delta);
            double y_var = c.getVariance(y_mean, y_delta);
            double z_var = c.getVariance(z_mean, z_delta);
            
            //find the normal vector
            double[] nor_1 = c.normalVector(x_1, y_1, z_1);
            double[] nor_2 = c.normalVector(x_2, y_2, z_2);
            
            //determine whether two normal vectors are parallel
            double nn = (nor_1[0] * nor_2[0] + nor_1[1] * nor_2[1] + nor_1[2] * nor_2[2]) / (Math.sqrt(nor_1[0] * nor_1[0] + nor_1[1] * nor_1[1] + nor_1[2] * nor_1[2]) * Math.sqrt(nor_2[0] * nor_2[0] + nor_2[1] * nor_2[1] + nor_2[2] * nor_2[2]));
            
            //if not just translation, calculate rotaion first then the new translation, and output the Matrix
            if((x_var + y_var + z_var) > 0.00001 && nn < 0.999999){
                System.out.println("Aligning Point Cloud 1 to Point Cloud 2 needs:\nROTATION before TRANSLATION");
                
                //Calculate the Rotation Matrix
                double[] nor_1_x = {0, nor_1[1], nor_1[2]};
                double[] nor_2_x = {0, nor_2[1], nor_2[2]};
                double alpha = c.angle(nor_1_x, nor_2_x);
                
                double[] nor_1_y = {nor_1[0], 0, nor_1[2]};
                double[] nor_2_y = {nor_2[0], 0, nor_2[2]};
                double beta = c.angle(nor_1_y, nor_2_y);
                
                double[] nor_1_z = {nor_1[0], nor_1[1], 0};
                double[] nor_2_z = {nor_2[0], nor_2[1], 0};
                double gamma = c.angle(nor_1_z, nor_2_z);
                
                double[][] RMatrix = c.rotationMatrix(alpha, beta, gamma);
                
                //Apply the Rotation Matrix to pointcloud1 to get a 3rd pointcloud, called pointcloud3
                ArrayList<ArrayList<Double>> rotated_pc = new ArrayList<ArrayList<Double>>();
                ArrayList<Double> one = new ArrayList<Double>();
                for(int n = 0; n < i; n++){
                    one.add(1.0);
                }
                rotated_pc.add(x_1);
                rotated_pc.add(y_1);
                rotated_pc.add(z_1);
                rotated_pc.add(one);
                ArrayList<ArrayList<Double>> pointcloud3_plus1 = c.pointcloud_plus1(RMatrix, rotated_pc);
                
                //Calculate Translation Matrix from pointcloud3 to pointcloud2
                ArrayList<Double> x_new = pointcloud3_plus1.get(0);
                ArrayList<Double> y_new = pointcloud3_plus1.get(1);
                ArrayList<Double> z_new = pointcloud3_plus1.get(2);
                
                ArrayList<Double> x_delta_new = new ArrayList<Double>();
                ArrayList<Double> y_delta_new = new ArrayList<Double>();
                ArrayList<Double> z_delta_new = new ArrayList<Double>();
                
                double x_sum_new = 0;
                double y_sum_new = 0;
                double z_sum_new = 0;
                
                for(int n = 0; n < i; n++){
                    double delta = 0;
                    delta = x_new.get(n) - x_2.get(n);
                    x_delta_new.add(delta);
                    x_sum_new += delta;
                    delta = y_new.get(n) - y_2.get(n);
                    y_delta_new.add(delta);
                    y_sum_new += delta;
                    delta = z_new.get(n) - z_2.get(n);
                    z_delta_new.add(delta);
                    z_sum_new += delta;
                }
                
                double x_mean_new = x_sum_new / i;
                double y_mean_new = y_sum_new / i;
                double z_mean_new = z_sum_new / i;
                
                //combine Rotation Matrix with Translation Matrix and give output
                double[] Trol1 = {1, 0, 0, x_mean_new};
                double[] Trol2 = {0, 1, 0, y_mean_new};
                double[] Trol3 = {0, 0, 1, z_mean_new};
                double[] Trol4 = {0, 0, 0, 1};
                
                double[][] TMatrix = {Trol1, Trol2, Trol3, Trol4};
                
                double[][] CombinedMatrix = c.combineMatrix(TMatrix, RMatrix);
                
                System.out.println("The Matrix is:");
                for(int a = 0; a < 4; a++){
                    System.out.print("|");
                    for(int b = 0; b < 4; b++){
                        System.out.print(CombinedMatrix[a][b]);
                        if(b < 3)
                            System.out.print(" ");
                    }
                    System.out.println("|");
                }
            }
            else{ //if only translation, output Matrix
                System.out.println("Aligning Point Cloud 1 to Point Cloud 2 only needs:\nTRANSLATION\nThe Matrix is:\n|1\t0\t0\t" + x_mean_o + "|\n" + "|0\t1\t0\t" + y_mean_o + "|\n" + "|0\t0\t1\t" + z_mean_o + "|\n" + "|0\t0\t0\t1|");
            }
        }
        else System.out.println("Two pointclouds don't match!");
	}
    
    public double getVariance(double m, ArrayList<Double> alst){
        double temp = 0;
        for(double d : alst){
            temp += (m - d) * (m - d);
        }
        return temp/alst.size();
    }
    
    public double[] normalVector(ArrayList<Double> xal, ArrayList<Double> yal, ArrayList<Double> zal){
        double x_1 = xal.get(1) - xal.get(0);
        double y_1 = yal.get(1) - yal.get(0);
        double z_1 = zal.get(1) - zal.get(0);
        
        double x_2 = xal.get(2) - xal.get(0);
        double y_2 = yal.get(2) - yal.get(0);
        double z_2 = zal.get(2) - zal.get(0);
        
        
        double[] vec1 = {x_1, y_1, z_1};
        double[] vec2 = {x_2, y_2, z_2};
        
        double[] normal = {1, (z_1 * x_2 - z_2 * x_1)/(y_1 * z_2 - y_2 * z_1), (y_1 * x_2 - y_2 * x_1)/(y_2 * z_1 - y_1 * z_2)};
        return normal;
    }
    
    public double angle(double[] vec1, double[] vec2){
        double numer = vec1[0] * vec2[0] + vec1[1] * vec2[1] + vec1[2] * vec2[2];
        double deno = Math.sqrt(vec1[0] * vec1[0] + vec1[1] * vec1[1] + vec1[2] * vec1[2]) * Math.sqrt(vec2[0] * vec2[0] + vec2[1] * vec2[1] + vec2[2] * vec2[2]);
        double cosAngle = numer / deno;
        return Math.acos(cosAngle);
    }
    
    public double[][] rotationMatrix(double alpha, double beta, double gamma){
        double[] rol1 = {Math.cos(beta) * Math.cos(gamma), Math.cos(gamma) * Math.sin(alpha) * Math.sin(beta) - Math.cos(alpha) * Math.sin(gamma), Math.cos(alpha) * Math.cos(gamma) * Math.sin(beta) + Math.sin(alpha) * Math.sin(gamma), 0};
        double[] rol2 = {Math.cos(beta) * Math.sin(gamma), Math.cos(alpha) * Math.cos(gamma) + Math.sin(alpha) * Math.sin(beta) * Math.sin(gamma), Math.cos(alpha) * Math.sin(beta) * Math.sin(gamma) - Math.cos(gamma) * Math.sin(alpha), 0};
        double[] rol3 = {Math.sin(beta) * -1, Math.cos(beta) * Math.sin(alpha), Math.cos(alpha) * Math.cos(beta), 0};
        double[] rol4 = {0, 0, 0, 1};
        double[][] rm = {rol1, rol2, rol3, rol4};
        return rm;
    }
    
    public ArrayList<ArrayList<Double>> pointcloud_plus1(double[][] matrix, ArrayList<ArrayList<Double>> pc){
        ArrayList<ArrayList<Double>> pc_new = new ArrayList<ArrayList<Double>>();
        for(int i = 0; i < matrix.length; i++){
            ArrayList<Double> pc_temp = new ArrayList<Double>();
            for(int j = 0; j < pc.get(0).size(); j++){
                double sum = 0;
                for(int k = 0; k < 4; k++){
                    sum += matrix[i][k] * pc.get(k).get(j);
                }
                pc_temp.add(sum);
            }
            pc_new.add(pc_temp);
        }
        return pc_new;
    }
    
    public double[][] combineMatrix(double[][] m1, double[][] m2){
        double[][] mnew = new double[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                double sum = 0;
                for(int k = 0; k < 4; k++){
                    sum += m1[i][k] * m2[k][j];
                }
                mnew[i][j] = sum;
            }
        }
        return mnew;
    }
}