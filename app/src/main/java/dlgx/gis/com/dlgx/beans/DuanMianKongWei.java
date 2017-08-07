package dlgx.gis.com.dlgx.beans;

/**
 * 断面孔位信息表
 * Created by admin on 2017/5/4.
 */
//@Table(name = "DuanMianKongWei")
public class DuanMianKongWei {
    //主键id
    private String uuid = "";
    //断面名称
    private String name = "";
    //电缆段现场编号
    private String nummber = "";
    //管孔类型
    private String guankongleiixng= "";
    //管孔材质
    private String guankongcaizhi = "";
    //管孔管径
    private String guankongguanjing = "";
    //行号
    private String hanghao = "";
    //列号
    private String liehao = "";

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNummber() {
        return nummber;
    }

    public void setNummber(String nummber) {
        this.nummber = nummber;
    }

    public String getGuankongleiixng() {
        return guankongleiixng;
    }

    public void setGuankongleiixng(String guankongleiixng) {
        this.guankongleiixng = guankongleiixng;
    }

    public String getGuankongcaizhi() {
        return guankongcaizhi;
    }

    public void setGuankongcaizhi(String guankongcaizhi) {
        this.guankongcaizhi = guankongcaizhi;
    }

    public String getGuankongguanjing() {
        return guankongguanjing;
    }

    public void setGuankongguanjing(String guankongguanjing) {
        this.guankongguanjing = guankongguanjing;
    }

    public String getHanghao() {
        return hanghao;
    }

    public void setHanghao(String hanghao) {
        this.hanghao = hanghao;
    }

    public String getLiehao() {
        return liehao;
    }

    public void setLiehao(String liehao) {
        this.liehao = liehao;
    }

    @Override
    public String toString() {
        return "DuanMianKongWei{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", nummber='" + nummber + '\'' +
                ", guankongleiixng='" + guankongleiixng + '\'' +
                ", guankongcaizhi='" + guankongcaizhi + '\'' +
                ", guankongguanjing='" + guankongguanjing + '\'' +
                '}';
    }
}
