class Solution {
    public int trap(int[] height) {
        return sol2(height);
        // return sol1(height);
    }

    public int sol2(int[] height){
        int l = 0 , r = height.length-1;
        int maxL = 0 , maxR = 0;
        int total = 0;

        while(l<r){
            if(height[l] <= height[r]){
                if(height[l] >= maxL) maxL = height[l];

                else
                total += maxL - height[l];

                l++;
            }

            else{
                if(height[r] >= maxR) maxR = height[r];

                else
                total += maxR - height[r];

                r--;
            }
        }

        return total;
    }

    //find NGE , PGE and find total
    public int sol1(int[] height){
        int[] leftMax = getMaxArray(height , true);
        int[] rightMax = getMaxArray(height,false);

        int total = 0;
        for(int i =0 ; i < height.length ; i++){
            total += Math.min(leftMax[i],rightMax[i]) - height[i];
        }

        return total;
    }

    public int[] getMaxArray(int nums[],boolean flag){
        int n = nums.length;
        int max[] = new int[n];

        //leftMax
        if(flag){
            int maxHeight = nums[0];
            max[0] = maxHeight;

            for(int i = 1 ;i < n ; i++){
                if(nums[i]> maxHeight){
                    maxHeight = nums[i];
                }
                max[i] = maxHeight;
            }
            return max;
        }

            int maxHeight = nums[n-1];
            max[n-1] = maxHeight;

            for(int i = n-2 ;i >=0 ; i--){
                if(nums[i]> maxHeight){
                    maxHeight = nums[i];
                }
                max[i] = maxHeight;
            }

            return max;
    }
}