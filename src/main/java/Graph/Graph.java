package Graph;

import java.util.*;
import java.util.stream.Collectors;

public  class Graph {

    public class Node{
        private HashSet<Node> neighbors;
        public Node() {
            neighbors=new HashSet<>();
        }

        public void removeNeighbour(Node node)
        {
            neighbors.remove(node);
        }

        public boolean hasNeighbour(Node node)
        {
            return neighbors.contains(node);
        }

        public int getDegree(){return neighbors.size();}
        public HashSet<Node> getNeighbors(){return neighbors;}
    }

    public class Edge {
        private Node from;
        private Node to;

        public Edge(Node from, Node to)
        {
            this.from=from;
            this.to=to;
        }

        public Node getFrom()
        {
            return from;
        }
        public Node getTo()
        {
            return to;
        }
    }

    private List<Node> nodes;
    private HashSet<Edge> edges;
    private List<Integer> degrees;
    private boolean degCalculated;

    private final List<Integer> K33_DEG = List.of(3, 3, 3, 3, 3, 3);
    private final List<Integer> K5_DEG = List.of(4, 4, 4, 4, 4);

    public Graph(List<Node> nodes, HashSet<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
        degCalculated=false;
    }

    public void addNode() {
        nodes.add(new Node());
    }

    public void removeNode(Node node) {nodes.remove(node);}


    public void addEdge(Node A, Node B) {
        edges.add(new Edge(A,B));
    }

    public List<Integer> createDegreeSequence() {
        if(!degCalculated){
            degrees = new ArrayList<Integer>();
            for (Node node : nodes) {
                int degree = node.getDegree();
                degrees.add(degree);
            }
        }
        return degrees;
    }

    public boolean isRealizableBoolean() {
        List<Integer> deglist = isRealizable();
        for (int deg : deglist) {
            if (deg > 0 || deg < 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> isRealizable() {
        if (checkCorrectness()) {
            createDegreeSequence();
            degrees = topDownReduction(degrees);
            return degrees;
        }
        return new ArrayList<Integer>();
    }

    public boolean isConnected() {
        List<Node> nodes = new ArrayList<Node>();
        isConnectedRec(nodes, this.nodes.get(0));
        return nodes.size() == this.nodes.size();
    }

    public void isConnectedRec(List<Node> nodeList, Node current) {
        if (!nodeList.contains(current)) {
            nodeList.add(current);
        } else return;
        for (Node node : current.getNeighbors()) {
            isConnectedRec(nodeList, node);
        }
    }


    public boolean checkCorrectness() {
        if (degrees == null) return false;
        if (degrees.size() == 0) return false;
        for (int deg : degrees) {
            if (deg < 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> topDownReduction(List<Integer> degrees) {
        while (canStillCalculateReduction(degrees)) {
            Collections.sort(degrees, Collections.reverseOrder());
            int toReduce = degrees.get(0);
            for (int i = 1; i <= toReduce; i++) {
                int deg = degrees.get(i);
                deg -= 1;
                degrees.remove(i);
                degrees.add(i, deg);
            }
            degrees.remove(0);
        }
        return degrees;
    }

    public boolean canStillCalculateReduction(List<Integer> degrees) {
        boolean possible = false;
        for (int deg : degrees) {
            if (deg < 0) {
                return false;
            }
            if (deg >= 1) {
                possible = true;
            }
        }
        return possible;
    }

    public boolean hasEulerCirlce() {
        if (isRealizableBoolean() && isConnected()) {
            createDegreeSequence();
            boolean isTrue = true;
            for (int deg : degrees) {
                if (!isTrue) {
                    return false;
                }
                isTrue = (deg > 0 && deg % 2 == 0) ? true : false;
            }
            return isTrue;
        }
        return false;
    }

    public boolean hasHamiltonCircle() {
        if (isRealizableBoolean() && isConnected()) {
            createDegreeSequence();
            boolean isTrue = nodes.size() < 3 ? false : true;
            for (int deg : degrees) {
                if (!isTrue) {
                    return false;
                }
                isTrue = (deg >= (nodes.size() / 2)) ? true : false;
            }
            return isTrue;
        }
        return false;
    }

    public int[][] getAdjencyMatrix() {
        int[][] adjenzMatrix = new int[nodes.size()][nodes.size()];
        for(Node node: nodes)
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (nodes.get(i).hasNeighbour(nodes.get(j))) {
                    adjenzMatrix[i][j] = 1;
                }
            }
        }
        return adjenzMatrix;
    }

    public Graph getUndirectedGraph()
    {
        HashSet<Edge> newEdges = new HashSet<Edge>(edges);

        for(Edge edge : edges) {
            newEdges.add(new Edge(edge.getTo(), edge.getFrom()));
        }
        List<Node> newNodes = new ArrayList<Node>(nodes);
        return new Graph(newNodes,newEdges);
    }

    public boolean isPlanar() {
        Graph minor = this;
        List<Integer> degree = minor.getDegrees();

        for(Node node:minor.getNodes())
        {
            if(node.getNeighbors().isEmpty()) minor.removeNode(node);
        }

        if(!(couldHaveK5minor(degree) || couldHaveK33minor(degree))) return false;

        // TODO

        return true;
    }

    public void edgeContraction(Node node){
        // TODO
    }

    public boolean compareDegToK33(List<Integer> degrees)
    {
        if(degrees.size()!=6) return false;
        for(int i=0;i<6;i++)
        {
            if(!(degrees.get(i)==K33_DEG.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    public boolean couldHaveK33minor(List<Integer> degrees)
    {
        if(degrees.size()<6) return false;
        for(int i=0;i<degrees.size();i++)
        {
            if(degrees.get(i)<3) return false;
        }
        return true;
    }

    public boolean compareDegToK5(List<Integer> degrees)
    {
        if(degrees.size()!=5) return false;
        for(int i=0;i<5;i++)
        {
            if(!(degrees.get(i)==K5_DEG.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    public boolean couldHaveK5minor(List<Integer> degrees)
    {
        if(degrees.size()<5) return false;
        for(int i=0;i<degrees.size();i++)
        {
            if(degrees.get(i)<4) return false;
        }
        return true;
    }

    public boolean isCircleFree() {
        return edges.size() + 1 == nodes.size();
    }

    public boolean isATree() {
        return isConnected() && isCircleFree();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Integer> getDegrees() {
        createDegreeSequence();
        return degrees;
    }

    public HashSet<Edge> getEdges() {
        return edges;
    }

}
