import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientSocket
{
  public static void main(String[] args) throws Exception
  {
//      Socket s = new Socket("localhost", 4001);
//      PrintStream ps = new PrintStream(s.getOutputStream());
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//      BufferedReader br_server = new BufferedReader(new InputStreamReader(s.
//              getInputStream()));
//      String temp = br.readLine();
//      while (true)
//      {
//          ps.println(temp);
//          temp = br_server.readLine();
//          System.out.println("服务器的信息:" + temp);
//          if (temp.equals("stop"))
//          {
//              break;
//          }
//          temp = br.readLine();
//      }
//      s.close();
//      br.close();
//      br_server.close();

      String str = "#description\\n\\n消费时间：#dtTransaction\\n消费门店：#storeName\\n付款金额：￥#amountProcessed元（总金额：￥#amountRequested元）\\n优惠金额：￥#amountDiscounted\\n交易订单：#rrn";

      System.out.println(str.replace("#amountProcessed","sd"));
  }
}