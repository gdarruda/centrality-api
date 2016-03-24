import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;


public class CentralityCalculator {

    private  Graph graph;

    public class CentralityNode implements Comparable<CentralityNode> {

        private Integer node;
        private Float centrality;

        CentralityNode(Integer node, Float centrality) {
            this.setNode(node);
            this.setCentrality(centrality);
        }

        @Override
        public int compareTo(CentralityNode edge) {

            if (this.getCentrality() <  edge.getCentrality()) {
                return -1;
            }

            if (this.getCentrality() >  edge.getCentrality()) {
                return 1;
            }

            return  0;
        }

        public Integer getNode() {
            return node;
        }

        public void setNode(Integer node) {
            this.node = node;
        }

        public Float getCentrality() {
            return centrality;
        }

        public void setCentrality(Float centrality) {
            this.centrality = centrality;
        }
    }


    CentralityCalculator(Graph graph) {

        this.setGraph(graph);
    }

    public ArrayList<CentralityNode> getRankedCentrality() {

        Set<Integer> nodes = getGraph().getNodes();
        ArrayList<CentralityNode> centralityDistances = new ArrayList<CentralityNode>();

        for (Integer node : nodes) {
            Float centrality = getGraph().getCentrality(node);
            centralityDistances.add(new CentralityNode(node, centrality));
        }

        Collections.sort(centralityDistances);

        return centralityDistances;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

}

