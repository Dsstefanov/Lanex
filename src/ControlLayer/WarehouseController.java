package ControlLayer;
import DBLayer.DBWarehouse;
import Exceptions.ValidationException;
import ModelLayer.Warehouse;
import ValidatorLayer.SavedErrors;
import ValidatorLayer.Validator;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by USER on 26.4.2017 г..
 */
public class WarehouseController extends Controller{

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
                errors.clear();
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
    public Warehouse read(int id) throws ValidationException{
        try{
            if (errors.size()!=0){
                errors.clear();
            }
            Warehouse warehouse = new DBWarehouse().read(id);
           if (warehouse!=null){
               return warehouse;
           }else{
               throw new NullPointerException();
           }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }catch (NullPointerException e){
            errors.add(SavedErrors.getInstance().getErrors().get("WAREHOUSE_NOT_FOUND"));
            throw new ValidationException(String.join("\n", errors));
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
            if (errors.size()!=0){
                errors.clear();
            }
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
            if (errors.size()!=0){
                errors.clear();
            }
            new DBWarehouse().delete(id);
            return true;
        }catch (SQLException e){
            return false;
        }catch (NullPointerException e){
            errors.add(SavedErrors.getInstance().getErrors().get("WAREHOUSE_NOT_FOUND"));
            return false;
        }
    }
}