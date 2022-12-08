package com.revature.employeereimbursementsystem.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.employeereimbursementsystem.Model.Employee;
import com.revature.employeereimbursementsystem.Model.Ticket;
import com.revature.employeereimbursementsystem.Util.Exceptions.InvalidEmployeeInputException;
import com.revature.employeereimbursementsystem.Service.EmployeeService;
import com.revature.employeereimbursementsystem.Util.DTO.LoginCredentials;
import com.revature.employeereimbursementsystem.Util.Tokens.JWT;

import io.javalin.http.Context;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import io.javalin.Javalin;


public class EmployeeController {

    // Takes http requests and guides them where they need to go
    // URI endpoints are created using handlers.
    // entry point for API
    // controllers map to models

    EmployeeService employeeService;
    Javalin app;
    JWT jwt;
//    private Logger logger = LogManager.getLogger();

    public EmployeeController(Javalin app, EmployeeService employeeService, JWT jwt) {
        this.employeeService =  employeeService;
        this.app = app;
        this.jwt = jwt;
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
            context.json("Username or email is already in use. Please register with an original username or email.");
        } else {
            context.json("You are now registered into the ERS. Welcome.");
        }
    }

    private void loginHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        LoginCredentials loginCredentials = mapper.readValue(context.body(), LoginCredentials.class);
        try{
            Employee employee = employeeService.login(loginCredentials.getEmployeeUsername(), loginCredentials.getPassword());
            String token = jwt.createToken(employee);
            context.header("Access-Control-Allow-Headers", "Authorization, Content-Type");
            context.header("Authorization", token);
            context.json("Login Successful.");
        } catch (InvalidEmployeeInputException e){
            context.status(404);
            context.json(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            context.status(500);
            context.json("We are working on this issue. Thank you for your patience.");
        }

//        Employee employee = mapper.readValue(context.body(), Employee.class);
//        int temp = employeeService.login(employee);
//        if (temp == 1) {
//            context.json("You are now logged in to the ERS. Welcome.");
//        } else if (temp == 2) {
//            context.json("Error. Please try logging in again.");
//        }
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

    private void getAllEmployeesHandler(Context context) {
        String authorization = context.header("Authorization");
        Employee authEmployee = jwt.extractTokenDetails(authorization);
//        logger.info("{} attempted to his the getAllEmployeesHandler");
        if(!String.valueOf(authEmployee.getRank()).equals("SUPRVISOR")){
            context.status(403);
            context.json("Unauthorized request - Must be a Supervisor.");
        } else {
            List<Employee> allEmployees = employeeService.getAllEmployees();
            context.header("Response Message", "Successfully retrieved all employees.");
            context.json(allEmployees);
        }
    }

}




