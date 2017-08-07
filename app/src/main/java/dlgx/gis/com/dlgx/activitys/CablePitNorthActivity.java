package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * 编辑断面属性
 * Created by admin on 2017/4/21.
 */
public class CablePitNorthActivity extends Activity implements View.OnClickListener {
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
    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_fangwei)
    TextView tvFangwei;
    @Bind(R.id.et_shangbianju)
    EditText etShangbianju;
    @Bind(R.id.et_xiabianju)
    EditText etXiabianju;
    @Bind(R.id.et_zuobianju)
    EditText etZuobianju;
    @Bind(R.id.et_youbianju)
    EditText etYoubianju;
    @Bind(R.id.et_hangshu)
    EditText etHangshu;
    @Bind(R.id.et_lieshu)
    EditText etLieshu;
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.view_di)
    View viewDi;
    @Bind(R.id.btn_etid)
    Button btnEtid;
    private DianLanJingDuanMianBiao beans;
    private String Fangwei;
    private List<GongJingXinxiBean> address = new ArrayList<GongJingXinxiBean>();
    private String jingName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cablepitnorthactivity_layout);
        ButterKnife.bind(this);
        address = DatabaseAdapter.getIntance(getBaseContext()).queryJingAddress();
        getDataForSql();
        initView();


    }

    private void initView() {
        titleBack1.setOnClickListener(this);
        btnEtid.setOnClickListener(this);
        titleTitle.setText("电缆井断面表");
        quit.setVisibility(View.GONE);
        String imagename = getIntent().getStringExtra("imagename");
        if(imagename!=null) {
            tvFangwei.setText(imagename);
        }
        String dongimagename = getIntent().getStringExtra("dongimagename");
        if(dongimagename !=null) {
            tvFangwei.setText(dongimagename);
        }
        String imagenamenan = getIntent().getStringExtra("imagenamenan");
        if(imagenamenan !=null) {
            tvFangwei.setText(imagenamenan);
        }
        String imagenamexi = getIntent().getStringExtra("imagenamexi");
        if(imagenamexi !=null) {
            tvFangwei.setText(imagenamexi);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back1:
                finish();
                break;
            case R.id.btn_etid:
               String name = tvName.getText().toString();
                String hangshu = etHangshu.getText().toString();
                String lieshu = etLieshu.getText().toString();
                if (click() == true) {
                    offlineSave();
                    Intent intent2 = new Intent(CablePitNorthActivity.this, PlotActivity.class);
                    intent2.putExtra("name",name);
                    intent2.putExtra("hangshu",hangshu);
                    intent2.putExtra("lieshu",lieshu);
                    startActivity(intent2);
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
                Log.w("999999999", str);

            }
        }
    }

    //检查登录格式是否正确
    private Boolean click() {
        Fangwei = tvFangwei.getText().toString().trim();
        String Shangbianju = etShangbianju.getText().toString().trim();
        String Xiabianju = etXiabianju.getText().toString().trim();
        String Zuobianju = etZuobianju.getText().toString().trim();
        String Youbianju = etYoubianju.getText().toString().trim();
        String hangShu = etHangshu.getText().toString().trim();
        String lieShu = etLieshu.getText().toString().trim();
        tvName.setText(jingName + Fangwei);
        if (Fangwei.equals("")) {
            Toast.makeText(CablePitNorthActivity.this, "请选择方位", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Shangbianju.equals("")) {
            Toast.makeText(CablePitNorthActivity.this, "请输入上边距", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Xiabianju.equals("")) {
            Toast.makeText(CablePitNorthActivity.this, "请输入下边距", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Zuobianju.equals("")) {
            Toast.makeText(CablePitNorthActivity.this, "请输入左边距", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Youbianju.equals("")) {
            Toast.makeText(CablePitNorthActivity.this, "请输入右边距", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (hangShu.equals("")) {
            Toast.makeText(CablePitNorthActivity.this, "请输入行数", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (lieShu.equals("")) {
            Toast.makeText(CablePitNorthActivity.this, "请输入列数", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    /**
     * 离线存储罚单
     */
    private void offlineSave() {
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
        //发证城市
        beans.setShangbianju(Integer.parseInt(etShangbianju.getText().toString()));
        beans.setXiabianju(etXiabianju.getText().toString());
        beans.setZuobianju(etZuobianju.getText().toString());
        beans.setYoubianju(etYoubianju.getText().toString());
        //行数
        beans.setHangshu(etHangshu.getText().toString());
        //列数
        beans.setLieshu(etLieshu.getText().toString());
        DatabaseAdapter.getIntance(getBaseContext()).insertDuanMianAddress(beans);
        List<DianLanJingDuanMianBiao> list = DatabaseAdapter.getIntance(getBaseContext()).queryDuanMianAddress();
        if (list != null) {
            Iterator<DianLanJingDuanMianBiao> iterator = list.iterator();
            while (iterator.hasNext()) {
                DianLanJingDuanMianBiao a = iterator.next();
                DatabaseAdapter.getIntance(getBaseContext()).updeteDuanMianAddress(a);
            }
        }

    }



}
