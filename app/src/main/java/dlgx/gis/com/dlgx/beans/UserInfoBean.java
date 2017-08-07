package dlgx.gis.com.dlgx.beans;

/**
 * 用户信息
 * <p/>
 * Created by Joe on 16/5/7.
 */
public class UserInfoBean {
   //用户ID
    private String id = "";

    private String app_token = "";

    private String name = "";

    private String category_id = "";

    private String token_end_time = "";
    //密码
    private String password = "";

   //作业单位
    private String zuoyedanwei = "";

    /**
     * 展示
     */
    private String show_id = "";

    private String show_title = "";

    private String show_pid = "";

    /**
     * 调节
     */
    private String handle_id = "";

    private String handle_title = "";

    private String handle_pid = "";

    private String handle_tel = "";

    private String handle_address = "";

    private String handle_court = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApp_token() {
        return app_token;
    }

    public void setApp_token(String app_token) {
        this.app_token = app_token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getToken_end_time() {
        return token_end_time;
    }

    public void setToken_end_time(String token_end_time) {
        this.token_end_time = token_end_time;
    }

    public String getShow_id() {
        return show_id;
    }

    public void setShow_id(String show_id) {
        this.show_id = show_id;
    }

    public String getShow_title() {
        return show_title;
    }

    public void setShow_title(String show_title) {
        this.show_title = show_title;
    }

    public String getShow_pid() {
        return show_pid;
    }

    public void setShow_pid(String show_pid) {
        this.show_pid = show_pid;
    }

    public String getHandle_id() {
        return handle_id;
    }

    public void setHandle_id(String handle_id) {
        this.handle_id = handle_id;
    }

    public String getHandle_title() {
        return handle_title;
    }

    public void setHandle_title(String handle_title) {
        this.handle_title = handle_title;
    }

    public String getHandle_pid() {
        return handle_pid;
    }

    public void setHandle_pid(String handle_pid) {
        this.handle_pid = handle_pid;
    }

    public String getHandle_tel() { return handle_tel; }

    public void setHandle_tel(String handle_tel) {
        this.handle_tel = handle_tel;
    }

    public String getHandle_address() {
        return handle_address;
    }

    public void setHandle_address(String handle_address) {
        this.handle_address = handle_address;
    }

    public String getHandle_court() {
        return handle_court;
    }

    public void setHandle_court(String handle_court) {
        this.handle_court = handle_court;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZuoyedanwei() {
        return zuoyedanwei;
    }

    public void setZuoyedanwei(String zuoyedanwei) {
        this.zuoyedanwei = zuoyedanwei;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "id='" + id + '\'' +
                ", app_token='" + app_token + '\'' +
                ", name='" + name + '\'' +
                ", category_id='" + category_id + '\'' +
                ", token_end_time='" + token_end_time + '\'' +
                ", show_id='" + show_id + '\'' +
                ", show_title='" + show_title + '\'' +
                ", show_pid='" + show_pid + '\'' +
                ", handle_id='" + handle_id + '\'' +
                ", handle_title='" + handle_title + '\'' +
                ", handle_pid='" + handle_pid + '\'' +
                ", handle_tel='" + handle_tel + '\'' +
                ", handle_address='" + handle_address + '\'' +
                ", handle_court='" + handle_court + '\'' +
                '}';
    }

}
