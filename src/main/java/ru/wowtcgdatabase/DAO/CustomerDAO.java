package ru.wowtcgdatabase.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;
import ru.wowtcgdatabase.controller.CustomerRequest;
import ru.wowtcgdatabase.model.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerDAO {
    private CustomerRequest customerRequest;
    private static SessionFactory sessionFactory;

    public CustomerDAO(CustomerRequest customerRequest) {
        this.customerRequest = customerRequest;
    }

    public Optional<Customer> logIn () {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<Customer> loginResponse;
        session.beginTransaction();

        Query<Customer> query = session.createQuery("from Customer where login = '" + customerRequest.getLogin() + "' and password = '" + customerRequest.getPassword() + "'");

        List<Customer> customers = query.list();

        session.close();
        sessionFactory.close();

        Customer value = CollectionUtils.isEmpty(customers) ? null : customers.get(0);
        return Optional.ofNullable(value);
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
