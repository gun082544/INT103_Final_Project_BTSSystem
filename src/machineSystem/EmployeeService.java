package machineSystem;

import memberSystem.RabbitCard;

public interface EmployeeService {
    public abstract void addRabbitCard(String id, String pass, RabbitCard r1);
    public abstract void RedeemPoint(String id, String pass, long id_card, int money);
}
