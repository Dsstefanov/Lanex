package DBLayer;

import ModelLayer.Product;
import com.sun.xml.internal.bind.v2.TODO;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by RedJohn on 4/26/2017.
 */
public class DBProduct implements IDBProduct{

    public int getNumberRows(){
        int counter = 0;
        String sql = String.format("SELECT * FROM Product");
        try{
            Connection conn = DBConnection.getInstance().getDBcon();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()){
                counter++;
            }

        } catch (Exception e){
            e.getMessage();
        } finally {
            DBConnection.closeConnection();
        }
        return counter;
    }

    public Product create(Product product) throws SQLException
    {
            int currentRows = getNumberRows();
            double height = product.getHeight();
            double length = product.getLength();
            double width =  product.getWidth();
            String barcode = product.getBarcode();
            int minQuantity = product.getMinQuantity();
            int maxQuantity = product.getMaxQuantity();
            int currentCapacity = product.getCurrentQuantity();
            int dailyConsumption = product.getDailyConsumption();
            String name = product.getName();
            int cvr  = product.getCvr();
            String currentDate = getCurrentDate();
            int bit = 0 ;

            System.out.println(barcode);
            System.out.println(currentDate);

            String sql = "BEGIN TRANSACTION " +
                    "INSERT INTO Product (barcode,currentQuantity, minQuantity, "
                    + "maxQuantity, cvr, name, height, length, width,dailyConsumption,lastUpdated,isOrdered) " +
                    "VALUES ('"+barcode+"', '"+currentCapacity+"', '"+minQuantity+"', '"+maxQuantity+"', '"+cvr+"',  '"+name+"', " +
                    "'"+height+"', '"+length+"', '"+width+"', '"+dailyConsumption+"', '"+currentDate+"', '"+bit+"') " +
                    "IF @@ROWCOUNT <> 1 " +
                    "BEGIN " +
                    "ROLLBACK " +
                    "RAISERROR('Barcode already exists!', 16, 1) " +
                    "END " +
                    "ELSE " +
                    "BEGIN " +
                    "COMMIT " +
                    "END";

            try{
                Connection conn;
                conn = DBConnection.getInstance().getDBcon();
                conn.createStatement().executeUpdate(sql);

            } catch (SQLException e){
                e.getMessage();
            } finally {
                DBConnection.closeConnection();
            }

            int lastChangesOfRows = getNumberRows();
            if(currentRows == lastChangesOfRows) {
                throw new IllegalArgumentException("The barcode already exists!!!");
            }

            return product;
        }

    public  String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        return dateFormat.format(new java.util.Date());


    }


    public Product read(String productId) throws SQLException{
        Product product = null;
        try{
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format("SELECT * FROM product WHERE barcode= %s ",productId);
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()){
                product = buildObject(rs);
            }
        }catch (SQLException e) {
            throw e;
        }finally{
            DBConnection.closeConnection();
        }
        return product;
    }
    private  Product buildObject(ResultSet rs) throws SQLException{
        Product product;
        try {
            String productId = rs.getString("barcode");
            double height =rs.getDouble("height");
            double length =  rs.getDouble("length");
            double width = rs.getDouble("width");
            int minQuantity = rs.getInt("minQuantity");
            int maxQuantity = rs.getInt("maxQuantity");
            int currentCapacity = rs.getInt("currentQuantity");
            int dailyConsumption = rs.getInt("dailyConsumption");
            String name = rs.getString("name");
            int cvr  = rs.getInt("cvr");
            product = new Product(productId,height,length,width,minQuantity,maxQuantity,currentCapacity,dailyConsumption,name,cvr);
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return product;
    }

    private ArrayList<Product> buildObjects(ResultSet rs) throws SQLException{
        ArrayList<Product> products = new ArrayList<>();
        while(rs.next()) {
            products.add(buildObject(rs));
        }
        return products;
    }

    private boolean changeOrderStatus(String barcode,int isOrdered){
        try{
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format("UPDATE Product SET isOrdered = '%d' WHERE barcode= '%s' ",isOrdered,barcode);
            conn.createStatement().executeUpdate(sql);

            } catch (SQLException e1) {
            e1.printStackTrace();
        }
        finally{
            DBConnection.closeConnection();
        }
        return true;

    }

    private boolean setLastUpdate(String barcode,String date){
        try{
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format("UPDATE Product SET lastUpdated = '%s' WHERE barcode= '%s' ",date,barcode);
            conn.createStatement().executeUpdate(sql);

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        finally{
            DBConnection.closeConnection();
        }
        return true;
    }

    public ArrayList<Product> readAll() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try(Statement st = DBConnection.getInstance().getDBcon().createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                products = buildObjects(rs);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnection.closeConnection();
        }
        return  products;
    }


    public boolean update(Product product) throws SQLException{
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            double height = product.getHeight();
            double length = product.getLength();
            double width = product.getWidth();
            String barcode = product.getBarcode();
            System.out.println(barcode);
            int minQuantity = product.getMinQuantity();
            int maxQuantity = product.getMaxQuantity();
            int currentCapacity = product.getCurrentQuantity();
            int dailyConsumption = product.getDailyConsumption();
            String name = product.getName();
            int cvr  = product.getCvr();

            PreparedStatement psttm = conn.prepareStatement("UPDATE Product SET height = ?, length = ?, "
                    + "width = ?, minQuantity = ?, maxQuantity = ?,currentQuantity = ?, "
                    + "dailyConsumption = ?,name = ?, cvr = ? WHERE barcode = ? ");
            // psttm.setNString(1,productId);
            psttm.setDouble(1,height);
            psttm.setDouble(2,length);
            psttm.setDouble(3, width);
            psttm.setInt(4, minQuantity);
            psttm.setInt(5, maxQuantity);
            psttm.setInt(6,currentCapacity);
            psttm.setInt(7, dailyConsumption);
            psttm.setString(8, name);
            psttm.setInt(9, cvr);
            psttm.setString(10, barcode);
            psttm.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;

        }finally {
            DBConnection.closeConnection();
        }
        return true;
    }

    public ArrayList<Product> getProductsToOrder(){

       ArrayList<Product> productsToOrder = new ArrayList<>();
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT (barcode,minQuantity,maxQuantity,height,length,width,dailyConsumption) FROM Product WHERE isOrdered = 0 AND currentQuantity - 44*dailyConsumption<=0");
            // TODO: 5/31/2017 : to also change as soon as selected the isOrdered to 1 and to create a different objectBuilder or to use the old one and reuse the data in ControlLayer
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  productsToOrder;
    }




    public boolean delete(String productId)throws SQLException{
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format("Delete from Product where barcode='%s'", productId);
            conn.createStatement().executeUpdate(sql);
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            DBConnection.closeConnection();
        }
        return true;
    }
}
