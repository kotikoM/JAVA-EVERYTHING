package com.agriculture.AgroPlanner.domain;

import jakarta.persistence.*;

import static com.agriculture.AgroPlanner.constants.ColumnNames.*;

@Entity
@SuppressWarnings("unused")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = USER_USERID)
    private Long userID;
    @Column(name = USER_USERNAME)
    private String userName;
    @Column(name = USER_PASSWORD)
    private String password;
    @Column(name = USER_MAIL)
    private String mail;
    @Embedded
    private Location location;

    public User(Long userID, String userName, String password, String mail, Location location) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.location = location;
    }

    public User() {
    }

    public Long getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public Location getLocation() {
        return location;
    }
}
