

import java.util.ArrayList;

public class HamiltonianCycle {
    private WeightedGraph graph;
    private int numVer;
    private ArrayList<Integer> cycleWeights;
    private ArrayList<ArrayList<Integer>> allCycles;

    /*
     * Constructor
     * Takes Weighted graph as input variable
     * Creates instance variables:graph, numVer(number of Vertices of that graph),
     * cycleWeights(to store weights), allCycles(to store all hamiltonian cycles)
     */
    public HamiltonianCycle(WeightedGraph graph) {
        this.graph = graph;
        this.numVer = graph.getVerNum();
        cycleWeights = new ArrayList<>();
        allCycles = new ArrayList<>();
    }

    /*
     * Check if given cycle(list) is Hamiltonian
     * Input parameters:list( cycle that will be checked for being hamiltonian),
     * currVer-vertice from which this cycle started
     * Output: boolean(Hamiltonian or not)
     */
    public boolean isHamiltonian(ArrayList<Integer> list, int currVer) {
        if (list.size() != numVer) {
            return false;
        }

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals(list.get(i + 1))) {
                return false;
            }
        }
        return graph.getWeight(currVer, (list.get(list.size() - 1))) != 0;
    }

    /*
     * Runs findHamiltonian on all vertices available in graph
     */
    public void findHamiltoniansFromAllVertices() {
        for (int startVer = 0; startVer < numVer; startVer++) {
            findHamiltonian(graph, startVer);
        }
    }

    /*
     * Looks for Hamiltonian Cycles
     * Input parameters: WeightedGraph graph( graph in which we look for Ham.cycle),
     * currStart-starting vertice
     */
    public void findHamiltonian(WeightedGraph graph, int currStart) {
        Stack stackOfPaths = new Stack();
        ArrayList<Integer> startPath = new ArrayList<>();
        startPath.add(currStart);
        stackOfPaths.push(startPath);
        while (!stackOfPaths.isEmpty()) {
            ArrayList<Integer> currPath = stackOfPaths.pop();
            if (isHamiltonian(currPath, currStart)) {
                allCycles.add(currPath);
                cycleWeights.add(getWeight(currPath));
            } else {
                int last = currPath.get(currPath.size() - 1);
                for (int neighbor = 0; neighbor < numVer; neighbor++) {
                    if (!currPath.contains(neighbor) && graph.getWeight(last, neighbor) != 0) {
                        ArrayList<Integer> neighborPath = new ArrayList<>();
                        for (int i = 0; i < currPath.size(); i++) {
                            neighborPath.add(currPath.get(i));
                        }
                        neighborPath.add(neighbor);
                        stackOfPaths.push(neighborPath);
                    }
                }
            }
        }
    }

    /*
     * Prints contents of Ham. cycle
     * Input parameter:cycle(which contents we're printing)
     */
    public void printCycle(ArrayList<Integer> cycle) {
        System.out.print("Hamiltonian Cycle: ");
        for (int i = 0; i < cycle.size(); i++) {
            System.out.print(cycle.get(i) + " ");
        }
        System.out.println();
    }

    /*
     * Gets weight of given cycle
     * Input parameter:ArrayList<Integer> cycle ( cycle of which we want to get
     * weight)
     * Output parameter:int(weight of given cycle)
     */
    public int getWeight(ArrayList<Integer> cycle) {
        int weight = 0;
        for (int i = 0; i < cycle.size() - 1; i++) {
            weight += graph.getWeight(cycle.get(i), cycle.get(i + 1));
        }
        weight += graph.getWeight(cycle.get(cycle.size() - 1), cycle.get(0));
        return weight;
    }

    /*
     * Gets min weight of all Ham. cycles from arraylist of cycles
     * Either prints out min value of them all, or that Ham.cycle is not found in
     * given graph
     */
    public void getMinWeight() {
        if (!cycleWeights.isEmpty()) {
            int minWeight = 1000;
            int minIndex = -1;
            for (int i = 0; i < cycleWeights.size(); i++) {
                if (cycleWeights.get(i) < minWeight) {
                    minWeight = cycleWeights.get(i);
                    minIndex = i;
                }
            }
            System.out.println(
                    "Smallest Hamiltonian Cycle found with weight " + minWeight + ": " + allCycles.get(minIndex));
        } else {
            System.out.println("No Hamiltonian cycle found.");
        }
    }

    /*
     * Stack class for findHamiltonian function implmentation
     * Stack of ArrayLists of ArrayLists
     */
    public class Stack {
        private ArrayList<ArrayList<Integer>> paths;

        /*
         * Constructor
         * creates new ArrayList
         */
        public Stack() {
            paths = new ArrayList<>();
        }

        /*
         * Checks if stack is empty
         */
        public boolean isEmpty() {
            return paths.isEmpty();
        }

        /*
         * Returns top value of stack(last) and removes it from ArrayList
         */
        public ArrayList<Integer> pop() {
            return paths.remove(paths.size() - 1);
        }

        /*
         * Adds new ArrayList path onto stack
         */
        public void push(ArrayList<Integer> path) {
            paths.add(path);
        }
    }

    /*
     * Test part
     * Takes file name from command line
     * Creates cycle based on this graph
     * Looks for Hamiltonian cycles
     * If found prints the one with min weight
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Can't proceed with no file name");
            return;
        }

        String fileName = args[0];
        WeightedGraph graph = new WeightedGraph(fileName);
        graph.print();
        System.out.println("Original Graph:");
        graph.print();
        HamiltonianCycle cycle = new HamiltonianCycle(graph);
        cycle.findHamiltoniansFromAllVertices();
        cycle.getMinWeight();
    }
}
