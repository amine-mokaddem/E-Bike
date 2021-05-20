package tn.esprit.pidev.entities;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String type;
    private int phone;

    public User() {
    }

    public User(int id, String name, String email, String password, String type, int phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.phone = phone;
    }

    public User(String name, String email, String password, String type, int phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.phone = phone;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

}
