package com.rperezv365.apifirst.apifirstserver.controllers;

import com.rperezv365.apifirst.apifirstserver.controllers.traits.ConstraintViolationExceptionTrait;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

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
public class ExceptionAdvice implements ConstraintViolationExceptionTrait, ProblemHandling {
}
