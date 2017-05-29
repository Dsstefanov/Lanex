package DBLayer;

import ModelLayer.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;


/**
 * Created by RedJohn on 4/26/2017.
 */
public class DBProduct implements IDBProduct{
    public static void main(String[] args) {

        try{
            int dailyConsumption = 50;

            Product product = new Product(1.345,1.2,1.1,"123567",37*dailyConsumption,74*dailyConsumption,44*dailyConsumption,dailyConsumption,"Lime Rope",11111111);
            update(product);
        } catch (Exception e){
            System.out.println("Couldn't insert the contractor in the DB");
        }
    }

    public static Product create(Product product)
    {
        //Product product = new Product(height,length,width,barcode,minQuantity,maxQuantity,currentQuantity,dailyConsumption,name,cvr);
        //String sql = String.format("INSERT INTO Product VALUES ('%s', '%d', '%d', '%d', '%d') ",height,length,width,barcode,minQuantity,maxQuantity,currentQuantity,dailyConsumption,name,cvr);


        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();

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


            PreparedStatement psttm = conn.prepareStatement("INSERT INTO Product (barcode,currentQuantity, minQuantity, "
                    + "maxQuantity, cvr, name, height, length, width,dailyConsumption) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)");


            psttm.setDouble(7,height);
            psttm.setDouble(8,length);
            psttm.setDouble(9, width);
            psttm.setString(1, barcode);
            psttm.setInt(3, minQuantity);
            psttm.setInt(4, maxQuantity);
            psttm.setInt(2,currentCapacity);
            psttm.setInt(10, dailyConsumption);
            psttm.setString(6, name);
            psttm.setInt(5, cvr);

            psttm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnection.closeConnection();
        }
        return product;
    }


    public static Product read(String productId) throws SQLException{
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
    private static Product buildObject(ResultSet rs) throws SQLException{
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
            product = new Product(height,length,width,productId,minQuantity,maxQuantity,currentCapacity,dailyConsumption,name,cvr);
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


    public static boolean update(Product product) throws SQLException{
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

    public static boolean delete(String productId)throws SQLException{
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
