//note -> learn solution of printing in lexicographical order
/* LIS nlogn method doesn't garuntee a valid LIS but just the length
    so we store parent nodes of each node in LIS in an array 
    and reverse iterate from last index to the -1 (first node has no parent)
    and that becomes our LIS
*/
class Solution {
    public ArrayList<Integer> getLIS(int arr[]) {

        ArrayList<Integer> list = new ArrayList<>();
        int[] parent = new int[arr.length];
        Arrays.fill(parent,-1);

        for(int i = 0 ;i < arr.length ; i++){
            int val = arr[i];
            
            if(list.isEmpty() || val > arr[list.get(list.size()-1)]){
                //store parent node
                if(!list.isEmpty()){
                    parent[i] = list.get(list.size()-1);
                }
                list.add(i);
            }

            else{
                int pos = lowerBound(list,val,arr);

                if(pos > 0){
                    //store parent node which will be -1 of replacing node
                    parent[i] = list.get(pos-1);
                }
                list.set(pos,i);
            }
        }
        int lastIndex = list.get(list.size()-1);

        return printLIS(parent,arr , lastIndex);
    }

    public int lowerBound(ArrayList<Integer> list , int target , int[] arr){
        int n  = list.size();
        int low = 0 , high = n-1;

        int ans = n;
        while(low <= high){
            int mid = low + (high - low)/2;
            
            int midValue = arr[list.get(mid)];

            if(midValue >= target){
                ans = mid;
                high = mid-1;
            }
            else
                low = mid+1;
        }

        return ans;
    }

    public ArrayList<Integer> printLIS(int[]parent , int[]arr , int lastIndex){

        ArrayList<Integer> ans = new ArrayList<>();
        int curr = lastIndex;
        //traverse parent nodes in reverse till the first element who will have -1 as parent
        while(curr!= -1){
            ans.add(arr[curr]);
            curr = parent[curr];
        }

        Collections.reverse(ans);
        return ans;
    }
}

//in order O(N^2)

class Solution {
    public ArrayList<Integer> getLIS(int arr[]) {
        // return f(arr, 0 , -1 , new Integer[arr.length+1][arr.length+2]);        
        return tabulization(arr);
    }

    public int f(int[]arr , int index , int lastIndex , Integer[][] dp){
        if(index == arr.length){
            return dp[index][lastIndex+1] = 0;
        }

        if(dp[index][lastIndex+1] != null)
            return dp[index][lastIndex];

        int pick = 0
        if(lastIndex == -1 || arr[index] > arr[lastIndex]){
            pick = 1+ f(arr,index+1,index,dp);
        }

        int notPick = f(arr,index+1,lastIndex ,dp);

        return dp[index][lastIndex+1] = Math.max(pick,notPick);
    }

    public ArrayList<Integer> tabulization(int[] arr){
        int n = arr.length;
        int[][] dp = new int[n+1][n+2];

        for(int index = n ; index >= 0; index--){
            for(int lastIndex = -1 ; lastIndex < n ;lastIndex++){
                if(index == arr.length){
                    dp[index][lastIndex+1] = 0;
                    continue;
                }


                int pick = 0
                if(lastIndex == -1 || arr[index] > arr[lastIndex]){
                    pick = 1+ dp[index+1][index+1];
                }

                int notPick = dp[index+1][lastIndex+1];

                dp[index][lastIndex+1] = Math.max(pick,notPick);
            }
        }

        return printLIS(dp , arr );
    }

    //we back track from 0,0 and keep making answer based on largest LIS values stored in the cells
    public ArrayList<Integer> printLIS(int[][]dp,int[]arr){
        int lastIndex = -1;
        int n = arr.length;
        ArrayList<Integer> ans = new ArrayList<>();

        for(int index = 0 ;index < n ; index++){

            boolean canPick = (lastIndex == -1 || arr[index] > arr[lastIndex]);

            if(canPick && 
                1+ dp[index+1][index+1] >= dp[index+1][lastIndex+1]){
                
                    ans.add(arr[index]);
                    lastIndex = index;
            }

            //skip
        }

        return ans;
    }

    /* LIS in O(n^2) but 1D array 
        every element stores max LIS which can be made ending at that
        1) reverse iterate from i till 0 and find what all elements can
        come before it and add 1 to their answer 
    */
   // 1. Fill dp[]: Standard LIS ending at i
    public int LIS_1D(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        // 1. Fill dp[]: Standard LIS ending at i
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }
    }
}


