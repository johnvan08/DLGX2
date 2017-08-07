package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import dlgx.gis.com.dlgx.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 登录
 * Created by admin on 2017/4/17.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    @Bind(R.id.line_gray)
    TextView lineGray;
    @Bind(R.id.ed_phone)
    EditText edPhone;
    @Bind(R.id.ed_psw)
    EditText edPsw;
    @Bind(R.id.user_edit_text)
    RelativeLayout userEditText;
//    @Bind(R.id.login_btn)
//    TextView loginBtn;//登录
    @Bind(R.id.button_first)
    Button button_first;//登录
    @Bind(R.id.tv_nowRegister)
    TextView tvNowRegister;//立即注册
    @Bind(R.id.tv_forgetPassword)
    TextView tvForgetPassword;//忘记密码

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
//        set_search_onClick();
//        loginBtn.setOnClickListener(this);
        tvNowRegister.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);
        button_first.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.login_btn:
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
////                if(click()==true) {
////                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
////                    startActivity(intent);
////                }
//                break;
            case R.id.button_first:
                Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent1);
//                if(click()==true) {
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                }
                break;
            case R.id.tv_nowRegister:
//                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
//                startActivity(intent1);
                break;
            case R.id.tv_forgetPassword:
//                Intent intent2 = new Intent(LoginActivity.this,ForGetPassWordActivity.class);
//                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    //检查登录格式是否正确
    private Boolean click() {
        String et_name =  edPhone.getText().toString().trim();
        String et_passWord =   edPsw.getText().toString().trim();
        if (et_name.equals("")) {
            Toast.makeText(LoginActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( et_passWord .equals("")|| et_passWord.length()<6 ||et_passWord.length()>12) {
            Toast.makeText(LoginActivity.this,  "请输入6-12登录密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
               // finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
