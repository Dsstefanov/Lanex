package ModelLayer;


/**
 * Created by Luke on 09.05.2017.
 */
public class Crate extends Box {

    private int crateId;


    public Crate(int crateId, double  height, double  length, double width){
        super( height, length, width);
        this.crateId = crateId;
    }



    public int getCrateId() {
        return crateId;
    }

    public void setCrateId(int crateId) {
        this.crateId = crateId;
    }

    public String toString(){
        return "Crate id:" +getCrateId() + "Height : " + getHeight() + "Length : " +getLength() + "Width : " +getWidth();
    }


}
