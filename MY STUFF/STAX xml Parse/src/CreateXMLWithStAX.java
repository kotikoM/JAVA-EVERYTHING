import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CreateXMLWithStAX {
    public static void main(String[] args) throws XMLStreamException, ParseException, IOException {
        List<Customer> data = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        data.add(new Customer(1, "John Doe", "1234567890", "About John&", 30,
                new BigDecimal("1000.00"), true, sdf.parse("2023-06-08T14:30:00")));

        data.add(new Customer(2, "Jane Smith", "9876543210", "About Jane", 25,
                new BigDecimal("500.00"), true, sdf.parse("2023-06-08T14:30:00")));

        data.add(new Customer(3, "Michael Johnson", "5555555555", "About Michael", 40,
                new BigDecimal("2000.00"), true, sdf.parse("2023-06-08T14:30:00")));


        long start = System.currentTimeMillis();

        StAXStreamCreator creator = new StAXStreamCreator();
        creator.createDocument(data, "out/result.xml");


        long end = System.currentTimeMillis();

        System.out.println("StAX took " + (end-start) + " miliseconds");
    }
}
