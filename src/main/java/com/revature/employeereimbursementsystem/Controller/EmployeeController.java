package com.revature.employeereimbursementsystem.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.Model.Employee;
import io.javalin.Javalin;
import com.revature.employeereimbursementsystem.Service.EmployeeService;
import com.revature.employeereimbursementsystem.Util.DTO.LoginCredentials;
import io.javalin.http.Context;

import java.util.List;

public class EmployeeController {

        EmployeeService employeeService;
        Javalin app;
        public EmployeeController(Javalin app){
            employeeService = new EmployeeService((new EmployeeDAO()));
            this.app = app;
        }
        public void employeeEndpoint() {

        app.post("employee", this::getAllPostEmployeeHandler);
        app.get("employee_email", this::getSpecificEmployeeHandler);
        app.post("login", this::loginHandler);
        app.delete("logout", this::logoutHandler);
        }

    private void logoutHandler(Context context) {
            int employeeID = employeeService.getSessionEmployee().getEmployee_id();
            employeeService.logout();
            context.json(employeeID + " has logged out");
    }


    private void loginHandler(Context context) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Employee employee = mapper.readValue(context.body(), Employee.class);
    }

    private void getSpecificEmployeeHandler(Context context) {
            String employeeEmail = context.pathParam("employee_email");
            Employee employee = employeeService.getSessionEmployee();
            context.json(employee);
    }

    private void getAllPostEmployeeHandler(Context context) {
            List<Employee> allEmployees = employeeService.getAllEmployees();
    }




}

