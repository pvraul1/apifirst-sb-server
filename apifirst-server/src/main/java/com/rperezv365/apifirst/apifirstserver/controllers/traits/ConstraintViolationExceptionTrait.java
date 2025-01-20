package com.rperezv365.apifirst.apifirstserver.controllers.traits;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.AdviceTrait;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * DataIntegrityViolationTrait
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 20/01/2025 - 16:44
 * @since 1.17
 */
public interface ConstraintViolationExceptionTrait extends AdviceTrait {

    @ExceptionHandler
    default ResponseEntity<Problem> handleDataIntegrityViolationException(final DataIntegrityViolationException e,
                                                                          final NativeWebRequest request) {
        return create(Status.CONFLICT, e, request);
    }

}
