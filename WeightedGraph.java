

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WeightedGraph {
    private int numVer;
    private int[][] matrix;

    /*
     * Constructor:
     * Input parameters: int ver( number of vertices)
     * Creates matrix of given size(numVer)
     */
    public WeightedGraph(int ver) {
        numVer = ver;
        matrix = new int[ver][ver];
    }

    /*
     * Constructor:
     * Creates WeightedGraph from data from given file
     */
    public WeightedGraph(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            numVer = scanner.nextInt();
            scanner.nextLine();
            matrix = new int[numVer][numVer];

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parameters = line.split(" ");
                if (parameters.length == 3) {
                    int vertexOne = Integer.parseInt(parameters[0]);
                    int vertexTwo = Integer.parseInt(parameters[1]);
                    int weight = Integer.parseInt(parameters[2]);
                    matrix[vertexOne][vertexTwo] = weight;
                    matrix[vertexTwo][vertexOne] = weight;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /*
     * Returns number of vertices of graph
     */
    public int getVerNum() {
        return numVer;
    }

    /*
     * Returns weight of graph
     */
    public int getWeight(int i, int j) {
        return matrix[i][j];
    }

    /*
     * Returns matrix of graph
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /*
     * Prints contents of graph by adjacency matrix
     */
    public void print() {
        for (int i = 0; i < numVer; i++) {
            for (int j = 0; j < numVer; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
