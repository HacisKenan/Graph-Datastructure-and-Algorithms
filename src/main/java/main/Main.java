package main;
import Graph.Graph;
import Graph.Node;
import Graph.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(new ArrayList<Node>(),new ArrayList<Edge>(),new ArrayList<Edge>());

        Node node1 = graph.addNewNode();
        Node node2 = graph.addNewNode();
        Node node3 = graph.addNewNode();
        Node node4 = graph.addNewNode();

        node1.addEdgeTo(node2,graph);
        node1.addEdgeTo(node3,graph);
        node2.addEdgeTo(node1,graph);
        node2.addEdgeTo(node3,graph);
        node3.addEdgeTo(node1,graph);
        node3.addEdgeTo(node2,graph);
        node3.addEdgeTo(node4,graph);

        List<Integer> newList = graph.createDegreeList();
        int [] degrees = new int[newList.size()];
        int idx=0;
        for(int deg:newList)
        {
            degrees[idx++]=deg;
        }
        System.out.println(Arrays.toString(degrees));
        System.out.println(graph.isRealizable());
        System.out.println(graph.isConnected());
    }
}
