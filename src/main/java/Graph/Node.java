package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node {
    private List<Node> neighbors = new ArrayList<Node>();
    private Graph graph;

    public Edge addEdgeTo(Node node,Graph graph)
    {
        if(neighbors.contains(node)) return null;
        Edge edg = new Edge(this,node);
        neighbors.add(node);
        graph.addEdge(edg);
        return edg;
    }

    public Edge getEdgeTo(Node node)
    {
        List<Edge> lst = graph.getEdges().stream().filter(x->x.getFrom()==this && x.getTo()==node).collect(Collectors.toList());
        if(lst.isEmpty()) return null;
        return lst.get(0);
    }

    public List<Node> getNeighbors(){return neighbors;}

    public int getDegree(){return neighbors.size();}

}
