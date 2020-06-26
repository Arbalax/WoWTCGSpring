package ru.wowtcgdatabase.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ReactiveElasticsearchRepositoriesAutoConfiguration;
import ru.wowtcgdatabase.controller.CustomerRequest;
import ru.wowtcgdatabase.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private CustomerRequest customerRequest;
    private static SessionFactory sessionFactory;

    public CustomerDAO(CustomerRequest customerRequest) {
        this.customerRequest = customerRequest;
    }

    public List logIn () {

        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List loginResponse;
        session.beginTransaction();
        loginResponse = session.createQuery("from Customer where login = '"+customerRequest.getLogin()+"' and password = '"+customerRequest.getPassword()+"'").list();
        session.close();
        sessionFactory.close();
        return loginResponse;
    }

    public void signUp () {

        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Customer customer = new Customer();
        customer.setLogin(customerRequest.getLogin());
        customer.setPassword(customerRequest.getPassword());
        customer.setCustomerName(customerRequest.getCustomerName());
        System.out.println("From CustomerDAO Add: " + customer.getLogin() + customer.getPassword() + customer.getCustomerName());
        session.save(customer);
        session.getTransaction().commit();
        sessionFactory.close();

    }
}
