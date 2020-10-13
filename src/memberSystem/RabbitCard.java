package memberSystem;

import java.util.Comparator;
import java.util.Objects;

public class RabbitCard implements Comparator <Person> {

    private long rbc_idCard;
    private Person rbc_person;
    private int rbc_money;
    private int rbc_point;
    private CardStatus rbc_cardStatus;
    private CardType rbc_cardType;

    public RabbitCard(long rbc_idCard, Person rbc_person, int rbc_money, int rbc_point, CardStatus rbc_cardStatus, CardType rbc_cardType) {
        this.rbc_idCard = rbc_idCard;
        this.rbc_person = rbc_person;
        this.rbc_money = rbc_money;
        this.rbc_point = rbc_point;
        this.rbc_cardStatus = rbc_cardStatus;
        this.rbc_cardType = rbc_cardType;
    }

    public long getRbc_idCard() {
        return rbc_idCard;
    }

    public void setRbc_idCard(long rbc_idCard) {
        this.rbc_idCard = rbc_idCard;
    }

    public Person getRbc_person() {
        return rbc_person;
    }

    public void setRbc_person(Person rbc_person) {
        this.rbc_person = rbc_person;
    }

    public int getRbc_money() {
        return rbc_money;
    }

    public void setRbc_money(int rbc_money) {
        this.rbc_money = rbc_money;
    }

    public int getRbc_point() {
        return rbc_point;
    }

    public void setRbc_point(int rbc_point) {
        this.rbc_point = rbc_point;
    }

    public CardStatus getRbc_cardStatus() {
        return rbc_cardStatus;
    }

    public void setRbc_cardStatus(CardStatus rbc_cardStatus) {
        this.rbc_cardStatus = rbc_cardStatus;
    }

    public CardType getRbc_cardType() {
        return rbc_cardType;
    }

    public void setRbc_cardType(CardType rbc_cardType) {
        this.rbc_cardType = rbc_cardType;
    }
    public String getFirstname(){
        return rbc_person.getFirstname();
    }
    public String getLastName(){
        return rbc_person.getLastname();
    }
    public long getC_id(){
        return rbc_person.getC_id();
    }
    public long getPhone(){
        return rbc_person.getPhone();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RabbitCard other = (RabbitCard) obj;
        if (this.rbc_idCard != other.rbc_idCard) {
            return false;
        }
        if (this.rbc_money != other.rbc_money) {
            return false;
        }
        if (this.rbc_point != other.rbc_point) {
            return false;
        }
        if (!Objects.equals(this.rbc_person, other.rbc_person)) {
            return false;
        }
        if (this.rbc_cardStatus != other.rbc_cardStatus) {
            return false;
        }
        if (this.rbc_cardType != other.rbc_cardType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder r1 = new StringBuilder();
        r1.append("RabbitCard ID " + rbc_idCard+"\n");
        r1.append("Owner [ "+rbc_person+"]\n");
        r1.append("Money : "+rbc_money+", RabbitPoint : "+rbc_point+"\n");
        r1.append("Status : "+rbc_cardStatus+", TypeCard : "+rbc_cardType+"\n");
        r1.append("----------------------------------------------------");
        return r1.toString();
    }

    @Override
    public int compare(Person o1, Person o2) {
       return o1.getFirstname().compareTo(o2.getFirstname());
    }
}
