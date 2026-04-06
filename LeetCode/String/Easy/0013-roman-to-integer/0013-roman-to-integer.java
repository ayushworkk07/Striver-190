class Solution {
    public int romanToInt(String s) {
        HashMap<Character,Integer> romanMap = new HashMap<>();   
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);


        int n = s.length();

        int total = 0;
        for(int i = n-1 ;i >=0 ; i--){
            char ch = s.charAt(i);
            //anytime you find a lesser value than next its to be subtracted
            if(i!= n-1 && romanMap.get(ch) < romanMap.get(s.charAt(i+1))){
                total -= romanMap.get(ch);
            }
            else
                total+=romanMap.get(ch);
        }

        return total;
    }
}