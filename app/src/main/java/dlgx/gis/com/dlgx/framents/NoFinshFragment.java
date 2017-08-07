package dlgx.gis.com.dlgx.framents;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import dlgx.gis.com.dlgx.R;
//import com.example.admin.myapplication.activities.activitys.CutCableNewsActivity;
import dlgx.gis.com.dlgx.beans.DianLanDuanBean;
import dlgx.gis.com.dlgx.beans.Nofinsh;
import dlgx.gis.com.dlgx.db.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoFinshFragment extends Fragment implements View.OnClickListener {
    @Override
    public void onClick(View v)
    {

    }
    /*private static final String TAG = "碎片2";
    @Bind(R.id.lv)
    ListView lv;
    private ArrayList<Nofinsh> nofinshs = new ArrayList<>() ;
    private List<DianLanDuanBean> address;
    private NoFinshAdapter noFinshAdapter;
    //传值
    private String  intNummber,intid;
    private String  intStartname;
    private String  intdianyadengji,intxinzengshijian ;
    private String  intzhuangtai,intstartjingdu,intstartweidu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nosinshfragment_fragment, container, false);
        ButterKnife.bind(this, view);
        address =  DatabaseAdapter.getIntance(getActivity().getBaseContext()).queryNoDuanAddress();
        getDataForSql();
        noFinshAdapter = new NoFinshAdapter(nofinshs);
        lv.setAdapter(noFinshAdapter);
        initView();
        return view;
    }

    //显示本地缓存
    public void getDataForSql() {
        if (address != null && address.size() > 0) {
            nofinshs = new ArrayList<>();
            for (int i = 0; i < address.size(); i++) {
                Nofinsh nofinsh = new Nofinsh();
                nofinsh.setNumber(address.get(i).getNummber());
                nofinsh.setName(address.get(i).getName());
                nofinsh.setSuoshubiandianzhan(address.get(i).getSuoshubiandianzhan());
                nofinsh.setDianyadengji(address.get(i).getDianyadengji());
                nofinsh.setStartname(address.get(i).getStartname());
                nofinsh.setPushefangshi(address.get(i).getPushefangshi());
                nofinsh.setDianlanxinghao(address.get(i).getDianlanxinghao());
                nofinsh.setZhuangtai(address.get(i).getZhuangtai());
                nofinsh.setStartjingdu(address.get(i).getStartjingdu());
                nofinsh.setStartweidu(address.get(i).getStartweidu());
                nofinsh.setXuhao(address.get(i).getXuhao());
                nofinsh.setShebei_id(address.get(i).getShebei_id());
                nofinsh.setBiaoqianhao(address.get(i).getBiaoqianhao());
                nofinsh.setZuoyedanwei(address.get(i).getZuoyedanwei());
                nofinsh.setXinzengshijian(address.get(i).getXinzengshijian());
                nofinsh.setStartShebei_id(address.get(i).getStartShebei_id());
                nofinshs.add(nofinsh);
                Log.e("nofinshs",nofinshs.size()+"");
//                boolean contains = nofinshs.contains(nofinsh);
//                if (contains==false){
//                    nofinshs.add(nofinsh);
//                    //
//                }
            }
        }
    }


    protected void initView() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < nofinshs.size(); i++) {
                    intid= nofinshs.get(position).getStartShebei_id();
                    Log.e(" intid ", intid );
                    intNummber = nofinshs.get(position).getNumber();
                    intdianyadengji = nofinshs.get(position).getDianyadengji();
                    intStartname = nofinshs.get(position).getStartname();
                    intzhuangtai = nofinshs.get(position).getZhuangtai();
                    intstartjingdu = nofinshs.get(position).getStartjingdu();
                    intstartweidu = nofinshs.get(position).getStartweidu();
                    intxinzengshijian = nofinshs.get(position).getXinzengshijian();
                }
                Intent intent = new Intent(getActivity(), CutCableNewsActivity.class);
                intent.putExtra("nummber",  intNummber);
                intent.putExtra("dianyadengji", intdianyadengji);
                intent.putExtra("startname", intStartname);
                intent.putExtra("zhuangtai",  intzhuangtai);
                intent.putExtra("startjingdu", intstartjingdu);
                intent.putExtra("startweidu", intstartweidu);
                intent.putExtra("xinzengshijian", intxinzengshijian);
                intent.putExtra("intid", intid);
                startActivityForResult(intent, 1);
            }

        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            default:
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
            if (resultCode == getActivity().RESULT_OK) {
                if (data != null) {
                    Bundle myBundle = data.getExtras();
                    boolean b = myBundle.getBoolean("type");
                    if(b==true){
                        for(int i=0;i<nofinshs.size();i++){
                            nofinshs.remove(i);
                            DianLanDuanBean a = address.get(i);
                            DatabaseAdapter.getIntance(getActivity().getBaseContext()).deleteNoDuanAddress(a);
                            Log.w("66666666","牛牛牛牛牛牛牛");
                            noFinshAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
            break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    class NoFinshAdapter extends BaseAdapter {

        private List<Nofinsh>  mData;


        public NoFinshAdapter(List<Nofinsh>  mData) {
           this. mData =  mData;
        }

        @Override
        public int getCount() {
            return  mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(getActivity(),R.layout.nofnshfragment_list_item, null);
                holder.it_content = (TextView) convertView.findViewById(R.id.tv_number);
                holder.tv_adress = (TextView) convertView.findViewById(R.id.tv_adress);
                // holder.it_imageview = (ImageView) convertView.findViewById(R.id.it_imageview);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.it_content.setText(mData.get(position).getNumber());
            holder.tv_adress.setText(mData.get(position).getStartname()+"-");


            return convertView;
        }
    }

    class ViewHolder {
        TextView it_content;
        TextView tv_adress;

    }*/
}
