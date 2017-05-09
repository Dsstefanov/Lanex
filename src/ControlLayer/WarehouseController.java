package ControlLayer;
import DBLayer.DBWarehouse;

import java.sql.SQLException;

/**
 * Created by USER on 26.4.2017 г..
 */
public class WarehouseController {
    public boolean create(){
        try{
            new DBWarehouse().create();
            return true;
        }catch (SQLException e){
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
    public boolean update(int id){
        try{
            return new DBWarehouse().update(new DBWarehouse().read(id));
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
