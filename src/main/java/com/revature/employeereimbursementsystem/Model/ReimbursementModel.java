package com.revature.employeereimbursementsystem.Model;


public class ReimbursementModel {

    //Private instance variables that will be used as getters and setters.
    private double ticketID;
    private String requester;
    private double amount;
    private String requestType;
    private String status;


    public ReimbursementModel(double ticketID, double amount){

        this.ticketID = ticketID;
        this.requester = requester;
        this.amount = amount;
        this.requestType = requestType;
        this.status = status;
    }

    public double getTicketID() {
        return ticketID;
    }
    public void setTicketID(double ticketID) {
        this.ticketID = ticketID;
    }

    public String getRequester() {
        return requester;
    }
    public void setRequester(String requester) {
        this.requester = requester;
    }

    public double getAmount(){
        return amount;
    }
    public void  setAmount(double amount) {
        this.amount = amount;
    }

    public String getRequestType() {
        return requestType;
    }
    public void setRequestType(String requestType){
        this.requestType = requestType;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

}
