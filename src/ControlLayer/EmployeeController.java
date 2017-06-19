package ControlLayer;

import DBLayer.DBEmployee;
import ModelLayer.Person;
import ModelLayer.Employee;
import ValidatorLayer.Validator;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Yeah on 5/21/2017.
 */
public class EmployeeController extends Controller {
    private static EmployeeController instance;
    ArrayList<String> errors = new ArrayList<>();
    private DBEmployee dbEmployee = new DBEmployee();
    Validator validator = new Validator();

    public static EmployeeController getInstance() {
        if (instance == null) {
            instance = new EmployeeController();
        }
        return instance;
    }
    public ArrayList<String> getErrors(){
        return errors;
    }
    private String validateName, validateAddress, validateEmail, validatePhone, validateCity;
    private int validateWorkId;

    public void removeErrorMessages() {
        this.errors.clear();
    }

    public void checkAllErrors (String firstLastName,String address,String email,String phone,String city,int work_id){
        try {
            this.validateName = validator.validateName(firstLastName);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.validateAddress = validator.validateAddress(address);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.validateEmail = validator.validateEmail(email);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.validatePhone = validator.validatePhone(phone);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.validateCity = validator.validateCity(city);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.validateWorkId = validator.validateWorkId(work_id);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }


        public boolean create (String firstLastName, String address, String email, String phone, String city, int work_id) {
            checkAllErrors(firstLastName,address,email,phone,city,work_id);
            if (errors.size() == 0) {
                try {
                    dbEmployee.create(validateName, validateAddress, validateEmail, validatePhone, validateCity, validateWorkId);
                    return true;
                } catch (SQLException e) {
                    e.getMessage();
                    return false;
                }
            } else {
                throw new IllegalArgumentException(String.join("\n", errors));


            }
        }

        public String read(int work_id) {
            try {
                return dbEmployee.read(work_id).toString();
            } catch (SQLException e) {
                return null;
            }
        }

        public boolean update(Employee employee , int work_id) {
            checkAllErrors(employee.getName(), employee.getAddress(), employee.getEmail(), employee.getPhone(),employee.getCity(),work_id);
            if (errors.size() == 0) {
                try {
                    dbEmployee.update(employee, work_id);
                    return true;
                } catch (SQLException e) {
                    return false;
                }
            } else {
                throw new IllegalArgumentException(String.join("\n", errors));
            }
        }

        public boolean delete(int work_id) {
            try{
                dbEmployee.delete(work_id);
                return true;
            }catch(SQLException e) {
                return false;
            }
        }

        public ArrayList<Person> readAll() {
            try{
                return dbEmployee.readAll();
            }catch(SQLException e) {
                return null;
            }
        }
}
