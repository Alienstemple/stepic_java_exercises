public class Ex {
    public static void Main(String[] Args) {
        System.out.println("\nTest is OK\n");
        B b = new B("lalalala");
        A a = (A) b;
    }
}
public class A {
    private String lala;
    private String boo;
    public A(String lala, String boo) {
        this.lala = lala;
        this.boo = boo;
    }
}
public class B {
    private String lala;
    public B(String lala) {
        this.lala = lala;
    }
}