package info.ejava_student.maryc.assignment2.api.autorentals;

import info.ejava.examples.common.web.BaseExceptionAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = AutoRentalsController.class)
public class AutoRentalsExceptionAdvice extends BaseExceptionAdvice {
}