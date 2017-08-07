package dlgx.gis.com.dlgx.activitys;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.beans.GongJingXinxiBean;
import dlgx.gis.com.dlgx.beans.YingHuanXinXi;
import dlgx.gis.com.dlgx.db.DatabaseAdapter;
import dlgx.gis.com.dlgx.db.PictureDatabase;
import dlgx.gis.com.dlgx.utils.Bimp;
import dlgx.gis.com.dlgx.utils.FileUtils;
import dlgx.gis.com.dlgx.utils.ImageItem;
import dlgx.gis.com.dlgx.utils.PublicWay;
import dlgx.gis.com.dlgx.utils.Res;


/**
 * Created by admin on 2017/5/9.
 */
public class YingHuanXinXiActivity extends Activity implements OnClickListener {

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
    @Bind(R.id.tv_yinghuandengji)
    TextView tvYinghuandengji;
    @Bind(R.id.spinner)
    Spinner spinner;
    @Bind(R.id.tv_yinghuanleixing)
    TextView tvYinghuanleixing;
    @Bind(R.id.spinner2)
    Spinner spinner2;
    @Bind(R.id.et_yinghuanbeizhu)
    EditText etYinghuanbeizhu;
    @Bind(R.id.et_yinghuanweizhi)
    EditText etYinghuanweizhi;
    @Bind(R.id.tv_jingdu)
    TextView tvJingdu;
    @Bind(R.id.tv_weidu)
    TextView tvWeidu;
    @Bind(R.id.et_riqi)
    EditText etRiqi;
    @Bind(R.id.et_zuoyedanwei)
    EditText etZuoyedanwei;
    @Bind(R.id.btn_endcheck)
    Button btnEndcheck;//选取经纬度
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.noScrollgridview)
    GridView noScrollgridview;
    @Bind(R.id.ll_photo)
    LinearLayout llPhoto;
    @Bind(R.id.btn_finish)
    Button btnFinish;
    private List<GongJingXinxiBean> bean = new ArrayList<GongJingXinxiBean>();
    private GridAdapter adapter;
    private PopupWindow pop = null;
    private LinearLayout ll_popup;
    public static Bitmap bimap ;
    private View parentView;
    private ArrayList<Bitmap> bitmap_list;
    private PictureDatabase pictureDatabase;
    List<YingHuanXinXi> yingxinxi= new ArrayList<YingHuanXinXi>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Res.init(this);
        bimap = BitmapFactory.decodeResource(
                getResources(),
                R.drawable.icon_addpic_unfocused);
        PublicWay.activityList.add(this);
        parentView = getLayoutInflater().inflate(R.layout.yinghuanxinxiactivity_layout, null);
        setContentView(parentView);
        ButterKnife.bind(this);

       yingxinxi= DatabaseAdapter.getIntance(getBaseContext()).queryYingHuanXinAddress();
        Init();
       getjieTouDataForSql();
        pictureDatabase = new PictureDatabase(this);
        getPicture();
        initView();


    }

    private void initView() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        etRiqi.setText(str);
        titleBack1.setOnClickListener(this);
        titleTitle.setText("隐患信息");
        quit.setVisibility(View.GONE);
        btnEndcheck.setOnClickListener(this);
        btnFinish.setOnClickListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.yinghuandengji);
                tvYinghuandengji.setText(languages[pos]);
                //Toast.makeText(CablePitNewsActivity.this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.yinghuanleixing);
                tvYinghuanleixing.setText(languages[pos]);
                //Toast.makeText(CablePitNewsActivity.this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });


    }

    private void getPicture() {
        bitmap_list = pictureDatabase.getYingHuanDrawable();
        if(bitmap_list!=null&&bitmap_list.size()>0) {
            for (int i = 0; i < bitmap_list.size(); i++) {
                ImageItem takePhoto = new ImageItem();
                takePhoto.setBitmap(bitmap_list.get(i));
                Log.e("bitmap_list.get(i)", bitmap_list.get(i) + "");
            }
            bitmap_list.clear();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back1:
                finish();
                break;
            case R.id.btn_endcheck:
                Intent intent = new Intent( YingHuanXinXiActivity.this, ChooseSeBeiActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.btn_finish:
                if (click() == true) {
                    offlineSave();
                    Toast.makeText(YingHuanXinXiActivity.this, "新建隐患表成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }

    }


    //检查登录格式是否正确
    private Boolean click() {
        String beizhu = etYinghuanbeizhu.getText().toString().trim();
        String weizhi = etYinghuanweizhi.getText().toString().trim();
        String danwei = etZuoyedanwei.getText().toString().trim();
        if (beizhu.equals("")) {
            Toast.makeText(YingHuanXinXiActivity.this, "请填写隐患备注", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (weizhi.equals("")) {
            Toast.makeText(YingHuanXinXiActivity.this, "请填写隐患位置", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (danwei.equals("")) {
            Toast.makeText(YingHuanXinXiActivity.this, "请填写作业单位", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }



    /**
     * 离线存储
     */
    private void offlineSave() {
        YingHuanXinXi beans = new YingHuanXinXi();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String id = format.format(date);
        beans.setUuid(id);
        beans.setYinghuandengji(tvYinghuandengji.getText().toString());
        //数量
        beans.setYinghuanleixing(tvYinghuanleixing.getText().toString());
        //材质
        beans.setYinghuanbeizhu(etYinghuanbeizhu.getText().toString());
        //所属线路
        beans.setYinghuanweizhi(etYinghuanweizhi.getText().toString());
        beans.setJingdu(tvJingdu.getText().toString());
        beans.setWeidu(tvWeidu.getText().toString());
        beans.setXinzengshijian( etRiqi.getText().toString());
        //备注
        beans.setZuoyedanwei(etZuoyedanwei.getText().toString());
        DatabaseAdapter.getIntance(getBaseContext()).insertYingHuanXinddress(beans);
        List<YingHuanXinXi> list = DatabaseAdapter.getIntance(getBaseContext()).queryYingHuanXinAddress();
        if (list != null) {
            Iterator<YingHuanXinXi> iterator = list.iterator();
            while (iterator.hasNext()) {
                YingHuanXinXi a = iterator.next();
                DatabaseAdapter.getIntance(getBaseContext()).updeteYingHuanXinAddress(a);
            }
        }

    }

    //从本地数据库进行显示
    private void getjieTouDataForSql() {
        if (yingxinxi != null && yingxinxi.toString().length() > 0) {
            for (int i = 0; i < yingxinxi.size(); i++) {
                //编号
                tvYinghuandengji.setText(yingxinxi.get(i).getYinghuandengji());
                tvYinghuanleixing.setText(yingxinxi.get(i).getYinghuanleixing());
                etYinghuanbeizhu.setText(yingxinxi.get(i).getYinghuanbeizhu());
                etYinghuanweizhi.setText(yingxinxi.get(i).getYinghuanweizhi());
                tvJingdu.setText(yingxinxi.get(i).getJingdu());
                tvWeidu.setText(yingxinxi.get(i).getWeidu());
                etRiqi.setText(yingxinxi.get(i).getXinzengshijian());
                etZuoyedanwei.setText(yingxinxi.get(i).getZuoyedanwei());
            }
        }
    }

    public void Init() {

        pop = new PopupWindow( YingHuanXinXiActivity .this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(LayoutParams.MATCH_PARENT);
        pop.setHeight(LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                photo();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YingHuanXinXiActivity.this,
                        AlbumActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });

        noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridAdapter(this);
        adapter.update();
        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == Bimp.tempSelectBitmap.size()) {
                    Log.i("ddddddd", "----------");
                    ll_popup.startAnimation(AnimationUtils.loadAnimation(YingHuanXinXiActivity.this,R.anim.activity_translate_in));
                    pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
                } else {
                    Intent intent = new Intent(YingHuanXinXiActivity.this,
                            GalleryActivity.class);
                    intent.putExtra("position", "1");
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });

    }

    @SuppressLint("HandlerLeak")
    public class GridAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private int selectedPosition = -1;
        private boolean shape;

        public boolean isShape() {
            return shape;
        }

        public void setShape(boolean shape) {
            this.shape = shape;
        }

        public GridAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void update() {
            loading();
        }

        public int getCount() {
            if(Bimp.tempSelectBitmap.size() == 9){
                return 9;
            }
            return (Bimp.tempSelectBitmap.size() + 1);
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int arg0) {
            return 0;
        }

        public void setSelectedPosition(int position) {
            selectedPosition = position;
        }

        public int getSelectedPosition() {
            return selectedPosition;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_published_grida,
                        parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView
                        .findViewById(R.id.item_grida_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (position ==Bimp.tempSelectBitmap.size()) {
                holder.image.setImageBitmap(BitmapFactory.decodeResource(
                        getResources(), R.drawable.icon_addpic_unfocused));
                if (position == 9) {
                    holder.image.setVisibility(View.GONE);
                }
            } else {
                holder.image.setImageBitmap(Bimp.tempSelectBitmap.get(position).getBitmap());
                pictureDatabase.saveYingHuanPhoto(Bimp.tempSelectBitmap.get(position).getBitmap());
                Log.e("Bimp",Bimp.tempSelectBitmap.get(position).getBitmap()+"");


            }

            return convertView;
        }

        public class ViewHolder {
            public ImageView image;
        }

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        adapter.notifyDataSetChanged();
                        break;
                }
                super.handleMessage(msg);
            }
        };

        public void loading() {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        if (Bimp.max == Bimp.tempSelectBitmap.size()) {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            break;
                        } else {
                            Bimp.max += 1;
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    }
                }
            }).start();
        }
    }

    public String getString(String s) {
        String path = null;
        if (s == null)
            return "";
        for (int i = s.length() - 1; i > 0; i++) {
            s.charAt(i);
        }
        return path;
    }

    protected void onRestart() {
        adapter.update();
        super.onRestart();
    }

    private static final int TAKE_PICTURE = 0x000002;

    public void photo() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String jingdu = data.getStringExtra("jingdu");
                        tvJingdu.setText(jingdu);
                        String weidu = data.getStringExtra("weidu");
                        tvWeidu.setText(weidu);
                    }
                }
                break;
            case TAKE_PICTURE:
                if (Bimp.tempSelectBitmap.size() < 9 && resultCode == RESULT_OK) {
                    String fileName = String.valueOf(System.currentTimeMillis());
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    FileUtils.saveBitmap(bm, fileName);
                    ImageItem takePhoto = new ImageItem();
                    takePhoto.setBitmap(bm);
                    pictureDatabase.saveYingHuanPhoto(bm);
                    Log.e("bm",bm+"");
                    Bimp.tempSelectBitmap.add(takePhoto);
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            for(int i=0;i<PublicWay.activityList.size();i++){
//                if (null != PublicWay.activityList.get(i)) {
//                    PublicWay.activityList.get(i).finish();
//                }
//            }
//            System.exit(0);
//        }
//        return true;
//    }
}
