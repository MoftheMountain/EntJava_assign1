package info.ejava_student.maryc.assignment3.assignment3.aop.autorentals;

import info.ejava.assignments.aop.autorenters.util.NullPropertyAssertion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

public class NullPropertyAssertionImpl extends NullPropertyAssertion {
    /**
     * Return the named Method for the object or empty if
     * method does not exist.
     */
    @Override
    protected Optional<Method> getGetterMethod(Object object, String getterName) {

        Optional<Method> method = Optional.empty();
        try {
            return method = Optional.ofNullable(object.getClass().getMethod(getterName));
        } catch (NoSuchMethodException e) {
            return method;
        }
        //return method;

    }

    /**
     * Return the value returned from the getter method and report
     * any errors that with a server-type error.
     */
    @Override
    protected Object getValue(Object object, Method getterMethod) {
        Object valueObj = null;
        try {
            return valueObj = getterMethod.invoke(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
