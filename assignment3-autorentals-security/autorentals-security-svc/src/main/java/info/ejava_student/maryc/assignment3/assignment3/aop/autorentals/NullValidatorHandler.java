package info.ejava_student.maryc.assignment3.assignment3.aop.autorentals;

import info.ejava.assignments.aop.autorenters.util.NullPropertyAssertion;
import info.ejava.examples.common.exceptions.ClientErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class NullValidatorHandler implements InvocationHandler {
private final NullPropertyAssertion nullPropertyAssertion;
private final Object target;
private final String methodName;
private final List<String> isNullProperties;
private final List<String> nonNullProperties;

/**
 * Implement the handler method invoked by the dynamic proxy interpose.
 * @param proxy
 * @param method that was invoked by caller
 * @param args to the method invoked, supplied by caller
 * @return value returned from target.method() call if args valid
 * @throws ClientErrorException.InvalidInputException
 * if args not valid
 */
//@Override
public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Object result = null;
    if (this.methodName.equals(method.getName())) {
        for (int i = 0; i < args.length; i++) {

            nullPropertyAssertion.assertConditions(args[i], isNullProperties, true);
            log.debug("Properties:{} in Argument {} are not null", isNullProperties, args[1]);
            nullPropertyAssertion.assertConditions(args[i], nonNullProperties, false);
            log.debug("Properties:{} in Argument {} are  null", nonNullProperties, args[1]);

        }
        result = method.invoke(proxy, args);
        log.info("invoke {} returned: {}", method.getName(), result);
    }
    return result;
}

/**
 * Creates a new dynamic interface proxy for target that will perform
 * nullProperty assertion logic against provided objects for named
 * methods.
 * @param nullPropertyAssertion validator for handler to validate with
 * @param target the object we are proxying
 * @param methodName method for the handler to process
 * @param nullProperties properties validated against isNull
 * @param nonNullProperties properties validated against notNull
 * @param <T> target object type
 * @return dynamic proxy implementing same interfaces as target
 */
public static <T> T newInstance(
        NullPropertyAssertion nullPropertyAssertion,
        T target,
        String methodName,
        List<String> nullProperties,
        List<String> nonNullProperties) {

        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                ClassUtils.getAllInterfaces(target.getClass()).toArray(new Class[0]),
                new NullValidatorHandler(nullPropertyAssertion, target, methodName, nullProperties, nonNullProperties));
        }
}
