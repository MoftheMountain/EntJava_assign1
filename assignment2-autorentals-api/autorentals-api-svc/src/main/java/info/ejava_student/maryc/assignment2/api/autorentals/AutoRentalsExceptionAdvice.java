package info.ejava_student.maryc.assignment2.api.autorentals;

import info.ejava.examples.common.dto.MessageDTO;
import info.ejava.examples.common.web.BaseExceptionAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice(basePackageClasses = AutoRentalsController.class)
public class AutoRentalsExceptionAdvice extends BaseExceptionAdvice {

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<MessageDTO> handle(AccessDeniedException ex) {
        String text = String.format("caller[%s] is forbidden from making this request", getPrincipal());
        return this.buildResponse(HttpStatus.FORBIDDEN, null, text, (Instant) null);
    }

    protected String getPrincipal() {
        try {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (NullPointerException ex) {
            return "null";
        }

    }
}