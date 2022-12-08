import com.revature.employeereimbursementsystem.Controller.EmployeeController;
import com.revature.employeereimbursementsystem.Controller.TicketController;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.DAO.TicketDAO;
import com.revature.employeereimbursementsystem.Service.EmployeeService;
import com.revature.employeereimbursementsystem.Util.Tokens.JWT;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import io.javalin.Javalin;
import io.jsonwebtoken.Jwt;

public class Application {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config ->{
            config.plugins.enableCors(cors ->{
                cors.add(it -> {
                it.anyHost();
                });
            });
        }
        ).start(8080);

        JWT jwt = new JWT();
        EmployeeService employeeService = new EmployeeService(new EmployeeDAO(), new TicketDAO());

        new EmployeeController(app, employeeService, jwt).employeeEndpoint();
        new TicketController(app, employeeService).employeeEndpoint();



    }
}
