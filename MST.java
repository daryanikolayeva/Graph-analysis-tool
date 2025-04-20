

public class MST {
    private int weight;
    private int[][] matrix;
    private int numVer;

    /*
     * Constructor
     * Input parameters: int numVer: number of vertices
     * Creates default(empty) matrix of given size
     * Weight original is zero
     */
    public MST(int numVer) {
        this.weight = 0;
        this.matrix = new int[numVer][numVer];
        this.numVer = numVer;

    }

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Can't proceed with no file name");
            return;
        }

        String fileName = args[0];
        WeightedGraph graph = new WeightedGraph(fileName);

        System.out.println("Original Graph:");
        graph.print();
        int ver = graph.getVerNum();
        MST mst = new MST(ver);
        mst.minSpanTree(graph);
        System.out.println("Minimum Spanning Tree:");
        mst.printMST();

    }

    /*
     * Function for finding min span tree from given graph
     * Input parameters: WeightedGraph graph- graph in which we're looking for min
     * span tree
     * Return type void
     * During execution updates mst
     */
    public void minSpanTree(WeightedGraph graph) {
        int size = graph.getVerNum();
        boolean[] visited = new boolean[size];
        MinPQWeightedEdge edgeQueue = new MinPQWeightedEdge();
        int mstEdges = 0;
        visited[0] = true;
        addEdge(graph, 0, visited, edgeQueue);
        while (!edgeQueue.isEmpty() && mstEdges < size - 1) {
            WeightedEdge minEdge = edgeQueue.retrieveMin();
            int vertexOne = minEdge.getVertexOne();
            int vertexTwo = minEdge.getVertexTwo();
            int weight = minEdge.getWeight();

            if (!visited[vertexTwo]) {
                visited[vertexTwo] = true;
                addEdgeToMST(vertexOne, vertexTwo, weight);
                addEdge(graph, vertexTwo, visited, edgeQueue);
                mstEdges++;
            }

            else if (!visited[vertexOne]) {
                visited[vertexOne] = true;
                addEdgeToMST(vertexTwo, vertexOne, weight);
                addEdge(graph, vertexOne, visited, edgeQueue);
                mstEdges++;
            }
        }
        System.out.println("Total weight of min spannin tree is: " + this.weight);
    }

    /*
     * addEdge adds given WeightedEdge to priority queue
     * Input parameters: WeightedGraph- graph where edge is located
     * int vertex-the vertex from which to explore outgoing edges
     * boolean[]visited-if current vertex has already been visited
     * MinPQWeightedEdge edgeQueue- priority queue to where we add valid edges
     */
    private void addEdge(WeightedGraph graph, int vertex, boolean[] visited, MinPQWeightedEdge edgeQueue) {
        int n = graph.getVerNum();
        for (int i = 0; i < n; i++) {
            if (!visited[i] && graph.getWeight(vertex, i) > 0) {
                edgeQueue.insert(new WeightedEdge(vertex, i, graph.getWeight(vertex, i)));
            }
        }
    }

    /*
     * Adds edge to min span tree
     * Input parameters:
     * int vertexOne-first vertex on one side of edge
     * int vertexTwo-second verted on the other side of the edge
     * int weight- weight of given WeightedEdge
     */
    private void addEdgeToMST(int vertexOne, int vertexTwo, int weight) {
        matrix[vertexOne][vertexTwo] = weight;
        matrix[vertexTwo][vertexOne] = weight;
        this.weight += weight;
    }

    /*
     * Prints min span tree, by printing its matrix
     */
    private void printMST() {
        for (int i = 0; i < numVer; i++) {
            for (int j = 0; j < numVer; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
