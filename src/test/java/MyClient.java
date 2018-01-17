import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyClient {
    public static void main(String[] args) throws IOException {
//        Socket client = null;
//        PrintWriter printWriter = null;
//        BufferedReader bufferedReader = null;
//        try {
//            client = new Socket();
//            client.connect(new InetSocketAddress("localhost", 8686));
//            printWriter = new PrintWriter(client.getOutputStream(), true);
//            printWriter.println("hello");
//            printWriter.flush();
//
//            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));            //读取服务器返回的信息并进行输出
//            System.out.println("来自服务器的信息是：" + bufferedReader.readLine());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            printWriter.close();
//            bufferedReader.close();
//            client.close();
//        }

//        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
//        System.out.println(sf.format(new Date()));
////        String str = "INTEL-769163124030_4";
//
//        String str = "AML14930074";
//        System.out.println(str.indexOf("AML")+3);
//        String id = str.substring(str.indexOf("AML")+3);
//        System.out.println(id);
//        System.out.println(id[0]);

        String str = "#description\\n\\n您已经在本店完成消费！\\n消费时间：#dtTransaction\\n消费门店：#storeName\\n付款金额：￥#amountProcessed元（总金额：￥#amountRequested元）\\n优惠金额：￥#amountDiscounted\\n交易订单：#rrn";
        System.out.println(str.replace("优惠金额：￥#amountDiscounted\\n",""));
    }
}