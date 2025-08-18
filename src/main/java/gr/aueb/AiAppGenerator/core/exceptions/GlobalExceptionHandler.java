package gr.aueb.AiAppGenerator.core.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException exception, HttpServletRequest request) {
        log.error("BusinessException occured: {} - Path: {} ",
                exception.getMessage(),request.getRequestURI(),exception);
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Business Rule Violation");
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setProperty("path", request.getRequestURI());
        return problemDetail;


    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnexpectedException(Exception exception, HttpServletRequest request) {
        log.error("Unhandled exception in recipe controller: {} - Path: {}",
                exception.getMessage(),request.getRequestURI(),exception);
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setDetail("Something went wrong, please try again later. ");
        problemDetail.setProperty("path",request.getRequestURI());
        return problemDetail;
    }
}
