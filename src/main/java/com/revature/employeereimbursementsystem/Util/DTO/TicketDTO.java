package com.revature.employeereimbursementsystem.Util.DTO;

public class TicketDTO {

    private double ticket_id;

    private String status;

    public TicketDTO() {}

    public TicketDTO(double ticket_id, String status) {
        this.ticket_id = ticket_id;
        this.status = status;
    }
    public double getTicket_id() {
        return ticket_id;
    }
    public void setTicket_id(double ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
