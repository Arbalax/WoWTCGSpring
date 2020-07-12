package ru.wowtcgdatabase.DAO;

import org.hibernate.Session;
import ru.wowtcgdatabase.SessionCreator;
import ru.wowtcgdatabase.controller.CustomerRequest;
import ru.wowtcgdatabase.model.Customer;
import java.util.List;

public class CustomerDAO {
    private CustomerRequest customerRequest;

    public CustomerDAO(CustomerRequest customerRequest) {
        this.customerRequest = customerRequest;
    }

    public List<Customer> logIn () {

        try (Session session = SessionCreator.getSession()) {
            List loginResponse;
            session.beginTransaction();
            loginResponse = session.createQuery("from Customer where login = '" + customerRequest.getLogin() + "' and password = '" + customerRequest.getPassword() + "'").list();
            return loginResponse;
        }
    }

    public void signUp () {

        try (Session session = SessionCreator.getSession()) {

            session.beginTransaction();
            Customer customer = new Customer();
            customer.setLogin(customerRequest.getLogin());
            customer.setPassword(customerRequest.getPassword());
            customer.setCustomerName(customerRequest.getCustomerName());
            session.save(customer);
            session.getTransaction().commit();
        }
    }

    public List<Customer> checkLogin () {

        try (Session session = SessionCreator.getSession()) {
            List loginResponse;
            session.beginTransaction();
            loginResponse = session.createQuery("from Customer where login = '" + customerRequest.getLogin() + "'").list();
            return loginResponse;
        }
    }
}
