package ModelLayer;

import java.util.ArrayList;

/**
 * Created by Red John on 24-Mar-17.
 */
public class Container extends Box{
    private int containerId;
    private ArrayList<Integer> numberOfCrates;
    public Container(int containerId, double height, double width, double length)
    {
        super(height, width, length);
        this.containerId = containerId;

    }

    public Container( double height, double width, double length)
    {
        super(height, width, length);
        numberOfCrates = new ArrayList<>();
    }



    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }

    public void addCrateNumber(int crateNumber){
        numberOfCrates.add(crateNumber);
    }

    public ArrayList<Integer> getNumberOfCrates() {
        return numberOfCrates;
    }
}
