package dlgx.gis.com.dlgx.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.commom.AppContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.List;


/**
 * Created by admin on 2017/4/18.
 */
public class AndroidUtil {
/** The Constant TAG. */
//    private static final String TAG = AndroidUtil.class.getName();

/** The notification id. */
private static final int NOTIFICATION_ID = 0;

/**
 * 获取手机设备信息,增加到激活短信中 vdv厂家信息、 mdv设备信息、 osv终端系统信息，.
 *
 * @return String
 * @author chen.xin
 */

//    public static String collectTelDeviceInfo() {
//        String vdv = android.os.Build.MANUFACTURER;
//        String mdv = android.os.Build.MODEL;
//        String osv = android.os.Build.VERSION.RELEASE;
//        StringBuffer sb = new StringBuffer();
//        String svdv = StringUtils.getDeviceInfo(vdv, ConstantsCode.VDV);
//        String smdv = StringUtils.getDeviceInfo(mdv, ConstantsCode.MDV);
//        String sosv = StringUtils.getDeviceInfo(osv, ConstantsCode.OSV);
//
//        return sb.append(svdv).append(smdv).append(sosv).toString();
//    }
//
//    /**
//     * 显示Notification.
//     *
//     * @param activity
//     *            the activity
//     * @param noticeTitle
//     *            the notice title
//     * @param noticeName
//     *            the notice name
//     * @param notice
//     *            the notice
//     * @version
//     */
//    @SuppressWarnings("deprecation")
//    public static void showNotice(Class<?> activity, String noticeTitle, String noticeName, String notice) {
//        Context context = AppContext.getContext();
//        // 声明通知（消息）管理器，初始化NotificationManager对象
//        NotificationManager m_NotificationManager = (NotificationManager) context
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        // 点击通知时转移内容
//        Intent m_Intent = new Intent(context, activity);
//        // 主要是设置点击通知时显示内容的类
//        PendingIntent m_PendingIntent = PendingIntent.getActivity(context, 0, m_Intent, 0);
//        // 声明Notification对象
//        Notification m_Notification = new Notification();
//        m_Notification.flags = Notification.FLAG_AUTO_CANCEL;
//        // 设置通知在状态栏显示的图标
//        m_Notification.icon = R.drawable.logo;
//        // 当我们点击通知时显示的内容
//        m_Notification.tickerText = noticeTitle;
//        // 通知时发出默认的声音
//        m_Notification.defaults = Notification.DEFAULT_SOUND;
//        // 设置通知显示的参数
//        m_Notification.setLatestEventInfo(context, noticeName, notice, m_PendingIntent);
//        // 可以理解为执行这个通知
//        m_NotificationManager.notify(NOTIFICATION_ID, m_Notification);
//    }

/**
 * 取消Notification.
 *
 * @version
 */
public static void cancleNotice() {
        Context context = AppContext.getContext();
        // 声明通知（消息）管理器，初始化NotificationManager对象
        NotificationManager m_NotificationManager = (NotificationManager) context
        .getSystemService(Context.NOTIFICATION_SERVICE);
        m_NotificationManager.cancel(NOTIFICATION_ID);
        }

/**
 * 获取手机号码，由运营商写入SIM卡中存储，如果运营商未写入则获取不到。.
 *
 * @return String
 */
public static String getPhonenumber() {
        Context context = AppContext.getContext();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();
        }

/**
 * 获取手机设备号
 *
 * @return String
 */
public static String getDeviceId() {
        Context context = AppContext.getContext();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
        }

/**
 * 获取IMEI
 *
 * <pre>
 * IMEI：国际移动设备辨识码，15位数字，全球唯一，标识了GSM和UMTS网络里唯一的一个手机. 它通常被打印在手机里电池下面的那一面，拨*#06#能查到。
 * IMEI由TAC+FAC+SNR+SP组成。
 * TAC：型号核准号码，共6位，一般代码机型。
 * FAC：最后装配号码，共2位，一般代表产地。
 * SNR：串号，共6位，一般代表重产顺序号。
 * SP：检验码，共1位，一般为0，备用。
 * </pre>
 *
 * @return String
 */
public static String getIMEI() {
        Context context = AppContext.getContext();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimSerialNumber();
        }

/**
 * 获取IMSI
 *
 * <pre>
 * IMSI：国际移动用户识别码，不超过15位的数字，全球唯一，存在SIM卡中，标识了GSM和UMTS网络里唯一的一个用户。手机的信号是通过IMSI在网络中传递，不是通过手机号码。
 * IMSI由MCC+MNC+MIN组成。
 * MCC：移动国家码，共3位。中国为460。
 * MNC：移动网络码，共2位。在中国，移动的代码为电00和02，联通的代码为01，电信的代码为03。
 * MIN：移动用户标识码。
 * </pre>
 *
 * .
 *
 * @return String
 */
public static String getIMSI() {
        Context context = AppContext.getContext();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSubscriberId();
        }

/**
 * 判断是不是飞行模式.
 *
 * @return boolean
 */
@SuppressWarnings("deprecation")
public static boolean isAirplaneModeOn() {
        Context context = AppContext.getContext();
        return Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        }

/**
 * 判断网络是否可用
 *
 * @param context
 * @return
 */
public static boolean isNetAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isAvailable());
        }

