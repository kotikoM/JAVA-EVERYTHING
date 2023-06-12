import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateXMLWithJDOM {


    public static void main(String[] args) throws ParseException, IOException {

        List<Customer> data = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        data.add(new Customer(1, "John Doe", "1234567890", "About John&", 30,
                new BigDecimal("1000.00"), true, sdf.parse("2023-06-08T14:30:00")));

        data.add(new Customer(2, "Jane Smith", "9876543210", "About Jane", 25,
                new BigDecimal("500.00"), true, sdf.parse("2023-06-08T14:30:00")));

        data.add(new Customer(3, "Michael Johnson", "5555555555", "About Michael", 40,
                new BigDecimal("2000.00"), true, sdf.parse("2023-06-08T14:30:00")));


        JDOMCreator creator = new JDOMCreator();
        Document doc = creator.createXMLDocument(data);

        List<Element> list = doc.getRootElement().getChildren();
        System.out.println("retrieved " + list.size());


        //output on console
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        String xmlString = outputter.outputString(doc);
        System.out.println(xmlString);


        //create output as file
        FileWriter writer = new FileWriter(new File("out/result.xml"));
        outputter.output(doc, writer);
    }
}
