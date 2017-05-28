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
public class ContractorController extends Controller{
    private DBContractor dbContractor;
    ArrayList<String> errors = new ArrayList<>();
    private String validateName, validateAddress, validateEmail, validatePhone, validateCity;
    private int validateCVR;

    public ContractorController() {
        dbContractor = new DBContractor();
    }

    public ArrayList<String> getErrors(){
        return errors;
    }

    public void removeErrorMessages() {
        this.errors.clear();
    }

    public boolean create(String firstLastName, String address, String email, String phone, String city, int cvr) {
        checkMultipleErrors(firstLastName, address, email, phone, city, cvr);

        if(errors.size() == 0) {
            try{
                dbContractor.create(validateName, validateAddress, validateEmail, validatePhone, validateCity, validateCVR);
                return true;
            } catch(SQLException e) {
                return false;
            }
        }
        else {
            throw new IllegalArgumentException(String.join("\n", errors));
        }

    }

    private void checkMultipleErrors(String firstLastName, String address, String email, String phone, String city,
                                     int cvr) {
        try {
            this.validateName = Validator.validateName(firstLastName);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.validateAddress = Validator.validateAddress(address);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.validateEmail = Validator.validateEmail(email);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.validatePhone = Validator.validatePhone(phone);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.validateCity = Validator.validateCity(city);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.validateCVR = Validator.validateCVR(cvr);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    public String read(int cvr) {
        try {
           return dbContractor.read(cvr).getContractor();
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean update(Contractor contractor, int cvr) {
        checkMultipleErrors(contractor.getName(),contractor.getAddress(), contractor.getEmail(),contractor.getPhone(),contractor.getCity(), cvr);
        if(errors.size() == 0) {
            try {
                dbContractor.update(contractor, cvr);
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        else {
            throw new IllegalArgumentException(String.join("\n", errors));
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
