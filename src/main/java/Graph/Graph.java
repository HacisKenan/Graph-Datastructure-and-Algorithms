package Graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Node> nodes;
    private List<Edge> edges;
    private List<Integer> degrees;


    public Graph(List<Node> nodes, List<Edge> edges)
    {
        this.nodes=nodes;
        this.edges=edges;
    }

    public Node addNewNode()
    {
        Node node = new Node();
        nodes.add(node);
        return node;
    }

    public void addNewNode(Node node)
    {
        if(!nodes.contains(node)) nodes.add(node);
    }

    public void addEdge(Edge edg)
    {
        if(edges.contains(edg)) return;
        edges.add(edg);
    }

    public List<Integer> createDegreeList()
    {
        degrees= new ArrayList<Integer>();
        for(Node node : nodes)
        {
            int degree = node.getDegree();
            degrees.add(degree);
        }
        return degrees;
    }

    public List<Node> getNodes(){return nodes;}
    public List<Integer> getDegrees(){return degrees;}
    public List<Edge> getEdges(){return edges;}

}
