package dlgx.gis.com.dlgx.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by admin on 2017/4/18.
 */
public class HttpDate {

    //图片保存路径
    public static File file = Environment.getExternalStorageDirectory();
    public static String PATH = file.getPath() + "/traffic/";
    public static String IMG = "http://police.qixibird.cn/files/Image/index/sha1/%s";
    public static String UPIMGPATH = "http://police.qixibird.cn/files/image/upload";
}
