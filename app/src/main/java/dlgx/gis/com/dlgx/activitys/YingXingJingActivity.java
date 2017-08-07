package dlgx.gis.com.dlgx.activitys;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.beans.DaunMianBei;
import dlgx.gis.com.dlgx.beans.DaunMianDong;
import dlgx.gis.com.dlgx.beans.DaunMianNan;
import dlgx.gis.com.dlgx.beans.DaunMianXi;
import dlgx.gis.com.dlgx.beans.DianLanJingDuanMianBiao;
import dlgx.gis.com.dlgx.beans.GongJingXinxiBean;
import dlgx.gis.com.dlgx.com.yancy.imageselector.ImageConfig;
import dlgx.gis.com.dlgx.com.yancy.imageselector.ImageSelector;
import dlgx.gis.com.dlgx.com.yancy.imageselector.ImageSelectorActivity;
import dlgx.gis.com.dlgx.db.DatabaseAdapter;
import dlgx.gis.com.dlgx.db.PictureDatabase;
import dlgx.gis.com.dlgx.utils.GlideLoader;
import dlgx.gis.com.dlgx.utils.ImageUtil;
import dlgx.gis.com.dlgx.utils.PublicWay;
import dlgx.gis.com.dlgx.utils.Res;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/5/15.
 */
public class YingXingJingActivity extends Activity implements View.OnClickListener {


    public static final int REQUEST_CODE = 0x101;
    private static final int TAKE_PICTURE1 = 2;
    private static final int TAKE_PICTURE2 = 3;
    private static final int TAKE_PICTURE3 = 4;
    private static final int TAKE_PICTURE4 = 5;
    private static final int TAKE_PICTURE6 = 6;
    private static final int TAKE_PICTURE7 = 7;
    private static final int TAKE_PICTURE8 = 8;
    private static final int TAKE_PICTURE9 = 9;
    private static final int CROP_1_REQUEST = 10;
    private static final int CROP_2_REQUEST = 11;


