package ModelLayer;

import java.util.ArrayList;

/**
 * Created by Red John on 24-Mar-17.
 */
public class Container extends Box{
    int containerId;
    public Container(int containerId, double height, double width, double length)
    {
        super(height, width, length);
    }

    public Container()
    {
        super(2.591,2.438,6.058);
    }

    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }
}
