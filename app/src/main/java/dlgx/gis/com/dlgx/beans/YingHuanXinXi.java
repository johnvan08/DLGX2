package dlgx.gis.com.dlgx.beans;

/**
 * Created by admin on 2017/5/18.
 */
public class YingHuanXinXi {
    //主键id
    private String uuid = "";
    //隐患等级
    private String yinghuandengji = "";
    //隐患类型
    private String yinghuanleixing = "";
    //隐患备注
    private String yinghuanbeizhu= "";
    //隐患位置
    private String yinghuanweizhi = "";
    //精度
    private String jingdu = "";
    //纬度
    private String weidu = "";
    //日期
    private String xinzengshijian = "";
    //作业单位
    private String zuoyedanwei= "";

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getYinghuandengji() {
        return yinghuandengji;
    }

    public void setYinghuandengji(String yinghuandengji) {
        this.yinghuandengji = yinghuandengji;
    }

    public String getYinghuanleixing() {
        return yinghuanleixing;
    }

    public void setYinghuanleixing(String yinghuanleixing) {
        this.yinghuanleixing = yinghuanleixing;
    }

    public String getYinghuanbeizhu() {
        return yinghuanbeizhu;
    }

    public void setYinghuanbeizhu(String yinghuanbeizhu) {
        this.yinghuanbeizhu = yinghuanbeizhu;
    }

    public String getJingdu() {
        return jingdu;
    }

    public void setJingdu(String jingdu) {
        this.jingdu = jingdu;
    }

    public String getYinghuanweizhi() {
        return yinghuanweizhi;
    }

    public void setYinghuanweizhi(String yinghuanweizhi) {
        this.yinghuanweizhi = yinghuanweizhi;
    }

    public String getWeidu() {
        return weidu;
    }

    public void setWeidu(String weidu) {
        this.weidu = weidu;
    }

    public String getXinzengshijian() {
        return xinzengshijian;
    }

    public void setXinzengshijian(String xinzengshijian) {
        this.xinzengshijian = xinzengshijian;
    }

    public String getZuoyedanwei() {
        return zuoyedanwei;
    }

    public void setZuoyedanwei(String zuoyedanwei) {
        this.zuoyedanwei = zuoyedanwei;
    }
}
