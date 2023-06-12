import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StAXGetDataWtihStream {
    private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public List<Customer> getdataFromXML(String filename) throws FileNotFoundException, XMLStreamException, ParseException {

        List<Customer> data = new ArrayList<>();
        Customer customer = null;

        InputStream in = new FileInputStream(new File(filename));
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(in);

        int eventType = reader.next();

        while (reader.hasNext()) {
            eventType = reader.next();

            if (eventType == XMLEvent.START_ELEMENT) {

                String elemenetName = reader.getName().toString();

                switch (elemenetName) {
                    case "customer" :
                        customer = new Customer();
                        data.add(customer);
                        customer.setId(Integer.parseInt(reader.getAttributeValue("", Customer.ID)));
                        break;

                    case Customer.NAME :
                        customer.setName(reader.getElementText());
                        break;

                    case Customer.PHONE :
                        customer.setPhone(reader.getElementText());
                        break;

                    case Customer.ABOUT :
                        customer.setAbout(reader.getElementText());
                        break;

                    case Customer.ACTIVE :
                        customer.setActive(Boolean.parseBoolean(reader.getElementText()));
                        break;

                    case Customer.AGE :
                        customer.setAge(Integer.parseInt(reader.getElementText()));
                        break;

                    case Customer.BALANCE :
                        customer.setBalance(new BigDecimal(reader.getElementText()));
                        break;

                    case Customer.JOINED :
                        DateFormat df = new SimpleDateFormat(XMLDATEFORMAT);
                        customer.setJoined(df.parse(reader.getElementText()));
                        break;



                    //add more attributes

                    default :
                        break;
                }

            }

        }


        return data;
    }
}
