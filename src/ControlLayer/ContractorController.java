package ControlLayer;

import DBLayer.DBContractor;
import ModelLayer.Contractor;
import ModelLayer.Person;

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

    public boolean create() {
        try {
            dbContractor.create(null, null, null, null, null, 0);
            return true;
        } catch (SQLException e) {
            return  false;
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
