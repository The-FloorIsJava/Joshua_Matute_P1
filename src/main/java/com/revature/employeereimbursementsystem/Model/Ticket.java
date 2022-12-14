package com.revature.employeereimbursementsystem.Model;

public class Ticket {
    private double amount;
    private double ticketID;
    private String ticketType;
    private String status;
    private String requester;


    public Ticket() {};

    public Ticket(double amount, double ticketID, String ticketType, String status, String requester) {
        this.amount = amount;
        this.ticketID = ticketID;
        this.ticketType = ticketType;
        this.status = status;
        this.requester = requester;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTicketID() {
        return ticketID;
    }
    public void setTicketID(double ticketID){
        this.ticketID = ticketID;
    }

    public String getTicketType(){
        return ticketType;
    }
    public void setTicketType(String ticketType){
        this.ticketType = ticketType;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequester() {
        return requester;
    }
    public void setRequester(String requester) {
        this.requester = requester;
    }

}
