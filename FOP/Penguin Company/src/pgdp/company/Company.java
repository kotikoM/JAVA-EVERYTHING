package pgdp.company;
import pgdp.tree.Tree;

import java.util.*;

public class Company {

    private Employee CEO;
    private Tree<Integer> employeesTree;
    private Map<Integer,Employee> employees;
    private Queue<Integer> availableIDs;
    private static int availableID = 1;
    private String name;

    public Company(String name, Employee CEO) {
        this.name = name;
        this.CEO = CEO;
        employeesTree = new Tree<>(0);
        availableIDs = new PriorityQueue<>();
        employees = new HashMap<>();
        employees.put(0,CEO);
    }

    public void addEmployee(Employee newEmployee) {
        if (employeesTree.containsKey(newEmployee.getBoss().getID())){
            if (availableIDs.isEmpty()){
                employees.put(availableID, newEmployee);
                employeesTree.insert(availableID, newEmployee.getBoss().getID());
                availableID++;
            }
            else  {
                //since avaliableIDs is queue we give employee topmost int
               employees.put(availableIDs.poll(), newEmployee);
            }
        }
        //addEmployee(Employee newEmployee) - If boss of this new employee is not present in the tree, then method should do nothing.
        // ID of new employee is determined and assigned in this method.
    }

    public void fireEmployee(int ID) {
        if (!employees.containsKey(ID)){
            System.out.println("Employee with the given ID does not work in the company");
        }
        else if (employees.containsKey(ID)) {
            if (CEO.getID()==ID){
                System.out.println("You are trying to remove CEO. Impossible!");
            }
            else {
                employeesTree.remove(ID);
                employees.remove(ID);
                availableIDs.add(ID);
            }
        }
       //fireEmployee(int ID) - the method should fire employee with the given ID from the company;
    }

    public Employee findCommonBoss(Employee e1, Employee e2) {
        return employees.get(employeesTree.LCA(e1.getID(), e2.getID()));
        // findCommonBoss(Employee e1, Employee e2) - This method should return the lowest common boss of the two employees;
    }

    public Employee findByID(int ID) {
        if (employees.containsKey(ID))
            return employees.get(ID);
        return null;
       // findByID(int ID) - This method returns the employee with given ID. Null if the employee is not present.
    }

    public void  addIDs(int... a){
        for (int i = 0; i < a.length; i++) {
            availableIDs.add(a[i]);
        }
    }
    public Tree<Integer> getEmployeesTree(){
        return employeesTree;
    }

    public Map<Integer,Employee> getEmployees(){return employees;}

}
