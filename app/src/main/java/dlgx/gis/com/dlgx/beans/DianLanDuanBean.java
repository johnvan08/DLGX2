package dlgx.gis.com.dlgx.beans;

/**
 * Created by admin on 2017/5/3.
 */
//@Table(name = "DianLanDuanBean", onCreated = "CREATE UNIQUE INDEX index_dianlanduan ON dianlanduanbean(uuid,name,nummber,suoshubiandianzhan,suoshuxianlu,dianyadengji,startname,endname,pushefangshi,changdu,zhuangtai,startjingdu,startweidu,endjingdu,endweidu,xuhao,shebei_id,biaoqianhao,zuoyedanwei,xinzengshijian)")
public class DianLanDuanBean {
    //主键id

    private String uuid = "";
    //电缆段名称

    private String name = "";
    //现场编号

    private String nummber = "";
    //所属变电站

    private String suoshubiandianzhan= "";
    //所属线路

    private String suoshuxianlu = "";
    //电压等级

    private String dianyadengji = "";
    //起始设备命名
    private String startname = "";
    //终点设备命名
    private String endname = "";
    //敷设方式
    private String pushefangshi = "";
    //电缆型号
    private String dianlanxinghao= "";
    //长度
    private String changdu= "";
    //状态
    private String zhuangtai = "";
    //起始设备经度
    private String startjingdu = "";
    //起始设备纬度
    private String startweidu= "";
    //终点设备经度
    private String endjingdu = "";
    //终点设备纬度
    private String endweidu= "";
    //电缆段序号
    private String xuhao = "";
    //设备ID
    private String shebei_id= "";
    //电子标签号
    private String biaoqianhao= "";
    //作业单位
    private String zuoyedanwei= "";
    //新增时间
    private String xinzengshijian = "";
    //起点设备id
    private String startShebei_id= "";
    //终点设备id
    private String endShebei_id = "";



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

    public String getSuoshubiandianzhan() {
        return suoshubiandianzhan;
    }

    public void setSuoshubiandianzhan(String suoshubiandianzhan) {
        this.suoshubiandianzhan = suoshubiandianzhan;
    }

    public String getNummber() {
        return nummber;
    }

    public void setNummber(String nummber) {
        this.nummber = nummber;
    }

    public String getSuoshuxianlu() {
        return suoshuxianlu;
    }

    public void setSuoshuxianlu(String suoshuxianlu) {
        this.suoshuxianlu = suoshuxianlu;
    }

    public String getDianyadengji() {
        return dianyadengji;
    }

    public void setDianyadengji(String dianyadengji) {
        this.dianyadengji = dianyadengji;
    }

    public String getStartname() {
        return startname;
    }

    public void setStartname(String startname) {
        this.startname = startname;
    }

    public String getEndname() {
        return endname;
    }

    public void setEndname(String endname) {
        this.endname = endname;
    }

    public String getPushefangshi() {
        return pushefangshi;
    }

    public void setPushefangshi(String pushefangshi) {
        this.pushefangshi = pushefangshi;
    }

    public String getChangdu() {
        return changdu;
    }

    public void setChangdu(String changdu) {
        this.changdu = changdu;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public String getStartjingdu() {
        return startjingdu;
    }

    public void setStartjingdu(String startjingdu) {
        this.startjingdu = startjingdu;
    }

    public String getStartweidu() {
        return startweidu;
    }

    public void setStartweidu(String startweidu) {
        this.startweidu = startweidu;
    }

    public String getEndjingdu() {
        return endjingdu;
    }

    public void setEndjingdu(String endjingdu) {
        this.endjingdu = endjingdu;
    }

    public String getEndweidu() {
        return endweidu;
    }

    public void setEndweidu(String endweidu) {
        this.endweidu = endweidu;
    }

    public String getXuhao() {
        return xuhao;
    }

    public void setXuhao(String xuhao) {
        this.xuhao = xuhao;
    }

    public String getShebei_id() {
        return shebei_id;
    }

    public void setShebei_id(String shebei_id) {
        this.shebei_id = shebei_id;
    }

    public String getBiaoqianhao() {
        return biaoqianhao;
    }

    public void setBiaoqianhao(String biaoqianhao) {
        this.biaoqianhao = biaoqianhao;
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

    public String getDianlanxinghao() {
        return dianlanxinghao;
    }

    public void setDianlanxinghao(String dianlanxinghao) {
        this.dianlanxinghao = dianlanxinghao;
    }

    public String getStartShebei_id() {
        return startShebei_id;
    }

    public void setStartShebei_id(String startShebei_id) {
        this.startShebei_id = startShebei_id;
    }

    public String getEndShebei_id() {
        return endShebei_id;
    }

    public void setEndShebei_id(String endShebei_id) {
        this.endShebei_id = endShebei_id;
    }

    @Override
    public String toString() {
        return "DianLanDuanBean{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", nummber='" + nummber + '\'' +
                ", suoshubiandianzhan='" + suoshubiandianzhan + '\'' +
                ", suoshuxianlu='" + suoshuxianlu + '\'' +
                ", dianyadengji='" + dianyadengji + '\'' +
                ", startname='" + startname + '\'' +
                ", endname='" + endname + '\'' +
                ", pushefangshi='" + pushefangshi + '\'' +
                ", changdu='" + changdu + '\'' +
                ", zhuangtai='" + zhuangtai + '\'' +
                ", startjingdu='" + startjingdu + '\'' +
                ", startweidu='" + startweidu + '\'' +
                ", endjingdu='" + endjingdu + '\'' +
                ", endweidu='" + endweidu + '\'' +
                ", xuhao='" + xuhao + '\'' +
                ", shebei_id='" + shebei_id + '\'' +
                ", biaoqianhao='" + biaoqianhao + '\'' +
                ", zuoyedanwei='" + zuoyedanwei + '\'' +
                ", xinzengshijian='" + xinzengshijian + '\'' +
                '}';
    }
}
