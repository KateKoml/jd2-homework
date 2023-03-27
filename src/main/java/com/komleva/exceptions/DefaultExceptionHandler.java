package com.komleva.exceptions;

import com.komleva.util.RandomValuesGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.komleva.controller.response.ApplicationErrorCodes.FATAL_ERROR;
import static com.komleva.controller.response.ApplicationErrorCodes.USER_NOT_FOUND;

@ControllerAdvice
@RequiredArgsConstructor
public class DefaultExceptionHandler {
    private static final Logger logger = Logger.getLogger(DefaultExceptionHandler.class);

    private final RandomValuesGenerator generator;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
        String exceptionUniqueId = generator.uuidGenerator();
        logger.error(exceptionUniqueId + e.getMessage(), e);

        return new ResponseEntity<>(
                new ErrorMessage(exceptionUniqueId, FATAL_ERROR.getCodeId(), e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException e) {
        String exceptionUniqueId = generator.uuidGenerator();
        logger.error(exceptionUniqueId + e.getMessage(), e);

        return new ResponseEntity<>(
                new ErrorMessage(exceptionUniqueId, USER_NOT_FOUND.getCodeId(), e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
