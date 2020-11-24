package model.user;

import java.util.Date;

public class User {

    private String name;
    private Long userId;
    private String address;
    private Date datOfBirth;

    public User(String name, Long userId, String address, Date datOfBirth) {
        this.name = name;
        this.userId = userId;
        this.address = address;
        this.datOfBirth = datOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDatOfBirth() {
        return datOfBirth;
    }

    public void setDatOfBirth(Date datOfBirth) {
        this.datOfBirth = datOfBirth;
    }
}
