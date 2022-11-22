package com.revature.employeereimbursementsystem.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.Model.Employee;
import com.revature.employeereimbursementsystem.Model.Ticket;
import io.javalin.Javalin;
import com.revature.employeereimbursementsystem.Service.EmployeeService;
import com.revature.employeereimbursementsystem.Util.DTO.LoginCredentials;
import io.javalin.http.Context;

import java.util.List;

public class EmployeeController {

    EmployeeService employeeService;
    Javalin app;

    public EmployeeController(Javalin app) {
        employeeService = new EmployeeService((new EmployeeDAO()));
        this.app = app;
    }

    public void employeeEndpoint() {

        app.post("register", this::registerHandler);
        app.post("login", this::loginHandler);
        app.post("submit ticket", this::submitHandler);
        app.delete("logout", this::logoutHandler);
    }


    private void registerHandler(Context context) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Employee employee = mapper.readValue(context.body(), Employee.class);
        employee = employeeService.registerEmployee(employee);
        if (employee == null) {
            context.json("This email address is already in use. Please register with another email address.");
        } else {
            context.json(employee);
        }
    }

    private void loginHandler(Context context) throws JsonProcessingException{

        ObjectMapper mapper = new ObjectMapper();
        LoginCredentials loginCreds = mapper.readValue(context.body(), LoginCredentials.class);
        employeeService.login(loginCreds.getEmployee_email(), loginCreds.getPassword());
        context.json("Login Successful. Welcome.");

    }

    private void logoutHandler(Context context) {
        String employeeEmail = employeeService.getSessionEmployee().getEmployeeEmail();
        employeeService.logout();
        context.json(employeeEmail + " has logged out.");
    }


    private void submitHandler(Context context) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket = mapper.readValue(context.body(), Ticket.class);
        context.json(ticket);

    }


}




