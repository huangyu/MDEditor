package com.huangyu.mdeditor.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.huangyu.library.ui.BaseActivity;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.mvp.contract.IMainContract;
import com.huangyu.mdeditor.ui.fragment.MarkdownEditorFragment;
import com.huangyu.mdeditor.ui.fragment.MarkdownPreviewFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity<IMainContract.IMainView, IMainContract.AMainPresenter> implements IMainContract.IMainView {

    @Bind(R.id.pager)
    protected ViewPager mViewPager;

    private MarkdownEditorFragment mEditorFragment = new MarkdownEditorFragment();
    private MarkdownPreviewFragment mPreviewFragment = new MarkdownPreviewFragment();

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
                //刷新渲染数据
                if (position == 1) {
                    mPreviewFragment.refresh(mEditorFragment.getContent());
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private class EditFragmentAdapter extends FragmentPagerAdapter {

        public EditFragmentAdapter(FragmentManager fm) {
            super(fm);
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
