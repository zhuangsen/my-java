import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
public class SaxParser extends DefaultHandler
{
    public void characters(char[] ch, int start, int length)throws SAXException{
        String temp=new String(ch,start,length);
        System.out.println(temp);
    }

    public void endDocument()throws SAXException{
        System.out.println("正在开始一个文档");
    }

    public void endElement(String namespaceURI, String localName, String qName)throws SAXException{
        System.out.println("结束元素"+qName);
    }

    public void startDocument()throws SAXException{
        System.out.println("开始文档");
    }

    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts)throws SAXException{
         System.out.println("开始元素"+qName);
    }
    public static void main(String[] args) throws Exception{
        SAXParserFactory spf=SAXParserFactory.newInstance();
        SAXParser sp=spf.newSAXParser();
        sp.parse("F:\\Workspace\\workspace-javaee\\JeeSite\\src\\main\\resources\\spring-context.xml",new SaxParser());
    }
}