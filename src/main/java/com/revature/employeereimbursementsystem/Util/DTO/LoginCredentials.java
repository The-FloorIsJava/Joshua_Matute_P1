package com.revature.employeereimbursementsystem.Util.DTO;

// this is the data transfer object that is handling the users login credentials


public class LoginCredentials {

        private int employee_id;
        private String password;

        public int getEmployee_id() {
            return employee_id;
        }

        public void setEmployee_id(int employee_id) {
            this.employee_id = employee_id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

}
