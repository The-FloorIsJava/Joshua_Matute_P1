package com.revature.employeereimbursementsystem.Util.Exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Session time expired. Please log in again." + "");
    }
}
