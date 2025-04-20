public class TestMinPQ {
	static private final int TEST_SIZE = 10;
	static private WeightedEdge[] testEdges;
	static private MinPQWeightedEdge testPQ;

	public static void main(String args[]) {
		testPQ = new MinPQWeightedEdge();
		setupTestEdges();

		runAllTests();
	}

	static public void setupTestEdges() {
		int testWeights[] = new int[] { 76, 89, 95, 13, 22, 1, 63, 16, 14, 31 };

		testEdges = new WeightedEdge[TEST_SIZE];
		for (int i = 0; i < TEST_SIZE; i++) {
			testEdges[i] = new WeightedEdge(i, i + 1, testWeights[i]);
		}
	}

	static public void runAllTests() {
		testInsert();

		System.out.println();

		testRetrieveMin();
	}

	static public void testInsert() {
		System.out.println("Now testing insert()");
		for (int i = 0; i < TEST_SIZE; i++) {
			testPQ.insert(testEdges[i]);
		}
		System.out.println("New array contents:");
		testPQ.print();
	}

	static public void testRetrieveMin() {
		System.out.println("\nNow testing retrieveMin()");
		if (!testPQ.isEmpty()) {
			WeightedEdge minEdge = testPQ.retrieveMin();
			System.out.println("New array contents:");
			testPQ.print();
		}
	}
}
