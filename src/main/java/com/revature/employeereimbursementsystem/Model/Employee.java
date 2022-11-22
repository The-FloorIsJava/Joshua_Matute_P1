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
    @JsonAlias(value = {"pass", "PaSSWorD"})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    public Employee() {}

        public Employee(String employeeUsername, String employeeEmail, String password, boolean employeeRole)
        {
            this.employeeUsername = employeeUsername;
            this.employeeEmail = employeeEmail;
            this.employeeRole = false;
            this.password = password;

        }

        /*
        Used getters and setters to make the employees data more secure.
        Getters are used to return the correct data type information provided by the employee.
        Setters are used to update the value of the Getters.
         */


        //employeeID getters & setters
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
        public void employeeRole(boolean aDefault){
            if (employeeRole) this.employeeRole = true;
            else this.employeeRole = false;
        }

        //password getters & setters
        public String getPassword(){
        return password;
        }
        public void setPassword(String employee_pwd){
            this.password = password;
        }

}
