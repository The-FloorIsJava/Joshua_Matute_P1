package com.revature.employeereimbursementsystem.Controller;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import io.javalin.Javalin;
import com.revature.employeereimbursementsystem.Service.EmployeeService;

public class EmployeeController {

        EmployeeService employeeService;
        Javalin app;
        public EmployeeController(Javalin app){
             employeeService = new EmployeeService(new EmployeeDAO());
            this.app = app;
        }
    public void employeeEndpoint(){


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
}

