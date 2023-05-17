package ru.did.burgers.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public final class ExceptionUtils {
	private ExceptionUtils() {}

	public static ResponseStatusException conflict(String message) {
		return ex(HttpStatus.CONFLICT, message);
	}

	public static ResponseStatusException badRequest(String message) {
		return ex(HttpStatus.BAD_REQUEST, message);
	}

	public static ResponseStatusException notFound(String message) {
		return ex(HttpStatus.NOT_FOUND, message);
	}

	public static ResponseStatusException ex(HttpStatus status, String message) {
		return new ResponseStatusException(status, message);
	}
}