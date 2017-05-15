import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerSocketTest {
	public static void main(String[] args)
            throws Exception
    {
        ServerSocket ss = new ServerSocket(4001);
        Socket s = ss.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(s.
                getInputStream()));
        PrintStream ps=new PrintStream(s.getOutputStream());
        String temp = br.readLine();
        while (true)
        {
            System.out.println("客户端:"+temp);
            ps.println(temp);
            if (temp.equals("stop"))
            {
                break;
            }
            temp = br.readLine();
        }
        br.close();
        ps.close();
        ss.close();
    }
}


