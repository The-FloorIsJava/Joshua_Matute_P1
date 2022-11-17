import com.revature.employeereimbursementsystem.Controller.EmployeeController;
import io.javalin.Javalin;

public class Application {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8080);

        new EmployeeController(app).employeeEndpoint();




    }
}
