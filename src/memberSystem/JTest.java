
package memberSystem;

import static memberSystem.InputOutputEmployee.ReadEmployee;
import static memberSystem.InputOutputEmployee.WriteEmployee;


public class JTest {
    public static void main(String[] args) {
        Person p1=new Person("JJ","Montika","1102170018970","0806849641");
        Person person1[]={p1};
        Person p2=new Person("Leng","Loha","11015101846540","0946585541");
        Person person2[]={p2};
        Person p3=new Person("Gun","Chalo","11016108963240","0889688885");
        Person person3[]={p3};
        
        Employee em1 = new Employee("kakajj","jjlove555",person1);
        WriteEmployee(em1);
        Employee em2 = new Employee("leng101","lenglovetiny",person2);
        WriteEmployee(em2);
        Employee em3 = new Employee("gungun","gunpunnapop",person3);
        WriteEmployee(em3);
      
        ReadEmployee();
    }
}
