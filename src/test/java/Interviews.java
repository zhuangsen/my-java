import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Interviews {

	public static void split(String source, int num) {
		int k = 0;
		String temp = "";
		for (int i = 0; i < source.length(); i++) {
			// System.out.println(source.charAt(i));
			byte[] b = (source.charAt(i) + "").getBytes();
			k = k + b.length;
			System.out.println("k="+k);
			if (k > num) {
				break;
			}
			temp = temp + source.charAt(i);
		}
		System.out.println(temp);
	}

	public static void main(String[] args) throws Exception {
//		split("我 ABC", 4);
//		split("我 ABC 汉 DEF", 6);//我AB
//		System.out.println("发".getBytes().length);//3
		
		
//		A ab = new B(); //执行到此处,结果: 1a2b
//		ab = new B(); //执行到此处,结果: 1a2b2b
		
//		System.out.println(2 << 3);
		
		
//		writeFileString();
//		xmlparser();
		xmlwriter();
	}
	
	
	/**
	 * 文件读取流
	 * 输入输出流
	 * @throws Exception
	 */
	public static void writeFileChar() throws Exception {
	    FileWriter f = new FileWriter("f:\\aa.txt");
	    InputStream is = System.in;
	    int c = is.read();
	    while (((char) c) != 'x') {
	        f.write(c);
	        c = is.read();
	    }
	    f.close();
	    is.close();
	}

	public static void writeFileString() throws Exception {
	    FileWriter f = new FileWriter("f:\\aa.txt");
	    BufferedWriter bwr=new BufferedWriter(f);
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    String c = bf.readLine();
	    while (!c.equals("stop")) {
	        bwr.write(c+"\r\n");
	        c = bf.readLine();
	    }
	    bwr.close();
	    f.close();
	    bf.close();
	}

	public static void readFileChar() throws Exception {
	   FileReader f = new FileReader("f:\\aa.txt");
	   int c = f.read();
	   while (c!=-1) {
	       System.out.print((char)c);
	       c=f.read();
	   }
	   f.close();
	}

	public static void readFileString() throws Exception {
	   BufferedReader bf = new BufferedReader(new FileReader("f:\\aa.txt"));
	   String c = bf.readLine();
	   while (c!=null) 
	   {
	       System.out.println(c);
	       c=bf.readLine();
	   }
	   bf.close();
	}
	
	
	public static void xmlparser() throws Exception{
	    DocumentBuilderFactory xdf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = xdf.newDocumentBuilder();
	    Document d=db.parse("F:\\Workspace\\workspace-javaee\\JeeSite\\src\\main\\resources\\spring-context.xml");
	    NodeList nl = d.getElementsByTagName("bean");
	    for (int i = 0; i < nl.getLength(); i++){
	        Element e = (Element) nl.item(i);
	        Attr a = e.getAttributeNode("id");
	        System.out.println(a.getNodeValue());
	        NodeList nl1 = e.getElementsByTagName("property");
	        System.out.println(nl1.item(0).getFirstChild().getNodeValue());
	    }
	}
	public static void xmlwriter() throws Exception
	{
	    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	    DocumentBuilder db=dbf.newDocumentBuilder();
	    Document d=db.parse("F:\\Workspace\\workspace-javaee\\JeeSite\\src\\main\\resources\\spring-context.xml");
/*	    NodeList nl=d.getElementsByTagName("bean");
	    System.out.println(nl.getLength());
	    for(int i=0;i<nl.getLength();i++){
	       Element e=(Element) nl.item(i);
	       System.out.println(e.getFirstChild().getNodeValue());
	       e.getFirstChild().setNodeValue(e.getFirstChild().getNodeValue());
	    }*/
	    TransformerFactory tff=TransformerFactory.newInstance();
	    Transformer tf=tff.newTransformer();
	    tf.transform(new DOMSource(d),new StreamResult("f:\\aa.xml"));
	}
}


/**
 * static和构造方法执行顺序
 * @author madison
 *
 */
class C{
	static{
	System.out.print("1");
	}
	public C(){
	System.out.print("2");
	}
}
class D extends C{
	static{
	System.out.print("a");
	}
	public D(){
	System.out.print("b");
	} 
}




