package memberSystem;

import java.io.Serializable;

public class Employee implements Comparable<Employee>, Serializable {

    private String employee_id;
    private String password;
    private Person persons;

    public Employee(String employee_id, String password, Person persons) {
        this.employee_id = employee_id;
        this.password = password;
        this.persons = persons;
    }

    public String getPassword() {
        return password;
    }

    public String getEmployee_id() {
        return employee_id;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean resetPassword() {
        this.password = null;
        return true;
    }

    public Person getPersons() {
        return persons;
    }
    
    @Override
    public String toString() {
        return "Employee{" + "employee_id=" + employee_id + ", password=" + password + ", persons=" + persons + '}';
    }
    

    @Override
    public int compareTo(Employee o) {
        return this.employee_id.compareTo(o.employee_id);
    }

}
