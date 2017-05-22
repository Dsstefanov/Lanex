package ControlLayer;
import DBLayer.DBWarehouse;
import ModelLayer.Warehouse;
import ValidatorLayer.Validator;

import java.sql.SQLException;

/**
 * Created by USER on 26.4.2017 г..
 */
public class WarehouseController extends Controller{
    public static void main(String[] args) {
        new WarehouseController().create(10,11,12);
    }
    public boolean create(float length, float width, float height){
        try{
            check("Dimitar Stefanov", "validateName", new Validator());
            float checkedLength = Validator.validateObjectSize(length);
            float checkedWidth = Validator.validateObjectSize(width);
            float checkedHeight = Validator.validateObjectSize(height);
            //new DBWarehouse().create(checkedLength, checkedWidth, checkedHeight);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public String read(int id){
        try{
            return new DBWarehouse().read(id).toString();
        }catch (SQLException e){
            return null;
        }
    }
    public boolean update(Warehouse warehouse){
        try{

            return new DBWarehouse().update(new DBWarehouse().read(warehouse.getId()));
        }catch (SQLException e){
            return false;
        }
    }
    public boolean delete(int id){
        try{
            new DBWarehouse().delete(id);
            return true;
        }catch (SQLException e){
            return false;
        }
    }
}
