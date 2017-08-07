package dlgx.gis.com.dlgx.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.commom.AppContext;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import org.xutils.x;

import okhttp3.OkHttpClient;

/**
 * Created by admin on 2017/4/18.
 */

public class MyApplication extends Application {



//    /**
//     * 停止定位
//     */
//    public void stopLocationClient(){
//        if (mLocationClient != null && mLocationClient.isStarted()){
//            mLocationClient.stop();
//        }
//    }
//    /**
//     * 发起定位
//     */
//    public void requestLocationInfo(){
//        initLocation();
//        if (mLocationClient != null && !mLocationClient.isStarted()){
//            mLocationClient.start();
//        }
//        if (mLocationClient != null && mLocationClient.isStarted()){
//            mLocationClient.requestLocation();
//        }
//    }
//    /**
//     * 刷新位置信息
//     */
//    public void refreshLocation() {
//        if (null != mLocationClient && mLocationClient.isStarted()) {
//            mLocationClient.requestLocation();
//        }
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        sInstance = this;
        initImageLoader(this);
        AppContext.setContext(this.getApplicationContext());
        x.Ext.init(this);
//        x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
//
//        // 全局默认信任所有https域名 或 仅添加信任的https域名
//        // 使用RequestParams#setHostnameVerifier(...)方法可设置单次请求的域名校验
//        x.Ext.setDefaultHostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        });
    }

    public static MyApplication sInstance;




    public static void initImageLoader(Context context) {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new LruMemoryCache(20 * 1024 * 1024))
                .imageDownloader(new BaseImageDownloader(context, 10 * 1000, 30 * 1000))
                .diskCacheFileCount(5000)
                .diskCache(
                        new UnlimitedDiscCache(StorageUtils.getOwnCacheDirectory(context,
                                "/ebaby/" + context.getResources().getString(R.string.app_cache)))).build();
            ImageLoader.getInstance().init(config);
    }


    public static synchronized MyApplication getInstance() {
        if (sInstance == null) {
            sInstance = new MyApplication();
        }
        return sInstance;
    }


//    /**
//     * 监听函数，有更新位置的时候，格式化成字符串，输出到屏幕中
//     */
//    public class MyLocationListener implements BDLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation location){
//            if (location == null){
//                sendBroadCast("定位失败!");
//                return;
//            }
//            sendBroadCast(location.getStreet());
//        }
//        public void onReceivePoi(BDLocation poiLocation){
//            if (poiLocation == null){
//                sendBroadCast("定位失败!");
//                return;
//            }
//            sendBroadCast(poiLocation.getStreet());
//        }
//    }


//    /**
//     * 得到发送广播
//     * @param address
//     */
//    public void sendBroadCast(String address){
//        if(address == null){
//            return;
//        }
//        //stopLocationClient();
//        Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
//                sendBroadcast(intent);
//        //Intent intent = new Intent(HomeFragment.LOCATION_BCR);
//        intent.putExtra("address", address);
//        sendBroadcast(intent);
//    }
}

