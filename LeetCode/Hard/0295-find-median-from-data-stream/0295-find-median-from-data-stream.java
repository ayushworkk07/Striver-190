class MedianFinder {

    Queue<Integer> maxHeap ;
    Queue<Integer> minHeap ;
    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        maxHeap.offer(num);

        minHeap.offer(maxHeap.poll());

        if(maxHeap.size() < minHeap.size())
        maxHeap.offer(minHeap.poll());
    }
    
    public double findMedian() {
        if(maxHeap.size() > minHeap.size()) return maxHeap.peek();
        
        else
        return ((double)(maxHeap.peek() + minHeap.peek())/2);

    }
}