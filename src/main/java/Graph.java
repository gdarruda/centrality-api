import java.util.*;

public class Graph {

    private static Graph graph;

    private HashMap<Integer, HashSet<Integer>> graphMap;

    Graph() {
        this.setGraphMap(new HashMap<Integer, HashSet<Integer>>());
    }

    public void addEdge(Integer fromNode, Integer toNode, boolean inverse) {
        HashSet<Integer> neighborhood = graphMap.get(fromNode);

        if (neighborhood == null) neighborhood = new HashSet<Integer>();

        neighborhood.add(toNode);

        getGraphMap().put(fromNode, neighborhood);

        if (!inverse) addEdge(toNode, fromNode, true);
    }

    public Set<Integer> getNodes() {
        return graphMap.keySet();
    }

    public boolean containsKey(Integer node) {
        return graphMap.containsKey(node);
    }

    private HashMap<Integer, Integer> breadthFirstSearch(Integer fromNode) {

        HashMap<Integer, Integer> distances = new HashMap<Integer, Integer>();
        Queue<Integer[]> queue = new LinkedList<Integer[]>();

        Integer[] origin = {fromNode, 0};

        queue.add(origin);
        distances.put(fromNode, 0);

        while (!queue.isEmpty()) {

            Integer[] actualVertice = queue.poll();

            for (Integer neighbor : graphMap.get(actualVertice[0])) {

                if (!distances.containsKey(neighbor)){
                    Integer[] newNode = {neighbor, actualVertice[1] + 1};
                    distances.put(newNode[0], newNode[1]);
                    queue.add(newNode);
                }
            }
        }

        return distances;

    }

    public float getCentrality(Integer node) {

        HashMap<Integer, Integer> distancesFromNode = breadthFirstSearch(node);

        Integer farness = distancesFromNode
                .values()
                .stream()
                .reduce(0, (a,b) -> a + b);


        return (float) (1.0 / farness.floatValue());
    }

    public HashMap<Integer, HashSet<Integer>> getGraphMap() {
        return graphMap;
    }

    public void setGraphMap(HashMap<Integer, HashSet<Integer>> graphMap) {
        this.graphMap = graphMap;
    }

    public static Graph getGraph() {

        if (graph == null) {
            graph = new Graph();
        }

        return graph;

    }
}
