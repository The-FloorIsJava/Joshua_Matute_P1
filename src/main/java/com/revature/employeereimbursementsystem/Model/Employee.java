package com.revature.employeereimbursementsystem.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

    /*
    This is the model for the Employee.

    created the variables for the columns of my SQL table. I created them private because it is employee's private
    information and to keep it in the same class.
    */

    private String employeeUsername;
    private String employeeEmail;
    private boolean employeeRole;
    private String password;
    public Employee() {}

        public Employee(String employeeUsername, String employeeEmail, String password, boolean employeeRole)
        {
            this.employeeUsername = employeeUsername;
            this.employeeEmail = employeeEmail;
            this.employeeRole = employeeRole;
            this.password = password;

        }

        /*
        Used getters and setters to make the employees data more secure.
        Getters are used to return the correct data type information provided by the employee.
        Setters are used to update the value of the Getters.
         */


        //employeeUsername getters & setters
        public String getEmployeeUsername() {
        return employeeUsername;
        }
        public void setEmployeeUsername(String employeeUsername){
        this.employeeUsername = employeeUsername;
        }

        //employeeEmail getters & setters
        public String getEmployeeEmail(){
        return employeeEmail;
        }
        public void setEmployeeEmail(String employeeEmail){
        this.employeeEmail = employeeEmail;
        }

        //employeeRole getters & setters
        public boolean employeeRole(){
        return employeeRole;
        }
        public void setEmployeeRole(boolean employeeRole){
            this.employeeRole =employeeRole;
        }

        //password getters & setters
        public String getPassword(){
        return password;
        }
        public void setPassword(String password){
            this.password = password;
        }

    @Override
    public String toString() {
        return "User [Username = " + employeeUsername + ", Password = " + password + ", Role =" + employeeRole + ", Manager ID =" + "]";
    }


}
