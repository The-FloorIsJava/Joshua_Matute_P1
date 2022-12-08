package com.revature.employeereimbursementsystem.Util.DTO;

// this is the data transfer object that is handling the users login credentials


public class LoginCredentials {

        private String employeeUsername;
        private String password;

        public String getEmployeeUsername() {
                return employeeUsername;
        }
        public void setEmployeeUsername(String employeeUsername) {
                this.employeeUsername = employeeUsername;
        }

        public String getPassword() {
                return password;
        }
        public void setPassword(String password) {
                this.password = password;
        }


}
