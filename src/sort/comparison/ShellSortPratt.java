
//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Xinyi Jiang
package sort.comparison;

import java.util.Collections;
import java.util.Comparator;

public class ShellSortPratt<T extends Comparable<T>> extends ShellSort<T> {

    public int p;
    public int q;
    public ShellSortPratt()
    {
        this(Comparator.naturalOrder());
    }
    public ShellSortPratt(Comparator<T> comparator)
    {
        this(comparator, 1000);
    }

    public ShellSortPratt(Comparator<T> comparator, int n)
    {
        super(comparator, n);
        p=0;
        q=0;
    }

    @Override
    protected void populateSequence(int n) {

        n /=3;
        if (sequence.size()==0){
            sequence.add(1);
           // p=0;
            //q=0;
        }
        /*else{
            if(p==0&&q==0) {
                p = getP(n);
                q = getP(n);
            }
        }*/


        while(true){
            int i =sequence.get(p);
            int j =sequence.get(q);
            if(i*2<j*3){
                if(i*2 <= n){
                sequence.add(i*2);
                p++;
                }else{

                    break;
                }
            }else if (i*2>j*3){
                if(j*3 <=n){
                    sequence.add(j*3);
                    q++;
                }else
                {
                    break;
                }

            }else{
                if(j*3 <=n){
                    sequence.add(j*3);
                    q++;
                    p++;
                }else
                {break;
                }
            }

        }
       System.out.println(sequence);

    }

    @Override
    protected int getSequenceStartIndex(int n) {
        int index = Collections.binarySearch(sequence, n/3);
        if (index < 0) index = -(index+1);
        if (index == sequence.size()) index--;
        return index;
    }
    private int getP(int n){
        for (int i = sequence.size()-1;i>0;i--){
         if((sequence.get(i)*2 <= sequence.get(sequence.size()-1)&& sequence.get(i-1)*3>=sequence.get(sequence.size()-1))||((sequence.get(i)*2 >= sequence.get(sequence.size()-1)&& sequence.get(i-1)*3<=sequence.get(sequence.size()-1)))){
         return i;
         }
        }
        return sequence.get(sequence.size()-1);
        }

}
