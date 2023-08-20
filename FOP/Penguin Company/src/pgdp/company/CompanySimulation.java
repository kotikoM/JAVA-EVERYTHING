package pgdp.company;

import java.io.IOException;
import java.util.Scanner;

public class CompanySimulation {
    public static void main(String[] args) {
        simulation("Novak","ATP");

    }

    private static void simulation(String ceoName, String companyName) {
        Employee ceo = new Employee(ceoName,null);
        ceo.setID(0);
        Company company = new Company(companyName,ceo);

        System.out.println("How many commands to execute?");
        Scanner scanner = new Scanner(System.in);
        String numb = scanner.nextLine();

        for (int k = 0; k < Integer.parseInt(numb.trim()); k++) {
            String numberOfQueries = scanner.nextLine();
        if (numberOfQueries.contains("EmployeeWith")) {
            try {
                numberOfQueries = numberOfQueries.substring(numberOfQueries.indexOf("h")+1).strip();
                int ID = Integer.parseInt(numberOfQueries);
                Employee emp = company.findByID(ID);
                if (emp == null)
                    System.out.println("Employee is not in the company");
                else System.out.println(emp.toString());
        }
            catch (Exception e){
                System.out.println("Error! Please input correct string");
            }


            //EmployeeWithID ID - If the string has the following form, then employee with given
            // ID should be printed on to the console. If employee is not present in the company,
            // an appropriate error message should be displayed;
        }
        else if (numberOfQueries.contains("Add")){
            int ID=0;
            String name="test";
            try{
                numberOfQueries=numberOfQueries.substring(numberOfQueries.indexOf("d")+2).strip();
                String [] arr=numberOfQueries.split("\s");
                if (arr.length!=2){
                    throw new Exception ();
                }
                else {
                    name=arr[0];
                    ID=Integer.parseInt(arr[1]);
                    Employee boss = company.findByID(ID);
                    if (boss == null) {
                        System.out.println("Boss does not Exist");
                    } else {
                        Employee emp = new Employee(name, boss);
                        company.addEmployee(emp);
                        System.out.println("Employee has been added");
                    }
                }
            }
            catch (Exception e){
                    System.out.println("Error! Please input correct string");
            }


            //Add name ID - If the string has the following form,
            // then we should check if the employee in the tree exists with given ID.
            // If employee is not present, we should print corresponding message that boss doesn’t exist,
            // otherwise add a new employee with given name as their subordinate.
        }
        else if (numberOfQueries.contains("Fire")){
            int ID=0;
            try {
                ID = Integer.parseInt(numberOfQueries.substring(numberOfQueries.indexOf("e")+1).trim());
               if (company.getEmployeesTree().containsKey(ID)){
                   company.fireEmployee(ID);
                   System.out.println("Employee has been fired!");
               }
               else System.out.println("Employee with the give ID does not work here");
            }
            catch (Exception e){
                System.out.println("Please Enter Valid Input");
            }


            //Fire id - If the employee with given ID doesn’t exist, we should print the corresponding message, otherwise fire the employee.
        }
        else if (numberOfQueries.contains("FindCommonBoss")){
            try {
                numberOfQueries = numberOfQueries.substring(numberOfQueries.indexOf("s") + 2).strip();
                String[] temp = numberOfQueries.split("\s");
                if (temp.length!=2){
                    throw new Exception ("Please Enter Valid String");
                }
                else {
                    int ID1=Integer.parseInt(temp[0]);
                    int ID2=Integer.parseInt(temp[1]);
                    Employee boss=company.findByID(company.getEmployeesTree().LCA(ID1, ID2));
                    System.out.println("Lowest Common Boss is "+boss.toString());
                }
            }
            catch (Exception e){
                System.out.println("Please Enter Valid String");
            }



            //FindCommonBoss ID1 ID2 - If either ID1 or ID2 is not present in the company,
            // we should print the corresponding message, otherwise find lowest common boss of two employees.
            // If user decides to input string in any other form,
            // the corresponding error message should be printed. We can handle that by means of try/catch;
            // Important remark: You must not change signature in any of the declared methods.
        }

        }
        //company.getEmployeesTree().traversal();
        scanner.close();
    }
}
