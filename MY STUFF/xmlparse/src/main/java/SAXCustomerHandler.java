
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class SAXCustomerHandler extends DefaultHandler {

    private List<Customer> data;
    private String currentElement = "";
    private Customer customer;

    private StringBuilder currentText;
    private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";


    public List<Customer> readDataFromXML(String filename) throws Exception{

        SAXParserFactory factory = SAXParserFactory.newInstance();
//        set name space aware on
//        factory.setNamespaceAware(true);
        SAXParser parser = factory.newSAXParser();

        parser.parse(new File(filename), this);

       return data;
    }

    @Override
    public void startDocument() throws SAXException {
//        System.out.println("Start Document");
          data = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.println("Start Element" + qName);

        currentElement = qName;

        switch (currentElement) {
            case "customer" :
                customer  = new Customer();
                String idAsString = attributes.getValue("id");
                customer.setId(Integer.parseInt(idAsString));
                data.add(customer);
                break;


            default:
                currentText = new StringBuilder();

                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.println("End Element"+qName);

        if (currentElement.equals("customers") || currentElement.equals("customer")){
            return;
        }

        String content = currentText.toString();

        switch (currentElement) {

            case  Customer.NAME :
                customer.setName(content);
                break;

            case  Customer.PHONE:
                customer.setPhone(content);
                break;

            case  Customer.ABOUT:
                customer.setAbout(content);
                break;

            case  Customer.AGE :
                customer.setAge(Integer.parseInt(content));
                break;

            case  Customer.ACTIVE:
                customer.setActive(Boolean.parseBoolean(content));
                break;


            case  Customer.BALANCE:
                customer.setBalance(new BigDecimal(content));
                break;

            case  Customer.JOINED:
                DateFormat df = new SimpleDateFormat(XMLDATEFORMAT);
                try {
                    customer.setJoined(df.parse(content));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;

            default :
                break;
        }

          currentElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
//        System.out.println("Characters");
        if (currentText != null){
            currentText.append(ch, start, length);
        }
    }
}
