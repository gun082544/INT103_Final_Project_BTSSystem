package memberSystem;

import java.io.Serializable;
import static memberSystem.InputOutputEmployee.ReadEmployee;
import static memberSystem.InputOutputEmployee.WriteEmployee;

public class Employee extends Person implements Comparable<Employee>, Serializable {

    private String employee_id;
    private String password;
    private Person persons[];

    public Employee(String employee_id, String password, Person[] persons) {
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
    public Person[] getPerson(){
        return persons;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean resetPassword() {
        this.password = null;
        return true;
    }

    public boolean registerEmployee(Employee e) {
        if (compareTo(e) != 0) {
            WriteEmployee(e);
            return true;
        }
        System.out.println("Error : This employee already exist ");
        return false;
    }

    public void listEmployee() {
        ReadEmployee();
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
