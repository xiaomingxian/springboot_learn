import org.junit.Test;

public class T3_SortRe {
    @Test
    public void maopao() {
        int[] array = {2, 1, 6, 10, 8, 4, 7, 9};

        for (int i = 0; i < array.length-1; i++) {//比多少轮
            for (int j = 0; j < array.length-1-i; j++) {//每一轮比完后 就会出现一个极值
                if(array[j]>array[j+1]){
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
                for (int x : array) {
                    System.out.print(x + ",");
                }
                System.out.println();
            }
        }
        for (int i : array) {
            System.out.print(i + ",");
        }
    }

}
