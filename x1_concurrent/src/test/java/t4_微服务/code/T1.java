package t4_微服务.code;

public class T1 {

    public static void main(String[] args) {

        /**
         * 打印金字塔
         */
        new T1().printJ(10);

        //10
        //line  " "   *
        //1      9(10-1)    1(1*2-1)
        //2      8(10-2)    3(2*2-1)
        //3      7(10-3)    5(2*3-1)
        //4      6(10-4)    7(2*4-1)


    }


    public void printJ(int num) {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num-i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <2*i-1 ; j++) {
                System.out.print("*");
            }


            System.out.print("\n");
        }
    }
}
