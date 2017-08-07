package dlgx.gis.com.dlgx.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import dlgx.gis.com.dlgx.application.MyApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * Created by admin on 2017/4/18.
 */
public class PerfHelper {

    private static final String P_NAME = "artanddesign_traffic";
    public static final String WIDTH = "p_width";
    public static final String HEIGHT = "p_height";
    private static SharedPreferences sp;
    private static PerfHelper ph;

    private PerfHelper() {

    }
    public static PerfHelper getPerferences(Context a) {
        if (ph == null) {
            ph = new PerfHelper();
            sp = MyApplication.sInstance.getSharedPreferences(P_NAME, 0);
        }
        return ph;
    }

    public static PerfHelper getPerferences() {
        return ph;
    }

    public static void setInfo(String name, String data) {
        if (ph == null) {
            ph = new PerfHelper();
            sp = MyApplication.sInstance.getSharedPreferences(P_NAME, 0);
        }
        SharedPreferences.Editor e = sp.edit().putString(name, data);
        e.commit();
    }

    public static void setInfo(String name, int data) {
        if (ph == null) {
            ph = new PerfHelper();
            sp = MyApplication.sInstance.getSharedPreferences(P_NAME, 0);
        }
        SharedPreferences.Editor e = sp.edit().putInt(name, data);
        e.commit();
    }

    public static void setInfo(String name, boolean data) {
        if (ph == null) {
            ph = new PerfHelper();
            sp =MyApplication.sInstance.getSharedPreferences(P_NAME, 0);
        }
        SharedPreferences.Editor e = sp.edit().putBoolean(name, data);
        e.commit();
    }

    public static void setInfo(String name, Object data) {
        if (ph == null) {
            ph = new PerfHelper();
            sp = MyApplication.sInstance.getSharedPreferences(P_NAME, 0);
        }
        SharedPreferences.Editor e = sp.edit().putString(name, saveOAuth(data));
        e.commit();
    }

    public static Object getObjData(String name) {
        if (ph == null) {
            ph = new PerfHelper();
            sp = MyApplication.sInstance.getSharedPreferences(P_NAME, 0);
        }
        String rest = sp.getString(name, "");
        return readOAuth(rest);
    }

    public static int getIntData(String name) {
        if (ph == null) {
            ph = new PerfHelper();
            sp = MyApplication.sInstance.getSharedPreferences(P_NAME, 0);
        }
        return sp.getInt(name, 0);
    }

    public static String getStringData(String name) {
        if (ph == null) {
            ph = new PerfHelper();
            sp = MyApplication.sInstance.getSharedPreferences(P_NAME, 0);
        }
        return sp.getString(name, "");
    }

    /**
     * @param name
     * @param defaultStr
     * @return 自定义默认值的String
     */
    public static String getStringData(String name, String defaultStr) {
        if (ph == null) {
            ph = new PerfHelper();
            sp = MyApplication.sInstance.getSharedPreferences(P_NAME, 0);
        } else {
            sp = MyApplication.sInstance.getSharedPreferences(P_NAME, 0);
        }
        if (sp == null){
            sp = MyApplication.sInstance.getSharedPreferences(P_NAME, 0);
        }

        return sp.getString(name, defaultStr);
    }

    public static boolean getBooleanData(String name) {
        if (ph == null) {
            ph = new PerfHelper();
            sp = MyApplication.sInstance.getSharedPreferences(P_NAME, 0);
        }
        return sp.getBoolean(name, false);
    }

    public static void setInfo(String name, long data) {
        if (ph == null) {
            ph = new PerfHelper();
            sp = MyApplication.sInstance.getSharedPreferences(P_NAME, 0);
        }
        SharedPreferences.Editor e = sp.edit().putLong(name, data);
        e.commit();
    }

    public static long getLongData(String name) {
        if (ph == null) {
            ph = new PerfHelper();
            sp = MyApplication.sInstance.getSharedPreferences(P_NAME, 0);
        }
        return sp.getLong(name, 0);
    }

    public static String saveOAuth(Object oAuth_1) {
        String oAuth_Base64 = "";
        // 创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // 创建对象输出流，并封装字节流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // 将对象写入字节流
            oos.writeObject(oAuth_1);
            // 将字节流编码成base64的字符窜
            oAuth_Base64 = new String(Base64.encode(baos.toByteArray(),0));
        } catch (IOException e) {
            // TODO Auto-generated
        }
        return oAuth_Base64;
    }

    public static Object readOAuth(String str) {
        Object oAuth_1 = null;
        //读取字节
        byte[] base64 = Base64.decode(str.getBytes(),0);
        //封装到字节流
        ByteArrayInputStream bais = new ByteArrayInputStream(base64);
        try {
            //再次封装
            ObjectInputStream bis = new ObjectInputStream(bais);
            try {
                //读取对象
                oAuth_1 = (Object) bis.readObject();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (StreamCorruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return oAuth_1;
    }
}
