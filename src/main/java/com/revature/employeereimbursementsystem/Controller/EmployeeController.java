package com.revature.employeereimbursementsystem.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.Model.Employee;
import io.javalin.Javalin;
import com.revature.employeereimbursementsystem.Service.EmployeeService;
import com.revature.employeereimbursementsystem.Util.DTO.LoginCredentials;
import javax.naming.Context;

public class EmployeeController {

        EmployeeService employeeService;
        Javalin app;
        public EmployeeController(Javalin app){
             employeeService = new EmployeeService(new EmployeeDAO());
            this.app = app;
        }
    public void employeeEndpoint() {
        app.post("Hi", this::postEmployeeHandler);

    }

    private void postEmployeeHandler(Context context) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Employee employee = mapper.readValue(context.body(), Employee.class);
            content.json(employee);
    }
            /*
        app.[http verb]([url endpoint after localhost:8080], this::[handler method]);
        http verbs:
        get (retrieve some representations)
        post (persist some representations that is contained within a body)
        put (update a model representation)
        patch (update a part of a representation)
        delete (delete some representation)
        url endpoint: ex, localhost:8080/endpoint
        handler method: a method we write in this class which will be passed the Javalin context for us to use,
        which can hold information about the web request that was made, and can also generate a response.
         */



}

