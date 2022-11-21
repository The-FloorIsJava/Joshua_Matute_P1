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

        app.get("Greetings", this::greetingsHandler);
        app.post("employee", this::getAllPostEmployeeHandler);
        app.get("employee/{employeeEmail}", this::getSpecificEmployeeHandler);
        app.post("login", this::loginHandler);
        app.delete("logout", this::logoutHandler);
        }

    private void logoutHandler(Context context) {
            int employeeID = employeeService.getSessionEmployee().getEmployeeID();
            employeeService.logout();
            context.json(employeeID + " has logged out");
    }

    private void loginHandler(Context context) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            LoginCredentials loginCreds = mapper.readValue(context.body(), LoginCredentials.class);
            employeeService.login(loginCreds.getEmployeeID(), loginCreds.getPassword());
            context.json("Successfully logged in.");
    }

    private void getSpecificEmployeeHandler(Context context) {
            String employeeEmail = context.pathParam("employeeEmail");
            Employee employee = employeeService.getSessionEmployee();
            context.json(employee);
    }

    private void getAllPostEmployeeHandler(Context context) {
            List<Employee> allEmployees = employeeService.getAllEmployees();
    }


    private void greetingsHandler(Context context) {
            context.result("Greetings");
    }


}

