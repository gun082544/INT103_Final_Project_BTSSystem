package memberSystem;

public class Employee {

    private long employee_id;
    private String password;
    private Person person;

    public Employee(Employee employee) {
        this.employee_id = employee.employee_id;
        this.password = employee.password;
    }

    public Employee(long employee_id, Person person) {
        this.employee_id = employee_id;
        this.person = person;
    }

    public long getEmployee_id() {
        return employee_id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean resetPassword() {
        this.password = null;
        return true;
    }
    
    
}
