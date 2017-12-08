package dynamic.test;

public class randomtest {
    public static void main(String[] args){
        int i = (int)(Math.pow(2,31))/10;
        System.out.println('3'+'0');
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));

        int newResult = i * 10 ;
        System.out.println(Integer.toBinaryString(newResult));
        System.out.println(newResult<Integer.MIN_VALUE);
        System.out.println(
                (Integer.toBinaryString(newResult / 10 )));
    }
}
