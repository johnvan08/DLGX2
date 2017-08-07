package dlgx.gis.com.dlgx.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/1.
 */
public class ChannelEntity1 implements Serializable {
    private String title;
    // private String tips;
    // private int image_url;
    private int image_url;

    public ChannelEntity1(String title, int image_url) {
        this.title = title;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage_url() {
        return image_url;
    }

    public void setImage_url(int image_url) {
        this.image_url = image_url;
    }
}
