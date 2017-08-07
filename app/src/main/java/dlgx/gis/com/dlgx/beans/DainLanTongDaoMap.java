package dlgx.gis.com.dlgx.beans;

/**
 * Created by admin on 2017/5/11.
 */
public class DainLanTongDaoMap {
    //电缆井名称

    private String name = "";
    //现场编号

    private String nummber = "";
    //通道长度

    private String tongdaolong= "";
    //结构形式

    private String jiegouxingshi = "";
    //起点经度

    private double startjingdu ;
    //起点纬度
    //@Column(name = "startweidu")
    private double startweidu ;
    //终点经度
    //@Column(name = "endjingdu")
    private double endjingdu ;
    //终点纬度
    //@Column(name = "endweidu")
    private double endweidu ;
    //顶部埋深
    //@Column(name = "dingbumaishen")
    private String dingbumaishen = "";
    //底部埋深
    //@Column(name = "dibumaishen")
    private String dibumaishen = "";
    //管沟宽度
    //@Column(name = "guangoukuandu")
    private String guangoukuandu = "";
    //井数量
    //@Column(name = "jingshuliang")
    private String jingshuliang= "";
    //管径
    //@Column(name = "guanjing")
    private String guanjing = "";
    //材质
    //@Column(name = "caizhi")
    private String caizhi= "";
    //类型
    //@Column(name = "leixing")
    private String leixing = "";
    //施工单位
    //@Column(name = "jietoushuliang")
    private String sigongdanwei= "";
    //投运日期
    //@Column(name = "touyunriqi")
    private String touyunriqi= "";
    //作业单位
    //@Column(name = "zuoyedanwei")
    private String zuoyedanwei= "";
    //新增时间
    //@Column(name = "xinzengshijian ")
    private String xinzengshijian = "";


    private double guaidianjingdu;
    private double guaidianweidu;

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

    public String getTongdaolong() {
        return tongdaolong;
    }

    public void setTongdaolong(String tongdaolong) {
        this.tongdaolong = tongdaolong;
    }

    public String getJiegouxingshi() {
        return jiegouxingshi;
    }

    public void setJiegouxingshi(String jiegouxingshi) {
        this.jiegouxingshi = jiegouxingshi;
    }

    public double getStartjingdu() {
        return startjingdu;
    }

    public void setStartjingdu(double startjingdu) {
        this.startjingdu = startjingdu;
    }

    public double getStartweidu() {
        return startweidu;
    }

    public void setStartweidu(double startweidu) {
        this.startweidu = startweidu;
    }

    public double getEndjingdu() {
        return endjingdu;
    }

    public void setEndjingdu(double endjingdu) {
        this.endjingdu = endjingdu;
    }

    public double getEndweidu() {
        return endweidu;
    }

    public void setEndweidu(double endweidu) {
        this.endweidu = endweidu;
    }

    public String getDingbumaishen() {
        return dingbumaishen;
    }

    public void setDingbumaishen(String dingbumaishen) {
        this.dingbumaishen = dingbumaishen;
    }

    public String getDibumaishen() {
        return dibumaishen;
    }

    public void setDibumaishen(String dibumaishen) {
        this.dibumaishen = dibumaishen;
    }

    public String getGuangoukuandu() {
        return guangoukuandu;
    }

    public void setGuangoukuandu(String guangoukuandu) {
        this.guangoukuandu = guangoukuandu;
    }

    public String getJingshuliang() {
        return jingshuliang;
    }

    public void setJingshuliang(String jingshuliang) {
        this.jingshuliang = jingshuliang;
    }

    public String getGuanjing() {
        return guanjing;
    }

    public void setGuanjing(String guanjing) {
        this.guanjing = guanjing;
    }

    public String getCaizhi() {
        return caizhi;
    }

    public void setCaizhi(String caizhi) {
        this.caizhi = caizhi;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public String getSigongdanwei() {
        return sigongdanwei;
    }

    public void setSigongdanwei(String sigongdanwei) {
        this.sigongdanwei = sigongdanwei;
    }

    public String getTouyunriqi() {
        return touyunriqi;
    }

    public void setTouyunriqi(String touyunriqi) {
        this.touyunriqi = touyunriqi;
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

    public double getGuaidianjingdu() {
        return guaidianjingdu;
    }

    public void setGuaidianjingdu(double guaidianjingdu) {
        this.guaidianjingdu = guaidianjingdu;
    }

    public double getGuaidianweidu() {
        return guaidianweidu;
    }

    public void setGuaidianweidu(double guaidianweidu) {
        this.guaidianweidu = guaidianweidu;
    }
}
