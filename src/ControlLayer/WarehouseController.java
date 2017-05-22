package ControlLayer;
import DBLayer.DBWarehouse;
import Exceptions.ValidationException;
import ModelLayer.Warehouse;
import ValidatorLayer.Validator;

import java.sql.SQLException;

/**
 * Created by USER on 26.4.2017 г..
 */
public class WarehouseController extends Controller{
    public static void main(String[] args) {
        new WarehouseController().create(13,-11,-12);
    }
    public boolean create(int length, int width, int height){
        try{
            int checkedLength = (check(length, "validateObjectLength")!=null)?(int) check(length, "validateObjectLength"):length;
            int checkedWidth =  (check(width, "validateObjectWidth")!=null)?(int) check(width, "validateObjectWidth"):width;
            int checkedHeight = (check(height, "validateObjectHeight")!=null)?(int) check(height, "validateObjectHeight"):height;
            if (errors.size()>0){
                throw new ValidationException(String.join("\n", errors));
            }else {
                new DBWarehouse().create(checkedLength, checkedWidth, checkedHeight);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getClass());
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
