package ru.wowtcgdatabase.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.wowtcgdatabase.controller.Request;

import java.util.ArrayList;
import java.util.List;

public class CardsDAO {
    private Request request;
    private static SessionFactory sessionFactory;

    public CardsDAO(Request request) {
        this.request = request;
    }

    public List getCards () {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List cards;
        Transaction transaction = null;
        session.beginTransaction();
        cards = session.createQuery("FROM CardWow where rarity like case when '"+request.getRarity()+"' = 'All' then '%%' else '"+request.getRarity()+"' end " +
                                                           "and type like case when '"+request.getType()+"' = 'All' then '%%' else '"+request.getType()+"' end " +
//                                                           "and cost = case when "+request.getCost()+" = -1 then >=0 else "+request.getCost()+" end " +
                                                           "and cost like case when "+request.getCost()+" =-1 then '%%' else "+request.getCost()+" end " +
                                                           "and faction like case when '"+request.getFaction()+"' = 'All' then '%%' else '"+request.getFaction()+"' end").list();
        System.out.println(cards);
        session.close();
        sessionFactory.close();
        return cards;


    }


//    public ArrayList<Bean> getAll() {
//        sessionFactory = new Configuration().configure().buildSessionFactory();
//    }
}