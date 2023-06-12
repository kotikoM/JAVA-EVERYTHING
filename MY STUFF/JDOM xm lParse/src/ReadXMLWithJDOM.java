import org.jdom2.DataConversionException;

import java.text.ParseException;
import java.util.List;

public class ReadXMLWithJDOM {
    public static void main(String[] args) throws ParseException, DataConversionException {

        JDOMReader reader = new JDOMReader();
        List<Customer> data = reader.getDataFromXML("out/result.xml");

        for(Customer customer : data){
            System.out.println(customer.toString());
        }
    }
}
