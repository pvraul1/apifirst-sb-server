package com.rperezv365.apifirst.apifirstserver.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ExceptionAdvice
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 20/01/2025 - 10:28
 * @since 1.17
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation") // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict() {
        log.error("Conflict");
    }

}
