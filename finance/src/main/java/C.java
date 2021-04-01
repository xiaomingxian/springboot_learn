
class A{
    static {
        System.out.println("------>static A");
    }
    {
        System.out.println("========>block A");
    }
    public A(){
        System.out.println("cccccc A");

    }

}

class B extends A{
    static {
        System.out.println("------>static B");
    }

    {
        System.out.println("========>block B");
    }
    public B(){
        System.out.println("cccccc B");

    }

}
public class C extends B{

    static {
        System.out.println("------>static C");
    }
    {
        System.out.println("========>block C");
    }
    public C(){
        System.out.println("cccccc C");

    }

}