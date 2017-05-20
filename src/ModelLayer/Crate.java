package ModelLayer;


/**
 * Created by Luke on 09.05.2017.
 */
public class Crate extends Box {

    private String crateId;


    public Crate(String crateId, double  height,double width, double  length){
        super( height, width, length);
        this.crateId = crateId;
    }



    public String getCrateId() {
        return crateId;
    }

    public void setCrateId(String crateId) {
        this.crateId = crateId;
    }


}
