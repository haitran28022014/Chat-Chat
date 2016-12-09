package com.example.haitran.chatchat.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haitran.chatchat.App;
import com.example.haitran.chatchat.R;
import com.example.haitran.chatchat.manager.StringManager;
import com.github.nkzawa.emitter.Emitter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hai Tran on 9/27/2016.
 */

public class RegisterActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.txt_title)
    TextView txtTitle;

    @Bind(R.id.edt_phone)
    EditText edtPhone;

    @Bind(R.id.edt_username)
    EditText edtUser;

    @Bind(R.id.edt_pass)
    EditText edtPass;

    @Bind(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initializeComponent();
        initializeListener();
        connectRegisterToSocket();
    }

    private void initializeComponent() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), StringManager.FONT);
        txtTitle.setTypeface(typeface);
        txtTitle.setTextSize(80);
    }


    private void initializeListener() {
        btnRegister.setOnClickListener(this);
    }

    private void connectRegisterToSocket() {
        App.getSocket().on(StringManager.INFO_REGISTER, onInRegister);
    }


    /**
     * Tương tự với Class LoginActivity thì đây là sự kiện khi ấn nút đăng ký em sẽ xử lý gửi lên phone,user,pass của người dùng
     * Câu lệnh gửi lên server:   App.getSocket().emit(StringManager.INFO_REGISTER, phone, user, pass);
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                String phone = edtPhone.getText().toString();
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                if (phone.isEmpty()) {
                    edtPhone.setError(StringManager.ERROR);
                } else if (user.isEmpty()) {
                    edtUser.setError(StringManager.ERROR);
                } else if (pass.isEmpty()) {
                    edtPass.setError(StringManager.ERROR);
                } else {
                    App.getSocket().emit(StringManager.INFO_REGISTER, phone, user, pass);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Tương tự với LoginActivity thì đây sẽ là nơi xử lý kết quả trả về từ server
     * câu lệnh lắng nghe là App.getSocket().on(StringManager.INFO_REGISTER, onInRegister);
     */
    private Emitter.Listener onInRegister = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            RegisterActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String data=args[0].toString();
                    if (data.equals(StringManager.TRUE)){
                        Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                    }else {
                        edtPhone.requestFocus();
                        edtPhone.setError("Phone to exist");
                    }
                }
            });
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getSocket().off(StringManager.INFO_REGISTER,onInRegister);
    }
}
