package dlgx.gis.com.dlgx.activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapOptions;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.MarkerSymbol;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.beans.BianDainZhanMap;
import dlgx.gis.com.dlgx.beans.BianDianZhanBean;
import dlgx.gis.com.dlgx.beans.BianYaQiBean;
import dlgx.gis.com.dlgx.beans.BianYaQiMap;
import dlgx.gis.com.dlgx.beans.DainLanTongDaoMap;
import dlgx.gis.com.dlgx.beans.DianLanDuanBean;
import dlgx.gis.com.dlgx.beans.DianLanDuanMap;
import dlgx.gis.com.dlgx.beans.DianLanJingMap;
import dlgx.gis.com.dlgx.beans.DianlantongdaoBean;
import dlgx.gis.com.dlgx.beans.DltdglBean;
import dlgx.gis.com.dlgx.beans.FenZhiXiangBean;
import dlgx.gis.com.dlgx.beans.FenZhiXiangMap;
import dlgx.gis.com.dlgx.beans.GanTaBean;
import dlgx.gis.com.dlgx.beans.GanTaMap;
import dlgx.gis.com.dlgx.beans.GongJingXinxiBean;
import dlgx.gis.com.dlgx.beans.GuaiDianMap;
import dlgx.gis.com.dlgx.beans.GuaidainBean;
import dlgx.gis.com.dlgx.beans.HuanWangGuiBean;
import dlgx.gis.com.dlgx.beans.HuanWangGuiMap;
import dlgx.gis.com.dlgx.beans.KaiGuanZhanBean;
import dlgx.gis.com.dlgx.beans.KaiGuanZhuanMap;
import dlgx.gis.com.dlgx.beans.PeiDianShiMap;
import dlgx.gis.com.dlgx.beans.PeiDinshiBean;
import dlgx.gis.com.dlgx.beans.XaingBianBean;
import dlgx.gis.com.dlgx.beans.XaingBianMap;
import dlgx.gis.com.dlgx.beans.YanMaiJingBean;
import dlgx.gis.com.dlgx.beans.YanMaiJingMap;
import dlgx.gis.com.dlgx.db.DatabaseAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
///ddefdfe
    @Bind(R.id.map)
    MapView map;
    @Bind(R.id.del)
    ImageButton del;
    @Bind(R.id.dljcz)
    LinearLayout dljcz;
    @Bind(R.id.main_rg_tabs)
    LinearLayout mainRgTabs;
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.ib_weixing)
    ImageButton ibWeixing;
    @Bind(R.id.ib_pingmian)
    ImageButton ibPingmian;
    @Bind(R.id.layout_lixinxiazai)
    LinearLayout layoutLixinxiazai;
    @Bind(R.id.esrimapbtn)
    TextView esrimapbtn;
    @Bind(R.id.tv_sure)
    TextView tvSure;
    @Bind(R.id.dispatch_item_select_user_name)
    TextView dispatchItemSelectUserName;
    @Bind(R.id.dispatch_item_select_user_ckb)
    CheckBox dispatchItemSelectUserCkb;
    @Bind(R.id.cb_tongdao)
    CheckBox cbTongdao;
    @Bind(R.id.cb_dianlanduan)
    CheckBox cbDianlanduan;
    @Bind(R.id.cb_shebei)
    CheckBox cbShebei;
    @Bind(R.id.cb_guaidain)
    CheckBox cbGuaidain;
    @Bind(R.id.cb_fenzhixiang)
    CheckBox cbFenzhixiang;
    @Bind(R.id.cb_huangwanggui)
    CheckBox cbHuangwanggui;
    @Bind(R.id.cb_xiangbian)
    CheckBox cbXiangbian;
    @Bind(R.id.cb_peidianshi)
    CheckBox cbPeidianshi;
    @Bind(R.id.cb_bianyaqi)
    CheckBox cbBianyaqi;
    @Bind(R.id.cb_ganta)
    CheckBox cbGanta;
    @Bind(R.id.cb_biandianzhan)
    CheckBox cbBiandianzhan;
    @Bind(R.id.cb_kaiguanzhan)
    CheckBox cbKaiguanzhan;
    @Bind(R.id.cb_yanmaijing)
    CheckBox cbYanmaijing;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private RelativeLayout maprela;

    private ImageButton sure;
    private LinearLayout right;
    private DrawerLayout drawerlayout;
    private Toolbar toolbar;

    @Bind(R.id.tab_rb_a)
    ImageButton tabRbA;
    @Bind(R.id.tab_rb_b)
    ImageButton tabRbB;
    @Bind(R.id.tab_rb_c)
    ImageButton tabRbC;
    @Bind(R.id.tab_rb_d)
    ImageButton tabRbD;

    // The MapView.
    private MapView mMapView = null;

    private List<DianLanJingMap> bdMapClientList1;
    private List<DianLanDuanMap> bdMapClientList2;
    private List<DainLanTongDaoMap> bdMapClientList3;
    private List<GuaiDianMap> bdMapClientList4;
    private List<FenZhiXiangMap> bdMapClientList5;
    private List<HuanWangGuiMap> bdMapClientList6;
    private List<XaingBianMap> bdMapClientList7;
    private List<PeiDianShiMap> bdMapClientList8;
    private List<BianYaQiMap> bdMapClientList9;
    private List<GanTaMap> bdMapClientList10;
    private List<BianDainZhanMap> bdMapClientList11;
    private List<KaiGuanZhuanMap> bdMapClientList12;
    private List<YanMaiJingMap> bdMapClientList13;

    //从本地数据库查询经度纬度
    private List<GongJingXinxiBean> gongJingXinxiBeen = new ArrayList<>();
    private  List<DianlantongdaoBean> dianlantongdaoBeen = new ArrayList<DianlantongdaoBean>();
    private List<DianLanDuanBean> dianLanDuanBeen = new ArrayList<DianLanDuanBean>();
    private List<GuaidainBean> guaidainBeen = new ArrayList<GuaidainBean>();
    private List<FenZhiXiangBean> fenZhiXiangBeen = new ArrayList<FenZhiXiangBean>();
    private List<HuanWangGuiBean> huanWangGuiBeen = new ArrayList<HuanWangGuiBean>();
    private List<XaingBianBean> xaingBianBeen = new ArrayList<XaingBianBean>();
    private List<PeiDinshiBean> peiDinshiBeen = new ArrayList<PeiDinshiBean>();
    List<GanTaBean> ganTaBeen = new ArrayList<GanTaBean>();
    private List<BianDianZhanBean> bianDianZhanBeen = new ArrayList<BianDianZhanBean>();
    private List<BianYaQiBean> bianYaQiBeen = new ArrayList<BianYaQiBean>();
    private List<KaiGuanZhanBean> kaiGuanZhanBeen = new ArrayList<KaiGuanZhanBean>();
    private List<YanMaiJingBean> yanMaiJingBeen = new ArrayList<YanMaiJingBean>();
    private List<DltdglBean> dltdglBeen = new ArrayList<DltdglBean>();


    private Graphic jingmarker,duanmarker,duanmarker1,guaidianmarker,fenzhixiangmarker;
    private Graphic huanmarker,xiangbianmarker,peidianshimarker,bianyamarker,gantamarker;
    private Graphic biandianzhanmarker, kaiguanmarker,maijingmarker;

    // The basemap switching menu items.
    private MenuItem mStreetsMenuItem = null;
    private MenuItem mTopoMenuItem = null;
    private MenuItem mGrayMenuItem = null;
    private MenuItem mOceansMenuItem = null;

    // Create MapOptions for each type of basemap.
    private final MapOptions mTopoBasemap = new MapOptions(MapOptions.MapType.TOPO);
    private final MapOptions mStreetsBasemap = new MapOptions(MapOptions.MapType.STREETS);
    private final MapOptions mGrayBasemap = new MapOptions(MapOptions.MapType.GRAY);
    private final MapOptions mOceansBasemap = new MapOptions(MapOptions.MapType.OCEANS);
    // The current map extent, use to set the extent of the map after switching basemaps.
    private Polygon mCurrentMapExtent = null;
    private GraphicsLayer graLyr = null;
    private String tiledUrl = "http://222.188.115" +
            ".94:6080/arcgis/rest/services/yxt20160727/MapServer";
    private ArcGISTiledMapServiceLayer tiledMapServiceLayer = null;
    private ImageView mImageView;

    private String jingdu = "";
    private String weidu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        right = (LinearLayout) findViewById(R.id.right);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawerlayout.closeDrawer(right);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_gx);
        //toolbar.setTitle("Material Design ToolBar");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setTitleTextColor(Color.BLACK);
