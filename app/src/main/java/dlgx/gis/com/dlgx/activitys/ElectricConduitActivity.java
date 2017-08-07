package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.beans.DianLanDuanBean;
import dlgx.gis.com.dlgx.beans.DianLanJingDuanMianBiao;
import dlgx.gis.com.dlgx.beans.DuanMianKongWei;
import dlgx.gis.com.dlgx.db.DatabaseAdapter;
import dlgx.gis.com.dlgx.utils.PinYin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 长方形井-编辑断面孔位信息
 * Created by admin on 2017/4/21.
 */
public class ElectricConduitActivity extends Activity implements View.OnClickListener {
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
    @Bind(R.id.guankongleixing)
    TextView guankongleixing;
    @Bind(R.id.spinner)
    Spinner spinner;
    @Bind(R.id.guankongcaizhi)
    TextView guankongcaizhi;
    @Bind(R.id.spinner2)
    Spinner spinner2;
    @Bind(R.id.guankongkongjing)
    TextView guankongkongjing;
    @Bind(R.id.spinner3)
    Spinner spinner3;
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.view_di)
    View viewDi;
    @Bind(R.id.btn_sure)
    Button btnSure;//确定
    @Bind(R.id.et_shurunummber)
    EditText etShurunummber;//输入编号
    @Bind(R.id.lv_jiansuo)
    ListView lvJiansuo;
    @Bind(R.id.tv_name)
    TextView tvName;


    private List<String> testArray = new ArrayList<String>();
    private MyAdapter adapter;
    private String str,nostr;
    private int raw=0;
    private int col=0;
    private List<DianLanJingDuanMianBiao> address = new ArrayList<DianLanJingDuanMianBiao>();
   private List<DianLanDuanBean> dianLanDuanBeen = new ArrayList<DianLanDuanBean>();
    private List<DianLanDuanBean> nodianLanDuanBeen = new ArrayList<DianLanDuanBean>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.electricconduitactivity_layout);
        ButterKnife.bind(this);
        address =  DatabaseAdapter.getIntance(getBaseContext()).queryDuanMianAddress();
        dianLanDuanBeen  =  DatabaseAdapter.getIntance(getBaseContext()).queryDuanAddress();
        nodianLanDuanBeen  =  DatabaseAdapter.getIntance(getBaseContext()).queryNoDuanAddress();
        getDataForSql();
//        getDldDataForSql();
//        getNoDldDataForSql();
        initView();
        // 每次进入前清空一下
        DatabaseAdapter.getIntance(this).deleteAll();
        if(getDldDataForSql()!=null&&getDldDataForSql().size()>0){
            Log.e("3333888","333388888");
            // 向数据库中插入指定数据
            DatabaseAdapter.getIntance(this).inserInfo(getDldDataForSql());
        }
        if(getNoDldDataForSql()!=null &&getNoDldDataForSql().size()>0){
            Log.e("33336666","333366666");
            // 向数据库中插入指定数据
            DatabaseAdapter.getIntance(this).inserInfo(getNoDldDataForSql());
        }


    }

    //显示本地缓存
    private void getDataForSql() {
        if (address != null && address.size()> 0) {
            for (int i = 0; i < address.size(); i++) {
                //编号
                tvName.setText(address.get(i).getName());

            }
        }
    }
    //从数据库中获取电缆段编号
    private List<String> getDldDataForSql() {
        List<String> titleArray = new ArrayList<String>();
        if ( dianLanDuanBeen!= null &&  dianLanDuanBeen.size()> 0) {
            for (int i = 0; i <  dianLanDuanBeen.size(); i++) {
                Log.e("bbbbbbb1","bbbbbbbb1");
                //编号

                titleArray.add(dianLanDuanBeen.get(i).getNummber());

            }
        }
        return titleArray;

    }
    //从数据库中获取未完成电缆段编号
    private List<String>  getNoDldDataForSql() {
        List<String> titleArray1 = new ArrayList<String>();
        if ( nodianLanDuanBeen!= null &&  nodianLanDuanBeen.size()> 0) {
            for (int i = 0; i <  nodianLanDuanBeen.size(); i++) {
                Log.e("bbbbbbb1","bbbbbbbb1");
                //编号
                titleArray1.add(nodianLanDuanBeen.get(i).getNummber());

            }
        }
        return titleArray1;

    }
    private void initView() {
        String str = getIntent().getStringExtra("hlh");
        raw =Integer.parseInt(str.split(",")[0]);
        col =Integer.parseInt(str.split(",")[1]);
        titleTitle.setText("编辑断面孔");
        quit.setVisibility(View.GONE);
        titleBack1.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.guankongleixing);
                guankongleixing.setText(languages[pos]);
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

                String[] languages = getResources().getStringArray(R.array.guankongcaizhi);
                guankongcaizhi.setText(languages[pos]);
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

                String[] languages = getResources().getStringArray(R.array.guankongkongjing);
                guankongkongjing.setText(languages[pos]);
                // Toast.makeText(CablePitNewsActivity.this, "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        adapter = new MyAdapter(this, testArray);
        lvJiansuo.setAdapter(adapter);// 设置Adapter，初始值为空

        lvJiansuo.setOnItemClickListener(new AdapterView.OnItemClickListener() {// listView点击事件

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                etShurunummber.setText(adapter.getItem(position));
                lvJiansuo.setVisibility(View.GONE);
            }
        });

        etShurunummber.addTextChangedListener(new TextWatcher() {// EditText变化监听

            /**
             * 正在输入
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub
                testArray = new ArrayList<String>();// 每次输入的时候，重新初始化数据列表

                if (!TextUtils.isEmpty(etShurunummber.getText().toString())) {// 判断输入内容是否为空，为空则跳过
                    // 查询相似数据--传入一个转换为拼音的字符串
                    testArray = DatabaseAdapter.getIntance(ElectricConduitActivity.this)
                            .queryInfo(
                                    PinYin.getPinYin(etShurunummber.getText()
                                            .toString()));
                }

                adapter.refreshData(testArray);// Adapter刷新数据
                lvJiansuo.setVisibility(View.VISIBLE);
            }

            /**
             * 输入之前
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            /**
             * 输入之后
             */
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });


    }




    /**
     * 获取ArrayList数组
     *
     * @param
     * @return
     */
