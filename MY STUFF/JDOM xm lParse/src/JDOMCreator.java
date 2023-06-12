import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class JDOMCreator {

    private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";


    public JDOMCreator() {
    }


    public Document createXMLDocument(List<Customer> data) {

        Document doc = new Document();
        Element root = new Element("customers");

        doc.addContent(root);

        for (Customer customer : data) {
            Element custElement = new Element("customer");
            root.addContent(custElement);

            custElement.setAttribute(Customer.ID, Integer.toString(customer.getId()));

            addChildElement(custElement, Customer.NAME, customer.getName());
            addChildElement(custElement, Customer.PHONE, customer.getPhone());
            addChildElement(custElement, Customer.AGE, Integer.toString(customer.getAge()));

            Element about = addChildElement(custElement, Customer.ABOUT, "");
            CDATA cdata = new CDATA(customer.getAbout());
            about.addContent(cdata);

            addChildElement(custElement, Customer.BALANCE, customer.getBalance().toString());
            addChildElement(custElement, Customer.ACTIVE, Boolean.toString(customer.isActive()));

            DateFormat df = new SimpleDateFormat(XMLDATEFORMAT);
            addChildElement(custElement, Customer.JOINED, df.format(customer.getJoined()));

        }

        return doc;
    }


    private Element addChildElement(Element parent, String elementName, String textvalue){
        Element child = new Element(elementName);
        child.setText(textvalue);
        parent.addContent(child);

        return child;
    }

}