//        toolbar.setSubtitle("  ToolBar subtitle");
//        toolbar.setSubtitleTextColor(Color.BLACK);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_menus);
        //添加菜单点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(toolbar, "Click setNavigationIcon", Snackbar.LENGTH_SHORT).show();
                //drawerlayout.closeDrawer();
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                if (!drawerlayout.isDrawerOpen(right)) {
//                    drawerlayout.openDrawer(right);
//                }else{
//                    drawerlayout.closeDrawer(right);
//                }
                Point wgspoint = new Point(118.78, 32.04);
                Point mapPoint = (Point) GeometryEngine
                        .project(wgspoint, SpatialReference.create(4326), mMapView
                                .getSpatialReference());
                mMapView.centerAt(mapPoint, true);
            }
        });
        // Retrieve the map and initial extent from XML layout
        mMapView = (MapView) findViewById(R.id.map);
//        tiledMapServiceLayer=new ArcGISTiledMapServiceLayer(tiledUrl);
//        mMapView.addLayer(tiledMapServiceLayer);
        // Set the Esri logo to be visible, and enable map to wrap around date line.
        mMapView.setEsriLogoVisible(true);
        mMapView.enableWrapAround(true);
        graLyr = new GraphicsLayer();

        mMapView.addLayer(graLyr);
        // Set a listener for map status changes; this will be called when switching basemaps.
        mMapView.setOnStatusChangedListener(new OnStatusChangedListener() {
            private static final long serialVersionUID = 1L;

            public void onStatusChanged(Object source, STATUS status) {
                // Set the map extent once the map has been initialized, and the basemap is added
                // or changed; this will be indicated by the layer initialization of the basemap
                // layer. As there is only
                // a single layer, there is no need to check the source object.
                if (STATUS.LAYER_LOADED == status) {
                    mMapView.setExtent(mCurrentMapExtent);
                    MarkerSymbol symbol = new SimpleMarkerSymbol(Color.RED, 12,
                            SimpleMarkerSymbol.STYLE.CIRCLE);
                    Point wgspoint = new Point(118.78, 32.04);
                    Point mapPoint = (Point) GeometryEngine
                            .project(wgspoint, SpatialReference.create(4326), mMapView
                                    .getSpatialReference());
                    Graphic gra = new Graphic(mapPoint, symbol);
                    // SpatialReference sp=gra.getSpatialReference();
                    graLyr.addGraphic(gra);
                }
            }
        });
        //mMapView.setClickable(true);
        mMapView.setEsriLogoVisible(false);