//    private List<String> getListArray() {
//        List<String> titleArray = new ArrayList<String>();
//        titleArray.add(str);
//        return titleArray;
//    }

   public boolean setImage(){
       if(click()==true){
           return true;
       }else {
           return false;

       }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back1:
                finish();
                break;
            case R.id.btn_finish:
                break;
            case R.id.btn_sure://确定
                String bianhao = etShurunummber.getText().toString().trim();
                String leixing = guankongleixing.getText().toString().trim();
                if (click() == true) {
                    if(leixing.equals("穿孔电缆")||leixing.equals("穿孔其他电缆")||leixing.equals("直埋其他电缆")||leixing.equals("直埋电缆") ||leixing.equals("直埋电缆")) {
                        guankongleixing.setText(bianhao + leixing);
                    }
                    offlineSave();
                    boolean b = setImage();
                    Bundle mybundle = new Bundle();
                    mybundle.putBoolean("image",b);
                    Intent intr = new Intent();
                    intr.putExtras(mybundle);
                    setResult(RESULT_OK, intr);
                    finish();
                    Toast.makeText(ElectricConduitActivity.this, "断面孔位信息表填写完成", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    //检查登录格式是否正确
    private Boolean click() {
        String bianhao = etShurunummber.getText().toString().trim();
        if (bianhao.equals("")) {
            Toast.makeText(ElectricConduitActivity.this, "请输入现场编号", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    class MyAdapter extends BaseAdapter {

        private List<String> mTitleArray;// 标题列表
        private LayoutInflater inflater = null;
        private Context mContext;

        /**
         * Adapter构造方法
         *
         * @param titleArray
         */
        public MyAdapter(Context context, List<String> titleArray) {
            // TODO Auto-generated constructor stub
            this.mTitleArray = titleArray;
            this.mContext = context;
            inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        /**
         * 获取总数
         */
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mTitleArray.size();
        }

        /**
         * 获取Item内容
         */
        @Override
        public String getItem(int position) {
            // TODO Auto-generated method stub
            return mTitleArray.get(position);
        }

        /**
         * 获取Item的ID
         */
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.list_item_layout, null);
                holder.titleTv = (TextView) convertView.findViewById(R.id.title_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // 设置
            holder.titleTv.setText(mTitleArray.get(position));

            return convertView;
        }

        class ViewHolder {
            TextView titleTv;
        }

        /**
         * 刷新数据
         *
         * @param array
         */
        public void refreshData(List<String> array) {
            this.mTitleArray = array;
            notifyDataSetChanged();
        }

    }

    /**
     * 离线存储罚单
     */
    private void offlineSave() {
        DuanMianKongWei beans = new DuanMianKongWei();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String id = format.format(date);
        beans.setUuid(id);
        beans.setName(tvName.getText().toString());
        //编号
        beans.setNummber(etShurunummber.getText().toString());
        //材质
        beans.setGuankongleiixng(guankongleixing.getText().toString());
        //形状
        beans.setGuankongcaizhi(guankongcaizhi.getText().toString());
        //驾驶证档案编号
        beans.setGuankongguanjing(guankongkongjing.getText().toString());
        beans.setHanghao(raw+"");
        beans.setLiehao(col+"");
        DatabaseAdapter.getIntance(getBaseContext()).insertKongWeiAddress(beans);
        List<DuanMianKongWei> list = DatabaseAdapter.getIntance(getBaseContext()).queryKongWeiAddress();
        if(list!=null){
            Iterator<DuanMianKongWei> iterator = list.iterator();
            while(iterator.hasNext()){
                DuanMianKongWei a = iterator.next();
                DatabaseAdapter.getIntance(getBaseContext()).updeteKongWeiAddress(a);
            }
        }
    }


}
