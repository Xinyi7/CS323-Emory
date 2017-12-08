//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Xinyi Jiang

package sort.distribution;

import java.util.Comparator;
import java.util.List;

public class MSDRadixSort extends RadixSort{

    public MSDRadixSort()
    {
        this(Comparator.naturalOrder());
    }

    public MSDRadixSort(Comparator<Integer> comparator)
    {
        super(comparator);
    }

    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex)
    {
        int maxBit = getMaxBit(array, beginIndex, endIndex);

       // for (int bit=maxBit-1; bit>=0; bit--)
            sort(array, beginIndex, endIndex, maxBit-1);
    }

    private void sort(Integer[] array, int beginIndex, int endIndex, int bit)
    {
        if( bit<0||(endIndex - beginIndex)==1 || (endIndex==beginIndex)){return;}
        int div = (int)Math.pow(10, bit), idx;
        //int[] a = new int[10];

        for (int i=beginIndex; i<endIndex; i++)
            buckets.get(getBucketIndex(array[i], div)).add(array[i]);

        for (List<Integer> bucket : this.buckets)
        {
            //int i = 0;
            //a[i++]=bucket.size();
            idx = beginIndex = beginIndex + bucket.size();
            while (bucket.size() > 0) array[--idx] = bucket.remove(bucket.size()-1);
            MSDRadixSort A = new MSDRadixSort();
           // System.out.println(idx);
            //System.out.println(beginIndex);
            //System.out.println();
            A.sort(array,idx,beginIndex,bit-1);
        }


        /*for(int i: array){
            System.out.print(i +" ");
        }
        System.out.println();*/
    }
}

