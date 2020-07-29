package debug;


import java.util.HashMap;
import java.util.Map;

public class DebugTest {
    public static void main(String[] args) {


        int i = 1;
        int j = 1;
        int k = i + j;
        String s = "a";

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int l = 0; l < 10; l++) {

            System.out.println(l);
        }

        method1();
        method2();
       Map mapReturn= forceReturn();
        System.out.println(mapReturn);//方法调用栈中右键 Force Return
    }

    private static Map forceReturn() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("x","X");
        map.put("y","Y");
        map.put("z","Z");
        return  map;
    }

    private static void method2() {
        System.out.println("==============method2");
    }

    private static void method1() {
        System.out.println("-------------method1");
    }
}
