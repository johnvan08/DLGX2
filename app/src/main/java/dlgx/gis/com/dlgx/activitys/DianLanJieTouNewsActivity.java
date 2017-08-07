package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import dlgx.gis.com.dlgx.beans.DianLanJieKou;
import dlgx.gis.com.dlgx.beans.DianLanJieTou;
import dlgx.gis.com.dlgx.beans.GongJingXinxiBean;
import dlgx.gis.com.dlgx.db.DatabaseAdapter;

/**
 * 新增设备-电缆接口信息
 * Created by admin on 2017/4/28.
 */
public class DianLanJieTouNewsActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.et_jietouname)
    EditText etJietouname;
    @Bind(R.id.et_bianhao)
    EditText etBianhao;
    @Bind(R.id.et_suoshubiandianzhan)
    EditText etSuoshubiandianzhan;
    @Bind(R.id.spinner)
    Spinner spinner;
    @Bind(R.id.et_suoshuxianlu)
    EditText etSuoshuxianlu;
    @Bind(R.id.tv_anzhuangweizhi)
    TextView tvAnzhuangweizhi;
    @Bind(R.id.spinner4)
    Spinner spinner4;
    @Bind(R.id.tv_anzhuangweizhileixing)
    TextView tvAnzhuangweizhileixing;
    @Bind(R.id.spinner6)
    Spinner spinner6;
    @Bind(R.id.et_sdianzibiaoqianhao)
    EditText etSdianzibiaoqianhao;
    @Bind(R.id.tv_anzhuangjingdu)
    TextView tvAnzhuangjingdu;
    @Bind(R.id.tv_anzhuangweidu)
    TextView tvAnzhuangweidu;
    @Bind(R.id.et_beizhu)
    EditText etBeizhu;
    @Bind(R.id.tv_zuoyedanwei)
    TextView tvZuoyedanwei;
    @Bind(R.id.tv_xinzengshijain)
    TextView tvXinzengshijain;
    @Bind(R.id.btn_xinjian)
    Button btnXinjian;
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
    private GridView noScrollgridview;
    private View parentView;
    private PopupWindow pop = null;
    private LinearLayout ll_popup;
    public static Bitmap bimap;
    List<DianLanJieTou> jietou = new ArrayList<DianLanJieTou>();
    private List<GongJingXinxiBean> bean = new ArrayList<GongJingXinxiBean>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dianlanjietounewsactivity_layout);
        ButterKnife.bind(this);
        initView();
        bean = DatabaseAdapter.getIntance(getBaseContext()).queryJingAddress();
        jietou  = DatabaseAdapter.getIntance(getBaseContext()).queryDianLanJieTouAddress();
        getDataForSql();
        getjieTouDataForSql();


    }

    private void initView() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        tvXinzengshijain.setText(str);
        titleBack1.setOnClickListener(this);
        titleTitle.setText("电缆接口信息");
        quit.setVisibility(View.GONE);
        btnXinjian.setOnClickListener(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.suoshubiandianzhan);
                etSuoshubiandianzhan.setText(languages[pos]);
                //Toast.makeText(CablePitNewsActivity.this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.anzhuangweizhi);
                tvAnzhuangweizhi.setText(languages[pos]);
                //Toast.makeText(CablePitNewsActivity.this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.anzhuangweizhileixing);
                tvAnzhuangweizhileixing.setText(languages[pos]);
                // Toast.makeText(CablePitNewsActivity.this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back1:
                finish();
                break;
            case R.id.btn_xinjian:
                if(click()==true){
                    offlineSave();
                    finish();
                }
                break;
            default:
                break;
        }

    }


    //检查登录格式是否正确
    private Boolean click() {
        String bianhao = etBianhao.getText().toString().trim();
        if (bianhao .equals("")) {
            Toast.makeText(DianLanJieTouNewsActivity.this, "请填写编号", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }


    //显示本地缓存
    private void getDataForSql() {
        if (bean != null && bean.toString().length() > 0) {
            for (int i = 0; i < bean.size(); i++) {
                tvAnzhuangjingdu.setText(bean.get(i).getJingdu());
                tvAnzhuangweidu.setText(bean.get(i).getWeidu());
                tvZuoyedanwei.setText(bean.get(i).getZuoyedanwei());
            }
        }
    }

    /**
     * 离线存储
     */
    private void offlineSave() {
        DianLanJieTou beans = new DianLanJieTou();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String id = format.format(date);
        beans.setUuid(id);
        beans.setJieTouName(etJietouname.getText().toString());
        //数量
        beans.setNummber(etBianhao.getText().toString());
        //材质
        beans.setSuoshubiandianzhan(etSuoshubiandianzhan.getText().toString());
        //所属线路
        beans.setSuoshuxianlu(etSuoshuxianlu.getText().toString());
        beans.setAnzhuangweizhi(tvAnzhuangweizhi.getText().toString());
        beans.setAnzhuangweizhi_leixing(tvAnzhuangweizhileixing.getText().toString());
        beans.setBiaoqianhao(etSdianzibiaoqianhao.getText().toString());
        //备注
        beans.setBeizhu(etBeizhu.getText().toString());
        //驾驶证档案编号
        beans.setZuoyedanwei(tvZuoyedanwei.getText().toString());//发证城市
        beans.setXinzengshijian(tvXinzengshijain.getText().toString());
        DatabaseAdapter.getIntance(getBaseContext()).insertDianLanJieTouddress(beans);
        List<DianLanJieTou> list = DatabaseAdapter.getIntance(getBaseContext()).queryDianLanJieTouAddress();
        if (list != null) {
            Iterator<DianLanJieTou> iterator = list.iterator();
            while (iterator.hasNext()) {
                DianLanJieTou a = iterator.next();
                DatabaseAdapter.getIntance(getBaseContext()).updeteDianLanJieTouAddress(a);
            }
        }

    }

    //从本地数据库进行显示
    private void getjieTouDataForSql() {
        if (jietou != null && jietou.toString().length() > 0) {
            for (int i = 0; i < jietou.size(); i++) {
                //编号
                etJietouname.setText(jietou.get(i).getJieTouName());
                etBianhao.setText(jietou.get(i).getNummber());
                etSuoshubiandianzhan.setText(jietou.get(i).getSuoshubiandianzhan());
                etSuoshuxianlu.setText(jietou.get(i).getSuoshuxianlu());
                tvAnzhuangweizhi.setText(jietou.get(i).getAnzhuangweizhi());
                tvAnzhuangweizhileixing.setText(jietou.get(i).getAnzhuangweizhi_leixing());
                etSdianzibiaoqianhao.setText(jietou.get(i).getBiaoqianhao());
                etBeizhu.setText(jietou.get(i).getBeizhu());
                tvZuoyedanwei.setText(jietou.get(i).getZuoyedanwei());
                tvXinzengshijain.setText(jietou.get(i).getXinzengshijian());
            }
        }
    }
}
