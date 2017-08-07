package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.beans.SheBeiBean;
import dlgx.gis.com.dlgx.com.yancy.imageselector.ImageConfig;
import dlgx.gis.com.dlgx.com.yancy.imageselector.ImageSelector;
import dlgx.gis.com.dlgx.com.yancy.imageselector.ImageSelectorActivity;
import dlgx.gis.com.dlgx.db.DatabaseAdapter;
import dlgx.gis.com.dlgx.db.PictureDatabase;
import dlgx.gis.com.dlgx.utils.GlideLoader;
import dlgx.gis.com.dlgx.utils.ImageUtil;

/**
 * 新增设备-掩埋井信息
 * Created by admin on 2017/4/28.
 */
public class YanMaiJingNewsActivity extends Activity implements View.OnClickListener {
    @Bind(R.id.title_back1)
    ImageView titleBack1;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.top_image)
    ImageView topImage;
    @Bind(R.id.top_center_text)
    LinearLayout topCenterText;
    @Bind(R.id.quit)
    TextView quit;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_maishen)
    EditText etMaishen;
    @Bind(R.id.tv_jingdu)
    TextView tvJingdu;
    @Bind(R.id.tv_weidu)
    TextView tvWeidu;
    @Bind(R.id.tv_zuoyedanwei)
    TextView tvZuoyedanwei;
    @Bind(R.id.tv_xinzengshijain)
    TextView tvXinzengshijain;
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.view_di)
    View viewDi;
    @Bind(R.id.noScrollgridview)
    ImageView noScrollgridview;
    @Bind(R.id.iv_quanmao)
    ImageView ivQuanmao;
    @Bind(R.id.ll_photo)
    LinearLayout llPhoto;
    @Bind(R.id.btn_finish)
    Button btnFinish;
    @Bind(R.id.et_beizhu)
    EditText etBeizhu;
    private View parentView;
    private PopupWindow pop = null;
    private LinearLayout ll_popup;
    public static Bitmap bimap;

    //新增时间
    private long wfTime;
    private String time;

    private String cPicState = "1";
    private String img1Path = "", img2Path = "", img3Path = "";
    ArrayList<String> path = new ArrayList<>();
    public static final int REQUEST_CODE = 0x101;//
    public static YanMaiJingNewsActivity YanMaiJingNewsActivity;
    private PictureDatabase pictureDatabase;
    private Bitmap bmp,bmp1;
    private String equipmentType;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yanmaijingnewsactivity_layout);
        ButterKnife.bind(this);
        YanMaiJingNewsActivity = this;
        initView();

        pictureDatabase = new PictureDatabase(this);


    }

    private void initView() {
        //输入框只能输入数字和小数点
        etMaishen.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        String jingDu = getIntent().getStringExtra("longitude");
        Log.w("jingDu2", jingDu);
        tvJingdu.setText(jingDu);
        String weiDu = getIntent().getStringExtra("latitude");
        tvWeidu.setText(weiDu);
        equipmentType = getIntent().getStringExtra("equipmentType");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        tvXinzengshijain.setText(str);
        titleBack1.setOnClickListener(this);
        titleTitle.setText("新增掩埋井");
        quit.setVisibility(View.GONE);
        btnFinish.setOnClickListener(this);
        noScrollgridview.setOnClickListener(this);
        ivQuanmao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back1:
                finish();
                break;
            case R.id.btn_rectangular:
                break;
            case R.id.noScrollgridview:
                cPicState = "1";
                path.clear();
                if (img1Path.length() > 1) {
                    path.add(img1Path);
                }
                configImageLoader();
                break;
            case R.id.iv_quanmao:
                cPicState = "2";
                path.clear();
                if (img2Path.length() > 1) {
                    path.add(img2Path);
                }
                configImageLoader();
                break;
            case R.id.btn_finish:
                if (click() == true) {
                    offlineSave();
                    if(bmp!=null){
                        pictureDatabase.saveYanMaiJingPhoto(bmp);
                    }
                    if(bmp1!=null) {
                        pictureDatabase.saveYanMaiJingPhoto(bmp1);
                    }
                    finish();
                    sendBroadcast(new Intent(AddSheBeiMapActivity.FINISH_ACTIVITY));
                    Toast.makeText(YanMaiJingNewsActivity.this, "新增掩埋井完成", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }


    //检查登录格式是否正确
    private Boolean click() {
        String Fangwei = etName.getText().toString().trim();
        if (Fangwei.equals("")) {
            Toast.makeText(YanMaiJingNewsActivity.this, "请输入设备名称", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    /**
     * 离线存储罚单
     */
    private void offlineSave() {
        SheBeiBean beans = new SheBeiBean();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String id = format.format(date);
        beans.setUuid(id);
        beans.setName(etName.getText().toString());
        //数量
        beans.setMaishen(etMaishen.getText().toString());
        //材质
        beans.setJingdu(tvJingdu.getText().toString());
        //形状
        beans.setWeidu(tvWeidu.getText().toString());
        //备注
        beans.setBeizhu(etBeizhu.getText().toString());
        //驾驶证档案编号
        beans.setZuoyedanwei(tvZuoyedanwei.getText().toString());//发证城市
        beans.setXinznegshijian(tvXinzengshijain.getText().toString());
        beans.setLx(equipmentType);
        DatabaseAdapter.getIntance(getBaseContext()).insertSheBeiAddress(beans);
        List<SheBeiBean> list = DatabaseAdapter.getIntance(getBaseContext()).querySheBeiAddress(equipmentType);
        if (list != null) {
            Iterator<SheBeiBean> iterator = list.iterator();
            while (iterator.hasNext()) {
                SheBeiBean a = iterator.next();
                DatabaseAdapter.getIntance(getBaseContext()).updeteSheBeiAddress(a);
            }
        }

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
                .filePath("/设备/掩埋井")
                // 开启拍照功能 （默认开启）
                .showCamera()
                .requestCode(REQUEST_CODE)
                .build();

        ImageSelector.open(YanMaiJingNewsActivity, imageConfig);   // 开启图片选择器
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
                            ImageLoader.getInstance().displayImage("file://" + img1Path, noScrollgridview);
                            bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(img1Path), 0,ImageUtil.decodeBitmap(img1Path).length);
                        } else if (cPicState.equals("2")) {
                            img2Path = pathList.get(0);
                            ImageLoader.getInstance().displayImage("file://" + img2Path, ivQuanmao);
                            bmp1 = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(img2Path), 0,ImageUtil.decodeBitmap(img2Path).length);

                        } else {
                            if (cPicState.equals("1")) {
                                img1Path = "";
                                noScrollgridview.setImageResource(R.drawable.picbtn);
                                noScrollgridview.setScaleType(ImageView.ScaleType.FIT_XY);
                            } else if (cPicState.equals("2")) {
                                img2Path = "";
                                ivQuanmao.setImageResource(R.drawable.picbtn);
                                ivQuanmao.setScaleType(ImageView.ScaleType.FIT_XY);
                            }
                        }
                        path.clear();
                        Log.e("onacresult-", "->" + img1Path + "   " + img2Path + "    " + img3Path);
                    }
                }
        }

    }

}