/**
 * 判断当前连接的网络是不是2G网络，没有连接网络返回false.
 *
 * @return boolean
 */
public static boolean is2GNetWork() {
        Context context = AppContext.getContext();
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = manager.getAllNetworkInfo();
        if (networkInfos != null) {
        for (NetworkInfo networkInfo : networkInfos) {
        if (networkInfo.isConnected()) {
        int type = networkInfo.getSubtype();
        if (type == TelephonyManager.NETWORK_TYPE_EDGE || type == TelephonyManager.NETWORK_TYPE_GPRS
        || type == TelephonyManager.NETWORK_TYPE_CDMA) {
        return true;
        }
        }
        }
        }
        return false;
        }

/**
 * 切换扬声器和听筒.
 *
 * @param isOpen
 *            为true，打开扬声器; 为false打开听筒.
 * @version
 */
@SuppressWarnings("deprecation")
public static void changeSpeakerphoneOn(boolean isOpen) {
        Context context = AppContext.getContext();
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (isOpen) {
        audioManager.setSpeakerphoneOn(true);
        if (!audioManager.isWiredHeadsetOn()) {
        audioManager.setMode(AudioManager.MODE_NORMAL);
        }
        } else {
        audioManager.setSpeakerphoneOn(false);
        audioManager.setRouting(AudioManager.MODE_NORMAL, AudioManager.ROUTE_HEADSET, AudioManager.ROUTE_ALL);
        audioManager.setMode(AudioManager.MODE_IN_CALL);
        }
        }

//    /**
//     * Gets the sD card path.
//     *
//     * @return the sD card path
//     */
//    public static String getSDCardPath() {
//        String stornPath = null;
//        String state = android.os.Environment.getExternalStorageState();
//        if (android.os.Environment.MEDIA_MOUNTED.equals(state)) {
//            if (android.os.Environment.getExternalStorageDirectory().canWrite()) {
//                stornPath = android.os.Environment.getExternalStorageDirectory().getPath();
//            }
//        }
//        if ((stornPath == null) || (ConstantsCode.EMPTY.equalsIgnoreCase(stornPath.trim()))) {
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
//                stornPath = getExtralcard();
//            }
//        }
//
//        return stornPath == null ? ConstantsCode.EMPTY : stornPath;
//    }

//    /**
//     * Gets the extralcard.
//     *
//     * @return the extralcard
//     */
//    @TargetApi(9)
//    private static String getExtralcard() {
//        // 获取sdcard的路径：外置和内置
//        String[] paths = null;
//        try {
//            StorageManager sm = (StorageManager) AppContext.getContext().getSystemService(Context.STORAGE_SERVICE);
//            paths = (String[]) sm.getClass().getMethod("getVolumePaths", null).invoke(sm, null);
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//        }
//
//        File file;
//        String extralPath = null;
//        if (paths != null) {
//            for (String path : paths) {
//                file = new File(path);
//                if (file.exists() && (file.canWrite())) {
//                    extralPath = path;
//                    break;
//                }
//            }
//        }
//        return extralPath;
//    }

