package dlgx.gis.com.dlgx.beans;

import android.graphics.Bitmap;

/**
 * Created by admin on 2017/5/17.
 */
public class DaunMianXi {

    private String adress;
    private String fangWei;
    private String xuhao;//序号
    private Bitmap photo;


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getFangWei() {
        return fangWei;
    }

    public void setFangWei(String fangWei) {
        this.fangWei = fangWei;
    }

    public String getXuhao() {
        return xuhao;
    }

    public void setXuhao(String xuhao) {
        this.xuhao = xuhao;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
