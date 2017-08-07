package dlgx.gis.com.dlgx.commom;

/**
 * The Class SystemParamHelper.getInstance().getInstance().
 */
public class SystemParamHelper {

    /** The system config ds. */
    private static ISystemParamConfig systemConfigDS = new SystemParamConfig();

    /**
     * Gets the single instance of SystemParamHelper.
     * 
     * @return single instance of SystemParamHelper
     */
    public static ISystemParamConfig getInstance() {
        return systemConfigDS;
    }

    /**
     * @param systemConfigDS
     *            the systemConfigDS to set
     */
    public static void setSystemConfigDS(ISystemParamConfig systemConfigDS) {
        SystemParamHelper.systemConfigDS = systemConfigDS;
    }

}
