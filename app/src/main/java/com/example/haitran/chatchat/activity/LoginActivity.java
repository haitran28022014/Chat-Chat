package com.example.haitran.chatchat.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.haitran.chatchat.App;
import com.example.haitran.chatchat.R;
import com.example.haitran.chatchat.manager.StringManager;
import com.github.nkzawa.emitter.Emitter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hai Tran on 9/27/2016.
 */

public class LoginActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.txt_title_1)
    TextView txtTitle1;

    @Bind(R.id.txt_title_2)
    TextView txtTitle2;

    @Bind(R.id.edt_phone)
    EditText edtPhone;

    @Bind(R.id.edt_pass)
    EditText edtPass;

    @Bind(R.id.btn_login)
    Button btnLogin;

    @Bind(R.id.txt_register)
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initializeComponent();
        initializeListener();
        connectLoginToSocket();
    }


    private void initializeComponent() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), StringManager.FONT);
        txtTitle1.setTypeface(typeface);
        txtTitle1.setTextSize(80);
        txtTitle2.setTypeface(typeface);
        txtTitle2.setTextSize(80);
    }

    private void connectLoginToSocket() {
        App.getSocket().on(StringManager.INFO_LOGIN, onInLogin);
    }

    private void initializeListener() {
        btnLogin.setOnClickListener(this);
        txtRegister.setOnClickListener(this);

    }

    /**
     * Sự kiện khi ấn nút đăng nhập người dũng sẽ gửi phone và pass lên server để server so sánh vói cơ sở dữ liệu
     * câu lệnh gửi là:  App.getSocket().emit(StringManager.INFO_LOGIN, phone, pass);
     * StringManager.INFO_LOGIN=LOGIN là cái key để nhận biết trên server với key này thì server sẽ hiểu là của người nào gửi
     * và sẽ gửi lại cho client thông qua cái key đó
     *
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                String phone = edtPhone.getText().toString();
                String pass = edtPass.getText().toString();
                if (phone.isEmpty()) {
                    edtPhone.setError(StringManager.EMPTY);
                } else if (pass.isEmpty()) {
                    edtPass.setError(StringManager.EMPTY);
                } else {
                    App.getSocket().emit(StringManager.INFO_LOGIN, phone, pass);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Đây là đối tượng giúp lắng nghe server khi server emit gửi về cho client dữ liệu:
     * Ở trên server gửi về thông tin người dùng vừa đăng nhập nếu có tài khoản là true không có tài khoản là false
     * Từ đó Em sẽ phân tích nếu true là có tài khoản thì cho đăng nhập còn false thì không cho đăng nhâp
     */

    private Emitter.Listener onInLogin = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            LoginActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String data = args[0].toString();
                    if (data.equals(StringManager.TRUE)) {
                        Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                        intent1.putExtra(StringManager.PHONE, edtPhone.getText().toString());
                        LoginActivity.this.startActivity(intent1);
                    } else {
                        edtPhone.setError(StringManager.ERROR);
                        edtPhone.requestFocus();
                    }
                }
            });
        }
    };


    @Override
    protected void onDestroy() {
        App.getSocket().off(StringManager.INFO_LOGIN, onInLogin);
        super.onDestroy();
    }
}
