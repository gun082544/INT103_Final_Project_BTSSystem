package memberSystem;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {

    private String firstname;
    private String lastname;
    private long c_id;
    private long phone;

    public Person(String firstname, String lastname, long c_id, long phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.c_id = c_id;
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public long getC_id() {
        return c_id;
    }

    public long getPhone() {
        return phone;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setC_id(long c_id) {
        this.c_id = c_id;
    }

    public void setPhone(long phone) {
        this.phone = phone;
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
        final Person other = (Person) obj;
        if (this.c_id != other.c_id) {
            return false;
        }
        if (this.phone != other.phone) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Firstname : " + firstname + "  Lastname : " + lastname + "\nID Card : " + c_id + "\nTel. : " + phone ;
    }
    
}
