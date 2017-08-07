package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.framents.CutcableFragment;
import dlgx.gis.com.dlgx.framents.NoFinshFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 新增电缆段
 * Created by admin on 2017/4/24.
 */
public class CutCableActivity extends Activity implements RadioGroup.OnCheckedChangeListener,View.OnClickListener
{
   /* @Bind(R.id.title_back1)
    ImageView titleBack1;
    @Bind(R.id.title_title)
    TextView titleTitle;*/
    @Bind(R.id.tab_rb_a)
    RadioButton tabRbA;
    @Bind(R.id.tab_rb_b)
    RadioButton tabRbB;
    @Bind(R.id.main_rg_tabs)
    RadioGroup mainRgTabs;
    @Bind(R.id.tab_content)
    FrameLayout tabContent;
    private FragmentManager fragmentManager;
    private CutcableFragment fragment1;
    private NoFinshFragment fragment2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.cutcableactivity_layout);
        ButterKnife.bind(this);
     //   titleTitle.setText("新增电缆段");
     //   titleBack1.setOnClickListener(this);

        mainRgTabs.check(R.id.tab_rb_a);
        mainRgTabs.setOnCheckedChangeListener(this);
        //第一个显示
        CutcableFragment fragment = new CutcableFragment();
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.
                beginTransaction();
        transaction.replace(R.id.tab_content, fragment);
        transaction.commit();

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//        FragmentTransaction transaction = getFragmentManager().
//                beginTransaction();
        switch (checkedId) {
            case R.id.tab_rb_a://电缆段信息表
                CutcableFragment fragment = new CutcableFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.
                        beginTransaction();
                transaction.replace(R.id.tab_content, fragment);
                transaction.commit();
                tabRbA.setBackgroundResource(R.drawable.xx_01);
                tabRbB.setBackgroundResource(R.drawable.xx_02);

                tabRbA.setTextColor(getResources().getColor(R.color.seller6));
                tabRbB.setTextColor(getResources().getColor(R.color.black));

                break;
            case R.id.tab_rb_b://未完成
                NoFinshFragment fragment1 = new NoFinshFragment();
                FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.
                        beginTransaction();
                transaction1.replace(R.id.tab_content, fragment1);
                transaction1.commit();
                tabRbB.setBackgroundResource(R.drawable.xx_01);
                tabRbA.setBackgroundResource(R.drawable.xx_02);

                tabRbB.setTextColor(getResources().getColor(R.color.seller6));
                tabRbA.setTextColor(getResources().getColor(R.color.black));

                break;
        }
        //transaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
    //        case R.id.title_back1:
    //            finish();
            default:
                break;
        }

    }
}
