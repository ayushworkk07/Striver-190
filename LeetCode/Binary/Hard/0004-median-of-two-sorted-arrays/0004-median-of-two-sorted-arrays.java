class Solution {
    //always prefer left heavy solution as it takes care of edge cases like empty array and stuff cause of the +1

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) return findMedianSortedArrays(nums2,nums1);

        int n = nums1.length , m = nums2.length;
        int total = m+n;
        int partitions = (m+n+1)/2; //works for both even and odd sizes

        //low and high represents the number of elements you can take in the partition which can be max n
        int low = 0 , high = n;
        
        while(low <= high){
            int cut1 = low + (high - low)/2;
            int cut2 = partitions - cut1;

            int l1 = cut1==0 ? Integer.MIN_VALUE : nums1[cut1-1];
            int l2 = cut2==0 ? Integer.MIN_VALUE : nums2[cut2-1];

            int r1 = cut1 == n? Integer.MAX_VALUE : nums1[cut1];
            int r2 = cut2 == m? Integer.MAX_VALUE : nums2[cut2];

            if(l1 > r2 )
                high = cut1-1;

            else if(l2 > r1)
                low = cut1 +1;

            else{
                if(total%2 == 0){
                    return (double)(Math.max(l1,l2) + Math.min(r1,r2))/2;
                }
                //in right heavy partition , right will have that extra element
                else
                    return (double)Math.max(l1,l2);
            }
        }

        return 0.0;
    }
}
