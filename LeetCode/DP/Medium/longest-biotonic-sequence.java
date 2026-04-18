class Solution {
    public static int longestBitonicSequence(int n, int[] nums) {
        if (n == 0) return 0;

        int[] LIS = new int[n];
        Arrays.fill(LIS,1);

        for(int i =0 ;i < n ;i++){
            for(int j = 0 ;j < i ; j++){

                if(nums[j] < nums[i]){
                    LIS[i] = Math.max(LIS[j]+1 , LIS[i]);
                }
            }
        }

        int[] LDS = new int[n];
        Arrays.fill(LDS,1);

        for(int i = n-1 ; i>=0 ;i--){
            for(int j = n-1 ;j > i ; j--){

                if(nums[j] < nums[i]){
                    LDS[i] = Math.max(LDS[j]+1 , LDS[i]);
                }
            }
        }

        int maxMountain = 0;
        for(int i =0 ;i < n ; i++){
            // MOUNTAIN CHECK: peak must have elements on both sides
            if(LIS[i] > 1 && LDS[i] > 1) { 
                maxMountain = Math.max(maxMountain, LIS[i] + LDS[i] - 1);
            }
        }

        return maxMountain;
    }
}
