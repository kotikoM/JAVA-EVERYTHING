
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder={"name","phone","about","age","balance","active","joined"})
public class Customer {

    private int id;
    private String name;
    private String phone;
    private String about;
    private int age;
    private BigDecimal balance;
    private boolean active;
    private Date joined;

    //data item names from JSON file
    public static final String
            ID="id",
            NAME="name",
            PHONE="phone",
            ABOUT="about",
            AGE="age",
            BALANCE="balance",
            ACTIVE="active",
            JOINED="joined";

    public Customer() {
    }
    @XmlAttribute
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setId(Number id) {
        this.id = id.intValue();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAbout() {
        return about;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public void setBalance(String balance) {
        this.balance = new BigDecimal(balance);
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setAge(Number age) {
        this.age = age.intValue();
    }
    public boolean getActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setActive(String isActive) {
        this.active = Boolean.parseBoolean(isActive);
    }
    public Date getJoined() {
        return joined;
    }
    public void setJoined(Date joined) {
        this.joined = joined;
    }
    public void setJoined(String joined) {
        //This format matches the date format in the JSON data file
        DateFormat inputDate = DateFormat.getDateInstance(DateFormat.SHORT);
        try {
            this.joined = inputDate.parse(joined);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        return this.id + ". " + this.name + " joined " + df.format(this.joined);
    }

}
