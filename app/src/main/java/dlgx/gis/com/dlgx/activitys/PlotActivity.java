package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.beans.DianLanJingDuanMianBiao;
import dlgx.gis.com.dlgx.beans.DuanMianKongWei;
import dlgx.gis.com.dlgx.beans.Images;
import dlgx.gis.com.dlgx.db.DatabaseAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 绘制断面孔线
 * Created by admin on 2017/4/21.
 */
public class PlotActivity extends Activity implements View.OnClickListener {
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
    @Bind(R.id.gv)
    GridView gv;
    @Bind(R.id.btn_finish)
    Button btnFinish;//完成
    private GridAdapter adapter;
    private ArrayList<HashMap<String,Object>> mDatas = new ArrayList<HashMap<String,Object>>();
    private  int id;//列数
    private  int id2;//行数
    private  ImageView imageView;
    public List<DianLanJingDuanMianBiao> list;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plotactivity_layout);
        ButterKnife.bind(this);
        initView();

    }


    private void initView() {

        //编号
        String hangshu= getIntent().getStringExtra("hangshu");
        if(hangshu!=null){
            id2=Integer.parseInt(hangshu);
        }
        String lieShu = getIntent().getStringExtra("lieshu");
        if(lieShu!=null){
            id=Integer.parseInt(lieShu);
        }
        titleBack1.setVisibility(View.GONE);
        titleTitle.setText("绘制断面孔线");
        btnFinish.setOnClickListener(this);
        add();
        adapter = new GridAdapter(PlotActivity .this);
        gv.setNumColumns(id);
        gv.setVerticalSpacing(10);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               imageView = (ImageView) view.findViewById(R.id.item_grida_image);
                if( !imageView.getTag().equals(R.drawable.nullkongwei)) {
                    Intent intent = new Intent(PlotActivity.this, ElectricConduitActivity.class);
                    intent.putExtra("hlh",(String)mDatas.get(position).get("hlh"));
                    startActivityForResult(intent, 1);
                }
            }
        });
        //长按删除
        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int position, long arg3) {
                final ImageView imageView1 = (ImageView) arg1.findViewById(R.id.item_grida_image);

                    if (imageView1.getTag().equals(R.drawable.nullkongwei)) {
                        // TODO Auto-generated method stub
                        AlertDialog dialog = new AlertDialog.Builder(PlotActivity.this)
                                .setTitle("增加孔位")
                                .setMessage("确定增加这个孔位?")
                                .setPositiveButton("确定", new AlertDialog.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                        // TODO Auto-generated method stub
                                        imageView1.setBackgroundResource(R.drawable.kongxinyuan);
                                        imageView1.setTag(R.drawable.kongxinyuan);
                                        adapter.notifyDataSetChanged();

                                    }
                                })
                                .setNegativeButton("取消", new AlertDialog.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .create();// 创建
                        // 显示对话框
                        dialog.show();


                }else {
                // TODO Auto-generated method stub
                AlertDialog dialog = new AlertDialog.Builder(PlotActivity.this)
                        .setTitle("删除孔位")
                        .setMessage("确定删除这个孔位?")
                        .setPositiveButton("确定", new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                // TODO Auto-generated method stub
                                imageView1.setBackgroundResource(R.drawable.nullkongwei);
                                imageView1.setTag(R.drawable.nullkongwei);
                                adapter.notifyDataSetChanged();
                                String hlh=(String)mDatas.get(position).get("hlh");
                                String rawtmp =hlh.split(",")[0];
                                String coltmp =hlh.split(",")[1];
                                offlineSave(rawtmp,coltmp);

                            }
                        })
                        .setNegativeButton("取消", new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();// 创建
                // 显示对话框
                dialog.show();
            }
                return true;
            }

        });

        btnFinish.setOnClickListener(this);

    }

//

//
private void add(){
     mDatas = new ArrayList<HashMap<String, Object>>();
    int b = id * id2;

    for(int i=0;i<id2;i++){
        for (int j=0;j<id;j++){
            Images images = new Images();
            images.setImage(R.drawable.kongxinyuan);
            HashMap<String,Object> map =new HashMap<String,Object>();
            map.put("img",images);
            map.put("hlh",i+","+j);
            mDatas.add(map);
        }
    }
}
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_finish:
                String xuHao = getIntent().getStringExtra("xuHao");
                if( xuHao!=null) {
                    Intent intent = new Intent(PlotActivity.this, YingXingJingActivity.class);
                    intent.putExtra("xuHao", xuHao);
                    startActivity(intent);
                    finish();
                    Log.e("xuHao1",xuHao);
                }else {
                    Intent intent = new Intent(PlotActivity.this, RectangularWellActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
//            case R.id.btn_getcode:
//
//                break;
            default:
                break;
        }
    }


    /**
     * 离线存储罚单
     */
    private void offlineSave(String raw,String col) {
        //断面名陈
        String name= getIntent().getStringExtra("name");
        String leiixng ="-1";
        DuanMianKongWei beans = new DuanMianKongWei();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String id = format.format(date);
        beans.setUuid(id);
        beans.setName(name);
        //材质
        beans.setGuankongleiixng(leiixng);
        //形状
        beans.setHanghao(raw);
        beans.setLiehao(col);
        DatabaseAdapter.getIntance(getBaseContext()).insertKongWeiAddress(beans);
//        List<DuanMianKongWei> list = DatabaseAdapter.getIntance(getBaseContext()).queryKongWeiAddress();
//        if(list!=null){
//            Iterator<DuanMianKongWei> iterator = list.iterator();
//            while(iterator.hasNext()){
//                DuanMianKongWei a = iterator.next();
//                DatabaseAdapter.getIntance(getBaseContext()).updeteKongWeiAddress(a);
//            }
//        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        Bundle myBundle = data.getExtras();
                        boolean b = myBundle.getBoolean("image");
                        Log.w(b+"","hahahahahahah");
                        if(b==true){
                            imageView.setBackgroundResource(R.drawable.shixinyuan);
                            adapter.notifyDataSetChanged();

//                            for(int i=0;i<mDatas.size();i++){
//                                mDatas.get(i).setImage(R.drawable.shixinyuan);
//                                adapter.notifyDataSetChanged();
//                                Log.w("66666666","牛牛牛牛牛牛牛");
//                            }

                        }
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    class GridAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        //private List<Images> mDatas;
        public GridAdapter(Context context) {
            this.context = context;
           // this.mDatas = mDatas;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mDatas  != null ? mDatas .size() : 0;
            //return   mDatas.size();//注意此处
        }

        @Override
        public Object getItem(int position) {
            return null;
           // return 0;
           // return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        ViewHolder holder = null;
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_published_grida, parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
                //holder.image.setBackgroundResource(R.drawable.kongxinyuan);
                holder.image.setBackgroundResource(((Images)mDatas.get(position).get("img")).getImage());
                holder.image.setTag(R.drawable.kongxinyuan);
                //holder.image.setTag(1,(String)mDatas.get(position).get("hlh"));
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();

            }

            return convertView;
        }
    }

        public class ViewHolder {
            public ImageView image;
        }
}
