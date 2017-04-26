package com.huangyu.mdeditor.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.huangyu.library.rx.RxManager;
import com.huangyu.library.ui.BaseActivity;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.mvp.contract.IMainContract;
import com.huangyu.mdeditor.ui.fragment.MarkdownEditorFragment;
import com.huangyu.mdeditor.ui.fragment.MarkdownPreviewFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity<IMainContract.IMainView, IMainContract.AMainPresenter> implements IMainContract.IMainView {

    @Bind(R.id.pager)
    protected ViewPager mViewPager;

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

    private class EditFragmentAdapter extends FragmentPagerAdapter {

        private MarkdownEditorFragment mEditorFragment;
        private MarkdownPreviewFragment mPreviewFragment;

        public EditFragmentAdapter(FragmentManager fm) {
            super(fm);
            mEditorFragment = new MarkdownEditorFragment();
            mPreviewFragment = new MarkdownPreviewFragment();
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return mEditorFragment;
            }
            return mPreviewFragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
