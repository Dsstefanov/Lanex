package ControlLayer;
import DBLayer.DBWarehouse;
import Exceptions.ValidationException;
import ModelLayer.Warehouse;
import ValidatorLayer.Validator;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by USER on 26.4.2017 г..
 */
public class WarehouseController extends Controller{
    public static void main(String[] args) {
        new WarehouseController().create(13,-11,-12);
    }

    /**
     * @param length: warehouse length
     * @param width: warehouse width
     * @param height: warehouse height
     *
     * @return true if object is saved in DB
     * @throws ValidationException
     */
    public boolean create(int length, int width, int height)throws ValidationException{
        try{
            if (errors.size()!=0){
                errors = null;
                errors = new ArrayList<>();
            }
            int checkedLength = (check(length, "validateObjectLength")!=null)?(int) check(length, "validateObjectLength"):length;
            // int checkedLength = if(check(length, "validateObjectLength")!=null){return (int) check(length, "validateObjectLength")}else{return length};
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

    /**
     * @param warehouse: current dbo
     * @param length: new length inputted
     * @param width: new width inputted
     * @param height: new height inputted
     * @return true if everything passes
     * @throws ValidationException
     */
    public boolean update(Warehouse warehouse, int length, int width, int height) throws ValidationException{
        try{
            int checkedLength = (check(length, "validateObjectLength")!=null)?(int) check(length, "validateObjectLength"):length;
            int checkedWidth =  (check(width, "validateObjectWidth")!=null)?(int) check(width, "validateObjectWidth"):width;
            int checkedHeight = (check(height, "validateObjectHeight")!=null)?(int) check(height, "validateObjectHeight"):height;
            if (errors.size()>0){
                throw new ValidationException(String.join("\n", errors));
            }else {
                warehouse.setLength(checkedLength);
                warehouse.setWidth(checkedWidth);
                warehouse.setHeight(checkedHeight);
                return new DBWarehouse().update(warehouse);
            }
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