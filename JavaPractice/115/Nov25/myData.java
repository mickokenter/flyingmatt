public class myData {
        private int [] data;
        private int currentCount;
        private static final int DEFAULT_LENGTH=10;
        public myData(){
                data = new int[DEFAULT_LENGTH];
                currentCount=0;
        }
        public myData(int newLength){
        // instantiate the array to size newLength, initialize currentCount;
                if (newLength>0)
                        data = new int[newLength];
                else
                        data = new int[DEFAULT_LENGTH];

                currentCount=0;
        }
        // accessor methods
        public int getCurrentCount(){
                return currentCount;
        }
        public int[] getData(){
                int [] copy = new int[currentCount];
                int i=0;
                while (i<currentCount){
                        copy[i]=data[i];
                        i++;
                }
                return copy;
        }
        public boolean findNumber(int number){
                int i=0;
                while (i<currentCount){
                        if (data[i] == number) return true;
                        i++;
                }
                return false;
        }
        public int getMax(){
                if (currentCount>0) {
                        int maxSoFar=data[0];
                        int i=1;
                        while (i<currentCount){
                                if(data[i] > maxSoFar) maxSoFar=data[i];
                                i++;
                        }
                        return maxSoFar;
                }
                else return Integer.MIN_VALUE;
        }

        // mutator methods, no SET* methods needed for instance variables
        public boolean addNumber(int newNumber){
        // no need to verify newNumber argument, any int is fine
                if (currentCount<data.length) {
                        data[currentCount]=newNumber;
                        currentCount++;
                        return true;
                }
                else return false;
        }
        public String toString() {
                String temp="currentCount="+currentCount+" Data:";
                int i=0;
                while (i<currentCount){
                        temp=temp+data[i]+" ";
                        i++;
                }
                return temp;
        }
}
