package com.epf.rentmanager.exception;

public class ServiceException extends Exception  {
	
    public ServiceException() {     
    }
    
	public ServiceException(String string) {
		super(string);
	}
}
