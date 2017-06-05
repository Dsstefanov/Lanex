package GUI;
import ControlLayer.ProductController;
import ModelLayer.Product;
import javax.swing.JFrame;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import ControlLayer.NotificationController;
/**
 * @author 1bestcsharp.blogspot.com
 */
public class A extends javax.swing.JFrame {

    private NotificationController notificationController = NotificationController.getInstance();

    ProductController productController = new ProductController();
    private ArrayList<Product> products;
    private ArrayList<Product> orderedProducts = new ArrayList<>();
    public A() {

        initComponents();

        // use the addRowToJTable
        addRowToJTable();
       //addRowToJTable();

    }






    // added rows from arraylist to jtable
    public void addRowToJTable()
    {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        ArrayList<Product> list = notificationController.getProductsToOrder();
        Object rowData[] = new Object[13];
        for(int i = 0; i < list.size(); i++)
        {
            rowData[0] = list.get(i).getBarcode();
            rowData[1] = list.get(i).getCurrentQuantity();
            rowData[2] = list.get(i).getMinQuantity();
            rowData[3] = list.get(i).getMaxQuantity();
            rowData[4] = list.get(i).getCvr();
            rowData[5] = list.get(i).getName();
            rowData[6] = list.get(i).getHeight();
            rowData[7] = list.get(i).getLength();
            rowData[8] = list.get(i).getWidth();
            rowData[9] = list.get(i).getDailyConsumption();
            rowData[10] = list.get(i).getLastUpdatedDate();
            rowData[11] = list.get(i).getIsOrdered();

            model.addRow(rowData);
        }

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1.setBounds(0,0,750,700);
        getContentPane().add(jScrollPane1);
        jTable1.setBounds(0,0,750,750);

        getContentPane().add(jTable1);
        Border border = jScrollPane1.getBorder();
        Border margin = new EmptyBorder(10,10,10,10);
        jScrollPane1.setBorder(new CompoundBorder(border, margin));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Barcode", "currentQuantity","minQuantity","max Quantity", "cvr", "name", "height", "length","width", "dailyConsumtion", "lastUpdated", "isOrdered"
                }
        ));
        jScrollPane1.setViewportView(jTable1);


        pack();
    }
    public static void main(String args[]) {


        try {
            A frame = new A();
            frame.setVisible(true);
            frame.getContentPane().setLayout(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setBounds(100, 100, 850, 700);

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(A.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(A.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(A.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(A.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


    }

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
