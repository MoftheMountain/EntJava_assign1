package info.ejava_student.maryc.assignment3.assignment3.aop.autorentals;

import info.ejava.assignments.aop.autorenters.util.MethodConstraints;
import info.ejava.assignments.aop.autorenters.util.NullPropertyAssertion;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ValidatorAspect {
    private final NullPropertyAssertion nullPropertyAssertion;
    private final List<MethodConstraints> methodConstraints;

    //pointcut

    //advice
}
