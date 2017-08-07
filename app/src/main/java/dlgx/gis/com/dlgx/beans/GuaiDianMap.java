package dlgx.gis.com.dlgx.beans;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by admin on 2017/5/11.
 */
public class GuaiDianMap {
    private String id;
    private String name;
    private String maiShen;//埋深
    private double latitude;//纬度
    private double longitude;//经度
    private String beiZhu;//备注
    private String zuoYe;//作业单位
    private String newTime;//新增时间
    private String leiixng_biaoji;//新增时间
    private ArrayList<Bitmap> photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaiShen() {
        return maiShen;
    }

    public void setMaiShen(String maiShen) {
        this.maiShen = maiShen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getBeiZhu() {
        return beiZhu;
    }

    public void setBeiZhu(String beiZhu) {
        this.beiZhu = beiZhu;
    }

    public String getZuoYe() {
        return zuoYe;
    }

    public void setZuoYe(String zuoYe) {
        this.zuoYe = zuoYe;
    }

    public ArrayList<Bitmap> getPhoto() {
        return photo;
    }

    public void setPhoto(ArrayList<Bitmap> photo) {
        this.photo = photo;
    }

    public String getNewTime() {
        return newTime;
    }

    public void setNewTime(String newTime) {
        this.newTime = newTime;
    }

    public String getLeiixng_biaoji() {
        return leiixng_biaoji;
    }

    public void setLeiixng_biaoji(String leiixng_biaoji) {
        this.leiixng_biaoji = leiixng_biaoji;
    }
}
