package queue;

public class Test {
    public static void main(String args[]){
        TernaryHeap<Double> heap = new TernaryHeap();
        heap.add(2.0);
        heap.add(3.2);
        heap.add(5.6);
        heap.add(7.4);
        heap.add(1.2);
        for(int i = 1; i<=heap.size();)
        {
            System.out.println(heap.removeAux());
        }

    }
}
