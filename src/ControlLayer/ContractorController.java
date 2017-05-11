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
    Validator validator = new Validator();

    public ContractorController() {
        dbContractor = new DBContractor();
    }

    public boolean create(String firstLastName, String address, String email, String phone, String city, int cvr) {
        try {
            validator.validateName(firstLastName);
            validator.validateAddress(address);
            validator.validateEmail(email);
            validator.validatePhone(phone);
            validator.validateCity(city);
            validator.validateCVR(cvr);
            dbContractor.create(null, null, null, null, null, 0);
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
