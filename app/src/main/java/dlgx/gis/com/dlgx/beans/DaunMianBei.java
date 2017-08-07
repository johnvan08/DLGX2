package dlgx.gis.com.dlgx.beans;

import android.graphics.Bitmap;

/**
 * Created by admin on 2017/5/16.
 */
public class DaunMianBei {
    private String adress;//位置
    private String fangWei;//方位
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

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getXuhao() {
        return xuhao;
    }

    public void setXuhao(String xuhao) {
        this.xuhao = xuhao;
    }


}
