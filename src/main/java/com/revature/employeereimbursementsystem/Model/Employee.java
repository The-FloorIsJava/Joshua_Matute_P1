package com.revature.employeereimbursementsystem.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Employee {

    /*
    This is the model for the Employee.

    created the variables for the columns of my SQL table. I created them private because it is employee's private
    information and to keep it in the same class.
    */

    private int employeeID;
    private String employeeEmail;
    private boolean isManager;
    @JsonAlias(value = {"pass", "PaSSWorD"})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    public Employee() {}

        public Employee(int employeeID, String employeeEmail, String password, boolean isManager)
        {
            this.employeeID = employeeID;
            this.employeeEmail = employeeEmail;
            this.isManager = false;
            this.password = password;

        }

        /*
        Used getters and setters to make the employees data more secure.
        Getters are used to return the correct data type information provided by the employee.
        Setters are used to update the value of the Getters.
         */


        //employeeID getters & setters
        public int getEmployeeID() {
        return employeeID;
        }
        public void setEmployeeID(int employeeID){
        this.employeeID = employeeID;
        }

        //employeeEmail getters & setters
        public String getEmployeeEmail(){
        return employeeEmail;
        }
        public void setEmployeeEmail(){
        this.employeeEmail = employeeEmail;
        }

        //employeeRole getters & setters
        public boolean getisManager(){
        return isManager;
        }
        public void setIsManager(){
            this.isManager = isManager;
        }

        //password getters & setters
        public String getPassword(){
        return password;
        }
        public void setPassword(){
            this.password = password;
        }

}
