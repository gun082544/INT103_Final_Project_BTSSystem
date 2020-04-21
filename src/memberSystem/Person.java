package memberSystem;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {

    private String firstname;
    private String lastname;
    private String c_id;
    private String phone;

    public Person(String firstname, String lastname, String c_id, String phone) {
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

    public String getC_id() {
        return c_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public void setPhone(String phone) {
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
        return "Person{" + "firstname=" + firstname + ", lastname=" + lastname + ", c_id=" + c_id + ", phone=" + phone + '}';
    }
    
}
