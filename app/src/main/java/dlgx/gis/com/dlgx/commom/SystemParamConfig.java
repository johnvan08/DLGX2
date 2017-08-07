package dlgx.gis.com.dlgx.commom;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import dlgx.gis.com.dlgx.beans.UserInfoBean;
import dlgx.gis.com.dlgx.utils.AndroidUtil;


/**
 * The Class SystemParamConfig.
 *
 * @author Administrator
 */
public class SystemParamConfig implements ISystemParamConfig {

    /**
     * The Constant TAG.
     */
    private static final String TAG = SystemParamConfig.class.getName();

    /**
     * Gets the my imsi.
     *
     * @return the my imsi
     */
    @Override
    public String getMyIMSI() {
        Context context = AppContext.getContext();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String imsi = settings.getString(AppConstant.SYSPARAME_MYIMSI, "");

        if (imsi != null && !imsi.equals("")) {
            // try {
            // imsi = Coder.decryptBeas64DES(imsi);
            // } catch (CoderException e) {
            // OperLog.error(TAG, "", e);
            // throw new SystemRuntimeException(e);
            // }
        }
        return imsi;
    }

    /**
     * Sets the my imsi.
     *
     * @param imsi the new my imsi
     */
    @Override
    public void setMyIMSI(String imsi) {

        if (imsi == null || imsi.trim().equals("")) {
            Log.i(TAG, "imsi can not be null");
            throw new IllegalArgumentException();
        }

        Context context = AppContext.getContext();
        if (imsi != null && !imsi.equals("")) {
            // try {
            // imsi = Coder.encryptDESBeas64(imsi);
            // } catch (CoderException e) {
            // OperLog.error(TAG, "", e);
            // throw new SystemRuntimeException(e);
            // }
        }

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(AppConstant.SYSPARAME_MYIMSI, imsi);
        editor.commit();
    }

