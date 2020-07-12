package ru.wowtcgdatabase.DAO;

import org.hibernate.Session;
import ru.wowtcgdatabase.SessionCreator;
import ru.wowtcgdatabase.controller.CollectionRequest;
import ru.wowtcgdatabase.model.MyCollection;

import java.util.List;

public class CollectionDAO {

    private CollectionRequest collectionRequest;

    public CollectionDAO(CollectionRequest collectionRequest) {
        this.collectionRequest = collectionRequest;
    }

    public void addCard () {
        try (Session session = SessionCreator.getSession()) {
            session.beginTransaction();

            MyCollection myCollection = new MyCollection();
            myCollection.setCustomerId(collectionRequest.getCustomerId());
            myCollection.setCardId(collectionRequest.getCardId());

            session.save(myCollection);
            session.getTransaction().commit();
        }
    }

    public void deleteCard () {

        try (Session session = SessionCreator.getSession()) {
            session.beginTransaction();

            MyCollection myCollection = new MyCollection();
            myCollection.setCustomerId(collectionRequest.getCustomerId());
            myCollection.setCardId(collectionRequest.getCardId());

            List queryId = session.createQuery("select collectionId from MyCollection collection where collection.customerId = " + myCollection.getCustomerId() + " and collection.cardId = " + myCollection.getCardId() + "").list();
            int deleteId = (int) queryId.get(0);
            myCollection.setCollectionId(deleteId);

            session.delete(myCollection);
            session.flush();
            session.getTransaction().commit();
        }
    }
    public List getCard () {
        try (Session session = SessionCreator.getSession()) {
            List getCardResponse;
            session.beginTransaction();

            MyCollection myCollection = new MyCollection();
            myCollection.setCustomerId(collectionRequest.getCustomerId());
            myCollection.setCardId(collectionRequest.getCardId());

            getCardResponse = session.createQuery("from MyCollection collection where collection.customerId = " + myCollection.getCustomerId() + " and collection.cardId = " + myCollection.getCardId() + "").list();

            return getCardResponse;
        }
    }
}
