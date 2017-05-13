import java.util.*;


public class CaiPiao{

    public static void main(String args[]){
        while (true){
            List<Integer> list = printC();
            int blue = 9;
            int len = list.size();
            if(list.get(len-1) == blue){
                return;
            }
        }
    }

    private static List printC(){
        List<Integer> list = new ArrayList<>();
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

        list.addAll(reds);

        //遍历蓝球
        int blue = random.nextInt(17);
        while(blue==0){
            blue = random.nextInt(17);
        }
        System.out.print(blue);
        System.out.println();
        list.add(blue);
        return list;
    }
}