package dlgx.gis.com.dlgx.commom;

/**
 * Created by Joe on 16/5/6.
 */
public class AppConstant {

    public static final boolean isDebug = true;//调试模式

    private AppConstant() {
    }

    public static final String CHARSET_UTF8 = "UTF-8";


    /**
     * The Constant SYSPARAME_MYIMSI.
     */
    public static final String SYSPARAME_MYIMSI = "my_imsi";

    /**
     * 时间格式
     */
    public static final String DATETIME_FORMAT_STYLE_1 = "yyyy年MM月dd日";
    /**
     * 时间格式
     */
    public static final String DATETIME_FORMAT_STYLE_2 = "yyyy-MM-dd HH:mm";
    /**
     * 时间格式
     */
    public static final String DATETIME_FORMAT_STYLE_3 = "yyyy-MM-dd";
    /**
     * 时间格式
     */
    public static final String DATETIME_FORMAT_STYLE_4 = "yyyy/MM/dd";
    /**
     * 时间格式
     */
    public static final String DATETIME_FORMAT_STYLE_5 = "HH:mm";
    /**
     * 时间格式
     */
    public static final String DATETIME_FORMAT_STYLE_6 = "MM月";

    /**
     * 时间格式
     */
    public static final String DATETIME_FORMAT_STYLE_7 = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式
     */
    public static final String DATETIME_FORMAT_STYLE_8 = "yyyy年MM月dd日HH时mm分";

    /**
     * 请求结果"code"节点名
     */
    public static final String HTTP_REQUEST_RESULT_CODE = "code";
    /**
     * 请求结果"msg"节点名
     */
    public static final String HTTP_REQUEST_RESULT_MSG = "msg";
    /**
     * 请求结果"data"节点
     */
    public static final String HTTP_REQUEST_RESULT_DATA = "data";
    /**
     * 成功获取数据
     */
    public static final int HTTP_REQUEST_RESULT_OK = 200;

    /**
     * 用户BEAN
     */
    public static final String U_BEAN_ID = "id";
    public static final String U_BEAN_APP_TOKEN = "app_token";
    public static final String U_BEAN_NAME = "name";
    public static final String U_BEAN_CATEGORY_ID = "category_id";
    public static final String U_BEAN_TOKEN_END_TIME = "token_end_time";
    public static final String U_BEAN_SHOW_ID = "show_id";
    public static final String U_BEAN_SHOW_TITLE = "show_title";
    public static final String U_BEAN_SHOW_PID = "show_pid";
    public static final String U_BEAN_HANDLE_ID = "handle_id";
    public static final String U_BEAN_HANDLE_TITLE = "handle_title";
    public static final String U_BEAN_HANDLE_PID = "handle_pid";
    public static final String U_BEAN_HANDLE_TEL = "handle_tel";
    public static final String U_BEAN_HANDLE_ADRESS = "handle_address";
    public static final String U_BEAN_HANDLE_COURT = "handle_court";


    /**
     * 字符串0
     */
    public static final String NUMBER_ZERO = "0";
    /**
     * 字符串1
     */
    public static final String NUMBER_ONE = "1";
    /**
     * 数据Key
     */
    public static final String DATA_KEY = "data";

    /**
     * 定位成功
     */
    public static final String ACTION_UPDATE_LOC_OK = "cn.trafficpolice.ststem.ACTION_UPDATE_LOC_OK";
    /**
     * 号牌颜色
     */
    public static final String PROPERTY_NUMCOLOR = "numcolor";
    /**
     * 车辆类型
     */
    public static final String PROPERTY_VEHICLETYPE = "vehicletype";
    /**
     * 车辆颜色
     */
    public static final String PROPERTY_CARCOLOR = "carcolor";
    /**
     * 准驾车型
     */
    public static final String PROPERTY_ZJCX = "zjcx";
    /**
     * 车辆号码类型
     */
    public static final String PROPERTY_VEHICLENJMTYPE = "vehiclenumtype";
    /**
     * 违章处罚类型
     */
    public static final String PROPERTY_WZCFLX = "wzcflx";
    /**
     * ACCESSTOKEN
     */
    public static final String ACCESSTOKEN = "ACCESSTOKEN";


    /**
     * 数据库名称
     */
    public static final String DBNAME = "traffic_qxbdb.db";


}
