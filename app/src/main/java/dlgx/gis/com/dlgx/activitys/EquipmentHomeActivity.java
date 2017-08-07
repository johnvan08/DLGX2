package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import butterknife.Bind;
import butterknife.ButterKnife;
import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.constant.Constant;


/**
 * 新增设备主页
 * Created by admin on 2017/4/28.
 */
public class EquipmentHomeActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

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
    @Bind(R.id.btn_guaidian)
    RadioButton btnGuaidian;
    @Bind(R.id.btn_fenzhixiang)
    RadioButton btnFenzhixiang;
    @Bind(R.id.btn_huanwanggui)
    RadioButton btnHuanwanggui;
    @Bind(R.id.rg_check1)
    RadioGroup rgCheck1;
    @Bind(R.id.btn_xiangbian)
    RadioButton btnXiangbian;
    @Bind(R.id.btn_peidianshi)
    RadioButton btnPeidianshi;
    @Bind(R.id.btn_bianyaqi)
    RadioButton btnBianyaqi;
    @Bind(R.id.rg_check2)
    RadioGroup rgCheck2;
    @Bind(R.id.btn_ganta)
    RadioButton btnGanta;
    @Bind(R.id.btn_biandianzhan)
    RadioButton btnBiandianzhan;
    @Bind(R.id.btn_kaiguanzhan)
    RadioButton btnKaiguanzhan;
    @Bind(R.id.rg_check3)
    RadioGroup rgCheck3;
    @Bind(R.id.btn_yanmaijing)
    RadioButton btnYanmaijing;
    @Bind(R.id.btn_dianlanjietou)
    RadioButton btnDianlanjietou;
    @Bind(R.id.rg_check4)
    RadioGroup rgCheck4;
    @Bind(R.id.btn_yinghuanxinxi)
    RadioButton btnYinghuanxinxi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipmenthomeactivity_layout);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        titleTitle.setText("新增设备");
        titleBack1.setOnClickListener(this);
        quit.setVisibility(View.GONE);
        rgCheck1.setOnCheckedChangeListener(this);
        rgCheck2.setOnCheckedChangeListener(this);
        rgCheck3.setOnCheckedChangeListener(this);
        rgCheck4.setOnCheckedChangeListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back1:
                finish();
                break;
            default:
                break;
        }

    }

    /**
     * 点击切换按钮
     *
     * @param radioGroup
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        Intent intent;
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.btn_guaidian:
                //新增拐点
                intent = new Intent(EquipmentHomeActivity.this, AddSheBeiMapActivity.class);
                intent.putExtra("equipmentType", Constant.EQUIPMEN_GUAIDIAN);
                startActivity(intent);
                break;
            case R.id.btn_fenzhixiang:
                //新增分支箱
                intent = new Intent(EquipmentHomeActivity.this, AddSheBeiMapActivity.class);
                intent.putExtra("equipmentType", Constant.EQUIPMEN_FENZHIXIANG);
                startActivity(intent);
                break;
            case R.id.btn_huanwanggui:
                //新增环网柜
                intent = new Intent(EquipmentHomeActivity.this, AddSheBeiMapActivity.class);
                intent.putExtra("equipmentType", Constant.EQUIPMEN_HUANWANGGUI);
                startActivity(intent);
                break;
            case R.id.btn_xiangbian:
                //新增箱变
                intent = new Intent(EquipmentHomeActivity.this, AddSheBeiMapActivity.class);
                intent.putExtra("equipmentType", Constant.EQUIPMEN_XIANGBIAN);
                startActivity(intent);
                break;
            case R.id.btn_peidianshi:
                //新增配电室
                intent = new Intent(EquipmentHomeActivity.this, AddSheBeiMapActivity.class);
                intent.putExtra("equipmentType", Constant.EQUIPMEN_PEIDIANSHI);
                startActivity(intent);
                break;
            case R.id.btn_bianyaqi:
                //新增变压器
                intent = new Intent(EquipmentHomeActivity.this, AddSheBeiMapActivity.class);
                intent.putExtra("equipmentType", Constant.EQUIPMEN_BIANYAQI);
                startActivity(intent);
                break;
            case R.id.btn_ganta:
                //新增杆塔
                intent = new Intent(EquipmentHomeActivity.this, AddSheBeiMapActivity.class);
                intent.putExtra("equipmentType", Constant.EQUIPMEN_GANTA);
                startActivity(intent);
                break;
            case R.id.btn_biandianzhan:
                //新增变电站
                intent = new Intent(EquipmentHomeActivity.this, AddSheBeiMapActivity.class);
                intent.putExtra("equipmentType", Constant.EQUIPMEN_BIANDIANZHAN);
                startActivity(intent);
                break;
            case R.id.btn_kaiguanzhan:
                //新增开关站
                intent = new Intent(EquipmentHomeActivity.this, AddSheBeiMapActivity.class);
                intent.putExtra("equipmentType", Constant.EQUIPMEN_KAIGUANZHAN);
                startActivity(intent);
                break;
            case R.id.btn_yanmaijing:
                //新增掩埋井
                intent = new Intent(EquipmentHomeActivity.this, AddSheBeiMapActivity.class);
                intent.putExtra("equipmentType", Constant.EQUIPMEN_YANMAIJING);
                startActivity(intent);
                break;
            case R.id.btn_dianlanjietou:
                //新增电缆接头
                intent = new Intent(EquipmentHomeActivity.this, DianLanJieTouNewsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_yinghuanxinxi:
                //新增隐患信息
                intent = new Intent(EquipmentHomeActivity.this,YingHuanXinXiActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }
}
