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
public class EmployeeController {
    private DBEmployee dbEmployee;
    Validator validator = new Validator();

    public EmployeeController() {
        dbEmployee = new DBEmployee();
    }

        public boolean create (String firstLastName, String address, String email, String phone, String city, int work_id) {
            try {
                String validateName = validator.validateName(firstLastName);
                String validateAddress = validator.validateAddress(address);
                String validateEmail = validator.validateEmail(email);
                String validatePhone = validator.validatePhone(phone);
                String validateCity = validator.validateCity(city);
                int validateWorkId = validator.validateWorkId(work_id);
                dbEmployee.create(validateName, validateAddress, validateEmail, validatePhone, validateCity, validateWorkId);
                return true;
            }catch(SQLException e) {
                e.getMessage();
                return false;
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
            try{
                dbEmployee.update(employee, work_id);
                return true;
            }catch(SQLException e) {
                return false;
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
