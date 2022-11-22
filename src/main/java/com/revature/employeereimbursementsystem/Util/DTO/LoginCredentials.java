package com.revature.employeereimbursementsystem.Util.DTO;

// this is the data transfer object that is handling the users login credentials


public class LoginCredentials {

        private String employee_email;
        private String password;

        public String getEmployee_email() {
            return employee_email;
        }

        public void setEmployee_email(String employee_email) {
            this.employee_email = employee_email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

}
