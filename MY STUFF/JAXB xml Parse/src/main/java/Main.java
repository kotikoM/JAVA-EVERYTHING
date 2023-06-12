import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParseException, JAXBException {
        List<Customer> data = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("John Doe");
        customer1.setPhone("1234567890");
        customer1.setAbout("About John&");
        customer1.setAge(30);
        customer1.setBalance(new BigDecimal("1000.00"));
        customer1.setActive(true);
        customer1.setJoined(sdf.parse("2023-06-08T14:30:00"));
        data.add(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("Jane Smith");
        customer2.setPhone("9876543210");
        customer2.setAbout("About Jane");
        customer2.setAge(25);
        customer2.setBalance(new BigDecimal("500.00"));
        customer2.setActive(true);
        customer2.setJoined(sdf.parse("2023-06-08T14:30:00"));
        data.add(customer2);

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setName("Michael Johnson");
        customer3.setPhone("5555555555");
        customer3.setAbout("About Michael");
        customer3.setAge(40);
        customer3.setBalance(new BigDecimal("2000.00"));
        customer3.setActive(true);
        customer3.setJoined(sdf.parse("2023-06-08T14:30:00"));
        data.add(customer3);



        Customers customers = new Customers();
        customers.setCustomers(data);

        JAXBContext context = JAXBContext.newInstance(Customers.class); // Change Customer.class to Customers.class
        Marshaller marshaller = context.createMarshaller();

        StringWriter sw = new StringWriter();
        marshaller.marshal(customers, sw);

        System.out.println(sw);

    }
}
