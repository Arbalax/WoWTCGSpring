package ru.wowtcgdatabase.model;

import javax.persistence.*;

@Entity (name = "MyCollection")
@Table (name = "collections")
public class MyCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Id")
    private int collectionId;

    @Column (name = "CustomerID")
    private int customerId;

    @Column (name = "CardID")
    private int cardId;

    public MyCollection() {
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
