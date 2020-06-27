package ru.wowtcgdatabase.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.wowtcgdatabase.controller.CollectionRequest;
import ru.wowtcgdatabase.controller.Request;
import ru.wowtcgdatabase.model.MyCollection;

import java.util.List;

public class CollectionDAO {

    private CollectionRequest collectionRequest;
    private static SessionFactory sessionFactory;

    public CollectionDAO(CollectionRequest collectionRequest) {
        this.collectionRequest = collectionRequest;
    }

    public void addCard () {

        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;
        session.beginTransaction();

        MyCollection myCollection = new MyCollection();
        myCollection.setCustomerId(collectionRequest.getCustomerId());
        myCollection.setCardId(collectionRequest.getCardId());
        System.out.println("From CollectionDAO Add: " + myCollection.getCustomerId()+myCollection.getCardId());
        session.save(myCollection);


        session.getTransaction().commit();

        sessionFactory.close();

    }

    public void deleteCard () {

        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        MyCollection myCollection = new MyCollection();
        myCollection.setCustomerId(collectionRequest.getCustomerId());
        myCollection.setCardId(collectionRequest.getCardId());

        System.out.println("From CollectionDAO Delete: " + myCollection.getCustomerId()+myCollection.getCardId());

//        MyCollection managedMyCollection = (MyCollection) session.merge(myCollection);
//        session.delete(managedMyCollection);
//        session.flush();
//        session.getTransaction().commit();
        List queryId =session.createQuery("select collectionId from MyCollection collection where collection.customerId = "+ myCollection.getCustomerId() +" and collection.cardId = "+myCollection.getCardId()+"").list();
        int deleteId = (int) queryId.get(0);

        myCollection.setCollectionId(deleteId);

        System.out.println(myCollection.getCollectionId());

        session.delete(myCollection);

        session.flush();
        session.close();

        sessionFactory.close();

    }

    public List getCard () {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List getCardResponse;
        session.beginTransaction();
        MyCollection myCollection = new MyCollection();
        myCollection.setCustomerId(collectionRequest.getCustomerId());
        myCollection.setCardId(collectionRequest.getCardId());
        getCardResponse = session.createQuery( "from MyCollection collection where collection.customerId = "+ myCollection.getCustomerId() +" and collection.cardId = "+myCollection.getCardId()+"").list();
        session.close();
        sessionFactory.close();
        return getCardResponse;
    }
}
