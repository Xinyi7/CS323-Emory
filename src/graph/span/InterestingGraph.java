package graph.span;

import graph.Graph;

public class InterestingGraph {
    Graph house()
    {
        Graph graph = new Graph(5);

        graph.setUndirectedEdge(0, 1, 2);
        graph.setUndirectedEdge(0, 4, 2);
        graph.setUndirectedEdge(1, 4, 2);
        graph.setUndirectedEdge(3, 2, 2);
        graph.setUndirectedEdge(1, 2, 1.0);
        graph.setUndirectedEdge(4, 3, 1.0);

        return graph;

    }
    public static void main(String[] agrs){
        MSTAll system = new MSTJiangPrim();
        System.out.println(system.getMinimumSpanningTrees(new InterestingGraph().house()));

    }
}
