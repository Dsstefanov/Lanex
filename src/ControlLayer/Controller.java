package ControlLayer;

import Exceptions.ValidationException;
import ValidatorLayer.Validator;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.lang.reflect.Method;

/**
 * Created by Admin on 5/22/2017.
 */
public abstract class Controller implements IController{
    ArrayList<String> errors = new ArrayList<>();

    /**
     * <p>
     *     takes the class of ValidatorLayer.Validator and applies the given method
     *     'methodToBeExecuted' and then to this method passes the parameter 'parameterForMethod'
     * </p>
     * @param parameterForMethod: This is the parameter for the method inside ValidatorLayer.Validator.@param methodToBeExecuted
     * @param methodToBeExecuted: Method inside ValidatorLayer.Validator
     * @return Object if everything is correct or null if any of the exceptions triggers
     */
    Object check(Object parameterForMethod, String methodToBeExecuted){
        try{
            Method method = Validator.class.getMethod(methodToBeExecuted, parameterForMethod.getClass());
            try {
                return method.invoke(null, parameterForMethod);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return null;
            } catch (InvocationTargetException e) {
                if (e.getTargetException().getClass() == ValidationException.class) {
                    errors.add(e.getTargetException().getMessage());
                } else {
                    e.printStackTrace();
                }
                return null;
            } catch (IllegalAccessException e) {
                System.out.println("Developer error check Validator method visibility");
                return null;
            }
        }catch (NoSuchMethodException e){
            System.out.println(e.getMessage() + "   -   Method with that name does not exist");
            return null;
        }
    }
}
