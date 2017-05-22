package ControlLayer;

import DBLayer.DBContractor;
import ModelLayer.Contractor;
import ModelLayer.Person;
import ValidatorLayer.Validator;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Admin on 4/28/2017.
 */
public class ContractorController {
    private DBContractor dbContractor;

    public ContractorController() {
        dbContractor = new DBContractor();
    }

    public static void main(String[] args) {
        ContractorController cc = new ContractorController();
        try {
            cc.create("fg", "d", "fg", "d", "d", 5);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean create(String firstLastName, String address, String email, String phone, String city, int cvr) {
        try {
            String validateName = Validator.validateName(firstLastName);
            String validateAddress = Validator.validateAddress(address);
            String validateEmail = Validator.validateEmail(email);
            String validatePhone = Validator.validatePhone(phone);
            String validateCity = Validator.validateCity(city);
            int validateCVR = Validator.validateCVR(cvr);
            dbContractor.create(validateName, validateAddress, validateEmail, validatePhone, validateCity, validateCVR);
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    public String read(int cvr) {
        try {
           return dbContractor.read(cvr).toString();
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean update(Contractor contractor, int cvr) {
        try {
            dbContractor.update(contractor, cvr);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(int cvr) {
        try {
            dbContractor.delete(cvr);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public ArrayList<Person> readAll() {
        try {
            return dbContractor.readAll();
        } catch (SQLException e) {
            return null;
        }
    }
}
