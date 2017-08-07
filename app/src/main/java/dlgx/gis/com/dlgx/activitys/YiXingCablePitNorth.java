package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import dlgx.gis.com.dlgx.beans.DianLanJingDuanMianBiao;
import dlgx.gis.com.dlgx.beans.GongJingXinxiBean;
import dlgx.gis.com.dlgx.db.DatabaseAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/5/15.
 */
public class YiXingCablePitNorth extends Activity implements View.OnClickListener {
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
    @Bind(R.id.btn_etid)
    Button btnEtid;//绘制断面孔线
    @Bind(R.id.spinner)
    Spinner spinner;//方位
    @Bind(R.id.spinner2)
    Spinner spinner2;//断面序号
    @Bind(R.id.et_hangshu)
    EditText etHangshu;//行数
    @Bind(R.id.et_lieshu)
    EditText etLieshu;//列数
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.view_di)
    View viewDi;
    @Bind(R.id.tv_fangwei)
    TextView tvFangwei;
    @Bind(R.id.tv_duanmianxuhao)
    TextView tvDuanmianxuhao;
    @Bind(R.id.et_shangbianju)
    EditText etShangbianju;
    @Bind(R.id.et_xiabianju)
    EditText etXiabianju;
    @Bind(R.id.et_zuobianju)
    EditText etZuobianju;
    @Bind(R.id.et_youbianju)
    EditText etYoubianju;
    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_name)
    TextView tvName;
    private DianLanJingDuanMianBiao beans;
    private String Fangwei;
    private String Duanmianxuhao;
    private List<GongJingXinxiBean> address = new ArrayList<GongJingXinxiBean>();
    private String jingName;
    private String compareXuhao;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yixingcablepitnorth_layout);
        ButterKnife.bind(this);
        initView();
        address = DatabaseAdapter.getIntance(getBaseContext()).queryJingAddress();
        getDataForSql();

    }

    private void initView() {
        String  beiXuhao = getIntent().getStringExtra("beiXuhao1");
        String  dongXuhao = getIntent().getStringExtra("dongXuhao1");
        String  nanXuhao = getIntent().getStringExtra("nanXuhao1");
        String  xiXuhao = getIntent().getStringExtra("xiXuhao1");
        //方位
        String  beiFangwei = getIntent().getStringExtra("beiFangwei1");
        String  dongFangwei = getIntent().getStringExtra("dongFangwei1");
        String  nanFangwei = getIntent().getStringExtra("nanFangwei1");
        String  xiFangwei = getIntent().getStringExtra("xiFangwei1");

        if((beiXuhao != null && beiFangwei!= null) && (beiXuhao.equals("1")&& beiFangwei.equals("北"))){
            spinner.setVisibility(View.GONE);
            spinner2.setVisibility(View.GONE);
            tvFangwei.setText("北");
            tvDuanmianxuhao.setText("1");

        }
        if((dongXuhao!= null&& dongFangwei != null) && (dongXuhao.equals("1")&& dongFangwei.equals("东"))){
            spinner.setVisibility(View.GONE);
            spinner2.setVisibility(View.GONE);
            tvFangwei.setText("东");
            tvDuanmianxuhao.setText("1");
        }

        if((nanXuhao != null&& nanFangwei != null) && (nanXuhao.equals("1")&& nanFangwei.equals("南"))){
            spinner.setVisibility(View.GONE);
            spinner2.setVisibility(View.GONE);
            tvFangwei.setText("南");
            tvDuanmianxuhao.setText("1");
        }

        if(( xiXuhao!= null&& xiFangwei != null) && ( xiXuhao.equals("1")&& xiFangwei.equals("西"))){
            spinner.setVisibility(View.GONE);
            spinner2.setVisibility(View.GONE);
            tvFangwei.setText("西");
            tvDuanmianxuhao.setText("1");
        }

        titleBack1.setOnClickListener(this);
        btnEtid.setOnClickListener(this);
        titleTitle.setText("电缆井断面表");
        quit.setVisibility(View.GONE);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.fangwei);
                tvFangwei.setText(languages[pos]);

                //Toast.makeText(CablePitNorthActivity .this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
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

                String[] languages = getResources().getStringArray(R.array.duanmianxuhao);
                tvDuanmianxuhao.setText(languages[pos]);

                //Toast.makeText(CablePitNorthActivity .this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

