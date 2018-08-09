package com.example.searchphone.mvp;

/**
 * Created by 许东 on 2018/8/9.
 */

public interface MvpMainView extends MvpLoadingView{
    void showToast(String msg);  //显示信息
    void updatView();           //更新view

}
