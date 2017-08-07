package dlgx.gis.com.dlgx.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dlgx.gis.com.dlgx.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{


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
    protected void onCreate(Bundle savedInstanceState)
    {

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
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Snackbar.make(toolbar, "Click setNavigationIcon", Snackbar.LENGTH_SHORT).show();
                //drawerlayout.closeDrawer();
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
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
        mMapView.setOnStatusChangedListener(new OnStatusChangedListener()
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
        mMapView.setOnSingleTapListener(new OnSingleTapListener()
        {
            @Override
            public void onSingleTap(final float x, final float y)
            {
                if (!mMapView.isLoaded())
                {
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

    public void initRight()
    {
        // 关闭手势滑动
        drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerlayout.addDrawerListener(new DrawerLayout.DrawerListener()
        {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {

            }

            @Override
            public void onDrawerOpened(View drawerView)
            {
                // 打开手势滑动
                drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                // 关闭手势滑动
                drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerStateChanged(int newState)
            {

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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //加载菜单文件
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar
            .OnMenuItemClickListener()
    {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem)
        {
            switch (menuItem.getItemId())
            {
//                case R.id.action_search://因为使用android.support.v7.widget
// .SearchView类，可以在onCreateOptionsMenu(Menu menu)中直接设置监听事件
//                    Snackbar.make(toolbar,"Click Search",Snackbar.LENGTH_SHORT).show();
//                    break;

                case R.id.action_more:
                    // Snackbar.make(toolbar,"Click More",Snackbar.LENGTH_SHORT).show();
                    if (!drawerlayout.isDrawerOpen(right))
                    {
                        drawerlayout.openDrawer(right);
                    } else
                    {
                        drawerlayout.closeDrawer(right);
                    }
                    break;
            }
            return true;
        }
    };

    @Override
    public void onClick(View v)
    {
        int viewid = v.getId();
        Intent intent;
        switch (viewid)
        {
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
}
