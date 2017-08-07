package dlgx.gis.com.dlgx.commom;

import android.content.Context;

/**
 * Created by admin on 2017/4/18.
 */
public class AppContext {
    /** The context. */
    private static Context context;

    /**
     * Gets the context.
     *
     * @return the context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * Sets the context.
     *
     * @param paramContext
     *            the new context
     */
    public static void setContext(Context paramContext) {
        context = paramContext;
    }
}

