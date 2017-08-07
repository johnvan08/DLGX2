package dlgx.gis.com.dlgx.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2017/5/3.
 */
//@Table(name = "HuanWangGuiBean", onCreated = "CREATE UNIQUE INDEX index_huanwanggui ON huanwangguibean(uuid,name,maishen,jingdu,weidu,image,beizhu,zuoyedanwei,xinznegshijian)")
public class HuanWangGuiBean {
    //主键id
    //@Column(name = "uuid", isId = true)
    @SerializedName("uuid")
    private String uuid = "";
    //名称
    //@Column(name = "name")
    @SerializedName("name")
    private String name = "";
    //埋深
    //@Column(name = "maishen")
    @SerializedName("maishen")
    private String maishen = "";
    //经度
    //@Column(name = "jingdu")
    @SerializedName("jingdu")
    private String jingdu = "";
    //纬度
    //@Column(name = "weidu")
    @SerializedName("weidu")
    private String weidu= "";
    //上传接口返回的sha1值，多个请用逗号“,”连接
    //@Column(name = "image")
    @SerializedName("image")
    private String image = "";
    //备注
    //@Column(name = "beizhu")
    @SerializedName("beizhu")
    private String beizhu = "";
    //作业单位
    //@Column(name = "zuoyedanwei")
    @SerializedName("zuoyedanwei")
    private String zuoyedanwei= "";
    //新增时间
    //@Column(name = "xinznegshijian")
    @SerializedName("xinznegshijian")
    private String xinznegshijian= "";


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

    public String getMaishen() {
        return maishen;
    }

    public void setMaishen(String maishen) {
        this.maishen = maishen;
    }

    public String getJingdu() {
        return jingdu;
    }

    public void setJingdu(String jingdu) {
        this.jingdu = jingdu;
    }

    public String getWeidu() {
        return weidu;
    }

    public void setWeidu(String weidu) {
        this.weidu = weidu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getZuoyedanwei() {
        return zuoyedanwei;
    }

    public void setZuoyedanwei(String zuoyedanwei) {
        this.zuoyedanwei = zuoyedanwei;
    }

    public String getXinznegshijian() {
        return xinznegshijian;
    }

    public void setXinznegshijian(String xinznegshijian) {
        this.xinznegshijian = xinznegshijian;
    }

    @Override
    public String toString() {
        return "HuanWangGuiBean{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", maishen='" + maishen + '\'' +
                ", jingdu='" + jingdu + '\'' +
                ", weidu='" + weidu + '\'' +
                ", image='" + image + '\'' +
                ", beizhu='" + beizhu + '\'' +
                ", zuoyedanwei='" + zuoyedanwei + '\'' +
                ", xinznegshijian='" + xinznegshijian + '\'' +
                '}';
    }
}
