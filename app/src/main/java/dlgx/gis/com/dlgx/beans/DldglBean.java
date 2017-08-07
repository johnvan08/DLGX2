package dlgx.gis.com.dlgx.beans;

/**电缆段关联表
 * Created by admin on 2017/5/31.
 */
public class DldglBean {
    private String id;//id
    private String nummber;//电缆段现场编号
    private String shiBei_nummber;// 电缆通道现场编号
    private String shiBei_xuHao;// 设备序号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNummber() {
        return nummber;
    }

    public void setNummber(String nummber) {
        this.nummber = nummber;
    }

    public String getShiBei_nummber() {
        return shiBei_nummber;
    }

    public void setShiBei_nummber(String shiBei_nummber) {
        this.shiBei_nummber = shiBei_nummber;
    }

    public String getShiBei_xuHao() {
        return shiBei_xuHao;
    }

    public void setShiBei_xuHao(String shiBei_xuHao) {
        this.shiBei_xuHao = shiBei_xuHao;
    }
}
