package dlgx.gis.com.dlgx.commom;


import dlgx.gis.com.dlgx.beans.UserInfoBean;

/**
 * The Interface ISystemConfigDS.
 */
public interface ISystemParamConfig {

    /**
     * Gets the my imsi.
     *
     * @return the my imsi
     */
    public abstract String getMyIMSI();

    /**
     * Sets the my imsi.
     *
     * @param imsi the new my imsi
     */
    public abstract void setMyIMSI(String imsi);

    /**
     * Check mobile network.
     *
     * @return true, if successful
     * @version
     */
    public abstract boolean checkMobileNetwork();

    /**
     * Check network type.
     *
     * @return true, if successful
     * @version
     */
    public abstract boolean checkNetworkType();

    /**
     * Check http network.
     *
     * @return true, if successful
     * @version
     */
    public abstract boolean checkHTTPNetwork();

    /**
     * Check is airplane mode on.
     *
     * @return true, if successful
     * @version
     */
    public abstract boolean checkIsAirplaneModeOn();

    /**
     * Check is imsi.
     *
     * @return true, if successful
     * @version
     */
    public abstract boolean checkIsIMSI();

    // /**
    // * Check sd card is null.
    // *
    // * @return true, if successful
    // * @version
    // */
    // public abstract boolean checkSDCardIsNull();


    /**
     * Gets the first install flag.
     *
     * @return the first install flag
     */
    public boolean getFirstInstallFlag();

    /**
     * Sets the first install flag.
     *
     * @param isFirstInstallFlag the is first install flag
     * @version
     */
    public void setFirstInstallFlag(boolean isFirstInstallFlag);

    /**
     * Sets the UserInfo.
     *
     * @param bean
     */
    public void setUserInfo(UserInfoBean bean);


    /**
     * Gets the UserInfo.
     *
     * @return
     */
    public UserInfoBean getUserInfo();
}