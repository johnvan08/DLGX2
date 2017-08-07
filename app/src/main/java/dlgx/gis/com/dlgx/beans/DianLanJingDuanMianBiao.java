package dlgx.gis.com.dlgx.beans;

/**
 * 电缆井断面表
 * Created by admin on 2017/5/4.
 */
//@Table(name = "DianLanJingDuanMianBiao")
public class DianLanJingDuanMianBiao {
    //主键id
    private String uuid = "";
    //断面名称
    private String name = "";
    //现场编号
    private String nummber = "";
    //方位
    private String fangwei= "";
    //断面序号
    private String duanmianxuhao = "";
    //上边距
    private int shangbianju ;
    //下边距
    private String xiabianju = "";
    //左边距
    private String zuobianju= "";
    //右边距
    private String youbianju = "";
    //行数
    private String hangshu = "";
    //列数
    private String lieshu= "";
    private String adress= "";

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

    public String getFangwei() {
        return fangwei;
    }

    public void setFangwei(String fangwei) {
        this.fangwei = fangwei;
    }

    public String getDuanmianxuhao() {
        return duanmianxuhao;
    }

    public void setDuanmianxuhao(String duanmianxuhao) {
        this.duanmianxuhao = duanmianxuhao;
    }

    public int getShangbianju() {
        return shangbianju;
    }

    public void setShangbianju(int shangbianju) {
        this.shangbianju = shangbianju;
    }

    public String getXiabianju() {
        return xiabianju;
    }

    public void setXiabianju(String xiabianju) {
        this.xiabianju = xiabianju;
    }

    public String getZuobianju() {
        return zuobianju;
    }

    public void setZuobianju(String zuobianju) {
        this.zuobianju = zuobianju;
    }

    public String getYoubianju() {
        return youbianju;
    }

    public void setYoubianju(String youbianju) {
        this.youbianju = youbianju;
    }

    public String getHangshu() {
        return hangshu;
    }

    public void setHangshu(String hangshu) {
        this.hangshu = hangshu;
    }

    public String getLieshu() {
        return lieshu;
    }

    public void setLieshu(String lieshu) {
        this.lieshu = lieshu;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "DianLanJingDuanMianBiao{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", nummber='" + nummber + '\'' +
                ", fangwei='" + fangwei + '\'' +
                ", duanmianxuhao='" + duanmianxuhao + '\'' +
                ", shangbianju='" + shangbianju + '\'' +
                ", xiabianju='" + xiabianju + '\'' +
                ", zuobianju='" + zuobianju + '\'' +
                ", youbianju='" + youbianju + '\'' +
                ", hangshu='" + hangshu + '\'' +
                ", lieshu='" + lieshu + '\'' +
                '}';
    }


}
