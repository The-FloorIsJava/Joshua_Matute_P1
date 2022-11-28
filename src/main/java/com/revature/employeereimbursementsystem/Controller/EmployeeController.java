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

    public EmployeeController(Javalin app, EmployeeService employeeService) {
        this.employeeService =  employeeService;
        this.app = app;
    }

    public void employeeEndpoint() {

        app.post("register", this::registerHandler);
        app.post("login", this::loginHandler);
        app.post("submitTicket", this::submitHandler);
        app.delete("logout", this::logoutHandler);
        app.get("allEmployees", this::getAllEmployeesHandler);
    }

    private void registerHandler(Context context) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Employee employee = mapper.readValue(context.body(), Employee.class);
        employee = employeeService.registerEmployee(employee);
        if (employee == null) {
            context.json("Username/email is already in use. Please register with an original username/email.");
        } else {
            context.json("You are now registered into the ERS. Welcome.");
        }
    }

    private void getAllEmployeesHandler(Context context) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        context.json(allEmployees);
    }


    private void loginHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        LoginCredentials loginCreds = mapper.readValue(context.body(), LoginCredentials.class);
        Employee employee = mapper.readValue(context.body(), Employee.class);
        int temp = employeeService.login(employee);
        if (temp == 1) {
            context.json("You are now logged in to the ERS. Welcome.");
        } else if (temp == 2) {
            context.json("Error. Please try logging in again.");
        }
    }

    private void logoutHandler(Context context) {
        String employeeUsername = employeeService.getSessionEmployee().getEmployeeUsername();
        employeeService.logout();
        context.json(employeeUsername + " has logged out.");
    }


    private void submitHandler(Context context) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket = mapper.readValue(context.body(), Ticket.class);
        context.json(ticket);

        double temp = employeeService.submitTicket(ticket);

        if (temp == 1) {
            context.json("Please log in to submit a request.");
        } else if (temp == 2) {
            context.json("Your reimbursement ticket has been submitted.");
        }

    }

}




