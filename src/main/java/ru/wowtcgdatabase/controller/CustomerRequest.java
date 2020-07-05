package ru.wowtcgdatabase.controller;

public class CustomerRequest {

    private String login;
    private String login_test;
    private String password;
    private String customerName;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin_test() {
        return login_test;
    }

    public void setLogin_test(String login_test) {
        this.login_test = login_test;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