/**
 * 获取文件时长.
 *
 * @param path
 *            path
 * @return String
 */
public static long getDuringTime(String path) {
        int milliseconds = -1;
        if (path == null || path.equals("")) {
        return milliseconds;
        }
        File audioFile = new File(path);
        if (!audioFile.exists()) {
//            OperLog.error(TAG, "voice file " + path + "is not exist.");
        return milliseconds;
        }
        FileInputStream fis = null;
        MediaPlayer mMediaPlayer = new MediaPlayer();
        try {
        fis = new FileInputStream(audioFile);
        mMediaPlayer.setDataSource(fis.getFD());
        mMediaPlayer.prepare();
        milliseconds = mMediaPlayer.getDuration();
        } catch (IOException e) {
        return milliseconds;
        } catch (RuntimeException e) {
        return milliseconds;
        } finally {
        if (mMediaPlayer != null) {
        mMediaPlayer.release();
        mMediaPlayer = null;
        }
        if (fis != null) {
        try {
        fis.close();
        fis = null;
        } catch (IOException e) {
        e.printStackTrace();
        }
        }
        }

        return milliseconds;
        }

/**
 * 获取系统是否是扬声器.
 *
 * @return result is true 为扬声器，false 为听筒
 */
public static boolean getSystemSpeakerphoneOn() {
        Context context = AppContext.getContext();
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        boolean result = audioManager.isSpeakerphoneOn();
        return result;
        }

/**
 * Gets the app version.
 *
 * @return the app version
 */
public static String getAppVersion() {
        String versionName = "";
        Context context = AppContext.getContext();
        try {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
//            OperLog.error(TAG, "appversion:" + versionName, e);
        } catch (RuntimeException e) {
//            OperLog.error(TAG, "appversion error!", e);
        }
        return versionName;
        }

/**
 * Gets the app version.
 *
 * @return the app version
 */
public static String getAppVersionCode() {
        String versionName = "";
        Context context = AppContext.getContext();
        try {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        versionName = "" + packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
//            OperLog.error(TAG, "appversion:" + versionName, e);
        } catch (RuntimeException e) {
//            OperLog.error(TAG, "appversion error!", e);
        }
        return versionName;
        }

/**
 * Compare.
 *
 * @param version1
 *            the version1
 * @param version2
 *            the version2
 * @return the int
 * @version
 */
public static int compare(String version1, String version2) {
        if (version1 == null || version1.length() == 0 || version2 == null || version2.length() == 0) {
        throw new IllegalArgumentException("Invalid parameter!");
        }
        int index1 = 0;
        int index2 = 0;
        while (index1 < version1.length() && index2 < version2.length()) {
        int[] number1 = getValue(version1, index1);
        int[] number2 = getValue(version2, index2);
        if (number1[0] < number2[0]) {
        return -1;
        } else if (number1[0] > number2[0]) {
        return 1;
        } else {
        index1 = number1[1] + 1;
        index2 = number2[1] + 1;
        }
        }
        if (index1 == version1.length() && index2 == version2.length()) {
        return 0;
        }
        if (index1 < version1.length()) {
        return 1;
        } else {
        return -1;
        }
        }

/**
 * Gets the value.
 *
 * @param versionString
 *            the version
 * @param index
 *            the index
 * @return the value
 */
public static int[] getValue(String versionString, int index) {
        int[] value_index = new int[2];
        StringBuilder sb = new StringBuilder();
        while (index < versionString.length() && versionString.charAt(index) != '.') {
        sb.append(versionString.charAt(index));
        index++;
        }
        value_index[0] = Integer.parseInt(sb.toString());
        value_index[1] = index;
        return value_index;
        }

