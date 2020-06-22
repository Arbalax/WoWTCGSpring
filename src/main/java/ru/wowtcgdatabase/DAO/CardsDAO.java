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
        cards = session.createQuery("FROM CardWow where rarity like case when '"+request.getRarity()+"' = 'All' then '%' else '"+request.getRarity()+"' end " +
                                                           "and type like case when '"+request.getType()+"' = 'All' then '%' else '"+request.getType()+"' end " +
                                                           "and setName like case when '"+request.getSetName()+"' = 'All' then '%' else '"+request.getSetName()+"' end " +
                                                           "and cardClass like case when '"+request.getCardClass()+"' = 'All' then '%' else '"+request.getCardClass()+"' end " +
                                                           "and cost like case when "+request.getCost()+" =-10 then '%' when "+request.getCost()+" =10 then '__' else "+request.getCost()+" end " +
                                                           "and faction like case when '"+request.getFaction()+"' = 'All' then '%' else '"+request.getFaction()+"' end").list();
        System.out.println(cards);
        session.close();
        sessionFactory.close();
        return cards;


    }

    public List getMyCollection () {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List cards;
        Transaction transaction = null;
        session.beginTransaction();
        cards = session.createQuery("select cards FROM CardWow cards inner join MyCollection collection on cards.cardId = collection.cardId " +
                "where cards.rarity like case when '"+request.getRarity()+"' = 'All' then '%' else '"+request.getRarity()+"' end " +
                "and type like case when '"+request.getType()+"' = 'All' then '%' else '"+request.getType()+"' end " +
                "and cards.setName like case when '"+request.getSetName()+"' = 'All' then '%' else '"+request.getSetName()+"' end " +
                "and cards.cardClass like case when '"+request.getCardClass()+"' = 'All' then '%' else '"+request.getCardClass()+"' end " +
                "and cards.cost like case when "+request.getCost()+" =-10 then '%' when "+request.getCost()+" =10 then '__' else "+request.getCost()+" end " +
                "and cards.faction like case when '"+request.getFaction()+"' = 'All' then '%' else '"+request.getFaction()+"' end " +
                "and collection.customerId = 1").list();
        System.out.println(cards);
        session.close();
        sessionFactory.close();
        return cards;
    }


//    public ArrayList<Bean> getAll() {
//        sessionFactory = new Configuration().configure().buildSessionFactory();
//    }
}
