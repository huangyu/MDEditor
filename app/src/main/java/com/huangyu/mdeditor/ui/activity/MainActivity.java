package com.huangyu.mdeditor.ui.activity;

import android.os.Bundle;

import com.huangyu.library.ui.BaseActivity;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.mvp.contract.IMainContract;

public class MainActivity extends BaseActivity<IMainContract.IMainView, IMainContract.AMainPresenter> implements IMainContract.IMainView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected IMainContract.IMainView initAttachView() {
        return this;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

}
