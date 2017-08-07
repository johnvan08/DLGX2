package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.beans.GongJingXinxiBean;
import dlgx.gis.com.dlgx.db.DatabaseAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 电缆井信息
 * Created by admin on 2017/4/21.
 */
public class CablePitNewsActivity extends Activity implements View.OnClickListener {
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
    @Bind(R.id.tv_name)
    EditText tvName;
    @Bind(R.id.spinner)
    Spinner spinner;//井材质
    @Bind(R.id.spinner1)
    Spinner spinner1;//井盖形状
    @Bind(R.id.spinner3)
    Spinner spinner3;//功能
    @Bind(R.id.spinner4)
    Spinner spinner4;//类型
    @Bind(R.id.spinner5)
    Spinner spinner5;//接头数量
    @Bind(R.id.tv_jingcaizhi)
    TextView tvJingcaizhi;
    @Bind(R.id.tv_jinggaixingzhuang)
    TextView tvJinggaixingzhuang;
    @Bind(R.id.tv_gongneng)
    TextView tvGongneng;
    @Bind(R.id.tv_leiixng)
    TextView tvLeiixng;
    @Bind(R.id.tv_jietoushuliang)
    TextView tvJietoushuliang;
    @Bind(R.id.tv_jingdu)
    TextView tvJingdu;//经度
    @Bind(R.id.tv_weidu)
    TextView tvWeidu;//纬度
    @Bind(R.id.et_numeer)
    EditText etNumeer;
    @Bind(R.id.tv_shuliang)
    EditText tvShuliang;
    @Bind(R.id.et_chicun)
    EditText etChicun;
    @Bind(R.id.et_jingchang)
    EditText etJingchang;
    @Bind(R.id.et_jingkuan)
    EditText etJingkuan;
    @Bind(R.id.et_jingneishen)
    EditText etJingneishen;
    @Bind(R.id.et_jingshen)
    EditText etJingshen;
    @Bind(R.id.tv_zuoyedanwei)
    TextView etZuoyedanwei;
    @Bind(R.id.tv_gaocheng)
    TextView tvGaocheng;
    @Bind(R.id.tv_xinzengshijain)
    TextView tvXinzengshijain;
    @Bind(R.id.btn_finish)
    Button btnFinish;

