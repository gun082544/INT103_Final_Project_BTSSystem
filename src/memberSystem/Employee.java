package memberSystem;

public class Employee extends Person {

    private long employee_id;
    private String password;
    private Person person;

    public Employee(long employee_id, String password, String firstname, String lastname, int c_id, int phone) {
        super(firstname, lastname, c_id, phone);
        this.employee_id = employee_id;
        this.password = password;
    }

    public String getPassword() {
        return password;
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

    @Override
    public String toString() {
        return "Employee{" + "employee_id=" + employee_id + ", password=" + password + ", person=" + person + '}';
    }

}
