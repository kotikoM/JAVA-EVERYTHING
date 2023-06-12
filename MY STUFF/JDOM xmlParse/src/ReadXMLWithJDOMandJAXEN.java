import org.jdom2.*;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;

import org.jaxen.*;

import javax.xml.xpath.*;
import javax.xml.xpath.XPath;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReadXMLWithJDOMandJAXEN {

    private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    private static List<Customer> getDataFromXML(String filename, String filter) throws DataConversionException, ParseException, XPathExpressionException {
        List<Customer> data = new ArrayList<>();

        File file = new File(filename);
        SAXBuilder builder = new SAXBuilder();
        Document doc = null;

        try{
            doc = builder.build(file);
        }
        catch (JDOMException | IOException e){
            e.printStackTrace();
            return null;
        }


//        Element root = doc.getRootElement();
//        List<Element> custElements = root.getChildren("customer");

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        XPathExpression expression = xPath.compile(filter);

        List<Element> custElements = (List<Element>) expression.evaluate(doc, XPathConstants.NODESET);

        for (Element ce : custElements) {

            Customer customer = new Customer();
            data.add(customer);

            Attribute att = ce.getAttribute(Customer.ID);
            customer.setId(att.getIntValue());

            customer.setName(ce.getChildText(Customer.NAME));
            customer.setPhone(ce.getChildText(Customer.PHONE));
            customer.setAbout(ce.getChildText(Customer.ABOUT));
            customer.setAge(Integer.parseInt(ce.getChildText(Customer.AGE)));
            customer.setBalance(new BigDecimal(ce.getChildText(Customer.BALANCE)));
            customer.setActive(Boolean.parseBoolean(ce.getChildText(Customer.ACTIVE)));

            DateFormat df = new SimpleDateFormat(XMLDATEFORMAT);
            customer.setJoined(df.parse(ce.getChildText(Customer.JOINED)));
        }



        return data;

    }
    public static void main(String[] args) throws ParseException, DataConversionException, XPathExpressionException {
        JDOMReader reader = new JDOMReader();
        List<Customer> data = getDataFromXML("out/result.xml",
        "//customer[age >= 30]");
        //double slash to search whole xml document then name of element to find
        //then predicate that will be used as filter


        for(Customer customer : data){
            System.out.println(customer.toString());
        }
    }
}
