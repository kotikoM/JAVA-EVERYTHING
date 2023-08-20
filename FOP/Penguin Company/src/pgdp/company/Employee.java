package pgdp.company;

public class Employee {
    private String name;
    private int ID;
    private Employee boss;

    public Employee(String name, Employee boss) {
        this.name = name;
        this.boss = boss;
    }
    public String getName() {
        return this.name;
    }
    public Employee getBoss() {
        return this.boss;
    }
    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        //special case if we are trying toString on CEO
        if (boss==null)
            return "Ceo{"+
                    "name='"+name+'\''+
                    ", ID=" + ID +
                    '}';
        return "Employee{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                ", bossID=" + boss.ID +
                '}';
    }
}
