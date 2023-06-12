import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class CreateXMLwithDOM {

    public static void main(String[] args) {
        File f = new File ("data.xml");
        Document doc = null;

        try{
            DocumentBuilder builder =
                    DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.parse(f);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