    Bitmap photo;
    File cameraFile;
    @Bind(R.id.title_back1)
    ImageView titleBack1;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.top_image)
    ImageView topImage;
    @Bind(R.id.top_center_text)
    LinearLayout topCenterText;
    @Bind(R.id.quit)
    ImageButton quit;
    @Bind(R.id.title_item)
    RelativeLayout titleItem;
    @Bind(R.id.tv_adress_quanmao)
    TextView tvAdressQuanmao;
    @Bind(R.id.img_image)
    ImageView imgImage;
    @Bind(R.id.img_delete)
    ImageView imgDelete;
    @Bind(R.id.tv_adress_jingnei_quanmao)
    TextView tvAdressJingneiQuanmao;
    @Bind(R.id.img_image2)
    ImageView imgImage2;
    @Bind(R.id.img_delete2)
    ImageView imgDelete2;
    @Bind(R.id.tv_adress_bei)
    TextView tvAdressBei;
    @Bind(R.id.tv_imagename)
    TextView tvImagename;
    @Bind(R.id.img_image3)
    ImageView imgImage3;
    @Bind(R.id.img_delete3)
    ImageView imgDelete3;
    @Bind(R.id.img_etid)
    ImageView imgEtid;
    @Bind(R.id.tv_adress_dong)
    TextView tvAdressDong;
    @Bind(R.id.tv_imagenamedong)
    TextView tvImagenamedong;
    @Bind(R.id.img_image4)
    ImageView imgImage4;
    @Bind(R.id.img_delete4)
    ImageView imgDelete4;
    @Bind(R.id.img_dongetid)
    ImageView imgDongetid;
    @Bind(R.id.tv_adress_nan)
    TextView tvAdressNan;
    @Bind(R.id.tv_imagenamenan)
    TextView tvImagenamenan;
    @Bind(R.id.img_image5)
    ImageView imgImage5;
    @Bind(R.id.img_delete5)
    ImageView imgDelete5;
    @Bind(R.id.img_nanetid)
    ImageView imgNanetid;
    @Bind(R.id.tv_adress_xi)
    TextView tvAdressXi;
    @Bind(R.id.tv_imagenamexi)
    TextView tvImagenamexi;
    @Bind(R.id.img_image6)
    ImageView imgImage6;
    @Bind(R.id.img_textetid)
    ImageView imgTextetid;
    @Bind(R.id.img_xietid)
    ImageView imgXietid;
    @Bind(R.id.btn_up)
    Button btnUp;
    @Bind(R.id.sv)
    ScrollView sv;
    @Bind(R.id.lv_bei)
    ListView lvBei;
    @Bind(R.id.lv_dong)
    ListView lvDong;
    @Bind(R.id.lv_nan)
    ListView lvNan;
    @Bind(R.id.lv_xi)
    ListView lvXi;
    @Bind(R.id.btn_newAdd)
    Button btnNewAdd;//新增断面
    @Bind(R.id.tv_adressxuhao)
    TextView tvAdressxuhao;//序号
    @Bind(R.id.tv_adressDongxuhao)
    TextView tvAdressDongxuhao;//东序号
    @Bind(R.id.tv_adressNanxuhao)
    TextView tvAdressNanxuhao;//南序号
    @Bind(R.id.tv_adressXixuhao)
    TextView tvAdressXixuhao;//西序号


    private View parentView;


    private String img1Path = "", img2Path = "", img3Path = "", img4Path = "", img5Path = "", img6Path = "";
    ArrayList<String> path = new ArrayList<>();
    private ArrayList<String> upPath = new ArrayList<>();
    private String cPicState = "1";

    public static YingXingJingActivity yingXingJingActivity;

    private List<GongJingXinxiBean> address = new ArrayList<GongJingXinxiBean>();
    private List<DianLanJingDuanMianBiao> duanMianBiaos = new ArrayList<DianLanJingDuanMianBiao>();


    ImageView imageview;
    private String fangWei;
    private String xuHao;
    private String adress;
    private String fileName;
    private PopupWindow pop = null;
    private LinearLayout ll_popup;
    public static Bitmap bimap;
    //public static Bitmap beiBm;
    private int count;
    private int countDong;


    private ArrayList<DaunMianBei> daunMianBeiList;
    private ArrayList<DaunMianDong> daunMianDongList;
    private ArrayList<DaunMianNan> mianNanList;
    private ArrayList<DaunMianXi> mianXiList;
    private PictureDatabase pictureDatabase;
    private ArrayList<Bitmap> bitmap_list;
    private DaunMianBeiAdapter beiAdapter;
    private DaunMianDongAdapter dongAdapter;
    private DaunMianNanAdapter nanAdapter;
    private DaunMianXiAdapter xiAdapter;
    private DaunMianBei bei;
    private List ImList = new ArrayList();
    private String compareXuhao;
    private ArrayList<Bitmap> bitmap_listBei;
    private String dljnummber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Res.init(this);
        PublicWay.activityList.add(this);
        parentView = getLayoutInflater().inflate(R.layout.yingxingjingactivity_activity, null);
        setContentView(parentView);
        yingXingJingActivity = this;
        ButterKnife.bind(this);
        pictureDatabase = new PictureDatabase(this);
        address = DatabaseAdapter.getIntance(getBaseContext()).queryJingAddress();
        getDataForSql();
        getDainLanDuanMianDataForSql();
        if(dljnummber!=null) {
            duanMianBiaos = DatabaseAdapter.getIntance(getBaseContext()).queryDuanMianByDljAddress(dljnummber);
        }
        getPicture();
        initView();

        //北
        beiAdapter = new DaunMianBeiAdapter(add(), YingXingJingActivity.this);
        lvBei.setAdapter(beiAdapter);
        setListViewHeightBasedOnChildren(lvBei);
        //东
        dongAdapter = new DaunMianDongAdapter(addDong(), YingXingJingActivity.this);
        lvDong.setAdapter(dongAdapter);
        setListViewHeightBasedOnChildrenDong(lvDong);
        //南
        nanAdapter = new DaunMianNanAdapter(addNan(), YingXingJingActivity.this);
        lvNan.setAdapter(nanAdapter);
        setListViewHeightBasedOnChildrenNan(lvNan);
        //西
        xiAdapter = new DaunMianXiAdapter(addXi(), YingXingJingActivity.this);
        lvXi.setAdapter(xiAdapter);
        setListViewHeightBasedOnChildrenXi(lvXi);


    }

    //scrollview嵌套listview只显示一条的解决方法
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        lvBei.setLayoutParams(params);
    }

    //scrollview嵌套listview只显示一条的东解决方法
    public void setListViewHeightBasedOnChildrenDong(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        lvDong.setLayoutParams(params);
    }

    //scrollview嵌套listview只显示一条的东解决方法
    public void setListViewHeightBasedOnChildrenNan(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        lvNan.setLayoutParams(params);
    }

    //scrollview嵌套listview只显示一条的东解决方法
    public void setListViewHeightBasedOnChildrenXi(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        lvXi.setLayoutParams(params);
    }

    //显示本地缓存
    private void getDainLanDuanMianDataForSql() {
        if (duanMianBiaos != null && duanMianBiaos.size() > 0) {
            for (int i = 0; i < duanMianBiaos.size(); i++) {
                //编号
                fangWei = duanMianBiaos.get(i).getFangwei();
                xuHao = duanMianBiaos.get(i).getDuanmianxuhao();
                Log.e("fangWei1", fangWei);
                Log.e(" xuHao2", xuHao);
                //tvImagename.setText(fangWei + xuHao);

            }
        }
    }

    //显示本地缓存
    private void getDataForSql() {
        if (address != null && address.size() > 0) {
            for (int i = 0; i < address.size(); i++) {
                //电缆井现场编号
                dljnummber = address.get(i).getNummber();
                //电缆井名称
                fileName = address.get(i).getName();
                //地址
                adress = address.get(i).getAdress();

            }
        }
    }

    private ArrayList<DaunMianBei> add() {
        //序号
        String beiXuhao = tvAdressxuhao.getText().toString();
        daunMianBeiList = new ArrayList<>();
        if ( (xuHao != null && fangWei != null && adress != null) && (!beiXuhao.equals(xuHao))&&(fangWei.equals("北"))) {
            for (int i = 0; i < duanMianBiaos.size(); i++) {
                bei = new DaunMianBei();
                bei.setAdress(adress);
                bei.setFangWei(duanMianBiaos.get(i).getFangwei());
                bei.setXuhao(duanMianBiaos.get(i).getDuanmianxuhao());
                daunMianBeiList.add(bei);
            }
        }
        return daunMianBeiList;
    }

    private ArrayList<DaunMianDong> addDong() {
        //序号
        String dongXuhao = tvAdressDongxuhao.getText().toString();
        daunMianDongList = new ArrayList<>();
        if ( (xuHao != null && fangWei != null && adress != null) && (!dongXuhao.equals(xuHao)) &&(fangWei.equals("东")) ) {
            for (int i = 0; i < duanMianBiaos.size(); i++) {
                DaunMianDong bei = new DaunMianDong();
                bei.setAdress(adress);
                bei.setFangWei(duanMianBiaos.get(i).getFangwei());
                bei.setXuhao(duanMianBiaos.get(i).getDuanmianxuhao());
                daunMianDongList.add(bei);
            }
        }
        return daunMianDongList;
    }

    //南
    private ArrayList<DaunMianNan> addNan() {
        //序号
        String nanXuhao = tvAdressNanxuhao.getText().toString();
        mianNanList = new ArrayList<>();
        if ( (xuHao != null && fangWei != null && adress != null) && (!nanXuhao.equals(xuHao)) &&(fangWei.equals("南")) ){
            for (int i = 0; i < duanMianBiaos.size(); i++) {
                DaunMianNan bei = new DaunMianNan();
                bei.setAdress(adress);
                bei.setFangWei(duanMianBiaos.get(i).getFangwei());
                bei.setXuhao(duanMianBiaos.get(i).getDuanmianxuhao());
                mianNanList.add(bei);
            }
        }
        return mianNanList;
    }


    //西
    private ArrayList<DaunMianXi> addXi() {
        //序号
        String xiXuhao = tvAdressXixuhao.getText().toString();
        mianXiList = new ArrayList<>();
        if (( xuHao != null && fangWei != null && adress != null) && (!xiXuhao.equals(xuHao)) &&(fangWei.equals("西"))) {
            for (int i = 0; i < duanMianBiaos.size(); i++) {
                DaunMianXi bei = new DaunMianXi();
                bei.setAdress(adress);
                bei.setFangWei(duanMianBiaos.get(i).getFangwei());
                bei.setXuhao(duanMianBiaos.get(i).getDuanmianxuhao());
                mianXiList.add(bei);
            }
        }
        return mianXiList;
    }

    private void initView() {
        tvAdressBei.setText(adress);
        tvAdressDong.setText(adress);
        tvAdressNan.setText(adress);
        tvAdressXi.setText(adress);
        tvAdressQuanmao.setText(adress + "全貌");
        tvAdressJingneiQuanmao.setText(adress + "井内全貌");
        titleTitle.setText("异形井属性");
        quit.setVisibility(View.GONE);
        imgEtid.setOnClickListener(this);
        imgDongetid.setOnClickListener(this);
        imgNanetid.setOnClickListener(this);
        imgXietid.setOnClickListener(this);
        titleBack1.setOnClickListener(this);
        imgImage.setOnClickListener(this);
        imgImage2.setOnClickListener(this);
        imgImage3.setOnClickListener(this);
        imgImage4.setOnClickListener(this);
        imgImage5.setOnClickListener(this);
        imgImage6.setOnClickListener(this);
        quit.setOnClickListener(this);
        btnNewAdd.setOnClickListener(this);
        btnUp.setOnClickListener(this);


    }
    //从数据库中显示照片
    private void getPicture() {
        //获取图片
        bitmap_list = pictureDatabase.getYiXingJingDrawable();
        if(bitmap_list==null|| bitmap_list.size()==0){
            bitmap_list = new ArrayList<>();
            bitmap_list.add(BitmapFactory.decodeResource(getResources(), R.drawable.picbtn));
            bitmap_list.add(BitmapFactory.decodeResource(getResources(), R.drawable.picbtn));
            bitmap_list.add(BitmapFactory.decodeResource(getResources(), R.drawable.picbtn));
            bitmap_list.add(BitmapFactory.decodeResource(getResources(), R.drawable.picbtn));
            bitmap_list.add(BitmapFactory.decodeResource(getResources(), R.drawable.picbtn));
            bitmap_list.add(BitmapFactory.decodeResource(getResources(), R.drawable.picbtn));
            Log.e("bitmap_list222",bitmap_list.size()+"");
        }
        ImList.add(imgImage);
        ImList.add(imgImage2);
        ImList.add(imgImage3);
        ImList.add(imgImage4);
        ImList.add(imgImage5);
        ImList.add(imgImage6);
        for (int i = 0; i < bitmap_list.size(); i++) {
            ((ImageView) ImList.get(i)).setImageBitmap(bitmap_list.get(i));
        }

    }
    //从数据库中照片获取北方位图片
    private void getPictureBei(int count) {
        //获取图片
        bitmap_listBei = pictureDatabase.getBeiDrawable();
        if(bitmap_listBei.size() == 0){
            for (int i = 0; i < count; i++) {
                bitmap_listBei.add(BitmapFactory.decodeResource(getResources(), R.drawable.picbtn));
            }

        }


    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.title_back1:
                String xuHao = getIntent().getStringExtra("xuHao");
                if (xuHao != null) {
                    intent = new Intent(YingXingJingActivity.this, WellTypeActivity.class);
                    intent.putExtra("xuHao", xuHao);
                    startActivity(intent);

                } else {
                        //删除数据库图片
                        pictureDatabase.getYiXingJingDelete();
                        //往数据库插入新的图片
                        for (int i = 0; i < bitmap_list.size(); i++) {
                            pictureDatabase.saveYiXingJingPhoto(bitmap_list.get(i));
                    }

                    finish();
                }
                break;
            case R.id.quit:
                String yingxing = "yingxing";
                intent = new Intent(YingXingJingActivity.this, CameraActivity.class);
                intent.putExtra("yingxing", yingxing);
                startActivity(intent);
                break;
            case R.id.img_etid:
                String str ="1";
                String beiXuhao1 = tvAdressxuhao.getText().toString();
                String beiFangwei1 = tvImagename.getText().toString();
                intent = new Intent(YingXingJingActivity.this, YiXingCablePitNorth.class);
                intent.putExtra("beiXuhao1",beiXuhao1);
                intent.putExtra("beiFangwei1",beiFangwei1);
                intent.putExtra("str", str);
                startActivity(intent);
                break;
            case R.id.img_dongetid:
                String str1 ="1";
                String dongXuhao1 = tvAdressDongxuhao.getText().toString();
                String dongFangwei1 = tvImagenamedong.getText().toString();
                intent = new Intent(YingXingJingActivity.this, YiXingCablePitNorth.class);
                intent.putExtra("dongXuhao1",dongXuhao1);
                intent.putExtra("dongFangwei1",dongFangwei1);
                intent.putExtra("str", str1);
                startActivity(intent);
                break;
            case R.id.img_nanetid:
                String str2 ="1";
                String nanFangwei1 = tvImagenamenan.getText().toString();
                String nanXuhao1 = tvAdressNanxuhao.getText().toString();
                intent = new Intent(YingXingJingActivity.this, YiXingCablePitNorth.class);
                intent.putExtra("nanFangwei1",nanFangwei1);
                intent.putExtra("nanXuhao1",nanXuhao1);
                intent.putExtra("str", str2);
                startActivity(intent);
                break;
            case R.id.img_xietid:
                String str3 ="1";
                String xiFangwei1 = tvImagenamexi.getText().toString();
                String xiXuhao1 = tvAdressXixuhao.getText().toString();
                intent = new Intent(YingXingJingActivity.this, YiXingCablePitNorth.class);
                intent.putExtra("xiFangwei1",xiFangwei1);
                intent.putExtra("xiXuhao1", xiXuhao1);
                intent.putExtra("str", str3);
                startActivity(intent);
                break;
            case R.id.img_image:
                cPicState = "1";
                path.clear();
                if (img1Path.length() > 1) {
                    path.add(img1Path);
                }
                configImageLoader();
                break;
            case R.id.img_image2:
                path.clear();
                if (img2Path.length() > 1) {
                    path.add(img2Path);
                }
                cPicState = "2";
                configImageLoader();
                break;
            case R.id.img_image3://北
                beiInit();

                break;
            case R.id.img_image4://东
                dongInit();
                break;
            case R.id.img_image5://南
                nanInit();

                break;
            case R.id.img_image6://西
                xiInit();

                break;

            case R.id.btn_newAdd://新增断面
                getCompareXuhao();
                //序号
                String beiXuhao = tvAdressxuhao.getText().toString();
                String dongXuhao = tvAdressDongxuhao.getText().toString();
                String nanXuhao = tvAdressNanxuhao.getText().toString();
                String xiXuhao = tvAdressXixuhao.getText().toString();
                //方位
                String beiFangwei = tvImagename.getText().toString();
                String dongFangwei = tvImagenamedong.getText().toString();
                String nanFangwei = tvImagenamenan.getText().toString();
                String xiFangwei = tvImagenamexi.getText().toString();
                intent = new Intent(YingXingJingActivity.this, YiXingCablePitNorth.class);
                intent.putExtra("adress", adress);
                intent.putExtra("compareXuhao", compareXuhao);
                intent.putExtra("beiFangwei", beiFangwei);
                intent.putExtra("dongFangwei",dongFangwei);
                intent.putExtra("nanFangwei", nanFangwei);
                intent.putExtra("xiFangwei", xiFangwei);
                intent.putExtra("beiXuhao", beiXuhao);
                intent.putExtra("dongXuhao", dongXuhao);
                intent.putExtra("nanXuhao", nanXuhao);
                intent.putExtra("xiXuhao", xiXuhao);
                startActivity(intent);


                break;
            case R.id.btn_up://完成
                Toast.makeText(YingXingJingActivity.this, "异形井属性编辑完成!", Toast.LENGTH_SHORT).show();
                intent = new Intent(YingXingJingActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }


    //比较序号 相同的方位不能有相同的序号
    private void getCompareXuhao() {
        //北
        if (duanMianBiaos != null && duanMianBiaos.size() > 0) {
            if (daunMianBeiList != null && daunMianBeiList.size() > 0) {
                for (int i = 0; i < daunMianBeiList.size(); i++) {
                    compareXuhao = daunMianBeiList.get(i).getXuhao();
                }
            }
        }
        //东
        if (duanMianBiaos != null && duanMianBiaos.size() > 0) {
            if (daunMianDongList != null && daunMianDongList.size() > 0) {
                for (int i = 0; i < daunMianDongList.size(); i++) {
                    compareXuhao = daunMianDongList.get(i).getXuhao();
                }
            }
        }
        //南
        if (duanMianBiaos != null && duanMianBiaos.size() > 0) {
            if (mianNanList != null && mianNanList.size() > 0) {
                for (int i = 0; i < mianNanList.size(); i++) {
                    compareXuhao = mianNanList.get(i).getXuhao();
                }
            }
        }
        //西
        if (duanMianBiaos != null && duanMianBiaos.size() > 0) {
            if (mianXiList != null && mianXiList.size() > 0) {
                for (int i = 0; i < mianXiList.size(); i++) {
                    compareXuhao = mianXiList.get(i).getXuhao();
                }
            }
        }

    }

    public void beiInit() {

        pop = new PopupWindow(YingXingJingActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beiPhoto();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //从相册选择图片
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YingXingJingActivity.this, ChooseImageActivity.class);
                startActivityForResult(intent, 12);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });

    }

    public void dongInit() {

        pop = new PopupWindow(YingXingJingActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dongPhoto();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //从相册选择图片
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YingXingJingActivity.this, ChooseImageActivity.class);
                startActivityForResult(intent, 13);
                pop.dismiss();
                ll_popup.clearAnimation();

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
    }


    public void nanInit() {

        pop = new PopupWindow(YingXingJingActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nanPhoto();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //从相册选择图片
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YingXingJingActivity.this, ChooseImageActivity.class);
                startActivityForResult(intent, 14);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });


    }


    public void xiInit() {

        pop = new PopupWindow(YingXingJingActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                xiPhoto();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //从相册选择图片
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YingXingJingActivity.this, ChooseImageActivity.class);
                startActivityForResult(intent, 15);
                pop.dismiss();
                ll_popup.clearAnimation();

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });

    }


    public void addBeiInit() {

        pop = new PopupWindow(YingXingJingActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addBeiPhoto();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //从相册选择图片
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YingXingJingActivity.this, ChooseImageActivity.class);
                startActivityForResult(intent, 16);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });

    }

    //东
    public void addDongInit() {

        pop = new PopupWindow(YingXingJingActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addDongPhoto();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //从相册选择图片
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YingXingJingActivity.this, ChooseImageActivity.class);
                startActivityForResult(intent, 17);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });

    }


    public void addNanInit() {

        pop = new PopupWindow(YingXingJingActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addNanPhoto();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //从相册选择图片
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YingXingJingActivity.this, ChooseImageActivity.class);
                startActivityForResult(intent, 18);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });

    }


    public void addXiInit() {

        pop = new PopupWindow(YingXingJingActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addXiPhoto();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //从相册选择图片
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YingXingJingActivity.this, ChooseImageActivity.class);
                startActivityForResult(intent, 19);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });

    }

    public void beiPhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE1);
    }

    public void dongPhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE2);
    }

    public void nanPhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE3);
    }

    public void xiPhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE4);
    }


    //添加北方位
    public void addBeiPhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE6);
    }

    //添加东位
    public void addDongPhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE7);
    }

    //添加南方位
    public void addNanPhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE8);
    }

    //添加西方位
    public void addXiPhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE9);
    }

    //File file = new File("/sdcard/myImage/" +fileName);
    public static String SDPATH = Environment.getExternalStorageDirectory() + "/电缆井/";


    public static File createSDDir(String dirName) throws IOException {
        File dir = new File(SDPATH + dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            System.out.println("createSDDir:" + dir.getAbsolutePath());
            System.out.println("createSDDir:" + dir.mkdir());
        }
        return dir;
    }

    public static boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        file.isFile();
        return file.exists();
    }

    /**
     * 配置 ImageConfig
     */
    private void configImageLoader() {
        ImageConfig imageConfig = new ImageConfig.Builder(
                // GlideLoader 可用自己用的缓存库
                new GlideLoader())
                // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                .steepToolBarColor(getResources().getColor(R.color.blue))
                // 标题的背景颜色 （默认黑色）
                .titleBgColor(getResources().getColor(R.color.blue))
                // 提交按钮字体的颜色  （默认白色）
                .titleSubmitTextColor(getResources().getColor(R.color.white))
                // 标题颜色 （默认白色）
                .titleTextColor(getResources().getColor(R.color.white))
                // 开启多选   （默认为多选）  (单选 为 singleSelect)
//                .singleSelect()
                // 开启多选   （默认为多选）
                .mutiSelect()
                .crop()
                // 多选时的最大数量   （默认 9 张）
                .mutiSelectMaxSize(1)
                // 已选择的图片路径
                .pathList(path)
                // 拍照后存放的图片路径（默认 /temp/picture）
                .filePath("/电缆井/")
                // 开启拍照功能 （默认开启）
                .showCamera()
                .requestCode(REQUEST_CODE)
                .build();

        ImageSelector.open(yingXingJingActivity, imageConfig);   // 开启图片选择器
    }

    //图片回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                //***********************照片选择**********************
                if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
                    List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
                    if (pathList != null && pathList.size() > 0) {
                        if (cPicState.equals("1")) {
                            img1Path = pathList.get(0);
                            //Bitmap bmp = BitmapFactory.decodeFile(img1Path);//filePath
                            Bitmap bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(img1Path), 0,ImageUtil.decodeBitmap(img1Path).length);
                            Log.w("bitmap", String.valueOf(bmp));
                            for (int i = 0; i < ImList.size(); i++) {
                                if (ImList.get(i).equals(imgImage)) {
                                    bitmap_list.set(i,bmp);
                                }
                            }
                            ImageLoader.getInstance().displayImage("file://" + img1Path, imgImage);
                        } else if (cPicState.equals("2")) {
                            img2Path = pathList.get(0);
                            //Bitmap bmp = BitmapFactory.decodeFile(img2Path);//filePath
                           Bitmap bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(img2Path), 0,ImageUtil.decodeBitmap(img2Path).length);
                            Log.w("bitmap", String.valueOf(bmp));
                            for (int i = 0; i < ImList.size(); i++) {
                                if (ImList.get(i).equals(imgImage2)) {
                                    bitmap_list.set(i,bmp);
                                }
                            }
                            ImageLoader.getInstance().displayImage("file://" + img2Path, imgImage2);
                        } else {
                            if (cPicState.equals("1")) {
                                img1Path = "";
                                imgImage.setImageResource(R.drawable.picbtn);
                                imgImage.setScaleType(ImageView.ScaleType.FIT_XY);
                            } else if (cPicState.equals("2")) {
                                img2Path = "";
                                imgImage2.setImageResource(R.drawable.picbtn);
                                imgImage2.setScaleType(ImageView.ScaleType.FIT_XY);
                            }
                        }
                        path.clear();
                        Log.e("onacresult-", "->" + img1Path + "   " + img2Path + "    " + img3Path);
                    }
                }
                break;
            //拍照
            case TAKE_PICTURE1:
                String imagename = tvImagename.getText().toString().trim();
                if (resultCode == RESULT_OK) {
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    try {
                        if (!isFileExist("")) {
                            File tempf = createSDDir("");
                        }
                        File f = new File(SDPATH, fileName + "-" + imagename + ".JPEG");
                        if (f.exists()) {
                            f.delete();
                        }
                        FileOutputStream out = new FileOutputStream(f);
                        bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();
                        imgImage3.setImageBitmap(bm);
                        for (int i = 0; i < ImList.size(); i++) {
                            if (ImList.get(i).equals(imgImage3)) {
                                bitmap_list.set(i,bm);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case TAKE_PICTURE2:
                String dongimagename = tvImagenamedong.getText().toString().trim();
                if (resultCode == RESULT_OK) {
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    try {
                        if (!isFileExist("")) {
                            File tempf = createSDDir("");
                        }
                        File f = new File(SDPATH, fileName + "-" + dongimagename + ".JPEG");
                        if (f.exists()) {
                            f.delete();
                        }
                        FileOutputStream out = new FileOutputStream(f);
                        bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();
                        imgImage4.setImageBitmap(bm);
                        for (int i = 0; i < ImList.size(); i++) {
                            if (ImList.get(i).equals(imgImage4)) {
                                bitmap_list.set(i,bm);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case TAKE_PICTURE3:
                String nanimagename = tvImagenamenan.getText().toString().trim();
                if (resultCode == RESULT_OK) {
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    try {
                        if (!isFileExist("")) {
                            File tempf = createSDDir("");
                        }
                        File f = new File(SDPATH, fileName + "-" + nanimagename + ".JPEG");
                        if (f.exists()) {
                            f.delete();
                        }
                        FileOutputStream out = new FileOutputStream(f);
                        bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        imgImage5.setImageBitmap(bm);
                        for (int i = 0; i < ImList.size(); i++) {
                            if (ImList.get(i).equals(imgImage5)) {
                                bitmap_list.set(i,bm);
                            }
                        }
                        out.flush();
                        out.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case TAKE_PICTURE4:
                String xiimagename = tvImagenamexi.getText().toString().trim();
                if (resultCode == RESULT_OK) {
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    try {
                        if (!isFileExist("")) {
                            File tempf = createSDDir("");
                        }
                        File f = new File(SDPATH, fileName + "-" + xiimagename + ".JPEG");
                        if (f.exists()) {
                            f.delete();
                        }
                        FileOutputStream out = new FileOutputStream(f);
                        bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        imgImage6.setImageBitmap(bm);
                        for (int i = 0; i < ImList.size(); i++) {
                            if (ImList.get(i).equals(imgImage6)) {
                                bitmap_list.set(i,bm);
                            }
                        }
                        out.flush();
                        out.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case TAKE_PICTURE6:
                String xiimagename1 = tvImagenamexi.getText().toString().trim();
                if (resultCode == RESULT_OK) {
                    Bitmap beiBm = (Bitmap) data.getExtras().get("data");
                    Log.e("beiBm8", beiBm + "");
                    try {
                        if (!isFileExist("")) {
                            File tempf = createSDDir("");
                        }
                        File f = new File(SDPATH, fileName + "-" + xiimagename1 + ".JPEG");
                        if (f.exists()) {
                            f.delete();
                        }
                        FileOutputStream out = new FileOutputStream(f);
                        beiBm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    daunMianBeiList.get(count).setPhoto(beiBm);
                    beiAdapter.notifyDataSetChanged();

                }
                break;
            case TAKE_PICTURE7:
                String xiimagename2 = tvImagenamexi.getText().toString().trim();
                if (resultCode == RESULT_OK) {
                   Bitmap dongBm = (Bitmap) data.getExtras().get("data");
                    try {
                        if (!isFileExist("")) {
                            File tempf = createSDDir("");
                        }
                        File f = new File(SDPATH, fileName + "-" + xiimagename2 + ".JPEG");
                        if (f.exists()) {
                            f.delete();
                        }
                        FileOutputStream out = new FileOutputStream(f);
                        dongBm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    daunMianDongList.get(count).setPhoto(dongBm);
                    dongAdapter.notifyDataSetChanged();

                }
                break;
            case TAKE_PICTURE8:
                String xiimagename3 = tvImagenamexi.getText().toString().trim();
                if (resultCode == RESULT_OK) {
                    Bitmap nanBm = (Bitmap) data.getExtras().get("data");
                    try {
                        if (!isFileExist("")) {
                            File tempf = createSDDir("");
                        }
                        File f = new File(SDPATH, fileName + "-" + xiimagename3 + ".JPEG");
                        if (f.exists()) {
                            f.delete();
                        }
                        FileOutputStream out = new FileOutputStream(f);
                        nanBm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mianNanList.get(count).setPhoto(nanBm);
                    nanAdapter.notifyDataSetChanged();

                }
                break;
            case TAKE_PICTURE9:
                String xiimagename6 = tvImagenamexi.getText().toString().trim();
                if (resultCode == RESULT_OK) {
                    Bitmap xiBm = (Bitmap) data.getExtras().get("data");
                    try {
                        if (!isFileExist("")) {
                            File tempf = createSDDir("");
                        }
                        File f = new File(SDPATH, fileName + "-" + xiimagename6 + ".JPEG");
                        if (f.exists()) {
                            f.delete();
                        }
                        FileOutputStream out = new FileOutputStream(f);
                        xiBm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mianXiList.get(count).setPhoto(xiBm);
                    xiAdapter.notifyDataSetChanged();

                }
                break;
            case CROP_1_REQUEST:
                startPhotoZoom(Uri.fromFile(cameraFile));
                break;
            case CROP_2_REQUEST:
                if (photo != null)
                    photo.recycle();
                photo = data.getExtras().getParcelable("data");
                imgImage3.setImageBitmap(photo);
                imgImage4.setImageBitmap(photo);
                imgImage5.setImageBitmap(photo);
                imgImage6.setImageBitmap(photo);

                break;
            case 12:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String imagePath = data.getStringExtra("imagePath");
                        if (imagePath != null) {
                            Bitmap bm = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(imagePath), 0,ImageUtil.decodeBitmap(imagePath ).length);
                            imgImage3.setImageBitmap(bm);
                            if (bm != null) {
                                pictureDatabase.saveYiXingJingPhoto(bm);
                            }
                            Log.e("imagePath", imagePath);
                        }

                    }
                }

                break;
            case 13:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String imagePath = data.getStringExtra("imagePath");
                        if (imagePath != null) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(imagePath), 0,ImageUtil.decodeBitmap(imagePath ).length);
                            imgImage4.setImageBitmap(bmp);
                            if (bmp != null) {
                                pictureDatabase.saveYiXingJingPhoto(bmp);
                            }
                            Log.e("imagePath", imagePath);
                        }

                    }
                }
                break;
            case 14:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String imagePath = data.getStringExtra("imagePath");
                        if (imagePath != null) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(imagePath), 0,ImageUtil.decodeBitmap(imagePath ).length);
                            imgImage5.setImageBitmap(bmp);
                            if (bmp != null) {
                                pictureDatabase.saveYiXingJingPhoto(bmp);
                            }
                            Log.e("imagePath", imagePath);
                        }

                    }
                }

                break;
            case 15:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String imagePath = data.getStringExtra("imagePath");
                        if (imagePath != null) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(imagePath), 0,ImageUtil.decodeBitmap(imagePath ).length);
                            imgImage6.setImageBitmap(bmp);
                            if (bmp != null) {
                                pictureDatabase.saveYiXingJingPhoto(bmp);
                            }
                            Log.e("imagePath", imagePath);
                        }

                    }
                }

                break;
            case 16:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String imagePath = data.getStringExtra("imagePath");
                        if (imagePath != null) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(imagePath), 0,ImageUtil.decodeBitmap(imagePath ).length);
                            if (bmp != null) {
                                daunMianBeiList.get(count).setPhoto(bmp);
                                beiAdapter.notifyDataSetChanged();

                            }
                            Log.e("imagePath", imagePath);
                        }

                    }
                }

                break;
            case 17:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String imagePath = data.getStringExtra("imagePath");
                        if (imagePath != null) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(imagePath), 0,ImageUtil.decodeBitmap(imagePath ).length);
                            if (bmp != null) {
                                daunMianDongList.get(count).setPhoto(bmp);
                                dongAdapter.notifyDataSetChanged();
                                //pictureDatabase.saveYiXingJingPhoto(bmp);
                            }
                            Log.e("imagePath", imagePath);
                        }

                    }
                }
            case 18:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String imagePath = data.getStringExtra("imagePath");
                        if (imagePath != null) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(imagePath), 0,ImageUtil.decodeBitmap(imagePath ).length);
                            if (bmp != null) {
                                mianNanList.get(count).setPhoto(bmp);
                                nanAdapter.notifyDataSetChanged();
                                //pictureDatabase.saveYiXingJingPhoto(bmp);
                            }
                            Log.e("imagePath", imagePath);
                        }

                    }
                }

                break;

            case 19:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String imagePath = data.getStringExtra("imagePath");
                        if (imagePath != null) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(imagePath), 0,ImageUtil.decodeBitmap(imagePath ).length);
                            if (bmp != null) {
                                mianXiList.get(count).setPhoto(bmp);
                                xiAdapter.notifyDataSetChanged();
                                //pictureDatabase.saveYiXingJingPhoto(bmp);
                            }
                            Log.e("imagePath", imagePath);
                        }

                    }
                }

                break;
            default:
                break;
        }


    }

    //
    // 图片剪切
    private void startPhotoZoom(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 0);
        intent.putExtra("aspectY", 0);

        // outputX,outputY 是剪裁图片的宽高
