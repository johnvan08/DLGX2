package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.beans.FenZhiXiangMap;
import dlgx.gis.com.dlgx.constant.Constant;

/**
 * Created by john on 2017/7/27.
 */
public class AddSheBeiMapActivity extends Activity {

    @Bind(R.id.title_back1)
    ImageView back;
    @Bind(R.id.title_title)
    TextView title;
    @Bind(R.id.quit)
    TextView quit;
    @Bind(R.id.tv_longitude)
    TextView tvLongitude;
    @Bind(R.id.tv_latitude)
    TextView tvLatitude;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.btn_sure)
    Button btnSure;
    @Bind(R.id.mapview)
    MapView mMapView;
    private GraphicsLayer graLyr = null;

    final String URL_STREET_COLD = "http://cache1.arcgisonline.cn/ArcGIS/rest/services/ChinaOnlineStreetCold/MapServer";
    public static final String FINISH_ACTIVITY = "finish_activity";


    private String longitude, latitude, address;
    private int equipmentType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addshebeimap_layout);
        ButterKnife.bind(this);
        quit.setVisibility(View.GONE);
        title.setText("新增设备");

        //注册广播
        IntentFilter filter = new IntentFilter(FINISH_ACTIVITY);
        this.registerReceiver(broReceiver, filter);

        initDate();
        initListener();
    }

    private void initDate(){
        equipmentType = getIntent().getIntExtra("equipmentType", 0);
        //Toast.makeText(getApplicationContext(),"" + equipmentType,Toast.LENGTH_LONG).show();
    }

    private void initListener() {
        //添加在线图层
        mMapView.addLayer(new com.esri.android.map.ags.ArcGISTiledMapServiceLayer(
                URL_STREET_COLD));
        graLyr = new GraphicsLayer();

        mMapView.addLayer(graLyr);
        mMapView.setOnStatusChangedListener(new OnStatusChangedListener()
        {
            @Override
            public void onStatusChanged(Object o, STATUS status)
            {
                if (status.equals(STATUS.INITIALIZED)) { //初始化完成才显示，防止黑屏
                    mMapView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mMapView.setVisibility(View.VISIBLE);
                        }
                    }, 100);

                }
            }
        });

        mMapView.setOnSingleTapListener(new OnSingleTapListener()
        {
            @Override
            public void onSingleTap(float x, float y)
            {
                com.esri.core.geometry.Point identifyPoint = mMapView.toMapPoint(x, y);
                PictureMarkerSymbol pictureMarkerSymbol=null;
                Drawable d =null;
                switch (equipmentType){
                    case 101:
                        d = getResources().getDrawable(R.drawable.guaidian2);
                        break;
                    case 102:
                        d = getResources().getDrawable(R.drawable.fenzhixiang6);
                        break;
                    case 103:
                        d = getResources().getDrawable(R.drawable.huanwangggui);
                        break;
                    case 104:
                        d = getResources().getDrawable(R.drawable.xaingbian);
                        break;
                    case 105:
                        d = getResources().getDrawable(R.drawable.peidianshi);
                        break;
                    case 106:
                        d = getResources().getDrawable(R.drawable.bianyaqi);
                        break;
                    case 107:
                        d = getResources().getDrawable(R.drawable.ganta);
                        break;
                    case 108:
                        d = getResources().getDrawable(R.drawable.biandianzhan);
                        break;
                    case 109:
                        d = getResources().getDrawable(R.drawable.kaiguanzhan);
                        break;
                    case 110:
                        d = getResources().getDrawable(R.drawable.huanwangggui);
                        break;
                    case 111:
                       d = getResources().getDrawable(R.drawable.dianlanjietou);
                        break;
                    case 112:
                        d = getResources().getDrawable(R.drawable.yinghuan);
                        break;
                }
                pictureMarkerSymbol = new PictureMarkerSymbol(d);

                Graphic gra = new Graphic(identifyPoint, pictureMarkerSymbol);
                graLyr.removeAll();
                graLyr.addGraphic(gra);

                com.esri.core.geometry.Point mappoint = (com.esri.core.geometry.Point) GeometryEngine
                        .project(identifyPoint, mMapView.getSpatialReference(), SpatialReference
                                .create(4326));
                longitude = mappoint.getX() + "";
                latitude = mappoint.getY() + "";

                tvLongitude.setText(longitude);
                tvLatitude.setText(latitude);
                //tvAddress.setText(address);
            }
        });

    }


    @OnClick({R.id.title_back1, R.id.btn_sure})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.title_back1:
                finish();
                break;
            case R.id.btn_sure:
                //确定
                jumpValue();
                break;
        }
    }

    private void jumpValue(){

        Intent intent = null;
        switch (equipmentType){
            case Constant.EQUIPMEN_GUAIDIAN:
                //添加拐点
                intent = new Intent(this, GuaidianNewsActivity.class);
                break;
            case Constant.EQUIPMEN_FENZHIXIANG:
                //添加分支箱
                intent = new Intent(this, FenZhiXiangNewsActivity.class);
                break;
            case Constant.EQUIPMEN_HUANWANGGUI:
                //添加环网柜
                intent = new Intent(this, HuanWangGuiNewsActivity.class);
                break;
            case Constant.EQUIPMEN_XIANGBIAN:
                //添加箱变
                intent = new Intent(this, XiangBianNewsActivity.class);
                break;
            case Constant.EQUIPMEN_PEIDIANSHI:
                //添加配电室
                intent = new Intent(this, PeiDianShiNewsActivity.class);
                break;
            case Constant.EQUIPMEN_BIANYAQI:
                //添加变压器
                intent = new Intent(this, BianYaQiNewsActivity.class);
                break;
            case Constant.EQUIPMEN_GANTA:
                //添加杆塔
                intent = new Intent(this, GantaNewsActivity.class);
                break;
            case Constant.EQUIPMEN_BIANDIANZHAN:
                //变电站
                intent = new Intent(this, BianDianZhanNewsActivity.class);
                break;
            case Constant.EQUIPMEN_KAIGUANZHAN:
                //添加开关站
                intent = new Intent(this, KaiGuanZhanNewsActivity.class);
                break;
            case Constant.EQUIPMEN_YANMAIJING:
                //添加掩埋井
                intent = new Intent(this, YanMaiJingNewsActivity.class);
                break;
            case Constant.EQUIPMEN_DIANLANJIETOU:
                //添加电缆接头
                break;
            case Constant.EQUIPMEN_YINHUANXINXI:
                //添加隐患信息
                break;
        }

        intent.putExtra("longitude", longitude);
        intent.putExtra("latitude", latitude);
        intent.putExtra("equipmentType", equipmentType+"");
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        this.unregisterReceiver(broReceiver);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.unpause();
    }


    BroadcastReceiver broReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            finish();
        }
    } ;
}
