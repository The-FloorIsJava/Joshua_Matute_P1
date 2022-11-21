package com.revature.employeereimbursementsystem.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.Model.Employee;
import io.javalin.Javalin;
import com.revature.employeereimbursementsystem.Service.EmployeeService;
import com.revature.employeereimbursementsystem.Util.DTO.LoginCredentials;
import javax.naming.Context;
import java.util.List;

public class EmployeeController {

        EmployeeService employeeService;
        Javalin app;
        public EmployeeController(Javalin app){
            employeeService = new EmployeeService((new EmployeeDAO()));
            this.app = app;
        }
        public void employeeEndpoint() {

            app.get("hello", this::helloHandler);
            app.post("employee", this::postEmployeeHandler);
        }

        private void getSpecificEmployeeHandler(Context context) {
            int employeeID = context.pathParam("employeeID");
            Employee employee = employeeService.getSessionEmployee();
            context.json(employee);
        }

        private void getAllEmployeeHandler(Context context){
            List<Employee> allEmployees = employeeService.getAllEmployees();
            context.json(allEmployess);
        }



}

