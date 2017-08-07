package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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


import com.esri.core.geometry.Point;

import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.beans.DianLanDuanBean;
import dlgx.gis.com.dlgx.beans.DianlantongdaoBean;
import dlgx.gis.com.dlgx.beans.DldglBean;
import dlgx.gis.com.dlgx.beans.DltdglBean;
import dlgx.gis.com.dlgx.db.DatabaseAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 修改电缆段属性信息
 * Created by admin on 2017/4/24.
 */
public class CutCableNewsActivity extends Activity implements View.OnClickListener {
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
    @Bind(R.id.et_bianhao)
    EditText etBianhao;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.et_suoshubiandianzhan)
    EditText etSuoshubiandianzhan;
    @Bind(R.id.spinner)
    Spinner spinner;
    @Bind(R.id.et_suoshuxianlu)
    EditText etSuoshuxianlu;
    @Bind(R.id.spinner3)
    Spinner spinner3;
    @Bind(R.id.tv_dianyadengji)
    TextView tvDianyadengji;
    @Bind(R.id.spinner4)
    Spinner spinner4;
    @Bind(R.id.tv_startname)
    TextView tvStartname;
    @Bind(R.id.btn_check)
    Button btnCheck;
    @Bind(R.id.tv_endname)
    TextView tvEndname;
    @Bind(R.id.btn_endcheck)
    Button btnEndcheck;
    @Bind(R.id.tv_pushefangshi)
    TextView tvPushefangshi;
    @Bind(R.id.tv_xinghao)
    TextView tvXinghao;
    @Bind(R.id.spinner2)
    Spinner spinner2;
    @Bind(R.id.tv_changdu)
    TextView tvChangdu;
    @Bind(R.id.tv_zhuangtai)
    TextView tvZhuangtai;
    @Bind(R.id.spinner5)
    Spinner spinner5;
    @Bind(R.id.tv_startjingdu)
    TextView tvStartjingdu;
    @Bind(R.id.tv_startweidu)
    TextView tvStartweidu;
    @Bind(R.id.tv_endjingdu)
    TextView tvEndjingdu;
    @Bind(R.id.tv_endweidu)
    TextView tvEndweidu;
    @Bind(R.id.tv_zuoyedanwei)
    TextView tvZuoyedanwei;
    @Bind(R.id.tv_xinzengshijain)
    TextView tvXinzengshijain;
    @Bind(R.id.btn_xinjian)
    Button btnXinjian;
    @Bind(R.id.tv_xuhao)
    TextView tvXuhao;
    @Bind(R.id.tv_shebei_id)
    TextView tvShebeiId;
    @Bind(R.id.tv_dainzibiaoqianhao)
    TextView tvDainzibiaoqianhao;




    List<DianLanDuanBean> bean = new ArrayList<DianLanDuanBean>();
    private String startId,endId ;
    private String bianHao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cutcablenewsactivity_layout);
        ButterKnife.bind(this);
        initView();
        bean = DatabaseAdapter.getIntance(getBaseContext()).queryDuanAddress();


    }


    private void initView() {
        String sNummber = getIntent().getStringExtra("nummber");
        if (sNummber != null) {
            etBianhao.setText(sNummber);
            Log.e("sNummbe", sNummber);
        }

        String dianyadengji = getIntent().getStringExtra("dianyadengji");
        if (dianyadengji != null) {
            tvDianyadengji.setText(dianyadengji);
            Log.e("dianyadengji", dianyadengji);
        }
        String startname = getIntent().getStringExtra("startname");
        if (startname != null) {
            tvStartname.setText(startname);
            Log.e("startname", startname);
        }

        String zhuangtai = getIntent().getStringExtra("zhuangtai");
        if (zhuangtai != null) {
            tvZhuangtai.setText(zhuangtai);
            Log.e("zhuangtai", zhuangtai);
        }
        String startjingdu = getIntent().getStringExtra("startjingdu");
        if (startjingdu != null) {
            tvStartjingdu.setText(startjingdu);
            Log.e("startjingdu", startjingdu);
        }
        String startweidu = getIntent().getStringExtra("startweidu");
        if (startweidu != null) {
            tvStartweidu.setText(startweidu);
            Log.e("startweidu", startweidu);
        }

        String xinzengshijian = getIntent().getStringExtra("xinzengshijian");
        if (xinzengshijian != null) {
            tvXinzengshijain.setText(xinzengshijian);
            Log.e("xinzengshijian", xinzengshijian);
        }
        //开始设备id
        startId = getIntent().getStringExtra("intid");
        Log.e("startId",startId);


        titleTitle.setText("修改电缆段属性信息");
        quit.setVisibility(View.GONE);
        titleBack1.setOnClickListener(this);
        btnXinjian.setOnClickListener(this);
        btnCheck.setOnClickListener(this);
        btnEndcheck.setOnClickListener(this);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        tvXinzengshijain.setText(str);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.suoshubiandianzhan);
                etSuoshubiandianzhan.setText(languages[pos]);
                // Toast.makeText(CablePitNewsActivity.this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
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

                String[] languages = getResources().getStringArray(R.array.suoshuxianlu);
                etSuoshuxianlu.setText(languages[pos]);
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

                String[] languages = getResources().getStringArray(R.array.dainyadengji);
                tvDianyadengji.setText(languages[pos]);
                // Toast.makeText(CablePitNewsActivity.this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
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

                String[] languages = getResources().getStringArray(R.array.dianlanxinghao);
                tvXinghao.setText(languages[pos]);
                // Toast.makeText(CablePitNewsActivity.this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
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

                String[] languages = getResources().getStringArray(R.array.zhuangtai);
                tvZhuangtai.setText(languages[pos]);
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
       bianHao = etBianhao.getText().toString().trim();
        switch (v.getId()) {
            case R.id.title_back1:
                finish();
                break;
            case R.id.btn_xinjian:
                if (click() == true) {
                    getDltd(startId,endId,bianHao);
                    getLong();
                    AlertDialog();

                }
                break;
            case R.id.btn_check:
                Intent intent1 = new Intent(CutCableNewsActivity.this, ChooseSeBeiActivity.class);
                startActivityForResult(intent1, 1);
                break;
            case R.id.btn_endcheck:
                Intent intent2 = new Intent(CutCableNewsActivity.this, ChooseSeBeiActivity.class);
                startActivityForResult(intent2, 2);
                break;
            default:
                break;
        }
    }

    private boolean setCleanr() {
        if (click() == true) {
            return true;
        } else {
            return false;
        }

    }

    //检查登录格式是否正确
    private Boolean click() {
        String bianHao = etBianhao.getText().toString().trim();
        String suoShubiandianzhan = etSuoshubiandianzhan.getText().toString().trim();
        String suoShuxianlu = etSuoshuxianlu.getText().toString().trim();
        String startName = tvStartname.getText().toString().trim();
        String endName = tvEndname.getText().toString().trim();
        tvName.setText(startName + "-" + endName);
        String startjingdu = tvStartjingdu.getText().toString().trim();
        String tvendjingdu = tvEndjingdu.getText().toString().trim();
        String tvstartweidu = tvStartweidu.getText().toString().trim();
        String tvendweidu = tvEndweidu.getText().toString().trim();
        if (bianHao.equals("")) {
            Toast.makeText(CutCableNewsActivity.this, "请输入现场编号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (suoShubiandianzhan.equals("")) {
            Toast.makeText(CutCableNewsActivity.this, "请输入所属变电站", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (suoShuxianlu.equals("")) {
            Toast.makeText(CutCableNewsActivity.this, "请输入所属变线路", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (startName.equals("")) {
            Toast.makeText(CutCableNewsActivity.this, "请选取起点设备", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (endName.equals("")) {
            Toast.makeText(CutCableNewsActivity.this, "请选取终点设备", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (startjingdu.equals(tvendjingdu) && tvstartweidu.equals(tvendweidu)) {
            Toast.makeText(CutCableNewsActivity.this, "不能选择同一个设备建电缆段,请重新选择起点设备和终点设备!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    //获取所有设备关联的电缆通道
    private HashMap<String,DltdStartAndEnd> getDlbtdNummber(String id){
        Log.e("DltdStartAndEnd in",id);
        HashMap<String,DltdStartAndEnd> hashMap = new HashMap<String,DltdStartAndEnd>();
        List<DltdglBean> dltdglBeanList  =DatabaseAdapter.getIntance(getBaseContext()).GetDLTDSql(id);
        if(dltdglBeanList!=null && dltdglBeanList.size()>0){
            Log.e("dltdglBeanList size",dltdglBeanList.size()+"");
            for (int i = 0; i < dltdglBeanList.size(); i++) {
                Log.e("dltdglBeanList i",i+"");
                //得到电缆通道现场编号
                String dltd_nummbertmp = dltdglBeanList.get(i).getNummber();
                Log.e("dltd_nummbertmp",dltd_nummbertmp);
                if(!hashMap .containsKey(dltd_nummbertmp)){
                    Log.e("3333333333","3333333333333");
                    List<DltdglBean> dltdglBeanList1  =DatabaseAdapter.getIntance(getBaseContext()).GetDLTDByDltdNumber(dltd_nummbertmp);
                    DltdStartAndEnd startAndEnd =new DltdStartAndEnd();
                    boolean isnohaveWell=true;
                    for (int j=0;j<dltdglBeanList1.size();j++) {
                        String xuhao = dltdglBeanList1.get(j).getXuHao();
                        Log.e("xuhao8",xuhao);
                        String sbname = dltdglBeanList1.get(j).getShiBei_nummber();
                        Log.e("sbname8",sbname);
                        String sblx = dltdglBeanList1.get(j).getShebeiType();
                        Log.e(" sblx8", sblx );
                        int xuHaoInt = Integer.parseInt(xuhao);
                        int sblxInt = Integer.parseInt(sblx);
                        if(sblxInt==3){
                            isnohaveWell=true;
                            break;
                        }
                        if(xuHaoInt==1){
                            startAndEnd.setSsblx(sblxInt);
                            startAndEnd.setStratsbName(sbname);
                        }
                        if(xuHaoInt==(dltdglBeanList1.size())){
                            startAndEnd.setEsblx(sblxInt);
                            startAndEnd.setEndsbName(sbname);
                        }
                    }
                    if(isnohaveWell)
                        hashMap .put(dltd_nummbertmp,startAndEnd);
                    Log.e("hashMap ",hashMap.size()+"");
                }

            }
        }
        return hashMap ;
    }

    class DltdStartAndEnd{
        public String getStratsbName() {
            return stratsbName;
        }
        public void setStratsbName(String stratsbName) {
            this.stratsbName = stratsbName;
        }
        public String getEndsbName() {
            return endsbName;
        }
        public void setEndsbName(String endsbName) {
            this.endsbName = endsbName;
        }



        public int getSsblx() {
            return ssblx;
        }

        public void setSsblx(int ssblx) {
            this.ssblx = ssblx;
        }

        public int getEsblx() {
            return esblx;
        }

        public void setEsblx(int esblx) {
            this.esblx = esblx;
        }
        private String stratsbName;//电缆通道起始设备名称/电缆井现场编号
        private String endsbName;//电缆通道终点设备名称/电缆井现场编号
        private int ssblx;//起点设备类型
        private int esblx;//终点设备类型
    }

    //得到N条电缆通道 第一个参数:电缆段起点设备id  第二个参数:电缆段终点设备id  第三个参数:电缆段现场编号
    private void getDltd(String ssbmc,String essbmc,String dldnummber){
        Log.e("00000000","0000000000");
        List<String> stringList= updateDltdGL(dldnummber);
        ArrayList<String> list =new ArrayList<String>();
        if(stringList!=null && stringList.size()>0){//使用电缆井关联电缆通道获取电缆通道现场编码
            for (int i = 0;i<stringList.size();i++){
                //得到电缆通道所有现场编号
                String dltdnummber = stringList.get(i);
                if(!list.contains(dltdnummber)){
                    list.add(dltdnummber);
                }
            }
        }else {//使用只有设备的电缆通道的设备id获取电缆通道现场编码
            HashMap<String,DltdStartAndEnd> hsmap =  getDlbtdNummber(ssbmc);
            Log.e("hsmap",hsmap+"");
            for(String key: hsmap.keySet()){
                Log.e("6666666666","66666666");
                DltdStartAndEnd startAndEnd= hsmap.get(key);
                Log.e("startAndEnd",startAndEnd+"");
                if(startAndEnd.getEsblx()==3){
                    Log.e("8888888888","888888888888");
                    String sbmctmp =startAndEnd.getEndsbName();
                    Log.e("sbmctmp ",sbmctmp);
                    if(!list.contains(key)){
                        Log.e("999999999","999999999999");
                        list.add(key);
                        Log.e("list999999999",list.size()+"");
                    }
                    getDltd(sbmctmp,essbmc,dldnummber);
                }else if(startAndEnd.getEsblx()==2){
                    Log.e("10000000000","10000000000");
                    //电缆段起始设备id和终点设备id进行拼接
                    String a = ssbmc + essbmc;
                    //电缆通道起始设备id和终点设备id进行拼接
                    String dltdStart_id = startAndEnd.getStratsbName();//电缆通道起始设备id
                    String dltdEnd_id  = startAndEnd.getEndsbName();;//电缆通道终点设备id
                    String b = dltdStart_id +  dltdEnd_id;//相同方向
                    String c =  dltdEnd_id+ dltdStart_id;//相反方向
                    if(a.equals(b)){
                        Log.e("电缆段搜索完毕","电缆段创建完成");
                        list.add(key);
                        Log.e("list20000000000",list.size()+"");
                    }
                    if(a.equals(c)){
                        Log.e("电缆段搜索完毕","电缆段创建完成");
                        list.add(key);
                        Log.e("list20000000000",list.size()+"");
                    }
                }else{
                    Log.e("不存在电缆段","不存在电缆段");
                }
            }
        }
        //获取到的电缆通道的现场编号插入到数据库
        if(list!=null && list.size()>0) {
            for (int i = 0; i < list.size(); i++) {
                Log.e("55555555555", "5555555555");
                String dltdxcbh = list.get(i);
                Log.e("dltdxcbh", dltdxcbh);
                DldglBean bean = new DldglBean();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date();
                String id = format.format(date);
                bean.setId(id);
                bean.setNummber(bianHao);
                bean.setShiBei_nummber(dltdxcbh);
                bean.setShiBei_xuHao(i + "");
                DatabaseAdapter.getIntance(getBaseContext()).insertDldgldress(bean);
            }
        }
    }

    //得到电缆通道现场编号方法
    private List<String> updateDltdGL(String nummber){
        String sql ="select DISTINCT t.nummber from mydltdg t "+
                "left join mycablepit t1 on t.shiBei_nummber = t1.uuid "+
                "left join myduanmian t2 on t1.nummber = t2.nummber "+
                "left join myKongWei t3 on t2.name = t3.name "+
                "where t3.nummber = ? ";
        List<String> list =DatabaseAdapter.getIntance(getBaseContext()).GetDLTDNUMBERBySQL(sql,new String[]{ nummber});
        return list;
    }

    //得到段缆段长度
    private void getLong() {
       String bianHao = etBianhao.getText().toString().trim();
        //通过电缆段现场编号得到所有电缆通道现场编号
        List<DldglBean> list1 = DatabaseAdapter.getIntance(getBaseContext()).GetDLDByDltdNumber(bianHao);
        if (list1 != null && list1.size() > 0) {
            Log.e("aaaaaaaaaaaa", "aaaaaaaaaaaaa");
            for (int j = 0; j < list1.size(); j++) {
                //电缆通道现场编号
                String dltdxcbh = list1.get(j).getShiBei_nummber();
                Log.e("dltdxcbhopopopopop", dltdxcbh);
                if (dltdxcbh != null) {
                    Log.e("dltdxcbh2222222", dltdxcbh);
                    List<DianlantongdaoBean> dltdLatLng = DatabaseAdapter.getIntance(getBaseContext()).GetDLTDLatLng(dltdxcbh);
                    if (dltdLatLng != null && dltdLatLng.size() > 0) {
                        Log.e("dltdxcbh3333333", dltdLatLng + "");
                        for (int i = 0; i < dltdLatLng.size(); i++) {
                            Point startLantlng = new Point(Double.parseDouble(dltdLatLng.get(i).getStartweidu()), Double.parseDouble(dltdLatLng.get(i).getStartjingdu()));
                            Point endLantlng = new Point(Double.parseDouble(dltdLatLng.get(i).getEndweidu()), Double.parseDouble(dltdLatLng.get(i).getEndjingdu()));
                            getDistance(startLantlng, endLantlng);
                        }
                    }
                }
            }
        }
    }


    /**
     * 计算两点之间距离
     *
     * @param start
     * @param end
     * @return 米
     */
    public void getDistance(Point start, Point end) {
        Log.e("dltdxcbh55555", "5555555");
        double lat1 = (Math.PI / 180) * start.getY();
        double lat2 = (Math.PI / 180) * end.getY();

        double lon1 = (Math.PI / 180) * start.getX();
        double lon2 = (Math.PI / 180) * end.getX();

        //地球半径
        double R = 6371;
        //两点间距离 km，如果想要米的话，结果*1000就可以了
        String d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R + "";
        tvChangdu.setText(d);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        startId = data.getStringExtra("id");
                        String jingdu = data.getStringExtra("jingdu");
                        tvStartjingdu.setText(jingdu);
                        String weidu = data.getStringExtra("weidu");
                        tvStartweidu.setText(weidu);
                        String name = data.getStringExtra("string");
                        tvStartname.setText(name);

                    }
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        endId = data.getStringExtra("id");
                        String jingdu = data.getStringExtra("jingdu");
                        tvEndjingdu.setText(jingdu);
                        String weidu = data.getStringExtra("weidu");
                        tvEndweidu.setText(weidu);
                        String name = data.getStringExtra("string");
                        tvEndname.setText(name);
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AlertDialog() {
        String bianHao = etBianhao.getText().toString().trim();
        String startName = tvStartname.getText().toString().trim();
        String endName = tvEndname.getText().toString().trim();
        AlertDialog.Builder dialog = new AlertDialog.Builder
                (CutCableNewsActivity.this);
        //dialog.setTitle("This is Dialog");
        dialog.setMessage("电缆段" + bianHao
                + startName + "-" + endName
                + "新建完成"
        );
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                offlineSave();
                boolean b = setCleanr();
                Bundle mybundle = new Bundle();
                mybundle.putBoolean("type", b);
                Intent intr = new Intent();
                intr.putExtras(mybundle);
                setResult(RESULT_OK, intr);
                finish();
            }
        });
        dialog.show();

    }

    /**
     * 离线存储罚单
     */
    private void offlineSave() {
        DianLanDuanBean beans = new DianLanDuanBean();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String id = format.format(date);
        beans.setUuid(id);
        //编号
        beans.setName(tvName.getText().toString());
        //编号
        beans.setNummber(etBianhao.getText().toString());
        //材质
        beans.setSuoshubiandianzhan(etSuoshubiandianzhan.getText().toString());
        //形状
        beans.setSuoshuxianlu(etSuoshuxianlu.getText().toString());
        //驾驶证档案编号
        beans.setDianyadengji(tvDianyadengji.getText().toString());
        //发证城市
        beans.setStartname(tvStartname.getText().toString());
        //准驾车型
        beans.setEndname(tvEndname.getText().toString());
        //违法内容
        beans.setPushefangshi(tvPushefangshi.getText().toString());
        //车牌类型 字符串
        beans.setXuhao(tvXinghao.getText().toString());
        //车辆类型 字符串
        beans.setChangdu(tvChangdu.getText().toString());
        //违法代码
        beans.setZhuangtai(tvZhuangtai.getText().toString());
        //法律依据
        beans.setStartjingdu(tvStartjingdu.getText().toString());
        //材质
        beans.setStartweidu(tvStartweidu.getText().toString());
        //类型
        beans.setEndjingdu(tvEndjingdu.getText().toString());
        //作业单位
        beans.setEndweidu(tvEndweidu.getText().toString());
        //新增时间
        beans.setXinzengshijian(tvXinzengshijain.getText().toString());
        //起点设备id
        beans.setStartShebei_id(startId);
        //终点设备id
        beans.setEndShebei_id(endId);
        DatabaseAdapter.getIntance(getBaseContext()).insertDuanAddress(beans);
        List<DianLanDuanBean> list = DatabaseAdapter.getIntance(getBaseContext()).queryDuanAddress();
        if (list != null) {
            Iterator<DianLanDuanBean> iterator = list.iterator();
            while (iterator.hasNext()) {
                DianLanDuanBean a = iterator.next();
                DatabaseAdapter.getIntance(getBaseContext()).updeteDuanAddress(a);
            }
        }

    }


}
