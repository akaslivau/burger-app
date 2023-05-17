package ru.did.burgers.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

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

	/**
	 * Выкидывает ошибку при выполнении условия.
	 */
	public static <T extends Throwable> void iifThrow(
			boolean val, Supplier<? extends T> exceptionSupplier) throws T {
		if (val) {
			throw exceptionSupplier.get();
		}
	}

	/**
	 * Выкидывает ошибку если объект == null.
	 */
	public static <T extends Throwable> void throwIfNull(
			Object o, Supplier<? extends T> exceptionSupplier) throws T {
		if (o == null) {
			throw exceptionSupplier.get();
		}
	}
}