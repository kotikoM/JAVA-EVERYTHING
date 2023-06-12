import java.util.List;

public class ReadXMLWithSax {


    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        String filename = "customers.xml";

        SAXCustomerHandler saxCustomerHandler = new SAXCustomerHandler();

        List<Customer> data = saxCustomerHandler.readDataFromXML(filename);

        System.out.println("Number of Customers: " + data.size());

        for (Customer customer : data) {
            System.out.println(customer.toString());
        }
    }
}
