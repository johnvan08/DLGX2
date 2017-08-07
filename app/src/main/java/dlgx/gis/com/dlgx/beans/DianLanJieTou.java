package dlgx.gis.com.dlgx.beans;

/**
 * Created by admin on 2017/5/18.
 */
public class DianLanJieTou {
    //主键id
    private String uuid = "";
    //接头名称
    private String jieTouName = "";
    //现场编号
    private String nummber = "";
    //所属变电站
    private String suoshubiandianzhan= "";
    //所属线路
    private String suoshuxianlu = "";
    //安装位置
    private String anzhuangweizhi = "";
    //安装位置类型
    private String anzhuangweizhi_leixing = "";
    //电子标签号
    private String biaoqianhao = "";
    //安装位置精度
    private String anzhuangweizhi_jingdu = "";
    //安装位置纬度
    private String anzhuangweizhi_weidu = "";
    //备注
    private String beizhu= "";
    //作业单位
    private String zuoyedanwei= "";
    //新增时间
    private String xinzengshijian = "";

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getJieTouName() {
        return jieTouName;
    }

    public void setJieTouName(String jieTouName) {
        this.jieTouName = jieTouName;
    }

    public String getNummber() {
        return nummber;
    }

    public void setNummber(String nummber) {
        this.nummber = nummber;
    }

    public String getSuoshubiandianzhan() {
        return suoshubiandianzhan;
    }

    public void setSuoshubiandianzhan(String suoshubiandianzhan) {
        this.suoshubiandianzhan = suoshubiandianzhan;
    }

    public String getSuoshuxianlu() {
        return suoshuxianlu;
    }

    public void setSuoshuxianlu(String suoshuxianlu) {
        this.suoshuxianlu = suoshuxianlu;
    }

    public String getAnzhuangweizhi() {
        return anzhuangweizhi;
    }

    public void setAnzhuangweizhi(String anzhuangweizhi) {
        this.anzhuangweizhi = anzhuangweizhi;
    }

    public String getAnzhuangweizhi_leixing() {
        return anzhuangweizhi_leixing;
    }

    public void setAnzhuangweizhi_leixing(String anzhuangweizhi_leixing) {
        this.anzhuangweizhi_leixing = anzhuangweizhi_leixing;
    }

    public String getBiaoqianhao() {
        return biaoqianhao;
    }

    public void setBiaoqianhao(String biaoqianhao) {
        this.biaoqianhao = biaoqianhao;
    }

    public String getAnzhuangweizhi_jingdu() {
        return anzhuangweizhi_jingdu;
    }

    public void setAnzhuangweizhi_jingdu(String anzhuangweizhi_jingdu) {
        this.anzhuangweizhi_jingdu = anzhuangweizhi_jingdu;
    }

    public String getAnzhuangweizhi_weidu() {
        return anzhuangweizhi_weidu;
    }

    public void setAnzhuangweizhi_weidu(String anzhuangweizhi_weidu) {
        this.anzhuangweizhi_weidu = anzhuangweizhi_weidu;
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

    public String getXinzengshijian() {
        return xinzengshijian;
    }

    public void setXinzengshijian(String xinzengshijian) {
        this.xinzengshijian = xinzengshijian;
    }

    @Override
    public String toString() {
        return "DianLanJieTou{" +
                "uuid='" + uuid + '\'' +
                ", jieTouName='" + jieTouName + '\'' +
                ", nummber='" + nummber + '\'' +
                ", suoshubiandianzhan='" + suoshubiandianzhan + '\'' +
                ", suoshuxianlu='" + suoshuxianlu + '\'' +
                ", anzhuangweizhi='" + anzhuangweizhi + '\'' +
                ", anzhuangweizhi_leixing='" + anzhuangweizhi_leixing + '\'' +
                ", biaoqianhao='" + biaoqianhao + '\'' +
                ", anzhuangweizhi_jingdu='" + anzhuangweizhi_jingdu + '\'' +
                ", anzhuangweizhi_weidu='" + anzhuangweizhi_weidu + '\'' +
                ", beizhu='" + beizhu + '\'' +
                ", zuoyedanwei='" + zuoyedanwei + '\'' +
                ", xinzengshijian='" + xinzengshijian + '\'' +
                '}';
    }
}
