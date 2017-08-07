package dlgx.gis.com.dlgx.beans;

/**
 * Created by admin on 2017/5/3.
 */
//@Table(name = "GongJingXinxiBean ", onCreated = "CREATE UNIQUE INDEX index_gongjingxinxi ON gongjingxinxibean(uuid,name,nummber,shuliang,caizhi1,xingzhuang,chicun,jingchang,jingkuan,jingneishen,gongneng,leixing,jingdu,weidu,gaodu,jietoushuliang,shebeiId,dianzibainqianbianhao,touyunriqi,shigongdanwei,beizhu,zuoyedanwei,xinzengshijian)")
public class GongJingXinxiBean {
    //主键id
    //@Column(name = "uuid", isId = true)
    private String uuid = "";
    //电缆井名称
    private String name = "";
    //现场编号
    private String nummber = "";
    //井盖数量
    private String shuliang= "";
     //井材质（详情请查看属性接口）
    private String caizhi = "";
    //井形状（详情请查看属性接口）
    private String xingzhuang = "";
    //井盖尺寸
    private String chicun = "";
    //井长
    private String jingchang = "";
    //井宽
    private String jingkuan = "";
    //井内深
    private String  jingneishen ;
    //井深
    private String  jingshen ;
    //功能（详情请查看属性接口）
    private String gongneng = "";
    //类型
    private String leixing= "";
    //经度
    private String jingdu = "";
    //纬度
    private String weidu= "";
    //高度
    private String gaodu = "";
    //接头数量
    private String jietoushuliang= "";
    //设备id
    private String shebeiId= "";
    //电子标签编号
    private String dianzibainqianbianhao = "";
    //投运日期
    private String touyunriqi= "";
    //施工单位
    private String shigongdanwei= "";
    //备注
    private String beizhu = "";
    //作业单位
    private String zuoyedanwei= "";
    //新增时间
    private String xinzengshijian = "";
    //地址
    private String adress = "";

    private String Biaoji="";

    private String jingxingzhuang="";

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

    public String getBiaoji() {
        return Biaoji;
    }

    public void setBiaoji(String biaoji) {
        Biaoji = biaoji;
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

    public String getJingshen() {
        return jingshen;
    }

    public void setJingshen(String jingshen) {
        this.jingshen = jingshen;
    }

    public String getGongneng() {
        return gongneng;
    }

    public void setGongneng(String gongneng) {
        this.gongneng = gongneng;
    }


    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getJingxingzhuang() {
        return jingxingzhuang;
    }

    public void setJingxingzhuang(String jingxingzhuang) {
        this.jingxingzhuang = jingxingzhuang;
    }

    @Override
    public String toString() {
        return "GongJingXinxiBean{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", nummber='" + nummber + '\'' +
                ", shuliang='" + shuliang + '\'' +
                ", caizhi1='" + caizhi + '\'' +
                ", xingzhuang='" + xingzhuang + '\'' +
                ", chicun='" + chicun + '\'' +
                ", jingchang='" + jingchang + '\'' +
                ", jingkuan='" + jingkuan + '\'' +
                ", jingneishen='" + jingneishen + '\'' +
                ", jingshen='" + jingshen + '\'' +
                ", gongneng='" + gongneng + '\'' +
                ", leixing='" + leixing + '\'' +
                ", jingdu='" + jingdu + '\'' +
                ", weidu='" + weidu + '\'' +
                ", gaodu='" + gaodu + '\'' +
                ", jietoushuliang='" + jietoushuliang + '\'' +
                ", shebeiId='" + shebeiId + '\'' +
                ", dianzibainqianbianhao='" + dianzibainqianbianhao + '\'' +
                ", touyunriqi='" + touyunriqi + '\'' +
                ", shigongdanwei='" + shigongdanwei + '\'' +
                ", beizhu='" + beizhu + '\'' +
                ", zuoyedanwei='" + zuoyedanwei + '\'' +
                ", xinzengshijian='" + xinzengshijian + '\'' +
                '}';
    }
}
