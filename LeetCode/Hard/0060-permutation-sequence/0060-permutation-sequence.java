class Solution {
    public String getPermutation(int n, int k) {
        ArrayList<Integer> numbers = new ArrayList<>();
        int fact = 1;

        for(int i =1 ; i < n ; i++){
            fact *= i;
            numbers.add(i);
        }

        //add last number
        numbers.add(n);

        //turn k into 0 based indexing
        k = k-1;

        StringBuilder sb = new StringBuilder();
        while(true){
            sb.append(numbers.get(k/fact));         //fixing an element find which sequence bucket it lies in

            numbers.remove(k/fact);    //find the next sequence to find in that bucket

            if(numbers.isEmpty())   
                break;


            k = k%fact;                             //reduce k to find the next permutation in the remaining sequences

            
            fact = fact/numbers.size();             //reduce fact to remaining number of sequences
            
        }           
        return sb.toString();
    }
}