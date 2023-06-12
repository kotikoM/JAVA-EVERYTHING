import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

public class Customer {

    private int id;
    private String name;
    private String phone;
    private String about;
    private int age;
    private BigDecimal balance;
    private boolean active;
    private Date joined;


    public Customer(){

    }

    public Customer(int id, String name, String phone, String about, int age, BigDecimal balance, boolean active, Date joined) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.about = about;
        this.age = age;
        this.balance = balance;
        this.active = active;
        this.joined = joined;
    }

    public static final String
            ID = "id",
            NAME = "name",
            PHONE = "phone",
            ABOUT = "about",
            AGE = "age",
            BALANCE = "balance",
            ACTIVE = "active",
            JOINED = "joined";




    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAbout() {
        return about;
    }

    public int getAge() {
        return age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    public Date getJoined() {
        return joined;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", about='" + about + '\'' +
                ", age=" + age +
                ", balance=" + balance +
                ", active=" + active +
                ", joined=" + joined +
                '}';
    }
}
