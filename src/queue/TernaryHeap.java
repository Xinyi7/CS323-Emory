//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Xinyi Jiang
package queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class TernaryHeap<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private List<T> keys;
    private int size;

    public TernaryHeap() {
        this(Comparator.naturalOrder());
    }

    public TernaryHeap(Comparator<T> comparator) {
        super(comparator);
        keys = new ArrayList<>();
        keys.add(null);    // initialize the first item as null
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T key) {
        keys.add(key);
        swim(++size);
    }

    @Override
    protected T removeAux() {
        Collections.swap(keys, 1, size);
        T max = keys.remove(size--);
        sink(1);
        return max;
    }

    private void swim(int k) {
        while (k>1 &&comparator.compare(keys.get((k+1) / 3), keys.get(k)) < 0) {

               // if(comparator.compare(keys.get((k+1) / 3), keys.get(k)) >= 0) break;
                Collections.swap(keys, (k+1)/ 3, k);
                k=(k+1)/3;
        }
    }


    private void sink(int k) {
        for (int i = k * 3 -1 ; i <= size; k = i, i = 3*i -1) {
            if (i < size  && comparator.compare(keys.get(i+1), keys.get(i)) > 0) {
                i++;
                if(i< size && comparator.compare(keys.get(i+1),keys.get(i))>0){
                    i++;
                }

            }else if (i<size-1 &&comparator.compare(keys.get(i+2),keys.get(i))>0){
                i+=2;
            }

            if (comparator.compare(keys.get(k), keys.get(i)) >= 0) {break;
            }
            Collections.swap(keys, k, i);
        }

    }
}