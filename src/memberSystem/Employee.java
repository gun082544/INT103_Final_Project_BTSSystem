package memberSystem;

public class Employee extends Person implements Comparable<Employee> {

    private int employee_id;
    private String password;
    private Person person;

    public Employee(Employee employee){
        this.employee_id = employee.employee_id;
        this.password = employee.password;
        this.person = employee.person;
    }
    public Employee(int employee_id, String password, String firstname, String lastname, String c_id, int phone,Person person) {
        super(firstname, lastname, c_id, phone);
        this.employee_id = employee_id;
        this.password = password;
        this.person = person;
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

    @Override
    public int compareTo(Employee o) {
        return this.person.compareTo(o.person);
    }

}
