//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Xinyi Jiang

package sort.hybrid;

import sort.AbstractSort;
import sort.comparison.ShellSortKnuth;
import sort.divide_conquer.IntroSort;
import sort.divide_conquer.QuickSort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HybridSortJiang <T extends Comparable<T>> implements HybridSort<T> {
    private AbstractSort<T> engine1;
    private AbstractSort<T> engine2;
    private T[] temp;

    public HybridSortJiang()
    {
        AbstractSort<T> engine =new QuickSort<>();
               engine1 = new IntroSort<T>(engine);
        engine2 = new ShellSortKnuth<>();

    }
public T[] sort(T[][] input){
        int[] middlepoints =new int[input.length+1];
        int i = 0;
    int size = Arrays.stream(input).mapToInt(t -> t.length).sum();
    T[] output = (T[]) Array.newInstance(input[0][0].getClass(), size);
    int beginIndex=0;

    for (T[] t : input) {
        middlepoints[i]=beginIndex;
        i++;
        int counts = 0 ;
        for(int j = 0 ; j <t.length-1;j++){
            if(t[j].compareTo(t[j+1])<0){
                counts++;
            }else if(t[j].compareTo(t[j+1])>0){
                counts--;
            }
        }
        if( counts>=t.length/2){
                    if(counts == t.length-1){
                        System.arraycopy(t, 0, output, beginIndex, t.length);
                        beginIndex+=t.length;
                    }else{
                System.arraycopy(t, 0, output, beginIndex, t.length);
                engine2.sort(output,beginIndex,beginIndex+t.length);
                beginIndex+=t.length;
                    }
        }else if (counts<= -t.length/2){
                for(int k = t.length-1 ; k>=0;k--){
                    output[beginIndex]=t[k];
                    beginIndex++;
                }
                if (counts!=-(t.length-1)){
                engine2.sort(output,beginIndex-t.length,beginIndex);
                }
            }else{
                System.arraycopy(t, 0, output, beginIndex, t.length);
                engine1.sort(output,beginIndex,beginIndex+t.length);
                beginIndex+=t.length;
            }





    }

    middlepoints[i] = beginIndex;
    if(middlepoints.length>1){
        mergeall(output,middlepoints,0,middlepoints.length-1);
    }

    return output;
}
protected void mergeall(T[] array, int[] points,int beginIndex, int endIndex){
          if ((beginIndex+1)>=endIndex){
              return;

          }

              mergeall(array,points,(beginIndex+endIndex)/2  ,endIndex);
              mergeall(array,points,beginIndex,(beginIndex+endIndex)/2);
              merge(array,points[beginIndex],points[(beginIndex+endIndex)/2],points[endIndex]);


}
protected void merge(T[] array, int beginIndex, int middleIndex, int endIndex)
    {
        int N = array.length;

        if (temp == null || temp.length < N)
            temp = Arrays.copyOf(array, N);
        else
        {
            N = endIndex - beginIndex;
            System.arraycopy(array, beginIndex, temp, beginIndex, N);
        }


        int fst = beginIndex, snd = middleIndex;

        for (int k=beginIndex; k<endIndex; k++)
        {
            if (fst >= middleIndex)                        // no key left in the 1st half
                array[k]= temp[snd++];
            else if (snd >= endIndex)                    // no key left in the 2nd half
                array[k]= temp[fst++];
            else if (temp[fst].compareTo (temp[snd]) < 0)    // 1st key < 2nd key
                array[k]= temp[fst++];
            else
                array[k]= temp[snd++];
        }
    }


}
