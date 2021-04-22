import java.util.*;


public class CaiPiao {

    public static void main(String args[]) {
//        while (true){
//            List<Integer> list = printC();
//            int blue = 9;
//            int len = list.size();
//            if(list.get(len-1) == blue){
//                return;
//            }
//        }
        for (int i = 0; i < 1; i++) ssq();
//        for (int i = 0; i < 5; i++) dlt();
    }

    private static void ssq() {
        Random random = new Random();

        //遍历红球
        List<Integer> reds = new ArrayList<Integer>();
        int red;
        for (int i = 0; i < 6; i++) {
            red = random.nextInt(34);
            while (red == 0 || reds.contains(red)) {
                red = random.nextInt(34);
            }
            reds.add(red);
        }
        Collections.sort(reds);
        for (int r : reds) {
            System.out.print(r + "\t");
        }

        //遍历蓝球
        int blue = random.nextInt(17);
        while (blue == 0) {
            blue = random.nextInt(17);
        }
        System.out.print(blue);
        System.out.println();
    }

    private static void dlt() {
        Random random = new Random();

        //遍历红球
        List<Integer> reds = new ArrayList<>();
        int red;
        for (int i = 0; i < 5; i++) {
            red = random.nextInt(35) + 1;
            while (reds.contains(red)) {
                red = random.nextInt(35) + 1;
            }
            reds.add(red);
        }
        Collections.sort(reds);
        for (int r : reds) {
            System.out.print(r + "\t");
        }

        //遍历蓝球
        List<Integer> blues = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int blue = random.nextInt(12) + 1;
            while (blues.contains(blue)) {
                blue = random.nextInt(12) + 1;
            }
            blues.add(blue);
        }
        Collections.sort(blues);
        for (int b : blues) {
            System.out.print(b + "\t");
        }
        System.out.println();
    }
}