//        mImageView = new ImageView(this);//初始化imgview
        mMapView.setOnSingleTapListener(new OnSingleTapListener() {
            @Override
            public void onSingleTap(final float x, final float y) {
                if (!mMapView.isLoaded()) {
                    return;
                }
                // Add to Identify Parameters based on tapped location
                Point identifyPoint = mMapView.toMapPoint(x, y);
                MarkerSymbol symbol = new SimpleMarkerSymbol(Color.RED, 12, SimpleMarkerSymbol
                        .STYLE.CIRCLE);
                Drawable d = getResources().getDrawable(R.drawable.ic_xb20);
                PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(d);
                Graphic gra = new Graphic(identifyPoint, pictureMarkerSymbol);
                graLyr.removeAll();
                graLyr.addGraphic(gra);
                Point mappoint = (Point) GeometryEngine
                        .project(identifyPoint, mMapView.getSpatialReference(), SpatialReference
                                .create(4326));
                jingdu = mappoint.getX() + "";
                weidu = mappoint.getY() + "";
                Snackbar.make(toolbar, "x:" + mappoint.getX() + ";y:" + mappoint
                        .getY(), Snackbar.LENGTH_SHORT).show();
            }
        });
        initRight();

        tabRbC.setOnClickListener(this);
        tabRbD.setOnClickListener(this);
    }

    public void initRight() {
        // 关闭手势滑动
        drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // 打开手势滑动
                drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                // 关闭手势滑动
                drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        ///绑定到activity onclick
        ImageButton ib_weixing = (ImageButton) findViewById(R.id.ib_weixing);
        ib_weixing.setOnClickListener(this);
        ///绑定到activity onclick
        ImageButton ib_pingmian = (ImageButton) findViewById(R.id.ib_pingmian);
        ib_pingmian.setOnClickListener(this);
        sure = (ImageButton) findViewById(R.id.sure);
        sure.setOnClickListener(this);
    }


    /**
     * 该方法是用来加载菜单布局的
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单文件
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar
            .OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
//                case R.id.action_search://因为使用android.support.v7.widget
// .SearchView类，可以在onCreateOptionsMenu(Menu menu)中直接设置监听事件
//                    Snackbar.make(toolbar,"Click Search",Snackbar.LENGTH_SHORT).show();
//                    break;

                case R.id.action_more:
                    // Snackbar.make(toolbar,"Click More",Snackbar.LENGTH_SHORT).show();
                    if (!drawerlayout.isDrawerOpen(right)) {
                        drawerlayout.openDrawer(right);
                    } else {
                        drawerlayout.closeDrawer(right);
                    }
                    break;
            }
            return true;
        }
    };



    @Override
    public void onClick(View v) {
        int viewid = v.getId();
        Intent intent;
        switch (viewid) {
//            case R.id.button_first:
//                drawerlayout.closeDrawer(right);
//                break;
            case R.id.ib_pingmian:
                mMapView.setMapOptions(mTopoBasemap);

                break;
            case R.id.ib_weixing:
                mMapView.setMapOptions(mStreetsBasemap);
                break;
            case R.id.sure:
                intent = new Intent(MainActivity.this, CablePitNewsActivity.class);
                intent.putExtra("jingDu", jingdu);
                intent.putExtra("weiDu", weidu);
                break;
            case R.id.tab_rb_c:
                //跳转到新增电缆段
                intent = new Intent(MainActivity.this, CutCableActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_rb_d:
                //跳转到新增设备
                intent = new Intent(MainActivity.this, EquipmentHomeActivity.class);
                startActivity(intent);
                break;
            default:
                mMapView.setMapOptions(mOceansBasemap);
                break;
        }
    }



    @OnClick({R.id.ib_weixing, R.id.ib_pingmian, R.id.dispatch_item_select_user_ckb, R.id.cb_tongdao, R.id.cb_dianlanduan, R.id.cb_shebei, R.id.cb_guaidain, R.id.cb_fenzhixiang, R.id.cb_huangwanggui, R.id.cb_xiangbian, R.id.cb_peidianshi, R.id.cb_bianyaqi, R.id.cb_ganta, R.id.cb_biandianzhan, R.id.cb_kaiguanzhan, R.id.cb_yanmaijing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_weixing:
                break;
            case R.id.ib_pingmian:
                break;
            case R.id.dispatch_item_select_user_ckb:
                break;
            case R.id.cb_tongdao:
                break;
            case R.id.cb_dianlanduan:
                break;
            case R.id.cb_shebei:
                break;
            case R.id.cb_guaidain:

                break;
            case R.id.cb_fenzhixiang:
                break;
            case R.id.cb_huangwanggui:
                break;
            case R.id.cb_xiangbian:
                break;
            case R.id.cb_peidianshi:
                break;
            case R.id.cb_bianyaqi:
                break;
            case R.id.cb_ganta:
                break;
            case R.id.cb_biandianzhan:
                break;
            case R.id.cb_kaiguanzhan:
                break;
            case R.id.cb_yanmaijing:
                break;
        }
    }

    private void getDianLanjingDataForSql() {
        //从本地数据库中获取到的坐标
        if (gongJingXinxiBeen != null && gongJingXinxiBeen.toString().length() > 0) {
            bdMapClientList1 = new ArrayList<>();
            for (int i = 0; i < gongJingXinxiBeen.size(); i++) {
                Log.w("2233335555", gongJingXinxiBeen.size() + "");
                if(gongJingXinxiBeen.get(i)==null||"".equals(gongJingXinxiBeen.get(i).getNummber())){
                    continue;
                }
                // dainlanjingName = gongJingXinxiBeen.get(i).getName();
                DianLanJingMap b = new DianLanJingMap();
                b.setId(gongJingXinxiBeen.get(i).getUuid());
                b.setName(gongJingXinxiBeen.get(i).getName());
                b.setNummber(gongJingXinxiBeen.get(i).getNummber());
                b.setShuliang(gongJingXinxiBeen.get(i).getShuliang());
                b.setCaizhi(gongJingXinxiBeen.get(i).getCaizhi());
                b.setXingzhuang(gongJingXinxiBeen.get(i).getXingzhuang());
                b.setChicun(gongJingXinxiBeen.get(i).getChicun());
                b.setJingchang(gongJingXinxiBeen.get(i).getJingchang());
                b.setJingkuan(gongJingXinxiBeen.get(i).getJingkuan());
                b.setJingneishen(String.valueOf(gongJingXinxiBeen.get(i).getJingneishen()));
                b.setJingshen(String.valueOf(gongJingXinxiBeen.get(i).getJingshen()));
                b.setGongneng(gongJingXinxiBeen.get(i).getGongneng());
                b.setLeixing(gongJingXinxiBeen.get(i).getLeixing());
                b.setWeidu(Double.parseDouble(gongJingXinxiBeen.get(i).getWeidu()));
                b.setJingdu(Double.parseDouble(gongJingXinxiBeen.get(i).getJingdu()));
                b.setGaodu(gongJingXinxiBeen.get(i).getGaodu());
                b.setJietoushuliang(gongJingXinxiBeen.get(i).getJietoushuliang());
                b.setShebeiId(gongJingXinxiBeen.get(i).getShebeiId());
                b.setDianzibainqianbianhao(gongJingXinxiBeen.get(i).getDianzibainqianbianhao());
                b.setTouyunriqi(gongJingXinxiBeen.get(i).getTouyunriqi());
                b.setShigongdanwei(gongJingXinxiBeen.get(i).getShigongdanwei());
                b.setBeizhu(gongJingXinxiBeen.get(i).getBeizhu());
                b.setZuoyedanwei(gongJingXinxiBeen.get(i).getZuoyedanwei());
                b.setXinzengshijian(gongJingXinxiBeen.get(i).getXinzengshijian());
                //让所有覆盖物的经纬度与你自己的经纬度相近，以便打开地图就能看到那些覆盖物
                bdMapClientList1.add(b);
            }
        }
    }
    private void setDLTDChecked(){
        dispatchItemSelectUserCkb.setChecked(true);
        cbTongdao.setChecked(true);
        cbShebei.setChecked(true);
//        cbGuaidain.setChecked(true);
//        cbFenzhixiang.setChecked(true);
//        cbHuangwanggui.setChecked(true);
//        cbXiangbian.setChecked(true);
//        cbPeidianshi.setChecked(true);
//        cbBianyaqi.setChecked(true);
//        cbGanta.setChecked(true);
//        cbBiandianzhan.setChecked(true);
//        cbKaiguanzhan.setChecked(true);
//        cbYanmaijing.setChecked(true);
    }

    private void getShow() {
        //如果电缆井被选择
    }

    private void Quit() {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("退出登录")
                .setMessage("admin")
                .setPositiveButton("确定退出", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);


                    }
                })
                .setNegativeButton("我再想想", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();// 创建
        // 显示对话框
        dialog.show();
    }
}
