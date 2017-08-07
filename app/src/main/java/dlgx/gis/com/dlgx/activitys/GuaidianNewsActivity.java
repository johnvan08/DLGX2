package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.beans.SheBeiBean;
import dlgx.gis.com.dlgx.db.DatabaseAdapter;

/**
 * 新增设备-拐点信息
 * Created by admin on 2017/4/28.
 */
public class GuaidianNewsActivity extends Activity implements View.OnClickListener {
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
    @Bind(R.id.tv_xinzengshijian)
    TextView tvXinzengshijian;
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.view_di)
    View viewDi;
    @Bind(R.id.btn_finish)
    Button btnFinish;
    @Bind(R.id.et_beizhu)
    EditText etBeizhu;
    private GridView noScrollgridview;
    private View parentView;
    private PopupWindow pop = null;
    private LinearLayout ll_popup;
    public static Bitmap bimap;
    private String equipmentType;

    private SheBeiBean beans;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentView = getLayoutInflater().inflate(R.layout.guaidiannewsactivity_layout, null);
        setContentView(parentView);
        ButterKnife.bind(this);
        initView();

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
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = format.format(curDate);
        tvXinzengshijian.setText(str);
        titleBack1.setOnClickListener(this);
        titleTitle.setText("新增拐点信息");
        quit.setVisibility(View.GONE);
        topImage.setVisibility(View.GONE);
        btnFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back1:
                finish();
                break;
            case R.id.btn_finish:
                if (click() == true) {
                    offlineSave();
                    finish();
                    //发送广播关闭上级页面
                    sendBroadcast(new Intent(AddSheBeiMapActivity.FINISH_ACTIVITY));
                    Toast.makeText(GuaidianNewsActivity.this, "新增拐点完成", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }


    //检查登录格式是否正确
    private Boolean click() {
        String name = etName.getText().toString().trim();
        String maiShen = etMaishen.getText().toString().trim();
        if (name.equals("")) {
            Toast.makeText(GuaidianNewsActivity.this, "请输入设备名称", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (maiShen.equals("")) {
            Toast.makeText(GuaidianNewsActivity.this, "请输入埋深", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * 离线存储
     */
    private void offlineSave() {
        beans = new SheBeiBean();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String id = format.format(date);
        beans.setUuid(id);
        beans.setName(etName.getText().toString());
        beans.setMaishen(etMaishen.getText().toString());
        beans.setJingdu(tvJingdu.getText().toString());
        beans.setWeidu(tvWeidu.getText().toString());
        //备注
        beans.setBeizhu(etBeizhu.getText().toString());
        beans.setZuoyedanwei(tvZuoyedanwei.getText().toString());
        beans.setXinznegshijian(tvXinzengshijian.getText().toString());
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
}
