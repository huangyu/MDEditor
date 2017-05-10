package com.huangyu.mdeditor.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.huangyu.library.rx.RxManager;
import com.huangyu.library.ui.BaseActivity;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.mvp.contract.IEditContract;
import com.huangyu.mdeditor.ui.adapter.EditFragmentAdapter;

import butterknife.Bind;

public class EditActivity extends BaseActivity<IEditContract.IEditView, IEditContract.AEditPresenter> implements IEditContract.IEditView {

    @Bind(R.id.pager)
    ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit;
    }

    @Override
    protected IEditContract.IEditView initAttachView() {
        return this;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mViewPager.setAdapter(new EditFragmentAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    RxManager.getInstance().post("getContent", "");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

}
