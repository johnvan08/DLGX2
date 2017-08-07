package dlgx.gis.com.dlgx.utils;

/**
 * Created by admin on 2017/4/18.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImageUtils {
    static Handler handler = new Handler();

    /**
     * 压缩一个map集合里面的图片
     *
     * @param map      图片map集合  key是本机地址，value是压缩后的图片名字
     * @param listener 回调，图片压缩完以后执行
     */
    public static void imagesZoom(final Map<String, String> map, final OnImageZooSuccessListener listener) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                List<String> files = new ArrayList<String>();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    Log.e("------", "================图片修改===>" + entry.getKey());
                    files.add(imageZoom(entry.getKey(), entry.getValue()));
                }
                Log.e("-----", "==================图片修改完以后图片个数=======>" + files.size());
                listener.success(files);
            }
        });
    }

    /**
     * 压缩图片0
     *
     * @param imagePath 图片路径
     * @param fileName  储存的图片位置为TrafficPoliceImage文件夹下以.jpg结尾的文件
     * @return
     */
    public static String imageZoom(String imagePath, String fileName) {
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        //图片允许最大空间 单位kb
        double maxSize = 400.00;

        //将Bitmap放至数组中，意在bitmap的大小（与实际读取的远文件要大）
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();

        //图片文件保存的位置
        File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "TrafficPoliceImage" + File.separator + fileName + ".jpg");
        //判断父文件夹是否存在
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        //将字节转换成KB
        double mid = b.length / 1024;
        //判断bitmap占用的空间是否大于允许的最大空间。如果大于则压缩，否则不压缩
        Log.e("QM", "===============大小==>" + mid);
        if (mid > maxSize) {
            //获取bitmap大小，是允许最大大小的多少倍
            double i = mid / maxSize;
            /*开始压缩（此处用得到的平方根，将宽带和高度压缩掉对
            应的平方根倍-——1.保持刻度和高度和原bitmap比率一致，
            压缩后也达到了最打打小占用空间的大小）*/


            //图片保存位置
            Bitmap image = zoomImage(bitmap, bitmap.getWidth() / Math.sqrt(i), bitmap.getHeight() / Math.sqrt(i));
            try {
                image.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
            } catch (FileNotFoundException e) {
                Log.e("QM", "==================>" + "图片压缩出错");
                e.printStackTrace();
            }
        } else {
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
            } catch (FileNotFoundException e) {
                Log.e("QM", "==================>" + "图片不压缩");
                e.printStackTrace();
            }
        }
        return Environment.getExternalStorageDirectory().getPath() + File.separator + "TrafficPoliceImage" + File.separator + fileName + ".jpg";
    }

    /**
     * 图片缩放方法
     *
     * @param bitmap    图片资源
     * @param newWidth  缩放后的宽度
     * @param newHeight 缩放后的高度
     * @return
     */
    private static Bitmap zoomImage(Bitmap bitmap, double newWidth, double newHeight) {
        //获取这个图片的宽和高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        //计算宽高缩放率
        float scaleWidth = (float) newWidth / width;
        float scaleHeight = (float) newHeight / height;
        //缩放图片的动作
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /**
     * 生成罚单保存图片
     *
     * @param print    需要截屏的布局
     * @param fileName 图片保存名字
     *                 图片自动保存在SD卡下的TrafficPoliceImage文件夹下以_罚单截屏.jpg结尾
     */
    public static void createReceiptsPictrue(LinearLayout print, String fileName) {

        try {
            //获得可视组件的截图
            Bitmap bitmap = AndroidUtil.captureViewScreen(print);

            //将截图保存在SD卡根目录的test.png图像文件中
//            String dateName = Environment.getExternalStorageDirectory() + "trafficpolice/" + new Date().getTime() + ".png";
//            FileOutputStream fos = new FileOutputStream(dateName);
            File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "TrafficPoliceImage" + File.separator + fileName + ".jpg");
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(file);
            //将Bitmap对象中的图像数据压缩成png格式的图像数据，并将这些数据保存在test.png文件中
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            //关闭文件输出流
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface OnImageZooSuccessListener {
        void success(List<String> files);
    }
}