    //新增时间
    private long wfTime;
    private String time;
    private String adress;
    private String jingdu;
    private String weidu;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cablepitnewsactivity_layout);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
            etNumeer.setText(inputText);
            etNumeer.setSelection(inputText.length());
            Toast.makeText(CablePitNewsActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        Date date = new Date();
        String id = format.format(date);
        tvXinzengshijain.setText(id);
        jingdu = getIntent().getStringExtra("jingDu");
        weidu = getIntent().getStringExtra("weiDu");
//        if (jingdu == null) {
//            return;
//        }
        tvJingdu.setText(jingdu);
        tvWeidu.setText(weidu);
        titleBack1.setOnClickListener(this);
        titleTitle.setText("工井信息");
        quit.setVisibility(View.GONE);
//        quit.setText("新增断面");
//        quit.setOnClickListener(this);
        btnFinish.setOnClickListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.jiingcaizhi);
                tvJingcaizhi.setText(languages[pos]);
                //Toast.makeText(CablePitNewsActivity.this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.jigaixingzhuang);
                tvJinggaixingzhuang.setText(languages[pos]);
                //Toast.makeText(CablePitNewsActivity.this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.gongneng);
                tvGongneng.setText(languages[pos]);
                // Toast.makeText(CablePitNewsActivity.this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
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

                String[] languages = getResources().getStringArray(R.array.leixing);
                tvLeiixng.setText(languages[pos]);
                //Toast.makeText(CablePitNewsActivity.this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.jietoushulianag);
                tvJietoushuliang.setText(languages[pos]);
                //Toast.makeText(CablePitNewsActivity.this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }



    public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in =openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        String fileName = tvName.getText().toString().trim();
        switch (v.getId()) {
            case R.id.title_back1:
                String xuHao = getIntent().getStringExtra("xuHao");
                if(xuHao != null){
                    intent = new Intent(CablePitNewsActivity.this, MainActivity.class);
                    intent.putExtra("xuHao",xuHao);
                    startActivity(intent);

                }else {
                    finish();
                }
                break;
            case R.id.btn_finish://新建
                 String dljnummber =  etNumeer.getText().toString();
                Log.e("dljnummber1111 ",dljnummber );
                 adress= getIntent().getStringExtra("adress");
                if(click() == true){
                    offlineSave();
                    intent = new Intent(CablePitNewsActivity.this, WellTypeActivity.class);
                    //传入指定的照片存储路径文件名
                    intent.putExtra("name", fileName);
                    //传入电缆井现场编号
                    intent.putExtra("dljnummber", dljnummber);
                    startActivity(intent);
                    Toast.makeText(CablePitNewsActivity.this, "新建电缆井完成", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    //检查登录格式是否正确
    private Boolean click() {
        String Fangwei = etNumeer.getText().toString().trim();
        String Duanmianxuhao = tvShuliang.getText().toString().trim();
        String Shangbianju = etChicun.getText().toString().trim();
        String Xiabianju = etJingchang.getText().toString().trim();
        String Zuobianju = etJingkuan.getText().toString().trim();
        String Youbianju = etJingneishen.getText().toString().trim();
        String hangShu = etJingshen.getText().toString().trim();
        String lieShu = etZuoyedanwei.getText().toString().trim();

        if (Fangwei.equals("")) {
            Toast.makeText(CablePitNewsActivity.this, "请输入电缆井现场编号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Duanmianxuhao.equals("")) {
            Toast.makeText(CablePitNewsActivity.this, "请输入数量", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Shangbianju.equals("")) {
            Toast.makeText(CablePitNewsActivity.this, "请输入尺寸", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Xiabianju.equals("")) {
            Toast.makeText(CablePitNewsActivity.this, "请输入井长", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Zuobianju.equals("")) {
            Toast.makeText(CablePitNewsActivity.this, "请输入井宽", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Youbianju.equals("")) {
            Toast.makeText(CablePitNewsActivity.this, "请输入井内深", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (hangShu.equals("")) {
            Toast.makeText(CablePitNewsActivity.this, "请输入井深", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }



    /**
     * 离线存储罚单
     */
    private void offlineSave(){
        GongJingXinxiBean beans = new GongJingXinxiBean();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String id = format.format(date);
        beans.setUuid(id);
        beans.setName(tvName.getText().toString());
        //编号
        beans.setNummber(etNumeer.getText().toString());
        //数量
        beans.setShuliang(tvShuliang.getText().toString());
        //材质
        beans.setCaizhi(tvJingcaizhi.getText().toString());
        //形状
        beans.setXingzhuang(tvJinggaixingzhuang.getText().toString());
        //驾驶证档案编号
        beans.setChicun(etChicun.getText().toString());
        //发证城市
        beans.setJingchang(etJingchang.getText().toString());
        //准驾车型
        beans.setJingkuan(etJingkuan.getText().toString());
        //违法内容
        beans.setJingneishen(etJingneishen.getText().toString());
        //车牌类型 字符串
        beans.setJingshen(etJingshen.getText().toString());
        //车辆类型 字符串
        beans.setGongneng(tvGongneng.getText().toString());
        //违法代码
        beans.setLeixing(tvLeiixng.getText().toString());
        //法律依据
        beans.setJingdu(tvJingdu.getText().toString());
        //执法机关编码
        beans.setWeidu(tvWeidu.getText().toString());
        //车牌号码
        beans.setGaodu(tvGaocheng.getText().toString());
        //车辆类型
        beans.setJietoushuliang(tvJietoushuliang.getText().toString());
        //违法时间
        beans.setZuoyedanwei(etZuoyedanwei.getText().toString());
        //违法地点
        beans.setXinzengshijian(tvXinzengshijain.getText().toString());
        beans.setAdress(adress);
        DatabaseAdapter.getIntance(getBaseContext()).insertJingAddress(beans);
        List<GongJingXinxiBean> list = DatabaseAdapter.getIntance(getBaseContext()).queryJingAddress();
        if(list!=null){
            Iterator<GongJingXinxiBean> iterator = list.iterator();
           while(iterator.hasNext()){
                GongJingXinxiBean a = iterator.next();
               DatabaseAdapter.getIntance(getBaseContext()).updeteJingAddress(a);
          }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = etNumeer.getText().toString();
        save(inputText);
    }
    public void save(String inputText) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onRestart() {
        //offlineSave();
        super.onRestart();
    }
}
