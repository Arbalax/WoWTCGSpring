package ru.wowtcgdatabase.model;

import javax.persistence.*;

@Entity (name = "CardWow")
@Table (name = "cardswow")
public class Card {

    @Id
//    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "CardID")
    private int cardId;

    @Column (name = "Name")
    private String cardName;

    @Column (name = "ImageURL")
    private String imageUrl;

    @Column (name = "ImageFullUrl")
    private String imageFullUrl;

    @Column (name = "SetName")
    private String setName;

    @Column (name = "NumberInSet")
    private int numberInSet;

    @Column (name = "Rarity")
    private String rarity;

    @Column (name = "Type")
    private String type;

    @Column (name = "Faction")
    private String faction;

    @Column (name = "Class")
    private String cardClass;

    @Column (name = "Race")
    private String race;

    @Column (name = "Cost")
    private int cost;

    @Column (name = "Health")
    private int health;

    @Column (name = "Attack")
    private int attack;

    @Column (name = "StrikeCost")
    private int strikeCost;

    @Column (name = "AttackType")
    private String attackType;

    @Column (name = "Defence")
    private int defence;

    @Column (name = "AllyClass")
    private String allyClass;

    public Card() {
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageFullUrl() {
        return imageFullUrl;
    }

    public void setImageFullUrl(String imageFullUrl) {
        this.imageFullUrl = imageFullUrl;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public int getNumberInSet() {
        return numberInSet;
    }

    public void setNumberInSet(int numberInSet) {
        this.numberInSet = numberInSet;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getCardClass() {
        return cardClass;
    }

    public void setCardClass(String cardClass) {
        this.cardClass = cardClass;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getStrikeCost() {
        return strikeCost;
    }

    public void setStrikeCost(int strikeCost) {
        this.strikeCost = strikeCost;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public String getAllyClass() {
        return allyClass;
    }

    public void setAllyClass(String allyClass) {
        this.allyClass = allyClass;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", cardName='" + cardName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageFullUrl='" + imageFullUrl + '\'' +
                ", setName='" + setName + '\'' +
                ", numberInSet=" + numberInSet +
                ", rarity='" + rarity + '\'' +
                ", type='" + type + '\'' +
                ", faction='" + faction + '\'' +
                ", cardClass='" + cardClass + '\'' +
                ", race='" + race + '\'' +
                ", cost=" + cost +
                ", health=" + health +
                ", attack=" + attack +
                ", strikeCost=" + strikeCost +
                ", attackType='" + attackType + '\'' +
                ", defence=" + defence +
                ", allyClass='" + allyClass + '\'' +
                '}';
    }


}
