package com.example.searchphone.mvp.impl;

import com.example.searchphone.business.HttpUntil;
import com.example.searchphone.model.Phone;
import com.example.searchphone.mvp.MvpMainView;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 许东 on 2018/8/9.
 */

public class MainPresenter extends BasePresenter{     //继承后只需要处理mainactivity里面的逻辑

    String mUrl = "http://tcc.taobao.com/cc/json/mobile_tel_segment.htm";
    MvpMainView mvpMainView;
    Phone mPhone;

    public MainPresenter(MvpMainView mainView){     //实现构造函数，传入MvpMainView函数的类
        mvpMainView = mainView;
    }

    public Phone getPhoneInfo(){
        return mPhone;
    }

    //获取数据
    public void sarchPhoneInfo(String phone){
        if (phone.length() != 11){
            mvpMainView.showToast("请输入正确的手机号");
        }
        mvpMainView.showLoading();
        //http请求的处理逻辑
        sendHttp(phone);
    }
    private void sendHttp(String phone){
        Map<String,String> map = new HashMap<String, String>();
        map.put("tel",phone);
        HttpUntil httpUntil = new HttpUntil(new HttpUntil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                String json = object.toString();
                int index = json.indexOf("{");
                json = json.substring(index,json.length());

                //JSONObject
               mPhone = parseModelWithOrgJson(json);

                mvpMainView.hidenLoading();
                mvpMainView.updatView();
            }

            @Override
            public void onFail(String error) {
                mvpMainView.showToast(error);
                mvpMainView.hidenLoading();
            }
        });
        httpUntil.sendGetHttp(mUrl,map);
    }


    private Phone parseModelWithOrgJson(String json){
        Phone phone = new Phone();
        try {
            org.json.JSONObject jsonObject = new org.json.JSONObject(json);
            String value = jsonObject.getString("telString");
            phone.setTelString(value);

            value = jsonObject.getString("province");
            phone.setProvince(value);

            value = jsonObject.getString("catName");
            phone.setCatName(value);

            value = jsonObject.getString("carrier");
            phone.setCarrier(value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return phone;
    }
}
