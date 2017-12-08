//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Xinyi Jiang

package graph.span;

import graph.Edge;
import graph.Graph;

import java.util.*;

public class MSTJiangKruskal implements MSTAll {
    int weight;
    @Override

    public List<SpanningTree> getMinimumSpanningTrees(Graph graph) {
        DisjointSet forest = new DisjointSet(graph.size());
        PriorityQueue<Edge> queue = createEdgePQ(graph);
        SpanningTree tmplist = new SpanningTree();
        List<SpanningTree> list = new ArrayList<>();
        weight = Integer.MAX_VALUE;
        getMinimumSpanningTree(graph,list,tmplist,queue,forest);
        return list;
    }
    public void getMinimumSpanningTree(Graph graph, List<SpanningTree> list, SpanningTree tmplist, PriorityQueue<Edge> queue, DisjointSet forest) {

        if (tmplist.size() + 1 == graph.size()) {
            int weightOfTree = 0;
            for (Edge edge : tmplist.getEdges()) {
                weightOfTree += edge.getWeight();
            }
            if (weightOfTree <= weight) {
                weight = weightOfTree;
                list.add(tmplist);//If a spanning tree is found, add it to the result list
            }
            return;
        }


            Edge edge;
            DisjointSet forest1 = new DisjointSet(forest);
            SpanningTree tmplist1 =new SpanningTree(tmplist);
        PriorityQueue<Edge> queue1= new PriorityQueue<>(queue);
            while (!queue.isEmpty()) {
                edge = queue.poll();
                queue1.poll();
                if (!forest.inSameSet(edge.getTarget(), edge.getSource())) {
                    tmplist1.addEdge(edge);
                    forest.union(edge.getTarget(), edge.getSource());
                    // a spanning tree is found
                    getMinimumSpanningTree(graph, list, tmplist1, queue1, forest);
                    // merge forests
                    if (queue.peek()!=null && edge.getWeight() != queue.peek().getWeight()) break;
                }

                if (queue.peek()!=null && edge.getWeight() == queue.peek().getWeight()) {
                    getMinimumSpanningTree(graph, list, tmplist, queue, forest1);
                }

            }
        }

        /**
         * @param graph Graph
         * @return PriorityQueue that contains all edges in graph sorted by their weights
         */

        private PriorityQueue<Edge> createEdgePQ(Graph graph)
        {
            PriorityQueue<Edge> queue = new PriorityQueue<>();
            Set<String> set = new HashSet<>();
            for(Edge edge :graph.getAllEdges()) {
                if(!set.contains(edge.toString())) {
                    queue.add(edge);
                    set.add(new Edge(edge.getTarget(),edge.getSource(),edge.getWeight()).toString());
                }
            }
            return queue;
        }



    }
