package com.rperezv365.apifirst.apifirstserver.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zalando.problem.AbstractThrowableProblem;

/**
 * NotFoundException
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 19/01/2025 - 19:38
 * @since 1.17
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class NotFoundException extends AbstractThrowableProblem {

    public NotFoundException(String message) {
        super(null, message, org.zalando.problem.Status.NOT_FOUND);
    }

    public NotFoundException() {
        super(null, "Requested Entity Not Found", org.zalando.problem.Status.NOT_FOUND);
    }

    public NotFoundException(String message, String detail) {
        super(null, message, org.zalando.problem.Status.NOT_FOUND, detail);
    }

}
