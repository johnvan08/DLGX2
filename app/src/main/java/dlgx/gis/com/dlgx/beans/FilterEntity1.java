package dlgx.gis.com.dlgx.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/2.
 */
public class FilterEntity1 implements Serializable {
    private int image;
    private String value;
    private boolean isSelected;


    public FilterEntity1(int image, String value ) {
        this.image = image;
        this.value = value;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
