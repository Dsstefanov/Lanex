package ModelLayer;


public class Order {
    private static int ID=1;
    public Order(){
        ID++;
    }
    public static int getID(){
        return ID;
    }

}