/**
 * 判断是否打开距离感应器.
 *
 * @return true 为需要打开, false为不需打开
 * @version
 */
@SuppressWarnings("deprecation")
public static boolean detectionIsProximiny() {
        Context context = AppContext.getContext();
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.isWiredHeadsetOn() || audioManager.isSpeakerphoneOn()) {
        return false;
        }
        return true;
        }

/**
 * Gets the corner photo bitmap.
 *
 * @param bitmap
 *            the bitmap
 * @param roundPx
 *            the round px
 * @return the corner photo bitmap
 */
public static Bitmap getCornerPhotoBitmap(Bitmap bitmap, float roundPx) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
final int color = 0xff424242;
final Paint paint = new Paint();
final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
        }

/**
 * 图片按比例大小压缩方法（根据路径获取图片并压缩）
 *
 * @param srcPath
 * @return
 */
public static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
        be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
        be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
        be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
//        return bitmap;
        }

/**
 * 质量压缩方法
 *
 * @param image
 * @return
 */
public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
        baos.reset();// 重置baos即清空baos
        image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
        }

/**
 * 图片按比例大小压缩方法（根据Bitmap图片压缩）
 *
 * @param image
 * @return
 */
public static Bitmap compressBitmap(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
        baos.reset();// 重置baos即清空baos
        image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
        be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
        be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
        be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
        }
