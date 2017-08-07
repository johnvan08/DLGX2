package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.esri.android.map.Callout;
import com.esri.android.map.CalloutStyle;
import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.MarkerSymbol;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.beans.DianlantongdaoBean;
import dlgx.gis.com.dlgx.beans.SheBeiBean;
import dlgx.gis.com.dlgx.constant.Constant;
import dlgx.gis.com.dlgx.db.DatabaseAdapter;

/**
 * 选择设备
 * Created by admin on 2017/5/7.
 */
public class ChooseSeBeiActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.mapview)
    MapView mapView;

   /* private List<BDMapData> bdMapClientList;*/
    private double latitude;
    private double longitude;
    private boolean isFirstLocate = true;
    private GraphicsLayer graLyr = null;
    final String URL_STREET_COLD = "http://cache1.arcgisonline.cn/ArcGIS/rest/services/ChinaOnlineStreetCold/MapServer";

   //private LinkedList<LocationEntity> locationList = new LinkedList<LocationEntity>();
    private boolean toastLoad = false;
   /* private BitmapDescriptor bitmap;*/
    private String jingdu;
    private String weidu;
    private String name;
   /* private LatLng ll;*/

    /*private List<FenZhiXiangMap> bdMapClientList5;
    private List<HuanWangGuiMap> bdMapClientList6;
    private List<XaingBianMap> bdMapClientList7;
    private List<PeiDianShiMap> bdMapClientList8;
    private List<BianYaQiMap> bdMapClientList9;
    private List<GanTaMap> bdMapClientList10;
    private List<BianDainZhanMap> bdMapClientList11;
    private List<KaiGuanZhuanMap> bdMapClientList12;*/
    //从本地数据库查询经度纬度
    private List<SheBeiBean> fenZhiXiangBeen = new ArrayList<SheBeiBean>();
    private List<SheBeiBean> huanWangGuiBeen = new ArrayList<SheBeiBean>();
    private List<SheBeiBean> xaingBianBeen = new ArrayList<SheBeiBean>();
    private List<SheBeiBean> peiDinshiBeen = new ArrayList<SheBeiBean>();
    private List<SheBeiBean> bianYaQiBeen= new ArrayList<SheBeiBean>();
    private List<SheBeiBean> ganTaBeen= new ArrayList<SheBeiBean>();
    private List<SheBeiBean> bianDianZhanBeen = new ArrayList<SheBeiBean>();
    private List<SheBeiBean> kaiGuanZhanBeen = new ArrayList<SheBeiBean>();
    private List<SheBeiBean> yanMaiJingBeen = new ArrayList<SheBeiBean>();

    /*private LocationClient locationClient;
    private BDLocationListener listener = new MyLocationListener();
    private LinkedList<LocationEntity> locationList = new LinkedList<LocationEntity>();
    List<DianlantongdaoBean> dianlantongdaoBeen = new ArrayList<DianlantongdaoBean>();*/
    private String d;//电缆通道长度
    /*private LatLng point;
    private  LatLng ll2;
    private LatLng ll3;
    private List<LatLng> points = new ArrayList<LatLng>();*/
    private Polyline mCurrentMapExtent = null;
    //  private  OverlayOptions ooPolyline;

    float x = (float) 118.78;
    float y = (float) 32.04;
    private Callout callout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosesebeiactivity_layout);
        ButterKnife.bind(this);
        //dianlantongdaoBeen = DatabaseAdapter.getIntance(getBaseContext()).querytongdaoAddress();

        getAllMarker();
        mapView.setEsriLogoVisible(true);
        mapView.enableWrapAround(true);
        graLyr = new GraphicsLayer();

       // mapView.addLayer(graLyr);
        /*mapView.addLayer(new com.esri.android.map.ags.ArcGISTiledMapServiceLayer(
                URL_STREET_COLD));*/
        mapView.setOnStatusChangedListener(new OnStatusChangedListener()
        {
            private static final long serialVersionUID = 1L;

            public void onStatusChanged(Object source, STATUS status)
            {
                // Set the map extent once the map has been initialized, and the basemap is added
                // or changed; this will be indicated by the layer initialization of the basemap
                // layer. As there is only
                // a single layer, there is no need to check the source object.
                if (STATUS.LAYER_LOADED == status)
                {
                    mapView.setExtent(mCurrentMapExtent);
                    MarkerSymbol symbol = new SimpleMarkerSymbol(Color.RED, 12,
                            SimpleMarkerSymbol.STYLE.CIRCLE);

                    PictureMarkerSymbol pic = new PictureMarkerSymbol(ChooseSeBeiActivity.this.getResources().getDrawable(R.drawable.ic_xb20));
                    com.esri.core.geometry.Point wgspoint = new com.esri.core.geometry.Point(118.78, 32.04);
                    com.esri.core.geometry.Point mapPoint = (com.esri.core.geometry.Point) GeometryEngine
                            .project(wgspoint, SpatialReference.create(4326), mapView
                                    .getSpatialReference());
                    Graphic gra = new Graphic(mapPoint, pic);
                    // SpatialReference sp=gra.getSpatialReference();
                    graLyr.addGraphic(gra);
                    mapView.addLayer(new com.esri.android.map.ags.ArcGISTiledMapServiceLayer(URL_STREET_COLD));
                    mapView.addLayer(graLyr);


                    com.esri.core.geometry.Point wgspoint1 = new com.esri.core.geometry.Point(118.82, 32.04);
                    com.esri.core.geometry.Point mapPoint1 = (com.esri.core.geometry.Point) GeometryEngine
                            .project(wgspoint1, SpatialReference.create(4326), mapView
                                    .getSpatialReference());
                    Graphic gra1 = new Graphic(mapPoint1, pic);
                    graLyr.addGraphic(gra1);
                    mapView.addLayer(graLyr);
                }
            }
        });
        //mapView.setClickable(true);
        mapView.setEsriLogoVisible(false);

        mapView.setOnSingleTapListener(new OnSingleTapListener()
        {
            @Override
            public void onSingleTap(final float x, final float y)
            {
                if (!mapView.isLoaded())
                {
                    return;
                }
                // Add to Identify Parameters based on tapped location
                com.esri.core.geometry.Point identifyPoint = mapView.toMapPoint(x, y);
                /*MarkerSymbol symbol = new SimpleMarkerSymbol(Color.RED, 12, SimpleMarkerSymbol
                        .STYLE.CIRCLE);
                Drawable d = getResources().getDrawable(R.drawable.ic_xb20);
                PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(d);
                Graphic gra = new Graphic(identifyPoint, pictureMarkerSymbol);
                graLyr.removeAll();
                graLyr.addGraphic(gra);*/
                com.esri.core.geometry.Point mappoint = (com.esri.core.geometry.Point) GeometryEngine
                        .project(identifyPoint, mapView.getSpatialReference(), SpatialReference
                                .create(4326));
                jingdu = mappoint.getX() + "";
                weidu = mappoint.getY() + "";

                Toast.makeText(ChooseSeBeiActivity.this, "x:" + mappoint.getX() + ";y:" + mappoint
                        .getY(), Toast.LENGTH_LONG).show();

                callout.hide();
                TextView tv = new TextView(ChooseSeBeiActivity.this);
                tv.setText("这是一个气泡");
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        //调到拨号界面
                        Toast.makeText(ChooseSeBeiActivity.this,"tanchujiemian", Toast.LENGTH_LONG).show();
                    }
                });
                int[] graphicIDs = graLyr.getGraphicIDs(x, y, 1);
                if (graphicIDs != null && graphicIDs.length > 0) {
                    Graphic gr = graLyr.getGraphic(graphicIDs[0]);
                    com.esri.core.geometry.Point location = (com.esri.core.geometry.Point) gr.getGeometry();
                //    callout.setOffset(0, -15);//设置偏移量
                    callout.show(location, tv);//设置弹出窗显示的内容
                }

            //    callout.show(identifyPoint);

            }
        });

        //获取一个气泡
        callout = mapView.getCallout();
        //设置最大的长宽
     //   callout.setMaxWidth(1200);
     //   callout.setMaxHeight(300);

        CalloutStyle calloutStyle = new CalloutStyle();
        //设置尖尖角的位置，尖尖显示在气泡的左下角，
        calloutStyle.setAnchor(Callout.ANCHOR_POSITION_LOWER_MIDDLE);
        callout.setStyle(calloutStyle);


     /**
     * 设置marker点击事件
     *//*
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                final String info = (String) marker.getExtraInfo().get("info");
                InfoWindow infoWindow;
                //动态生成一个Button对象，用户在地图中显示InfoWindow
                final Button textInfo = new Button(getApplicationContext());
                textInfo.setBackgroundResource(R.drawable.popup);
                textInfo.setPadding(10, 10, 10, 10);
                textInfo.setTextColor(Color.BLACK);
                textInfo.setTextSize(12);
                textInfo.setText(info);
                //得到点击的覆盖物的经纬度
                ll = marker.getPosition();
                Log.e("choose1",weidu+"");
                Log.e("choose2",jingdu+"");
                textInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (fenZhiXiangBeen != null && fenZhiXiangBeen.toString().length() > 0) {
                            for (int i = 0; i < bdMapClientList5.size(); i++) {
                                double point = bdMapClientList5.get(i).getLatitude();
                                double point1 = bdMapClientList5.get(i).getLongitude();
                                if (ll.latitude == point && ll.longitude == point1) {
                                    String id = bdMapClientList5.get(i).getId();
                                    String string = bdMapClientList5.get(i).getName();
                                    String jingdu = String.valueOf(bdMapClientList5.get(i).getLongitude());
                                    String weidu = String.valueOf(bdMapClientList5.get(i).getLatitude());
                                    Intent intr = new Intent();
                                    intr.putExtra("id", id);
                                    intr.putExtra("jingdu", jingdu);
                                    intr.putExtra("weidu", weidu);
                                    intr.putExtra("string", string);
                                    setResult(RESULT_OK, intr);
                                    finish();
                                }
                            }
                        }
                        if (huanWangGuiBeen != null && huanWangGuiBeen.toString().length() > 0) {
                            for (int i = 0; i < bdMapClientList6.size(); i++) {
                                double point = bdMapClientList6.get(i).getLatitude();
                                double point1 = bdMapClientList6.get(i).getLongitude();
                                if (ll.latitude == point && ll.longitude == point1) {
                                    String id = bdMapClientList6.get(i).getId();
                                    String string = bdMapClientList6.get(i).getName();
                                    String jingdu = String.valueOf(bdMapClientList6.get(i).getLongitude());
                                    String weidu = String.valueOf(bdMapClientList6.get(i).getLatitude());
                                    Intent intr = new Intent();
                                    intr.putExtra("id", id);
                                    intr.putExtra("jingdu", jingdu);
                                    intr.putExtra("weidu", weidu);
                                    intr.putExtra("string", string);
                                    setResult(RESULT_OK, intr);
                                    finish();
                                }
                            }
                        }
                        if (xaingBianBeen != null && xaingBianBeen.toString().length() > 0) {
                            for (int i = 0; i < bdMapClientList7.size(); i++) {
                                double point = bdMapClientList7.get(i).getLatitude();
                                double point1 = bdMapClientList7.get(i).getLongitude();
                                if (ll.latitude == point && ll.longitude == point1) {
                                    String id = bdMapClientList7.get(i).getId();
                                    String string = bdMapClientList7.get(i).getName();
                                    String jingdu = String.valueOf(bdMapClientList7.get(i).getLongitude());
                                    String weidu = String.valueOf(bdMapClientList7.get(i).getLatitude());
                                    Intent intr = new Intent();
                                    intr.putExtra("id", id);
                                    intr.putExtra("jingdu", jingdu);
                                    intr.putExtra("weidu", weidu);
                                    intr.putExtra("string", string);
                                    setResult(RESULT_OK, intr);
                                    finish();
                                }
                            }
                        }
                        if (peiDinshiBeen!= null && peiDinshiBeen.toString().length() > 0) {
                            for (int i = 0; i < bdMapClientList8.size(); i++) {
                                double point = bdMapClientList8.get(i).getLatitude();
                                double point1 = bdMapClientList8.get(i).getLongitude();
                                if (ll.latitude == point && ll.longitude == point1) {
                                    String id = bdMapClientList8.get(i).getId();
                                    String string = bdMapClientList8.get(i).getName();
                                    String jingdu = String.valueOf(bdMapClientList8.get(i).getLongitude());
                                    String weidu = String.valueOf(bdMapClientList8.get(i).getLatitude());
                                    Intent intr = new Intent();
                                    intr.putExtra("id", id);
                                    intr.putExtra("jingdu", jingdu);
                                    intr.putExtra("weidu", weidu);
                                    intr.putExtra("string", string);
                                    setResult(RESULT_OK, intr);
                                    finish();
                                }
                            }
                        }
                        if (bianYaQiBeen!= null && bianYaQiBeen.toString().length() > 0) {
                            for (int i = 0; i < bdMapClientList9.size(); i++) {
                                double point = bdMapClientList9.get(i).getLatitude();
                                double point1 = bdMapClientList9.get(i).getLongitude();
                                if (ll.latitude == point && ll.longitude == point1) {
                                    String id = bdMapClientList9.get(i).getId();
                                    String string = bdMapClientList9.get(i).getName();
                                    String jingdu = String.valueOf(bdMapClientList9.get(i).getLongitude());
                                    String weidu = String.valueOf(bdMapClientList9.get(i).getLatitude());
                                    Intent intr = new Intent();
                                    intr.putExtra("id", id);
                                    intr.putExtra("jingdu", jingdu);
                                    intr.putExtra("weidu", weidu);
                                    intr.putExtra("string", string);
                                    setResult(RESULT_OK, intr);
                                    finish();
                                }
                            }
                        }
                        if (ganTaBeen!= null && ganTaBeen.toString().length() > 0) {
                            for (int i = 0; i < bdMapClientList10.size(); i++) {
                                double point = bdMapClientList10.get(i).getLatitude();
                                double point1 = bdMapClientList10.get(i).getLongitude();
                                if (ll.latitude == point && ll.longitude == point1) {
                                    String id = bdMapClientList10.get(i).getId();
                                    String string = bdMapClientList10.get(i).getName();
                                    String jingdu = String.valueOf(bdMapClientList10.get(i).getLongitude());
                                    String weidu = String.valueOf(bdMapClientList10.get(i).getLatitude());
                                    Intent intr = new Intent();
                                    intr.putExtra("id", id);
                                    intr.putExtra("jingdu", jingdu);
                                    intr.putExtra("weidu", weidu);
                                    intr.putExtra("string", string);
                                    setResult(RESULT_OK, intr);
                                    finish();
                                }
                            }
                        }
                        if (bianDianZhanBeen!= null && bianDianZhanBeen.toString().length() > 0) {
                            for (int i = 0; i < bdMapClientList11.size(); i++) {
                                double point = bdMapClientList11.get(i).getLatitude();
                                double point1 = bdMapClientList11.get(i).getLongitude();
                                if (ll.latitude == point && ll.longitude == point1) {
                                    String id = bdMapClientList11.get(i).getId();
                                    String string = bdMapClientList11.get(i).getName();
                                    String jingdu = String.valueOf(bdMapClientList11.get(i).getLongitude());
                                    String weidu = String.valueOf(bdMapClientList11.get(i).getLatitude());
                                    Intent intr = new Intent();
                                    intr.putExtra("id", id);
                                    intr.putExtra("jingdu", jingdu);
                                    intr.putExtra("weidu", weidu);
                                    intr.putExtra("string", string);
                                    setResult(RESULT_OK, intr);
                                    finish();
                                }
                            }
                        }
                        if (kaiGuanZhanBeen!= null && kaiGuanZhanBeen.toString().length() > 0) {
                            for (int i = 0; i < bdMapClientList12.size(); i++) {
                                double point = bdMapClientList12.get(i).getLatitude();
                                double point1 = bdMapClientList12.get(i).getLongitude();
                                if (ll.latitude == point && ll.longitude == point1) {
                                    String id = bdMapClientList12.get(i).getId();
                                    String string = bdMapClientList12.get(i).getName();
                                    String jingdu = String.valueOf(bdMapClientList12.get(i).getLongitude());
                                    String weidu = String.valueOf(bdMapClientList12.get(i).getLatitude());
                                    Intent intr = new Intent();
                                    intr.putExtra("id", id);
                                    intr.putExtra("jingdu", jingdu);
                                    intr.putExtra("weidu", weidu);
                                    intr.putExtra("string", string);
                                    setResult(RESULT_OK, intr);
                                    finish();
                                }
                            }
                        }
                        System.out.println(ll.latitude+"......." +ll.longitude);



                    }
                });
                //将marker所在的经纬度的信息转化成屏幕上的坐标
                Point p = baiduMap.getProjection().toScreenLocation(ll);
                p.y -= 90;
                LatLng llInfo = baiduMap.getProjection().fromScreenLocation(p);
                //初始化infoWindow，最后那个参数表示显示的位置相对于覆盖物的竖直偏移量，这里也可以传入一个监听器
                infoWindow = new InfoWindow(textInfo, llInfo, 0);
                baiduMap.showInfoWindow(infoWindow);//显示此infoWindow
                //让地图以备点击的覆盖物为中心
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
                baiduMap.setMapStatus(status);
                return true;
            }
        });*/

        //给线段设置点击事件
        /*baiduMap.setOnPolylineClickListener(new BaiduMap.OnPolylineClickListener() {
            @Override
            public boolean onPolylineClick(Polyline polyline) {
                OverlayOptions ooPolyline = new PolylineOptions().width(10).color(Color.BLUE).points(polyline.getPoints());
                if(mPolyline1!=null){
                    mPolyline1.remove();
                }
                //添加在地图中
              mPolyline1 = (Polyline) baiduMap.addOverlay(ooPolyline);
                Log.e("polyline2", "2222222222");
                return true;

            }

        });*/
        initView();
    }
    private void initView() {
        back.setOnClickListener(this);
    }

    ///获取
    private void getAllMarker(){
        //
        fenZhiXiangBeen= DatabaseAdapter.getIntance(getBaseContext()).querySheBeiAddress( Constant.EQUIPMEN_FENZHIXIANG+"");
        //
        huanWangGuiBeen = DatabaseAdapter.getIntance(getBaseContext()).querySheBeiAddress(Constant.EQUIPMEN_HUANWANGGUI+"");
        //
        xaingBianBeen = DatabaseAdapter.getIntance(getBaseContext()).querySheBeiAddress(Constant.EQUIPMEN_XIANGBIAN+"");
        //
        peiDinshiBeen = DatabaseAdapter.getIntance(getBaseContext()).querySheBeiAddress(Constant.EQUIPMEN_PEIDIANSHI+"");
        //
        bianYaQiBeen = DatabaseAdapter.getIntance(getBaseContext()).querySheBeiAddress(Constant.EQUIPMEN_BIANYAQI+"");
        //
        ganTaBeen =DatabaseAdapter.getIntance(getBaseContext()).querySheBeiAddress(Constant.EQUIPMEN_GANTA+"");
        //
        bianDianZhanBeen = DatabaseAdapter.getIntance(getBaseContext()).querySheBeiAddress(Constant.EQUIPMEN_BIANDIANZHAN+"");
        //
        kaiGuanZhanBeen = DatabaseAdapter.getIntance(getBaseContext()).querySheBeiAddress(Constant.EQUIPMEN_KAIGUANZHAN+"");
        //
        yanMaiJingBeen = DatabaseAdapter.getIntance(getBaseContext()).querySheBeiAddress(Constant.EQUIPMEN_YANMAIJING+"");
    }

    /**
     * 添加覆盖物的方法
     */
    private void addFenZhiXiangOverlay() {

        Drawable d = getResources().getDrawable(R.drawable.fenzhiixang2);
        if(fenZhiXiangBeen!=null) {
            for (SheBeiBean data : fenZhiXiangBeen) {
                Point point  = new Point(Double.valueOf(data.getJingdu()), Double.valueOf(data.getWeidu()));
                Point mapPoint = (Point) GeometryEngine
                        .project(point, SpatialReference.create(4326), mapView
                                .getSpatialReference());
                PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(d);
                Map<String,Object> attrs= new HashMap<String, Object>();
                attrs.put("id",data.getUuid());
                attrs.put("sblx",data.getLx());
                attrs.put("sbmc",data.getName());
                Graphic gra = new Graphic(mapPoint, pictureMarkerSymbol,attrs);
            }
        }
    }


    // 定位监听
    /*public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation loc) {
            if (loc != null && (loc.getLocType() == 161 || loc.getLocType() == 66)) {
                Bundle locData = algorithm(loc);
                Message locMsg = locHander.obtainMessage();
                if (locData != null) {
                    locData.putParcelable("loc", loc);
                    locMsg.setData(locData);
                    locHander.sendMessage(locMsg);

                    if (!toastLoad) {
                        Toast.makeText(ChooseSeBeiActivity.this, "正在加载地图...", Toast.LENGTH_SHORT).show();
                    }
                    toastLoad = true;
                    locationClient.stop();
                }
            } else {
                Toast.makeText(ChooseSeBeiActivity.this, "定位失败，请检查手机网络或设置！", Toast.LENGTH_LONG).show();
            }
        }

    }*/

    /***
     * 接收定位结果消息，并显示在地图上
     */
    /*private Handler locHander = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                BDLocation location = msg.getData().getParcelable("loc");

                latitude = location.getLatitude();
                longitude = location.getLongitude();

                Log.d("time", location.getTime());
                Log.d("error code", location.getLocType() + "");
                Log.d("latitude", location.getLatitude() + "");
                Log.d("longitude", location.getLongitude() + "");
                Log.d("radius", location.getRadius() + "");
                if (location.getLocType() == BDLocation.TypeGpsLocation) {
                    Log.d("GPS", "gps定位成功");
                    Log.d("speed", location.getSpeed() + " 单位：公里每小时");
                    Log.d("satellite", location.getSatelliteNumber() + "");
                    Log.d("height", location.getAltitude() + " 单位：米");
                    Log.d("direction", location.getDirection() + " 单位度");
                    Log.d("addr", location.getAddrStr());
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                    Log.d("网络定位结", "网络定位成功");
                    Log.d("addr", location.getAddrStr());
                    Log.d("operationers", "运营商信息：" + location.getOperators());
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
                    Log.d("离线定位", "离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    Toast.makeText(ChooseSeBeiActivity.this, "服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因", Toast.LENGTH_LONG).show();
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    Toast.makeText(ChooseSeBeiActivity.this, "网络不同导致定位失败，请检查网络是否通畅", Toast.LENGTH_LONG).show();
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    Toast.makeText(ChooseSeBeiActivity.this, "无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机", Toast.LENGTH_LONG).show();
                }
                Log.d("locationdescribe", location.getLocationDescribe());

                List<Poi> list = location.getPoiList();// POI数据
                StringBuffer sb = new StringBuffer("POI数据：");
                if (list != null && list.size() > 0) {
                    sb.append("\npoilist size = : ");
                    sb.append(list.size());
                    for (Poi p : list) {
                        sb.append("\npoi= : ");
                        sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                    }
                }

                Log.d("POI数据", sb.toString());
                Log.d("POI数据", sb.toString());
                if (location != null) {
                   point = new LatLng(location.getLatitude(), location.getLongitude());
                    drawLine();
                            getFenZhiXaingDataForSql();
                            addFenZhiXiangOverlay();
                            getHuanWangGuiDataForSql();
                            addHuanWangGuiOverlay();
                            getXiangBianMapDataForSql();
                            addXiangBianOverlay();
                            getPeiDianShiMapDataForSql();
                            addPeiDianShiOverlay();
                            getBianYaQiDataForSql();
                            addBianYaQiOverlay();
                            getGanTaMapDataForSql();
                            addGanTaOverlay();
                            getBianDainZhanMapDataForSql();
                            addBianDainZhanOverlay();
                            getKaiGuanZhanMapDataForSql();
                            addKaiGuanZhanOverlay();

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    };*/

    /***
     * 平滑策略代码实现方法，主要通过对新定位和历史定位结果进行速度评分，
     * 来判断新定位结果的抖动幅度，如果超过经验值，则判定为过大抖动，进行平滑处理,若速度过快，
     * 则推测有可能是由于运动速度本身造成的，则不进行低速平滑处理
     *
     * @param
     * @return Bundle
     */
    /*private Bundle algorithm(BDLocation location) {
        float[] EARTH_WEIGHT = {0.1f, 0.2f, 0.4f, 0.6f, 0.8f}; // 推算计算权重_地球
        Bundle locData = new Bundle();
        double curSpeed = 0;
        if (locationList.isEmpty() || locationList.size() < 2) {
            LocationEntity temp = new LocationEntity();
            temp.location = location;
            temp.time = System.currentTimeMillis();
            locData.putInt("iscalculate", 0);
            locationList.add(temp);
        } else {
            if (locationList.size() > 5)
                locationList.removeFirst();
            double score = 0;
            for (int i = 0; i < locationList.size(); ++i) {
                LatLng lastPoint = new LatLng(locationList.get(i).location.getLatitude(),
                        locationList.get(i).location.getLongitude());
                LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());
                //double distance = DistanceUtil.getDistance(lastPoint, curPoint);
                //curSpeed = distance / (System.currentTimeMillis() - locationList.get(i).time) / 1000;
                score += curSpeed * EARTH_WEIGHT[i];
            }
            if (score > 0.00000999 && score < 0.00005) {
                location.setLongitude(
                        (locationList.get(locationList.size() - 1).location.getLongitude() + location.getLongitude())
                                / 2);
                location.setLatitude(
                        (locationList.get(locationList.size() - 1).location.getLatitude() + location.getLatitude())
                                / 2);
                locData.putInt("iscalculate", 1);
            } else {
                locData.putInt("iscalculate", 0);
            }
            LocationEntity newLocation = new LocationEntity();
            newLocation.location = location;
            newLocation.time = System.currentTimeMillis();
            locationList.add(newLocation);

        }
        return locData;
    }

    class LocationEntity {
        BDLocation location;
        long time;
    }*/

    //电缆通道绘线
   /* private void drawLine() {
        //从本地数据库中获取到的坐标
        if (dianlantongdaoBeen != null && dianlantongdaoBeen.toString().length() > 0) {

            for (int i = 0; i < dianlantongdaoBeen.size(); i++) {
                if (dianlantongdaoBeen.get(i).getGuaidianweidu() ==0 && dianlantongdaoBeen.get(i).getGuaidianjingdu() == 0) {
                    List<LatLng>  points = new ArrayList<LatLng>();
                    // 构造折线点坐标
                    points.add(new LatLng(Double.parseDouble(dianlantongdaoBeen.get(i).getStartweidu()), Double.parseDouble(dianlantongdaoBeen.get(i).getStartjingdu())));
                    points.add(new LatLng(Double.parseDouble(dianlantongdaoBeen.get(i).getEndweidu()), Double.parseDouble(dianlantongdaoBeen.get(i).getEndjingdu())));
                    OverlayOptions  ooPolyline = new PolylineOptions().width(10).color(Color.RED).points(points);
                    //添加在地图中
                    Polyline mPolyline1 = (Polyline) baiduMap.addOverlay(ooPolyline);

                } else {
                    // 构造折线
                    List<LatLng> points = new ArrayList<LatLng>();
                    points.add(new LatLng(Double.parseDouble(dianlantongdaoBeen.get(i).getStartweidu()), Double.parseDouble(dianlantongdaoBeen.get(i).getStartjingdu())));
                    points.add(new LatLng(dianlantongdaoBeen.get(i).getGuaidianweidu(),dianlantongdaoBeen.get(i).getGuaidianjingdu()));
                    points.add(new LatLng(Double.parseDouble(dianlantongdaoBeen.get(i).getEndweidu()), Double.parseDouble(dianlantongdaoBeen.get(i).getEndjingdu())));
                    OverlayOptions ooPolyline = new PolylineOptions().width(10).color(Color.RED).points(points);
                    //添加在地图中
                    Polyline mPolyline1 = (Polyline) baiduMap.addOverlay(ooPolyline);
                }
            }
        }
    }*/



    // 在地图上添加标注
   /* private void setMapOverlay(LatLng point) {
        latitude = point.latitude;
        longitude = point.longitude;

        baiduMap.clear();
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_mark);
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
        baiduMap.addOverlay(option);
    }*/

    /*private void getFenZhiXaingDataForSql(){
        //从本地数据库中获取到的坐标
        if (fenZhiXiangBeen != null && fenZhiXiangBeen.toString().length() > 0) {
            bdMapClientList5 = new ArrayList<>();
            for (int i = 0; i <fenZhiXiangBeen.size(); i++) {
                Log.w("2233335555",fenZhiXiangBeen.size()+"");
                FenZhiXiangMap b = new FenZhiXiangMap();
               // b.setName("分支箱");
                b.setId(fenZhiXiangBeen.get(i).getUuid());
                b.setName(fenZhiXiangBeen.get(i).getName());
                b.setLatitude(Double.parseDouble(fenZhiXiangBeen.get(i).getWeidu()));
                b.setLongitude(Double.parseDouble(fenZhiXiangBeen.get(i).getJingdu()));
                //让所有覆盖物的经纬度与你自己的经纬度相近，以便打开地图就能看到那些覆盖物
                bdMapClientList5.add(b);
            }

        }
    }*/

    /**
     * 添加覆盖物的方法
     */
    /*private void addFenZhiXiangOverlay() {
        LatLng point = null;
        MarkerOptions option = null;
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.fenzhiixang2);
        if(bdMapClientList5!=null) {
            for (FenZhiXiangMap data : bdMapClientList5) {
                point = new LatLng(data.getLatitude(), data.getLongitude());
                option = new MarkerOptions().position(point).icon(bitmap);
                Marker marker1 = (Marker) baiduMap.addOverlay(option);
                //Bundle用于通信
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", data.getName() + "\n" + "纬度：" + data.getLatitude() + "   经度：" + data.getLongitude());
                marker1.setExtraInfo(bundle);//将bundle值传入marker中，给baiduMap设置监听时可以得到它
            }
            //将地图移动到最后一个标志点
            MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(point);
            baiduMap.setMapStatus(status);
        }
    }*/


    //初始化环网柜覆盖物对应的信息
    /*private void getHuanWangGuiDataForSql(){
        //从本地数据库中获取到的坐标
        if (huanWangGuiBeen != null && huanWangGuiBeen.toString().length() > 0) {
            bdMapClientList6 = new ArrayList<>();
            for (int i = 0; i <huanWangGuiBeen.size(); i++) {
                Log.w("2233335555",huanWangGuiBeen.size()+"");
                HuanWangGuiMap b = new  HuanWangGuiMap();
               // b.setName("环网柜");
                b.setId(huanWangGuiBeen.get(i).getUuid());
                b.setName(huanWangGuiBeen.get(i).getName());
                b.setLatitude(Double.parseDouble(huanWangGuiBeen.get(i).getWeidu()));
                b.setLongitude(Double.parseDouble(huanWangGuiBeen.get(i).getJingdu()));
                //让所有覆盖物的经纬度与你自己的经纬度相近，以便打开地图就能看到那些覆盖物
                bdMapClientList6.add(b);
            }
        }
    }*/


    /**
     * 添加覆盖物的方法
     */
   /* private void addHuanWangGuiOverlay() {
        LatLng point = null;
        MarkerOptions option = null;
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.huangwangguimap);
        if(bdMapClientList6!=null) {
            for (HuanWangGuiMap data : bdMapClientList6) {
                point = new LatLng(data.getLatitude(), data.getLongitude());
                option = new MarkerOptions().position(point).icon(bitmap);
                Marker marker1 = (Marker) baiduMap.addOverlay(option);
                //Bundle用于通信
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", data.getName() + "\n" + "纬度：" + data.getLatitude() + "   经度：" + data.getLongitude());
                marker1.setExtraInfo(bundle);//将bundle值传入marker中，给baiduMap设置监听时可以得到它
            }
            //将地图移动到最后一个标志点
            MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(point);
            baiduMap.setMapStatus(status);
        }
    }*/

    //初始化箱变覆盖物对应的信息
    /*private void getXiangBianMapDataForSql(){
        //从本地数据库中获取到的坐标
        if (xaingBianBeen != null && xaingBianBeen.toString().length() > 0) {
            bdMapClientList7 = new ArrayList<>();
            for (int i = 0; i <xaingBianBeen.size(); i++) {
                Log.w("2233335555",xaingBianBeen.size()+"");
                XaingBianMap b = new  XaingBianMap();
                b.setId(xaingBianBeen.get(i).getUuid());
                b.setName(xaingBianBeen.get(i).getName());
                b.setLatitude(Double.parseDouble(xaingBianBeen.get(i).getWeidu()));
                b.setLongitude(Double.parseDouble(xaingBianBeen.get(i).getJingdu()));
                //让所有覆盖物的经纬度与你自己的经纬度相近，以便打开地图就能看到那些覆盖物
                bdMapClientList7.add(b);
            }

        }
    }*/

    /**
     * 添加覆盖物的方法
     */
    /*private void addXiangBianOverlay() {
        LatLng point = null;
        MarkerOptions option = null;
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.xiangbianmap);
        if(bdMapClientList7!=null) {
            for (XaingBianMap data : bdMapClientList7) {
                point = new LatLng(data.getLatitude(), data.getLongitude());
                option = new MarkerOptions().position(point).icon(bitmap);
                Marker marker1 = (Marker) baiduMap.addOverlay(option);
                //Bundle用于通信
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", data.getName() + "\n" + "纬度：" + data.getLatitude() + "   经度：" + data.getLongitude());
                marker1.setExtraInfo(bundle);//将bundle值传入marker中，给baiduMap设置监听时可以得到它
            }
            //将地图移动到最后一个标志点
            MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(point);
            baiduMap.setMapStatus(status);
        }

    }*/


    //初始化配电室覆盖物对应的信息
    /*private void getPeiDianShiMapDataForSql(){
        //从本地数据库中获取到的坐标
        if (peiDinshiBeen!= null && peiDinshiBeen.toString().length() > 0) {
            bdMapClientList8 = new ArrayList<>();
            for (int i = 0; i <peiDinshiBeen.size(); i++) {
                Log.w("2233335555",peiDinshiBeen.size()+"");
                PeiDianShiMap b = new  PeiDianShiMap();
                b.setId(peiDinshiBeen.get(i).getUuid());
                b.setName(peiDinshiBeen.get(i).getName());
                b.setLatitude(Double.parseDouble(peiDinshiBeen.get(i).getWeidu()));
                b.setLongitude(Double.parseDouble(peiDinshiBeen.get(i).getJingdu()));
                //让所有覆盖物的经纬度与你自己的经纬度相近，以便打开地图就能看到那些覆盖物
                bdMapClientList8.add(b);
            }

        }
    }*/


    /**
     * 添加覆盖物的方法
     */
    /*private void addPeiDianShiOverlay() {
        LatLng point = null;
        MarkerOptions option = null;
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.peidianshimap);
        if(bdMapClientList8!=null) {
            for (PeiDianShiMap data : bdMapClientList8) {
                point = new LatLng(data.getLatitude(), data.getLongitude());
                option = new MarkerOptions().position(point).icon(bitmap);
                Marker marker1 = (Marker) baiduMap.addOverlay(option);
                //Bundle用于通信
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", data.getName() + "\n" + "纬度：" + data.getLatitude() + "   经度：" + data.getLongitude());
                marker1.setExtraInfo(bundle);//将bundle值传入marker中，给baiduMap设置监听时可以得到它
            }
            //将地图移动到最后一个标志点
            MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(point);
            baiduMap.setMapStatus(status);
        }
    }*/


    //初始化变压器覆盖物对应的信息
    /*private void getBianYaQiDataForSql(){
        //从本地数据库中获取到的坐标
        if (bianYaQiBeen!= null && bianYaQiBeen.toString().length() > 0) {
            bdMapClientList9 = new ArrayList<>();
            for (int i = 0; i <bianYaQiBeen.size(); i++) {
                Log.w("2233335555",bianYaQiBeen.size()+"");
                BianYaQiMap b = new   BianYaQiMap();
                b.setId(bianYaQiBeen.get(i).getUuid());
                b.setName(bianYaQiBeen.get(i).getName());
                b.setLatitude(Double.parseDouble(bianYaQiBeen.get(i).getWeidu()));
                b.setLongitude(Double.parseDouble(bianYaQiBeen.get(i).getJingdu()));
                //让所有覆盖物的经纬度与你自己的经纬度相近，以便打开地图就能看到那些覆盖物
                bdMapClientList9.add(b);
            }

        }
    }*/



    /**
     * 添加覆盖物的方法
     */
    /*private void addBianYaQiOverlay() {
        LatLng point = null;
        MarkerOptions option = null;
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.bianyaqi);
        if(bdMapClientList9!=null) {
            for (BianYaQiMap data : bdMapClientList9) {
                point = new LatLng(data.getLatitude(), data.getLongitude());
                option = new MarkerOptions().position(point).icon(bitmap);
                Marker marker1 = (Marker) baiduMap.addOverlay(option);
                //Bundle用于通信
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", data.getName() + "\n" + "纬度：" + data.getLatitude() + "   经度：" + data.getLongitude());
                marker1.setExtraInfo(bundle);//将bundle值传入marker中，给baiduMap设置监听时可以得到它
            }
            //将地图移动到最后一个标志点
            MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(point);
            baiduMap.setMapStatus(status);
        }
    }*/


    /*private void getGanTaMapDataForSql(){
        //从本地数据库中获取到的坐标
        if (ganTaBeen!= null && ganTaBeen.toString().length() > 0) {
            bdMapClientList10 = new ArrayList<>();
            for (int i = 0; i <ganTaBeen.size(); i++) {
                Log.w("2233335555",ganTaBeen.size()+"");
                GanTaMap b = new   GanTaMap();
                b.setId(ganTaBeen.get(i).getUuid());
                b.setName(ganTaBeen.get(i).getName());
                b.setLatitude(Double.parseDouble(ganTaBeen.get(i).getWeidu()));
                b.setLongitude(Double.parseDouble(ganTaBeen.get(i).getJingdu()));
                //让所有覆盖物的经纬度与你自己的经纬度相近，以便打开地图就能看到那些覆盖物
                bdMapClientList10.add(b);
            }

        }
    }*/


    /**
     * 添加覆盖物的方法
     */
   /* private void addGanTaOverlay() {
        LatLng point = null;
        MarkerOptions option = null;
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.gantamap);
        if(bdMapClientList10!=null) {
            for (GanTaMap data : bdMapClientList10) {
                point = new LatLng(data.getLatitude(), data.getLongitude());
                option = new MarkerOptions().position(point).icon(bitmap);
                Marker marker1 = (Marker) baiduMap.addOverlay(option);
                //Bundle用于通信
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", data.getName() + "\n" + "纬度：" + data.getLatitude() + "   经度：" + data.getLongitude());
                marker1.setExtraInfo(bundle);//将bundle值传入marker中，给baiduMap设置监听时可以得到它
            }
            //将地图移动到最后一个标志点
            MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(point);
            baiduMap.setMapStatus(status);
        }
    }


    //初始化变电站覆盖物对应的信息
    private void getBianDainZhanMapDataForSql(){
        //从本地数据库中获取到的坐标
        if (bianDianZhanBeen!= null && bianDianZhanBeen.toString().length() > 0) {
            bdMapClientList11 = new ArrayList<>();
            for (int i = 0; i <bianDianZhanBeen.size(); i++) {
                Log.w("2233335555",bianDianZhanBeen.size()+"");
                BianDainZhanMap b = new   BianDainZhanMap();
                b.setId(bianDianZhanBeen.get(i).getUuid());
                b.setName(bianDianZhanBeen.get(i).getName());
                b.setLatitude(Double.parseDouble(bianDianZhanBeen.get(i).getWeidu()));
                b.setLongitude(Double.parseDouble(bianDianZhanBeen.get(i).getJingdu()));
                //让所有覆盖物的经纬度与你自己的经纬度相近，以便打开地图就能看到那些覆盖物
                bdMapClientList11.add(b);
            }

        }
    }



    *//**
     * 添加覆盖物的方法
     *//*
    private void addBianDainZhanOverlay() {
        LatLng point = null;
        MarkerOptions option = null;
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.biandianzhanmap);
        if(bdMapClientList11!=null) {
            for (BianDainZhanMap data : bdMapClientList11) {
                point = new LatLng(data.getLatitude(), data.getLongitude());
                option = new MarkerOptions().position(point).icon(bitmap);
                Marker marker1 = (Marker) baiduMap.addOverlay(option);
                //Bundle用于通信
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", data.getName() + "\n" + "纬度：" + data.getLatitude() + "   经度：" + data.getLongitude());
                marker1.setExtraInfo(bundle);//将bundle值传入marker中，给baiduMap设置监听时可以得到它
            }
            //将地图移动到最后一个标志点
            MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(point);
            baiduMap.setMapStatus(status);
        }
    }

    //初始化开关站覆盖物对应的信息
    private void getKaiGuanZhanMapDataForSql(){
        //从本地数据库中获取到的坐标
        if (kaiGuanZhanBeen!= null && kaiGuanZhanBeen.toString().length() > 0) {
            bdMapClientList12 = new ArrayList<>();
            for (int i = 0; i <kaiGuanZhanBeen.size(); i++) {
                Log.w("2233335555",kaiGuanZhanBeen.size()+"");
                KaiGuanZhuanMap b = new   KaiGuanZhuanMap();
                b.setId(kaiGuanZhanBeen.get(i).getUuid());
                b.setName(kaiGuanZhanBeen.get(i).getName());
                b.setLatitude(Double.parseDouble(kaiGuanZhanBeen.get(i).getWeidu()));
                b.setLongitude(Double.parseDouble(kaiGuanZhanBeen.get(i).getJingdu()));
                //让所有覆盖物的经纬度与你自己的经纬度相近，以便打开地图就能看到那些覆盖物
                bdMapClientList12.add(b);
            }

        }
    }


    *//**
     * 添加覆盖物的方法
     *//*
    private void addKaiGuanZhanOverlay() {
        LatLng point = null;
        MarkerOptions option = null;
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.kaiguanzhanmap);
        if(bdMapClientList12!=null) {
            for (KaiGuanZhuanMap data : bdMapClientList12) {
                point = new LatLng(data.getLatitude(), data.getLongitude());
                option = new MarkerOptions().position(point).icon(bitmap);
                Marker marker1 = (Marker) baiduMap.addOverlay(option);
                //Bundle用于通信
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", data.getName() + "\n" + "纬度：" + data.getLatitude() + "   经度：" + data.getLongitude());
                marker1.setExtraInfo(bundle);//将bundle值传入marker中，给baiduMap设置监听时可以得到它
            }
            //将地图移动到最后一个标志点
            MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(point);
            baiduMap.setMapStatus(status);
        }
    }

    // 根据经纬度查询位置
    private void getInfoFromLAL(final LatLng point) {
        //pb.setVisibility(View.VISIBLE);
        //infoText.setText("经度：" + point.latitudeE6 + "，纬度" + point.latitudeE6);
        GeoCoder gc = GeoCoder.newInstance();
        gc.reverseGeoCode(new ReverseGeoCodeOption().location(point));
        gc.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                //pb.setVisibility(View.GONE);
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Log.e("发起反地理编码请求", "未能找到结果");
                } else {
                    //infoText.setText("经度：" + point.latitudeE6 + "，纬度" + point.latitudeE6+ "\n" + result.getAddress());
                }
            }

            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }*/


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            /*case R.id.dingwei:
                if(point!= null) {
                    setMapOverlay(point);
                    getInfoFromLAL(point);
                    baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
                    Log.e("5555555555", "555555555555");
                }
                break;*/
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.unpause();
    }
}
