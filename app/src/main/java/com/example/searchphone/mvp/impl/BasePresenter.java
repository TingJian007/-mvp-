package com.example.searchphone.mvp.impl;

import android.content.Context;

/**
 * Created by 许东 on 2018/8/9.
 */

public class BasePresenter {
    Context mContext;
    public void attach(Context context){
        mContext = context;
    }
    public void onPause(){}
    public void onResume(){}
    public void onDestroy(){
        //释放Context，不然Aciyity释放的时候，Presenter还在使用，引起内存泄漏
        mContext=null;
    }
}
