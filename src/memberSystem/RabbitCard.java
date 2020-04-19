
package memberSystem;


public class RabbitCard {
    
    private long idCard;
    private Person person;
    private int money;
    private int point;
    private CardStatus cardStatus;
    private CardType cardType;

    public RabbitCard(long idCard, Person person) {
        this.idCard = idCard;
        this.person = person;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public long getIdCard() {
        return idCard;
    }


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Person getPerson() {
        return person;
    }


    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public RabbitCard(long idCard, Person person, int money, int point, CardStatus cardStatus, CardType cardType) {
        this.idCard = idCard;
        this.person = person;
        this.money = money;
        this.point = point;
        this.cardStatus = cardStatus;
        this.cardType = cardType;
    }
    
    
}
