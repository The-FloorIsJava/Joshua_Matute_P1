import com.revature.employeereimbursementsystem.Controller.EmployeeController;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.Service.EmployeeService;
import io.javalin.Javalin;

public class Application {
    public static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAO();
        //add in request
        EmployeeService employeeService = new EmployeeService((employeeDAO));

        Javalin app = Javalin.create().start(8080);

        new EmployeeController(app).employeeEndpoint();




    }
}
