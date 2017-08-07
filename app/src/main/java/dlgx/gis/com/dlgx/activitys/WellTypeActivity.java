package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import dlgx.gis.com.dlgx.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 井类型 有两种选择
 * <p/>
 * Created by admin on 2017/4/21.
 */
public class WellTypeActivity extends Activity implements View.OnClickListener {
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
    @Bind(R.id.title_item)
    RelativeLayout titleItem;
    @Bind(R.id.btn_rectangular)
    Button btnRectangular;//长方形
    @Bind(R.id.btn_up)
    Button btnUp;
    private String fileName;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welltypeactivity_layout);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        fileName =getIntent().getStringExtra("name");
        titleTitle.setText("井形状");
        quit.setVisibility(View.GONE);
        titleBack1.setOnClickListener(this);
        btnRectangular.setOnClickListener(this);
        btnUp.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.title_back1:
                String xuHao = getIntent().getStringExtra("xuHao");
                if(xuHao != null){
                    intent = new Intent(WellTypeActivity.this,CablePitNewsActivity.class);
                    intent.putExtra("xuHao",xuHao);
                    startActivity(intent);

                }else {
                    finish();
                }
                break;
            case R.id.btn_rectangular:
                String dljnummber =getIntent().getStringExtra("dljnummber");
                String jingleixing = btnRectangular.getText().toString();
                intent = new Intent(WellTypeActivity.this,RectangularWellActivity.class);
                //传入指定的照片存储路径文件名
                intent.putExtra("name",fileName);
                //传入井的类型
                intent.putExtra("jingleixing",jingleixing);
                //传入井的现场编号
                intent.putExtra("dljnummber",dljnummber);
                startActivity(intent);
                break;
            case R.id.btn_up:
                String dljnummber1 =getIntent().getStringExtra("dljnummber");
                Log.e("dljnummber1",dljnummber1 );
                String yiixngjing = btnUp.getText().toString();
                Intent intent1 = new Intent(WellTypeActivity.this,YingXingJingActivity.class);
                //传入指定的照片存储路径文件名
                intent1.putExtra("name",fileName);
                //传入井的类型
                intent1.putExtra("yiixngjing", yiixngjing);
                //传入井的现场编号
                intent1.putExtra("dljnummber",dljnummber1);
                startActivity(intent1);
                break;
            default:
                break;
        }

    }
}