    /**
     * Check mobile network.
     *
     * @return true, if successful
     * @version
     */
    @Override
    public boolean checkMobileNetwork() {
        Context context = AppContext.getContext();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        /* 手机类型： 例如： PHONE_TYPE_NONE 无信号 PHONE_TYPE_GSM GSM信号 PHONE_TYPE_CDMA
         * CDMA信号 */
        int phoneType = tm.getPhoneType();
        if (phoneType == TelephonyManager.PHONE_TYPE_NONE) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check network type(WIFI).
     *
     * @return true, if successful
     * @version
     */
    @Override
    public boolean checkNetworkType() {
        // true wifi开启，false wifi关闭
        Context context = AppContext.getContext();
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = manager.getAllNetworkInfo();
        if (networkInfos != null) {
            for (NetworkInfo networkInfo : networkInfos) {
                State state = networkInfo.getState();
                int netType = networkInfo.getType();
                if (netType == ConnectivityManager.TYPE_WIFI && state.equals(State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Check http network.
     *
     * @return true, if successful
     * @version
     */
    @Override
    public boolean checkHTTPNetwork() {
        Context context = AppContext.getContext();
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = manager.getAllNetworkInfo();
        if (networkInfos != null) {
            for (NetworkInfo networkInfo : networkInfos) {
                State state = networkInfo.getState();
                if (state.equals(State.CONNECTED)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check is airplane mode on.
     *
     * @return true, if successful
     * @version
     */
    @Override
    public boolean checkIsAirplaneModeOn() {
        if (AndroidUtil.isAirplaneModeOn()) {
            return true;
        }
        return false;
    }

    /**
     * Check is imsi.
     *
     * @return true, if successful
     * @version
     */
    @Override
    public boolean checkIsIMSI() {
        String newSIM = AndroidUtil.getIMSI();
        if (newSIM != null && !newSIM.trim().equals("")) {
            return true;
        }
        return false;
    }

    // /**
    // * Check sd card is null.
    // *
    // * @return true, if successful
    // * @version
    // */
    // @Override
    // public boolean checkSDCardIsNull() {
    // if (StringUtil.isNull(AppConstant.SDCARD_ROOT)) {
    // return true;
    // }
    // return false;
    // }

    /**
     * Gets the first install flag.
     *
     * @return the first install flag
     */
    @Override
    public boolean getFirstInstallFlag() {
        Context context = AppContext.getContext();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
//        boolean flag = settings.getBoolean(AppConstant.SYSPARAME_FIRSTINSTALLFLAG, true);
        return false;
    }

    /**
     * Sets the first install flag.
     *
     * @param isFirstInstallFlag the is first install flag
     * @version
     */
    @Override
    public void setFirstInstallFlag(boolean isFirstInstallFlag) {
        Context context = AppContext.getContext();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
//        editor.putBoolean(AppConstant.SYSPARAME_FIRSTINSTALLFLAG, isGuideInstallFlag);
        editor.commit();

    }


    /**
     * Sets the UserInfo.
     *
     * @param bean
     */
    public void setUserInfo(UserInfoBean bean) {
        Context context = AppContext.getContext();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString(AppConstant.U_BEAN_ID, bean.getId());
        editor.putString(AppConstant.U_BEAN_APP_TOKEN, bean.getApp_token());
        editor.putString(AppConstant.U_BEAN_NAME, bean.getName());
        editor.putString(AppConstant.U_BEAN_CATEGORY_ID, bean.getCategory_id());
        editor.putString(AppConstant.U_BEAN_TOKEN_END_TIME,bean.getToken_end_time());
        editor.putString(AppConstant.U_BEAN_SHOW_ID, bean.getShow_id());
        editor.putString(AppConstant.U_BEAN_SHOW_TITLE,bean.getShow_title());
        editor.putString(AppConstant.U_BEAN_SHOW_PID,bean.getShow_pid());
        editor.putString(AppConstant.U_BEAN_HANDLE_ID,bean.getHandle_id());
        editor.putString(AppConstant.U_BEAN_HANDLE_TITLE,bean.getHandle_title());
        editor.putString(AppConstant.U_BEAN_HANDLE_PID, bean.getHandle_pid());
        editor.putString(AppConstant.U_BEAN_HANDLE_TEL, bean.getHandle_tel());
        editor.putString(AppConstant.U_BEAN_HANDLE_ADRESS, bean.getHandle_address());
        editor.putString(AppConstant.U_BEAN_HANDLE_COURT, bean.getHandle_court());

        editor.commit();

    }


    /**
     * Gets the UserInfo.
     *
     * @return
     */
    public UserInfoBean getUserInfo() {

        Context context = AppContext.getContext();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

        UserInfoBean bean = new UserInfoBean();
        bean.setId(settings.getString(AppConstant.U_BEAN_ID, ""));
        bean.setApp_token(settings.getString(AppConstant.U_BEAN_APP_TOKEN, ""));
        bean.setName(settings.getString(AppConstant.U_BEAN_NAME, ""));
        bean.setCategory_id(settings.getString(AppConstant.U_BEAN_CATEGORY_ID, ""));
        bean.setToken_end_time(settings.getString(AppConstant.U_BEAN_TOKEN_END_TIME, ""));
        bean.setShow_id(settings.getString(AppConstant.U_BEAN_SHOW_ID, ""));
        bean.setShow_title(settings.getString(AppConstant.U_BEAN_SHOW_TITLE, ""));
        bean.setShow_pid(settings.getString(AppConstant.U_BEAN_SHOW_PID, ""));
        bean.setHandle_id(settings.getString(AppConstant.U_BEAN_HANDLE_ID, ""));
        bean.setHandle_title(settings.getString(AppConstant.U_BEAN_HANDLE_TITLE, ""));
        bean.setHandle_pid(settings.getString(AppConstant.U_BEAN_HANDLE_PID, ""));
        bean.setHandle_tel(settings.getString(AppConstant.U_BEAN_HANDLE_TEL, ""));
        bean.setHandle_address(settings.getString(AppConstant.U_BEAN_HANDLE_ADRESS, ""));
        bean.setHandle_court(settings.getString(AppConstant.U_BEAN_HANDLE_COURT, ""));


        return bean;
    }

}
