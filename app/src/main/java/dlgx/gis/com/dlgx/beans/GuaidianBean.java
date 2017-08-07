package dlgx.gis.com.dlgx.beans;

import java.io.Serializable;

/**
 * Created by admin on 2017/5/26.
 */
public class GuaidianBean implements Serializable {
    private double latitude;//纬度
    private double longitude;//经度

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
