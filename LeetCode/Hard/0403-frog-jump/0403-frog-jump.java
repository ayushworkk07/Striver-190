class Solution {
    /* 1) we need to know at each stone what speeds are reaching it so HM<Stone,HS<speeds>>
        2) we add 0 speed for stone 0 and move from their
        3) ans = hm.get(lastStone).size() > 0 ? true;
    */
    public boolean canCross(int[] stones) {
        HashMap<Integer,HashSet<Integer>> dp = new HashMap<>();
        int n = stones.length;

        for(int i = 0 ;i < n ; i++){
            dp.put(stones[i],new HashSet<>());
        }

        dp.get(stones[0]).add(0);

        //if you use dp.keySet() , it will give keys in random order
        for(int i =0 ;i < n ; i++){
            int stone = stones[i];

            HashSet<Integer> speeds = dp.get(stone);

            for(int stoneSpeed : speeds){

                for(int j = stoneSpeed -1 ; j <= stoneSpeed+1 ;j++){

                    int nextStone = stone + j;

                    if(j >0 && dp.containsKey(nextStone)){
                        dp.get(nextStone).add(j);
                    }
                }
            }
        }

        return dp.get(stones[n-1]).size() > 0 ? true : false;
    }
}