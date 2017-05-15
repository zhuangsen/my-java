import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    String str = new String("good");
    char[] ch = {'a','b','c'};
    public static void main(String[] args){
        Short a = 128;
//            byte b= (byte)a;

        String s = "hello";
        String t = "hello";
        char c[] = {'h','e','l','l','o'};
        System.out.println(s.equals(t));//true
        System.out.println(s==t);//true
        System.out.println(t.equals(c));//false
        System.out.println(t.equals(new String("hello")));//true

/*            B aa = new A();
        aa = new A();

        int intArray[];
        intArray = new int[3];
        intArray[1] = 1;
        intArray[2] = 2;
        intArray[3] = 3;
        int g[] = {1,2,3,1,2};
        int asd[][] = new int[2][];
        asd[0] = new int[3];
        asd[1] = new int[3];*/

/*        void waitForSignal(){
            Object obj = new Object();
            synchronized (Thread.currentThread()){
                obj.wait();
                obj.notify();
            }
        }*/
        Main main = new Main();
        main.change(main.str,main.ch);
        System.out.print(main.str+"and");
        System.out.println(main.ch);

//        int x=1,y=2,z=3;
//        System.out.println(y+=z--/++x);
//        System.out.print(3/2);

        List<? super Number> list = new ArrayList<>();
        list.add(new Integer(3));
        list.add(2);

        Integer[] a1 = new Integer[]{2,12,15,19,20,21,25};
        Integer[] b1 = new Integer[]{3,5,13,15,22,55};
        Integer[] c1 = new Integer[a1.length+b1.length];
        List<Integer> aList = Arrays.asList(a1);
//        Arrays.copyOf(a1,a1.length);
        System.arraycopy(a1,0,c1,0,a1.length);
        for (int i = 0; i < c1.length; i++) {
            System.out.print(c1[i]+"===");
        }
        System.out.println();
        System.arraycopy(b1,0,c1,a1.length, b1.length);
        for (Integer i : aList) {
            System.out.print(i+"---");
        }
        System.out.println();
        for (int i = 0; i < c1.length; i++) {
            System.out.print(c1[i]+"===");
        }
        Arrays.sort(c1);
        System.out.println("sorted");
        for (int i = 0; i < c1.length; i++) {
            System.out.print(c1[i]+"===");
        }
    }

    public void change(String str, char ch[]){
        str = "test ok";
        ch[0] = 'g';
    }

}

class B extends Object{
    static {
        System.out.println("LoadB");
    }
    public B(){
        System.out.println("CreateB");
    }

    public int f(){
        return 1;
    }
}

class A extends B{
    static {
        System.out.println("LoadA");
    }
    public A(){
        System.out.println("CreateA");
    }

    //编译错误
//    public float f(){
//        return 1f;
//    }
}

class MyThread extends Thread implements Runnable{

    @Override
    public void run(){
        System.out.println("00000");
    }
}