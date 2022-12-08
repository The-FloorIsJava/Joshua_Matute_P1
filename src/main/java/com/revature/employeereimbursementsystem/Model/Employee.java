package com.revature.employeereimbursementsystem.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import java.util.Objects;

public class Employee {
    /*
    This is the model for the Employee.

    created the variables for the columns related to my SQL table.
    */
    private String employeeUsername;
    private String employeeEmail;
    private boolean employeeRole;
    @JsonAlias(value = {"pass", "paSsWorD"})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Rank rank = Rank.valueOf("ASSOCIATE");

    public Employee (){

    }
        public Employee(String employeeUsername, String employeeEmail, String password, boolean employeeRole)
        {
            this.employeeUsername = employeeUsername;
            this.employeeEmail = employeeEmail;
            this.employeeRole = employeeRole;
            this.password = password;
        }
        public Employee(String employeeUsername, String employeeEmail, String rank) {
        this.employeeUsername = employeeUsername;
        this.employeeEmail = employeeEmail;
        this.rank = Rank.valueOf(rank.toUpperCase());
        }



        /*
        Used getters and setters to make the employees data more secure.
        Getters are used to return the correct data type information provided by the employee.
        Setters are used to update the value of the Getters which we can use inside my controllers.
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

        public String getRank(){
            return rank.toString();
        }
        public void setRank(String rank) {
            this.rank = Rank.valueOf(rank.toUpperCase());
        }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeUsername=" + employeeUsername +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", rank=" + rank +
                '}';
    }

    @Override
    public int hashCode(){
            return Objects.hash(employeeUsername,employeeEmail,password,rank);
    }
    private enum  Rank {
            NEWBIE,
            ASSOCIATE,
            SUPERVISOR,
            MANAGER
    }

}
