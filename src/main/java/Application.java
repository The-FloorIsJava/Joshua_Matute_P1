import com.revature.employeereimbursementsystem.Controller.EmployeeController;
import com.revature.employeereimbursementsystem.Controller.TicketController;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.DAO.TicketDAO;
import com.revature.employeereimbursementsystem.Service.EmployeeService;
import com.revature.employeereimbursementsystem.Service.TicketService;
import io.javalin.Javalin;

public class Application {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8080);


        EmployeeService employeeService = new EmployeeService(new EmployeeDAO(), new TicketDAO());



        new EmployeeController(app, employeeService).employeeEndpoint();
        new TicketController(app, employeeService).employeeEndpoint();



    }
}
