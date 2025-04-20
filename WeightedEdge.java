public class WeightedEdge implements Comparable<WeightedEdge> {
    private int weight;
    private int verticeOne;
    private int verticeTwo;

    public WeightedEdge(int verticeOne, int verticeTwo, int weight) {
        this.verticeOne = verticeOne;
        this.verticeTwo = verticeTwo;
        this.weight = weight;

    }

    public int getVertexOne() {
        return verticeOne;
    }

    public int getVertexTwo() {
        return verticeTwo;
    }

    public int getWeight() {
        return weight;
    }

    public int compareTo(WeightedEdge otherEdge) {
        return Integer.compare(this.weight, otherEdge.weight);

    }
}