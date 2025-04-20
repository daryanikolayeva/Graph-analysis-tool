
public class MinPQWeightedEdge {
    private WeightedEdge[] edges;
    private int size;
    private int maxSize;

    /*
     * Constructor
     * Creates the priority queue
     * Assigns default values to instance variables
     */
    public MinPQWeightedEdge() {
        this.maxSize = 20;
        this.edges = new WeightedEdge[maxSize];
        this.size = 0;
    }

    /*
     * Inserts new edge into the priority queue.
     * Input paremeters: WeightedEdge newEdge(the edge to insert)
     * If reaches Maxsize, resizes
     * If not inserts maintaining min-heap order
     */
    public void insert(WeightedEdge newEdge) {
        if (size == maxSize) {
            resize();
        }
        edges[size] = newEdge;
        int newEdgeIndex = size;
        size++;
        int parentIndex = (newEdgeIndex - 1) / 2;
        while (newEdgeIndex > 0 && edges[newEdgeIndex].getWeight() < edges[parentIndex].getWeight()) {
            swap(newEdgeIndex, parentIndex);
            newEdgeIndex = parentIndex;
            parentIndex = (newEdgeIndex - 1) / 2;
        }
    }

    /*
     * Swaps given elements in priority queue
     * Input parameters: indexChild, indexParent(elements to swap)
     */
    public void swap(int indexChild, int indexParent) {
        WeightedEdge temp = edges[indexChild];
        edges[indexChild] = edges[indexParent];
        edges[indexParent] = temp;
    }

    /*
     * Resizes priority queue, increases size*2
     */
    public void resize() {
        maxSize = maxSize * 2;
        WeightedEdge[] newEdges = new WeightedEdge[maxSize];
        for (int i = 0; i < this.size; i++) {
            newEdges[i] = edges[i];
        }
        edges = newEdges;
    }

    /*
     * Returns and removes smallest edge of prioriy queue
     * Restores min-heap order
     */
    public WeightedEdge retrieveMin() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        WeightedEdge min = edges[0];
        edges[0] = edges[size - 1];
        size--;

        if (size > 0) {
            heapify(0);
        }

        return min;
    }

    /*
     * Returns boolean is priority queue is empty or not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * Prints out contents of prioriy queue
     * Or if it's empty, says so
     */
    public void print() {
        if (size == 0) {
            System.out.println("Queue is empty");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.print(edges[i].getWeight() + ",");
            }
        }
    }

    /*
     * Restores min-heap order
     * Input parameters: int index( will start from this index)
     */
    public void heapify(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int smallest = index;
        if (leftChild < size && edges[leftChild].getWeight() < edges[smallest].getWeight()) {
            smallest = leftChild;
        }
        if (rightChild < size && edges[rightChild].getWeight() < edges[smallest].getWeight()) {
            smallest = rightChild;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }
}
