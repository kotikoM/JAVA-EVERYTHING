import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

public class ReadXMLWithStAXStream {
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException, ParseException {

        StAXGetDataWtihStream reader = new StAXGetDataWtihStream();
        List<Customer> data = reader.getdataFromXML("out/result.xml");

        for (Customer customer : data){
            System.out.println(customer);
        }
        

//        StAXGetDataWithEvents reader = new StAXGetDataWithEvents();
//        List<Customer> data = reader.getdataFromXML("out/result.xml");
//
//        for (Customer customer : data){
//            System.out.println(customer);
//        }

    }
}