//		intent.putExtra("outputX", 2000);
//		intent.putExtra("outputY", 2000);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        this.startActivityForResult(intent, CROP_2_REQUEST);
    }

    class DaunMianBeiAdapter extends BaseAdapter {

        private List<DaunMianBei> mData;
        private Context mContext;


        public DaunMianBeiAdapter(List<DaunMianBei> mData, Context mContext) {
            super();
            this.mData = mData;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return mData.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(final int arg0, View convertView, ViewGroup arg2) {
            // TODO Auto-generated method stub
            Holder holder;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.duanmian_listview_item, null);
                holder = new Holder();
                holder.avator_iv1 = (TextView) convertView.findViewById(R.id.tv_adress_bei);
                holder.name_tv = (TextView) convertView.findViewById(R.id.tv_fangwei);
                holder.xuhao_tv = (TextView) convertView.findViewById(R.id.tv_xuhao);
                holder.addImage = (ImageView) convertView.findViewById(R.id.img_addimage);
                holder.etid = (ImageView) convertView.findViewById(R.id.img_etid);
                convertView.setTag(holder);
            }
            holder = (Holder) convertView.getTag();
            holder.avator_iv1.setText(mData.get(arg0).getAdress());
            holder.name_tv.setText(mData.get(arg0).getFangWei());
            holder.xuhao_tv.setText(mData.get(arg0).getXuhao());
            holder.addImage.setBackgroundResource(R.drawable.picbtn);
            holder.addImage.setImageBitmap(mData.get(arg0).getPhoto());
//            if(getCount()>0) {
//                //getPictureBei(getCount());
//                for (int i = 0; i < getCount(); i++) {
////                bitmap_listBei.set(arg0,mData.get(arg0).getPhoto());
//                    holder.addImage.setImageBitmap(bitmap_listBei.get(i));
//                }
//            }
//
//            if(getCount()>0) {
//                for (int i = 0; i < getCount(); i++) {
//                    bitmap_listBei.set(arg0, mData.get(arg0).getPhoto());
//
//                }
//            }
            // pictureDatabase.saveYiXingJingPhoto(mData.get(arg0).getPhoto());
            holder.addImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    addBeiInit();
                    count = arg0;
                }
            });

            //编辑
            holder.etid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(YingXingJingActivity.this, YiXingCablePitNorth.class);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        class Holder {
            TextView avator_iv1;
            TextView name_tv;
            TextView xuhao_tv;
            ImageView addImage;
            ImageView etid;
        }
    }


    class DaunMianDongAdapter extends BaseAdapter {

        private List<DaunMianDong> mData;
        private Context mContext;


        public DaunMianDongAdapter(List<DaunMianDong> mData, Context mContext) {
            super();
            this.mData = mData;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return mData.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(final int arg0, View convertView, ViewGroup arg2) {
            // TODO Auto-generated method stub
            Holder holder;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.duanmian_listview_item, null);
                holder = new Holder();
                holder.avator_iv1 = (TextView) convertView.findViewById(R.id.tv_adress_bei);
                holder.name_tv = (TextView) convertView.findViewById(R.id.tv_fangwei);
                holder.xuhao_tv = (TextView) convertView.findViewById(R.id.tv_xuhao);
                holder.addImage = (ImageView) convertView.findViewById(R.id.img_addimage);
                holder.etid = (ImageView) convertView.findViewById(R.id.img_etid);
                convertView.setTag(holder);
            }
            holder = (Holder) convertView.getTag();
            holder.avator_iv1.setText(mData.get(arg0).getAdress());
            holder.name_tv.setText(mData.get(arg0).getFangWei());
            holder.xuhao_tv.setText(mData.get(arg0).getXuhao());
            holder.addImage.setBackgroundResource(R.drawable.picbtn);
            holder.addImage.setImageBitmap(mData.get(arg0).getPhoto());
            //pictureDatabase.saveYiXingJingPhoto(mData.get(arg0).getPhoto());
            holder.addImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addDongInit();
                    count = arg0;
                }
            });

            //编辑
            holder.etid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(YingXingJingActivity.this, YiXingCablePitNorth.class);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        class Holder {
            TextView avator_iv1;
            TextView name_tv;
            TextView xuhao_tv;
            ImageView addImage;
            ImageView etid;
        }
    }


    class DaunMianNanAdapter extends BaseAdapter {

        private List<DaunMianNan> mData;
        private Context mContext;


        public DaunMianNanAdapter(List<DaunMianNan> mData, Context mContext) {
            super();
            this.mData = mData;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return mData.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int arg0, View convertView, ViewGroup arg2) {
            // TODO Auto-generated method stub
            Holder holder;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.duanmian_listview_item, null);
                holder = new Holder();
                holder.avator_iv1 = (TextView) convertView.findViewById(R.id.tv_adress_bei);
                holder.name_tv = (TextView) convertView.findViewById(R.id.tv_fangwei);
                holder.xuhao_tv = (TextView) convertView.findViewById(R.id.tv_xuhao);
                holder.addImage = (ImageView) convertView.findViewById(R.id.img_addimage);
                holder.etid = (ImageView) convertView.findViewById(R.id.img_etid);
                convertView.setTag(holder);
            }
            else {
                holder = (Holder) convertView.getTag();;
            }
            holder.avator_iv1.setText(mData.get(arg0).getAdress());
            holder.name_tv.setText(mData.get(arg0).getFangWei());
            holder.xuhao_tv.setText(mData.get(arg0).getXuhao());
            holder.addImage.setBackgroundResource(R.drawable.picbtn);
            holder.addImage.setImageBitmap(mData.get(arg0).getPhoto());
            //pictureDatabase.saveYiXingJingPhoto(mData.get(arg0).getPhoto());
            holder.addImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addNanInit();
                    count = arg0;
                }
            });

            //编辑
            holder.etid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(YingXingJingActivity.this, YiXingCablePitNorth.class);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        class Holder {
            TextView avator_iv1;
            TextView name_tv;
            TextView xuhao_tv;
            ImageView addImage;
            ImageView etid;
        }
    }


    class DaunMianXiAdapter extends BaseAdapter {

        private List<DaunMianXi> mData;
        private Context mContext;


        public DaunMianXiAdapter(List<DaunMianXi> mData, Context mContext) {
            super();
            this.mData = mData;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return mData.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int arg0, View convertView, ViewGroup arg2) {
            // TODO Auto-generated method stub
            Holder holder;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.duanmian_listview_item, null);
                holder = new Holder();
                holder.avator_iv1 = (TextView) convertView.findViewById(R.id.tv_adress_bei);
                holder.name_tv = (TextView) convertView.findViewById(R.id.tv_fangwei);
                holder.xuhao_tv = (TextView) convertView.findViewById(R.id.tv_xuhao);
                holder.addImage = (ImageView) convertView.findViewById(R.id.img_addimage);
                holder.etid = (ImageView) convertView.findViewById(R.id.img_etid);
                convertView.setTag(holder);
            }
            holder = (Holder) convertView.getTag();
            holder.avator_iv1.setText(mData.get(arg0).getAdress());
            holder.name_tv.setText(mData.get(arg0).getFangWei());
            holder.xuhao_tv.setText(mData.get(arg0).getXuhao());
            holder.addImage.setBackgroundResource(R.drawable.picbtn);
            holder.addImage.setImageBitmap(mData.get(arg0).getPhoto());
           // pictureDatabase.saveYiXingJingPhoto(mData.get(arg0).getPhoto());
            holder.addImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addXiInit();
                    count = arg0;
                }
            });

            //编辑
            holder.etid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(YingXingJingActivity.this, YiXingCablePitNorth.class);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        class Holder {
            TextView avator_iv1;
            TextView name_tv;
            TextView xuhao_tv;
            ImageView addImage;
            ImageView etid;
        }
    }

}