//
//    /**
//     * 把一个url的网络图片变成一个本地的BitMap
//     *
//     * @param url
//     * @return
//     */
//    public static Bitmap downloadAndShowPhotos(final String url, String type) {
//        try {
//            URL myFileUrl = null;
//            myFileUrl = new URL(StringUtils.toPicLinkType(url, type));
//            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
//            conn.setDoInput(true);
//            conn.connect();
//            InputStream is = conn.getInputStream();
//            Bitmap bitmap = BitmapFactory.decodeStream(is);
//            is.close();
//            if (bitmap != null) {
//                return bitmap;
//            } else {
//                return null;
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public static void saveImageToGallery(Context context, Bitmap bmp) {
//        // 首先保存图片
//        File appDir = new File(Environment.getExternalStorageDirectory(), "Weifeng");
//        if (!appDir.exists()) {
//            appDir.mkdir();
//        }
//        String fileName = System.currentTimeMillis() + ".jpg";
//        File file = new File(appDir, fileName);
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            bmp.compress(CompressFormat.JPEG, 100, fos);
//            fos.flush();
//            fos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 其次把文件插入到系统图库
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        // 最后通知图库更新
////        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(file.getPath()))));
//        Toast.makeText(context, R.string.commom_savepicsuccess, Toast.LENGTH_SHORT).show();
//    }

/**
 * View截图
 *
 * @param view
 * @return
 */
public static Bitmap captureViewScreen(View view) {
        LinearLayout panel = (LinearLayout) view;
        int sumHeight = 0;
        for (int i = 0; i < panel.getChildCount(); i++) {
        sumHeight += panel.getChildAt(i).getHeight();
        }

        Bitmap bmp = Bitmap.createBitmap(panel.getWidth(), sumHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        panel.draw(canvas);
        return bmp;
        }


/**
 * Activity 跳转
 *
 * @param activity
 */
public static void activity_In(Activity activity) {
        activity.overridePendingTransition(R.anim.push_left_in_a, R.anim.push_right_out_a);
        }

/**
 * Activity 退出
 *
 * @param activity
 */
public static void activity_Out(Activity activity) {
        activity.overridePendingTransition(R.anim.push_right_in_a, R.anim.push_left_out);
        }



public static LayoutInflater inflater;
public static Dialog dialog;

/**
 * 弹出框
 *
 * @param context
 * @param titleStr 确认按钮监听
 */
public static void showDeleteDialog(Context context, String titleStr, String messageStr, String cancelName, String okName,
        View.OnClickListener oklistener, View.OnClickListener cancelListener) {
        dialog = null;
        if (inflater == null) {
        inflater = LayoutInflater.from(context);
        }
        LinearLayout dialogView = (LinearLayout) inflater.inflate(R.layout.editdialog_layout, null);
        if (PerfHelper.getIntData(PerfHelper.WIDTH) > 1000) {
        dialogView.setMinimumWidth(Dp2Px.dip2px(context, 400));
        } else {
        dialogView.setMinimumWidth(PerfHelper.getIntData(PerfHelper.WIDTH) - 100);
        }
        Log.e("------:", "vvvvvvvvvvvv:" + PerfHelper.getIntData(PerfHelper.WIDTH));
        dialog = new Dialog(context, R.style.fullDialog);
        dialog.getWindow().setContentView(dialogView);
        TextView titleView = (TextView) dialog.findViewById(R.id.hintDialogdelete_hint);
        TextView messageView = (TextView) dialog.findViewById(R.id.hintDialogdelete_hint_message);
        Button btnCancel = (Button) dialog.findViewById(R.id.hintbtnDialogdelete_delete);
        Button btnOk = (Button) dialog.findViewById(R.id.hintbtnDialogdelete_close);
        if (messageStr != null) {
        messageView.setVisibility(View.VISIBLE);
        messageView.setText(messageStr);
        }
        titleView.setText(titleStr);
        if (okName != null) {
        btnOk.setText(okName);
        }
        if (cancelName != null) {
        btnCancel.setText(cancelName);
        }
        btnOk.setOnClickListener(oklistener);
        if (cancelListener != null) {
        btnCancel.setOnClickListener(cancelListener);
        } else {
        btnCancel.setOnClickListener(new View.OnClickListener() {

@Override
public void onClick(View v) {
        dialog.cancel();
        }
        });
        }
        dialog.show();
        }

public static final String writepath = Environment.getExternalStorageDirectory().toString() + "/traffic/";

/**
 * 手写板屏幕截图
 *
 * @param context
 * @param name
 */
public static String GetandSaveCurrentImageEE(Activity context, String name) {
        // 1.构建Bitmap
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        int w = display.getWidth();
        int h = display.getHeight();

        Bitmap Bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);

        // 2.获取屏幕
        View decorview = context.getWindow().getDecorView();
        decorview.setDrawingCacheEnabled(true);

        Canvas c = new Canvas(Bmp);
        decorview.draw(c);

        // 获取状态栏高度
        Rect frame = new Rect();
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        Bitmap bitmap1 = Bitmap.createBitmap(Bmp, 0, statusBarHeight, w, h - statusBarHeight-120);
        // 3.保存Bitmap
        try {
        File filePath = new File(writepath);
        // 文件
        File file = new File(writepath + name);

        if (!filePath.exists()) {
        filePath.mkdirs();
        }
        if (!file.exists()) {
        file.createNewFile();
        }

        FileOutputStream fos = null;
        fos = new FileOutputStream(file);
        if (null != fos) {
        bitmap1.compress(Bitmap.CompressFormat.PNG, 70, fos);
        fos.flush();
        fos.close();
        }
        if(!Bmp.isRecycled()){
        Bmp.recycle();
        }
        } catch (Exception e) {
        e.printStackTrace();
        }
        return writepath + name;
        }

/**
 * 手写板
 *
 * @param context
 * @param name
 */
public static String GetandSaveCurrentImageWW(Activity context, String name) {
        // 1.构建Bitmap
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        int w = display.getWidth();
        int h = display.getHeight();

        Bitmap Bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);

        // 2.获取屏幕
        View decorview = context.getWindow().getDecorView();
        decorview.setDrawingCacheEnabled(true);

        Canvas c = new Canvas(Bmp);
        decorview.draw(c);

        // 获取状态栏高度
        Rect frame = new Rect();
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top+100;
        Bitmap bitmap1 = Bitmap.createBitmap(Bmp, 0, statusBarHeight, w, h - statusBarHeight-120);
        // 3.保存Bitmap
        try {
        File filePath = new File(writepath);
        // 文件
        File file = new File(writepath + name);

        if (!filePath.exists()) {
        filePath.mkdirs();
        }
        if (!file.exists()) {
        file.createNewFile();
        }

        FileOutputStream fos = null;
        fos = new FileOutputStream(file);
        if (null != fos) {
        bitmap1.compress(Bitmap.CompressFormat.PNG, 70, fos);
        fos.flush();
        fos.close();
        }
        if(!Bmp.isRecycled()){
        Bmp.recycle();
        }
        } catch (Exception e) {
        e.printStackTrace();
        }
        return writepath + name;
        }



