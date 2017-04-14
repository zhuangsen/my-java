import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class CaiPiao{

    public static void main(String args[]){
        Random random = new Random();

        //遍历红球
        List<Integer> reds = new ArrayList<Integer>();
        int red;
        for(int i=0;i<6;i++){
            red = random.nextInt(34);
            while(red==0 || reds.contains(red)){
                red = random.nextInt(34);
            }
            reds.add(red);
        }



        Collections.sort(reds);
        for(int r : reds){
            System.out.print(r+" ");
        }
        System.out.println();

        //遍历蓝球
        int blue = random.nextInt(17);
        while(blue==0){
            blue = random.nextInt(17);
        }
        System.out.print(blue);
    }
}