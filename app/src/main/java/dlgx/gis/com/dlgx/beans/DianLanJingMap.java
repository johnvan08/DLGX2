package dlgx.gis.com.dlgx.beans;

/**
 * Created by admin on 2017/5/11.
 */
public class DianLanJingMap {


    private String id="";
    //电缆井名称
    //@Column(name = "name")
    private String name = "";
    //现场编号
    //@Column(name = "nummber")
    private String nummber = "";
    //井盖数量
    //@Column(name = "shuliang")
    private String shuliang= "";
    //井材质（详情请查看属性接口）
    //@Column(name = "caizhi1")
    private String caizhi = "";
    //井形状（详情请查看属性接口）
    //@Column(name = "xingzhuang")
    private String xingzhuang = "";
    //井盖尺寸
    //@Column(name = "chicun")
    private String chicun = "";
    //井长
    //@Column(name = "jingchang")
    private String jingchang = "";
    //井宽
    //@Column(name = "jingkuan")
    private String jingkuan = "";
    //井内深
    //@Column(name = "jingneishen ")
    private String jingneishen = "";
    //井深
    //@Column(name = "jingshen")
    private String jingshen = "";
    //功能（详情请查看属性接口）
    //@Column(name = "gongneng")
    private String gongneng = "";
    //类型
    //@Column(name = "leixing")
    private String leixing= "";
    //经度
    //@Column(name = "jingdu")
    private double jingdu;
    //@Column(name = "leixing")
    //纬度
    private double weidu;
    //高度
    //@Column(name = "jingdu")
    private String gaodu = "";
    //接头数量
    //@Column(name = "jietoushuliang")
    private String jietoushuliang= "";
    //@Column(name = "shebeiId")
    //设备id
    private String shebeiId= "";
    //电子标签编号
    //@Column(name = "dianzibainqianbianhao ")
    private String dianzibainqianbianhao = "";
    //投运日期
    //@Column(name = "touyunriqi")
    private String touyunriqi= "";
    //施工单位
    //@Column(name = "shigongdanwei")
    private String shigongdanwei= "";
    //备注
    //@Column(name = "beizhu")
    private String beizhu = "";
    //作业单位
    //@Column(name = "zuoyedanwei")
    private String zuoyedanwei= "";
    //新增时间
    //@Column(name = "xinzengshijian ")
    private String xinzengshijian = "";
    private String leixing_biaoji = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang;
    }

    public String getCaizhi() {
        return caizhi;
    }

    public void setCaizhi(String caizhi) {
        this.caizhi = caizhi;
    }

    public String getXingzhuang() {
        return xingzhuang;
    }

    public void setXingzhuang(String xingzhuang) {
        this.xingzhuang = xingzhuang;
    }

    public String getChicun() {
        return chicun;
    }

    public void setChicun(String chicun) {
        this.chicun = chicun;
    }

    public String getJingchang() {
        return jingchang;
    }

    public void setJingchang(String jingchang) {
        this.jingchang = jingchang;
    }

    public String getJingkuan() {
        return jingkuan;
    }

    public void setJingkuan(String jingkuan) {
        this.jingkuan = jingkuan;
    }

    public String getJingneishen() {
        return jingneishen;
    }

    public void setJingneishen(String jingneishen) {
        this.jingneishen = jingneishen;
    }

    public String getGongneng() {
        return gongneng;
    }

    public void setGongneng(String gongneng) {
        this.gongneng = gongneng;
    }

    public String getJingshen() {
        return jingshen;
    }

    public void setJingshen(String jingshen) {
        this.jingshen = jingshen;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public double getJingdu() {
        return jingdu;
    }

    public void setJingdu(double jingdu) {
        this.jingdu = jingdu;
    }

    public double getWeidu() {
        return weidu;
    }

    public void setWeidu(double weidu) {
        this.weidu = weidu;
    }

    public String getGaodu() {
        return gaodu;
    }

    public void setGaodu(String gaodu) {
        this.gaodu = gaodu;
    }

    public String getJietoushuliang() {
        return jietoushuliang;
    }

    public void setJietoushuliang(String jietoushuliang) {
        this.jietoushuliang = jietoushuliang;
    }

    public String getShebeiId() {
        return shebeiId;
    }

    public void setShebeiId(String shebeiId) {
        this.shebeiId = shebeiId;
    }

    public String getDianzibainqianbianhao() {
        return dianzibainqianbianhao;
    }

    public void setDianzibainqianbianhao(String dianzibainqianbianhao) {
        this.dianzibainqianbianhao = dianzibainqianbianhao;
    }

    public String getTouyunriqi() {
        return touyunriqi;
    }

    public void setTouyunriqi(String touyunriqi) {
        this.touyunriqi = touyunriqi;
    }

    public String getShigongdanwei() {
        return shigongdanwei;
    }

    public void setShigongdanwei(String shigongdanwei) {
        this.shigongdanwei = shigongdanwei;
    }

    public String getZuoyedanwei() {
        return zuoyedanwei;
    }

    public void setZuoyedanwei(String zuoyedanwei) {
        this.zuoyedanwei = zuoyedanwei;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getXinzengshijian() {
        return xinzengshijian;
    }

    public void setXinzengshijian(String xinzengshijian) {
        this.xinzengshijian = xinzengshijian;
    }

    public String getLeixing_biaoji() {
        return leixing_biaoji;
    }

    public void setLeixing_biaoji(String leixing_biaoji) {
        this.leixing_biaoji = leixing_biaoji;
    }
}
