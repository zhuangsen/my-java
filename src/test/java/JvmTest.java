import java.util.Vector;

/**
 * Created by madison on 2017/2/28.
 */
public class JvmTest {
    public static void main(String[] args){
//        byte[] b = new byte[1 * 1024 * 1024];
//        System.out.println("分配了1M空间给数组");

//        byte[] b = new byte[10 * 1024 * 1024];
//        System.out.println("分配了10M空间给数组");

//        System.gc();

//        //系统的最大空间
//        System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");
//        //系统的空闲空间
//        System.out.println("free mem=" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");
//        System.out.println("total mem=" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");

/*        byte[] b = null;
        for (int i = 0; i < 10; i++)
            b = new byte[1 * 1024 * 1024];*/


/*        Vector v = new Vector();
        for (int i = 0; i < 25; i++)
            v.add(new byte[1 * 1024 * 1024]);*/

        try {
            recursion(0L, 0L, 0L);
        } catch (Throwable e) {
            System.out.println("deep of calling = " + count);
            e.printStackTrace();
        }

    }

    private static int count = 0;

    public static void recursion(long a, long b, long c) {
        long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
        count++;
        recursion(a, b, c);
    }
}
