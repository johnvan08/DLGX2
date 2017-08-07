package dlgx.gis.com.dlgx.beans;

/**电缆通道关联表
 * Created by admin on 2017/5/30
 */
public class  DltdglBean {
    private String id;//id
    private String nummber;// 电缆通道现场编号 guid  再点击完成按钮的时候  将guid  换成已经生成的现场编号
    private String shiBei_nummber;// 设备/电缆井/拐点现场编号
    private String xuHao;// 设备/电缆井/拐点序号
    private String shebeiType;// 判断类型

    public String getShebeiType() {
        return shebeiType;
    }

    public void setShebeiType(String shebeiType) {
        this.shebeiType = shebeiType;
    }


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

    public String getXuHao() {
        return xuHao;
    }

    public void setXuHao(String xuHao) {
        this.xuHao = xuHao;
    }
}
