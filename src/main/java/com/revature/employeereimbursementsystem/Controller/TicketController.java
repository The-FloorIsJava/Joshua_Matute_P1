package com.revature.employeereimbursementsystem.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.DAO.TicketDAO;
import com.revature.employeereimbursementsystem.Model.Employee;
import com.revature.employeereimbursementsystem.Model.Ticket;
import com.revature.employeereimbursementsystem.Service.EmployeeService;
import com.revature.employeereimbursementsystem.Service.TicketService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.eclipse.jetty.util.DateCache;

import java.util.List;

public class TicketController {

    EmployeeService employeeService;
    Javalin app;

    public TicketController(Javalin app, EmployeeService employeeService) {

        this.employeeService = employeeService;
        this.app = app;

    }

    public void employeeEndpoint() {

        app.get("EmployeeTicket", this::viewEmployeeTicketsHandler);
        app.post("ApproveTicket", this::approveTicket);
        app.post("DenyTicket", this::denyTicket);
        app.get("PendingTicket", this::viewPendingTicketsHandler);
        app.get("ApprovedEmployeeTickets", this::viewApprovedEmployeeTickets);
        app.get("DeniedEmployeeTickets", this::viewDeniedEmployeeTickets);

    }



    private void viewEmployeeTicketsHandler(Context context) {
        if (employeeService.getSessionEmployee() != null) {
            Employee employee = employeeService.getSessionEmployee();
            List<Ticket> employeeTicket = employeeService.employeeTickets(employee);
            if (employeeTicket != null) {
                context.json(employeeTicket);
            } else {
                context.json("Error. Reimbursement ticket retrieval error. Try again.");
            }
        } else {
            context.json("You must log in to view your reimbursement tickets.");
        }
    }

    private void viewApprovedEmployeeTickets(Context context) {
        if (employeeService.getSessionEmployee() != null) {
            Employee employee = employeeService.getSessionEmployee();
            List<Ticket> employeeTickets = employeeService.approvedEmployeeTickets(employee);
            if(employeeTickets != null) {
                context.json(employeeTickets);
            } else {
                context.json("Error retrieving your approved reimbursement tickets.");
            }
        } else {
            context.json("Please log in to your account to view your approved reimbursement tickets.");
        }
    }

    private void viewDeniedEmployeeTickets(Context context) {
        if (employeeService.getSessionEmployee() != null) {
            Employee employee = employeeService.getSessionEmployee();
            List<Ticket> employeeTickets = employeeService.deniedEmployeeTickets(employee);
            if(employeeTickets != null) {
                context.json(employeeTickets);
            } else {
                context.json("Error retrieving your denied reimbursement tickets.");
            }
        } else {
            context.json("Please log in to your account to view your denied reimbursement tickets.");
        }
    }




    private void viewPendingTicketsHandler(Context context) {
        if (employeeService.getSessionEmployee() != null) {
            Employee employee = employeeService.getSessionEmployee();
            if (employee.employeeRole()) {
                List<Ticket> pendingTickets = employeeService.viewPendingEmployeeTickets(employee);
                context.json(pendingTickets);
            } else {
                context.json("Error. Unable to retrieve a pending reimbursement ticket");
            }
        } else {
            context.json("Please log in.");
        }
    }

    private void denyTicket(Context context) throws JsonProcessingException {
            try {
                if (employeeService.getSessionEmployee() != null) {
                    Employee employee = employeeService.getSessionEmployee();
                    if(employee.employeeRole()) {
                        ObjectMapper mapper = new ObjectMapper();
                        Ticket ticket = mapper.readValue(context.body(), Ticket.class);
                        boolean approved = employeeService.denyTicket(ticket);
                        if (approved) {
                            context.json("Reimbursement ticket has been denied.");
                        }
                    } else {
                        context.json("You must be logged in as a manager in order to deny reimbursement tickets.");
                }
            } else {
                    context.json("Error while attempting to deny reimbursement ticket, try again.");
                }
        } catch (RuntimeException r) {
                r.printStackTrace();
                context.json("This ticket does not exist in this system.");
    }
}

    private void approveTicket(Context context) throws JsonProcessingException {
        try {
            if(employeeService.getSessionEmployee() != null) {
                Employee employee = employeeService.getSessionEmployee();
                if (employee.employeeRole()) {
                    ObjectMapper mapper = new ObjectMapper();
                    Ticket ticket = mapper.readValue(context.body(), Ticket.class);
                    boolean approved = employeeService.approveTicket(ticket);
                    if (approved) {
                        context.json("You have approved this reimbursement ticket.");
                    }
                } else {
                    context.json("You must be logged in as a manager in order to approve reimbursement tickets.");
                }
            } else {
                context.json("Error approving reimbursement ticket, try again.");
            }
        } catch (RuntimeException r) {
            r.printStackTrace();
            context.json("This reimbursement ticket does not exist.");
        }
    }

}
