package com.example.searchphone;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.searchphone.model.Phone;
import com.example.searchphone.mvp.MvpMainView;
import com.example.searchphone.mvp.impl.MainPresenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MvpMainView {
    EditText input_phone;
    Button btn_search;
    TextView result_phone;
    TextView result_province;
    TextView result_type;
    TextView result_carrier;

    MainPresenter mainPresenter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_phone = (EditText) findViewById(R.id.input_phone);
        btn_search = (Button) findViewById(R.id.btn_search);
        result_phone = (TextView) findViewById(R.id.result_phone);
        result_province = (TextView) findViewById(R.id.result_province);
        result_carrier = (TextView) findViewById(R.id.result_carrier);
        result_type = (TextView) findViewById(R.id.result_type);

        btn_search.setOnClickListener(this);

        mainPresenter = new MainPresenter(this);
        mainPresenter.attach(this);
    }

    @Override
    public void onClick(View view) {
        mainPresenter.sarchPhoneInfo(input_phone.getText().toString());
    }

    // mvpMainView接口的方法
    @Override
    public void showLoading() {
        if (progressDialog==null){
            progressDialog = ProgressDialog.show(this,"","正在加载...",true,false);
        }else if (progressDialog.isShowing()){
            progressDialog.setTitle("");
            progressDialog.setMessage("正在加载...");
        }
        progressDialog.show();
    }

    @Override
    public void hidenLoading() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void updatView() {
        Phone phone = mainPresenter.getPhoneInfo();
        result_phone.setText("手机号码："+phone.getTelString());
        result_province.setText("省份："+phone.getProvince());
        result_type.setText("运营商："+phone.getCatName());
        result_carrier.setText("归属运营商："+phone.getCarrier());
    }
}
