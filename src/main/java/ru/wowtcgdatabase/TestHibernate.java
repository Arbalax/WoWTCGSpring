package ru.wowtcgdatabase;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.wowtcgdatabase.model.Card;

import java.util.List;


public class TestHibernate {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

        sessionFactory = new Configuration().configure().buildSessionFactory();

        TestHibernate testHibernate = new TestHibernate();

        System.out.println("List of Cards:");
        testHibernate.listCards();

        List result = testHibernate.listCards();

        System.out.println(testHibernate.listCards());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sessionFactory.close();
    }

    public List <Card> listCards() {

        Session session = sessionFactory.openSession();

        List <Card> cards;

        Transaction transaction = null;

        session.beginTransaction();

        cards = session.createQuery("FROM CardWow where rarity = 'rare' and type = 'ally' and cost = 5 and faction = 'Horde'").list();

//        for (Card card : cards) {
//
//            System.out.println(card);
//            System.out.println("\n=================\n");
//
//        }



        session.close();

        return cards;
    }


}
