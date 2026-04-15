import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;

        // 1. Sort based on starting time O(N log N)
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        // Start with the first interval as our current candidate
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            // 2. Check for overlap: If next starts before/when current ends
            if (nextStart <= currentEnd) {
                // Merge: Update the end to the furthest point
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // 3. No overlap: Move to next interval and add to list
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
