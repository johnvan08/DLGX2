package dlgx.gis.com.dlgx.beans;

import java.io.Serializable;

/**
 * Created by admin on 2017/6/9.
 */
public class ShaiXuanBeans implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String userCode;
    private String userName;
    private boolean checked;    //保存复选框的状态

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
