package com.revature.employeereimbursementsystem.Util.DTO;

// this is the data transfer object that is handling the users login credentials


public class LoginCredentials {

        private int employeeID;
        private String password;

        public int getEmployeeID() {
            return employeeID;
        }

        public void setEmployeeID(int employeeID) {
            this.employeeID = employeeID;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

}