//        if(compareXuhao==null){
//            return;
//        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back1:
                finish();
                break;
            case R.id.btn_etid:
                compareXuhao = getIntent().getStringExtra("compareXuhao");
                if (compareXuhao != null) {
                        if (click1() == true) {
                            offlineSave();
                            String hangShu = etHangshu.getText().toString().trim();
                            String lieShu = etLieshu.getText().toString().trim();
                            String fangWei = tvFangwei.getText().toString().trim();
                            String xuHao = tvDuanmianxuhao.getText().toString().trim();
                            Intent intent2 = new Intent(YiXingCablePitNorth.this, PlotActivity.class);
                            intent2.putExtra("hangshu", hangShu);
                            intent2.putExtra("lieshu", lieShu);
                            intent2.putExtra("fangWei", fangWei);
                            intent2.putExtra("xuHao", xuHao);
                            startActivity(intent2);
                        }
                    } else {
                        if (click() == true) {
                            offlineSave();
                            String hangShu = etHangshu.getText().toString().trim();
                            String lieShu = etLieshu.getText().toString().trim();
                            String fangWei = tvFangwei.getText().toString().trim();
                            String xuHao = tvDuanmianxuhao.getText().toString().trim();
                            Intent intent2 = new Intent(YiXingCablePitNorth.this, PlotActivity.class);
                            intent2.putExtra("hangshu", hangShu);
                            intent2.putExtra("lieshu", lieShu);
                            intent2.putExtra("fangWei", fangWei);
                            intent2.putExtra("xuHao", xuHao);
                            startActivity(intent2);
                        }

                }

                break;
            default:
                break;
        }

    }


    //显示本地缓存
    private void getDataForSql() {
        if (address != null && address.toString().length() > 0) {
            for (int i = 0; i < address.size(); i++) {
                //编号
                String str = address.get(i).getNummber();
                jingName = address.get(i).getName();
                //tvName.setText(address.get(i).getName());
                tvBianhao.setText(address.get(i).getNummber());
                Log.w("999999999",str);

            }
        }
    }

    //检查登录格式是否正确
    private Boolean click() {
        Fangwei = tvFangwei.getText().toString().trim();
        Duanmianxuhao = tvDuanmianxuhao.getText().toString().trim();
        String Shangbianju = etShangbianju.getText().toString().trim();
        String Xiabianju = etXiabianju.getText().toString().trim();
        String Zuobianju = etZuobianju.getText().toString().trim();
        String Youbianju = etYoubianju.getText().toString().trim();
        String hangShu = etHangshu.getText().toString().trim();
        String lieShu = etLieshu.getText().toString().trim();
        tvName.setText(jingName+Fangwei+Duanmianxuhao);
        if (Fangwei.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请选择方位", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Duanmianxuhao.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请选择断面序号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Shangbianju.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请输入上边距", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Xiabianju.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请输入下边距", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Zuobianju.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请输入左边距", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Youbianju.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请输入右边距", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (hangShu.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请输入行数", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (lieShu.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请输入列数", Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (beiFangwei.equals(Fangwei) && beiXuhao.equals(Duanmianxuhao)) {
//            Toast.makeText(YiXingCablePitNorth.this, "新增同的方位断面时不能选择相同的序号，请重新选择其它序号!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (dongFangwei.equals(Fangwei) && dongXuhao.equals(Duanmianxuhao)) {
//            Toast.makeText(YiXingCablePitNorth.this, "新增同的方位断面时不能选择相同的序号，请重新选择其它序号!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if ( nanFangwei.equals(Fangwei) && nanXuhao.equals(Duanmianxuhao)) {
//            Toast.makeText(YiXingCablePitNorth.this, "新增同的方位断面时不能选择相同的序号，请重新选择其它序号!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (xiFangwei.equals(Fangwei) && xiXuhao.equals(Duanmianxuhao)) {
//            Toast.makeText(YiXingCablePitNorth.this, "新增同的方位断面时不能选择相同的序号，请重新选择其它序号!", Toast.LENGTH_SHORT).show();
//            return false;
//        }

        return true;
    }



    //检查登录格式是否正确
    private Boolean click1() {
        String  beiXuhao = getIntent().getStringExtra("beiXuhao");
        String  dongXuhao = getIntent().getStringExtra("dongXuhao");
        String  nanXuhao = getIntent().getStringExtra("nanXuhao");
        String  xiXuhao = getIntent().getStringExtra("xiXuhao");

        //方位
        String  beiFangwei = getIntent().getStringExtra("beiFangwei");
        String  dongFangwei = getIntent().getStringExtra("dongFangwei");
        String  nanFangwei = getIntent().getStringExtra("nanFangwei");
        String  xiFangwei = getIntent().getStringExtra("xiFangwei");
        Fangwei = tvFangwei.getText().toString().trim();
        Duanmianxuhao = tvDuanmianxuhao.getText().toString().trim();
        String Shangbianju = etShangbianju.getText().toString().trim();
        String Xiabianju = etXiabianju.getText().toString().trim();
        String Zuobianju = etZuobianju.getText().toString().trim();
        String Youbianju = etYoubianju.getText().toString().trim();
        String hangShu = etHangshu.getText().toString().trim();
        String lieShu = etLieshu.getText().toString().trim();
        tvName.setText(jingName+Fangwei+Duanmianxuhao);
        if (Fangwei.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请选择方位", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Duanmianxuhao.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请选择断面序号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Shangbianju.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请输入上边距", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Xiabianju.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请输入下边距", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Zuobianju.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请输入左边距", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Youbianju.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请输入右边距", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (hangShu.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请输入行数", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (lieShu.equals("")) {
            Toast.makeText(YiXingCablePitNorth.this, "请输入列数", Toast.LENGTH_SHORT).show();
            return false;
        }

        if ((beiFangwei.equals(Fangwei)) && ((beiXuhao.equals(Duanmianxuhao))||(compareXuhao.equals(Duanmianxuhao)))) {
            Toast.makeText(YiXingCablePitNorth.this, "新增同的方位断面时不能选择相同的序号，请重新选择其它序号!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (dongFangwei.equals(Fangwei) && ((dongXuhao.equals(Duanmianxuhao))||(compareXuhao.equals(Duanmianxuhao)))) {
            Toast.makeText(YiXingCablePitNorth.this, "新增同的方位断面时不能选择相同的序号，请重新选择其它序号!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( nanFangwei.equals(Fangwei) && ((nanXuhao.equals(Duanmianxuhao))||(compareXuhao.equals(Duanmianxuhao))) ){
            Toast.makeText(YiXingCablePitNorth.this, "新增同的方位断面时不能选择相同的序号，请重新选择其它序号!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (xiFangwei.equals(Fangwei) && ((xiXuhao.equals(Duanmianxuhao))||(compareXuhao.equals(Duanmianxuhao)))) {
            Toast.makeText(YiXingCablePitNorth.this, "新增同的方位断面时不能选择相同的序号，请重新选择其它序号!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }



    /**
     * 离线存储罚单
     */
    private void offlineSave(){
        beans = new DianLanJingDuanMianBiao();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String id = format.format(date);
        beans.setUuid(id);
        //编号
        beans.setNummber(tvBianhao.getText().toString());
        //材质
        beans.setName(tvName.getText().toString());
        //形状
        beans.setFangwei(tvFangwei.getText().toString());
        //驾驶证档案编号
        beans.setDuanmianxuhao(tvDuanmianxuhao.getText().toString());
        //发证城市
        beans.setShangbianju(Integer.parseInt(etShangbianju.getText().toString()));
        beans.setXiabianju(etXiabianju.getText().toString());
        beans.setZuobianju(etZuobianju.getText().toString());
        beans.setYoubianju(etYoubianju.getText().toString());
        beans.setHangshu(etHangshu.getText().toString());
        beans.setLieshu(etLieshu.getText().toString());
        DatabaseAdapter.getIntance(getBaseContext()).insertDuanMianAddress(beans);
        List<DianLanJingDuanMianBiao> list = DatabaseAdapter.getIntance(getBaseContext()).queryDuanMianAddress();
        if(list!=null){
            Iterator<DianLanJingDuanMianBiao> iterator = list.iterator();
            while(iterator.hasNext()){
                DianLanJingDuanMianBiao a = iterator.next();
                DatabaseAdapter.getIntance(getBaseContext()).updeteDuanMianAddress(a);
            }
        }

    }

}