/**
 * @Description 公用get请求
 * @param UriStr
 *            url
 * @return
 */
public static String commonHttpGet(String UriStr) {
        String result = "";
        try {
        HttpGet httpget = new HttpGet();
        URI uri = new URI(UriStr);
        httpget.setURI(uri);
        HttpClient httpClient = new DefaultHttpClient();
        HttpParams httpParams = httpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 20000);// 连接超时
        HttpConnectionParams.setSoTimeout(httpParams, 20000);// 读取超时
        HttpResponse httpResponse = httpClient.execute(httpget);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
        result = EntityUtils.toString(httpResponse.getEntity());
        } else {
        return "";
        }

        } catch (Exception e) {
        e.printStackTrace();
        result = "";
        }
        return result;
        }


/**
 * @Description 公用post请求
 * @param url
 * @return
 */
public static String commonHttpPost(String url, List<BasicNameValuePair> param, String action) {
        try {
        HttpPost httppost = new HttpPost();
        URI uri = new URI(url);
        httppost.setURI(uri);
        String mycode = PerfHelper.getStringData("code");
        String ecode = PerfHelper.getStringData("e");
        param.add(new BasicNameValuePair("action", action));
        param.add(new BasicNameValuePair("os", "android_phone"));
        if (!TextUtils.isEmpty(mycode)) {
        param.add(new BasicNameValuePair("code", mycode));
        }
        if (!TextUtils.isEmpty(ecode)) {
        param.add(new BasicNameValuePair("e", ecode));
        }
        String str = url;
        for (int i = 0; i < param.size(); i++) {
        str = i == 0 ? (str += "?" + param.get(i).getName() + "=" + param.get(i).getValue()) : (str += "&" + param.get(i).getName() + "="
        + param.get(i).getValue());
        }
        Log.e("url", str);
        httppost.setEntity(new UrlEncodedFormEntity(param, "utf-8"));
        HttpClient httpClient = new DefaultHttpClient();
        HttpParams httpParams = httpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 20000);// 连接超时
        HttpConnectionParams.setSoTimeout(httpParams, 20000);// 读取超时
        HttpResponse httpResponse = httpClient.execute(httppost);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
        String result = EntityUtils.toString(httpResponse.getEntity());
        Log.e("result", result);
        return result;
        } else {
        return "";
        }

        } catch (Exception e) {
        e.printStackTrace();
        }
        return "";
        }



public static void SaveFile(Context context, String result, String name) {
        File file = new File(HttpDate.PATH);
        if (!file.exists()) {
        file.mkdirs();
        }
        File f1 = new File(HttpDate.PATH + name + ".txt");
        if (f1.exists()) {
        f1.delete();
        }
        try {
        f1.createNewFile();
        FileOutputStream outStream = new FileOutputStream(f1);
        outStream.write(result.getBytes());
        outStream.close();
        } catch (IOException e) {
        e.printStackTrace();
        }
        }

public static String ReadfFile(Context context, String name) {
        String result = "";
        File f1 = new File(HttpDate.PATH + name + ".txt");
        if (f1.exists()) {
        try {
        BufferedReader br = new BufferedReader(new FileReader(f1));
        String readline = "";
        StringBuffer sb = new StringBuffer();
        while ((readline = br.readLine()) != null) {
        sb.append(readline);
        }
        br.close();
        result = sb.toString();
        Log.i("sj","=============================result==>" + result);
        } catch (Exception e) {
        e.printStackTrace();
        }
        }
        return result;
        }


/**
 * 检测网络是否可用
 *
 * @return
 */
public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
        }


        }
