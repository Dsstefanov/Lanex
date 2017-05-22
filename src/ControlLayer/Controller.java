package ControlLayer;

import ValidatorLayer.Validator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.function.DoubleBinaryOperator;
import java.lang.reflect.Method;

/**
 * Created by Admin on 5/22/2017.
 */
public abstract class Controller implements IController{
    //TODO finish the code
    ArrayList<String> errors = new ArrayList<>();
    void check(Object parameterForMethod, String methodToBeExecuted, Object objectOfValidator){
        try{
            Method method = objectOfValidator.getClass().getMethod(methodToBeExecuted, parameterForMethod.getClass());
            System.out.println(method);
            try {
                System.out.println(method.invoke(parameterForMethod, parameterForMethod));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IllegalAccessException e) {
                System.out.println(e.getClass());
            } catch (InvocationTargetException e) {
                System.out.println(e.getClass());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<String> getErrors(){
        return errors;
    }
}
