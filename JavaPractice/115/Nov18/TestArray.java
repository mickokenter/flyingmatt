class TestArray {
	public static void main(String[] args) 	{
		int [] data = new int[100];
		int i;
		for (i=0;i<100 ;i++ ) {
			data[i]=(int)Math.floor((Math.random()*200)-100);
		}
		for (i=0;i<100 ;i++ ) {
			System.out.print(data[i]+" ");
			if (i%10==9)System.out.println();
		}		
		// add code to find and output the sum of all elements in array data
		i=0;
		int sum=0;
		while ( i <data.length) {
			sum =data[i] + sum;		
			i = i+1;
		}
		System.out.println("sum="+sum);		
		
		// add code to find and output the largest element in array data
		int largestSoFar=data[data.length-1];
		int largestSoFarI=data.length-1;
		for (i=data.length-2; i>=0; i--){
			if (data[i] >largestSoFar) {
				largestSoFar = data[i];
				largestSoFarI=i;
			}		
		}
		System.out.println("largest="+largestSoFar+ " at location "+largestSoFarI);	

		// add code to count and output how many positive elements are in array data
		i=0;
		int count=0;
		while ( i <data.length) {
			if (data[i]>0) count++;
			i = i+1;
		}
		System.out.println("count positive="+count);		
		

		// add code to find and output the smallest difference between consecutive elements in array data

		i=0;
		int smallDifference=Math.abs(data[0]-data[1]);   
		while ( i <data.length-1) {
			if (Math.abs(data[i]-data[i+1])<smallDifference)
				smallDifference=Math.abs(data[i]-data[i+1]);
			i = i+1;
		}
		System.out.println("small difference="+smallDifference);		
		
		double avg = average(data);
		System.out.println("average="+avg);
		
		Date [] examDays = new Date[5];
		i=0;
		while ( i <examDays.length) {
			System.out.println(examDays[i]);
			i++;
		}	
		examDays[0]=new Date(12,5,2013);
		examDays[1]=new Date(12,6,2013);
		examDays[2]=new Date(12,7,2013);
		i=0;
		while ( i <examDays.length) {
			System.out.println(examDays[i]);
			i++;
		}
		examDays[1].setDate(examDays[1].getMonth(),9,examDays[1].getYear());	
		i=0;
		while ( i <examDays.length) {
			if (examDays[i]!=null) 
				System.out.println(examDays[i]);
			i++;
		}		
	}
	
	public static double average(int [] d){
		int i=0;
		double sum=0;
		while ( i <d.length) {
			sum =d[i] + sum;		
			i = i+1;
		}
		return sum / d.length;
	
	}
	
}