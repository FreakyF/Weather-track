package org.weathertrack.api.service.exception;

public class BadRequestException extends Exception {
	public BadRequestException(String message) {
		super(message);
	}
}
