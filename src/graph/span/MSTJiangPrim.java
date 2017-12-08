//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Xinyi Jiang
package graph.span;

import graph.Edge;
import graph.Graph;

import java.util.*;

public class MSTJiangPrim implements MSTAll {
    public int weight;
    @Override

    public List<SpanningTree> getMinimumSpanningTrees(Graph graph)
    {
        weight = Integer.MAX_VALUE;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        SpanningTree tmplist= new SpanningTree();
        Set<Integer> visited = new HashSet<>();
        List<SpanningTree> list = new ArrayList<>();
        add(queue, visited, graph, 0);

        //Add all connecting vertices from start vertex to the queue
        getMinimumSpanningTree(graph,visited,list,tmplist,queue);

        return list;
    }
    public void getMinimumSpanningTree(Graph graph, Set<Integer> visited, List<SpanningTree> list, SpanningTree tmplist, PriorityQueue<Edge> queue)
    {
        if(tmplist.size()+1 == graph.size()){
        int weightOfTree = 0;
        for(Edge edge: tmplist.getEdges()){
            weightOfTree+=edge.getWeight();
        }
        if(weightOfTree<= weight){
            weight = weightOfTree;
            list.add(tmplist);//If a spanning tree is found, add it to the result list
        }
            return;

    }

        Edge edge;

        PriorityQueue<Edge> queue1= new PriorityQueue<>(queue);
     //Add all connecting vertices from start vertex to the queue
        while (!queue.isEmpty()) {
            edge = queue.poll();
            queue1.poll();
            SpanningTree tmplist1 = new SpanningTree(tmplist);



            if (!visited.contains(edge.getSource())) {
                tmplist1.addEdge(edge);
                add(queue1, visited, graph, edge.getSource());
                getMinimumSpanningTree(graph, visited, list, tmplist1, queue1);
                visited.remove(edge.getSource());
                if (queue.peek()!=null && edge.getWeight() != queue.peek().getWeight()) break;
            }

                if (queue.peek() != null && edge.getWeight() == queue.peek().getWeight()) {

                    getMinimumSpanningTree(graph, visited, list, tmplist, queue);
                }

            }
        }

    private void add(PriorityQueue<Edge> queue, Set<Integer> visited, Graph graph, int target)
    {

        visited.add(target);
        for (Edge edge : graph.getIncomingEdges(target))
        {
            if (!visited.contains(edge.getSource()))
                queue.add(edge);
        }
    }
}
