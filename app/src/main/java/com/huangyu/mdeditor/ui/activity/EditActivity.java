package com.huangyu.mdeditor.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.huangyu.library.rx.RxManager;
import com.huangyu.library.ui.BaseActivity;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.bean.Mode;
import com.huangyu.mdeditor.mvp.contract.IEditContract;
import com.huangyu.mdeditor.ui.fragment.MarkdownEditorFragment;
import com.huangyu.mdeditor.ui.fragment.MarkdownPreviewFragment;

import butterknife.Bind;

public class EditActivity extends BaseActivity<IEditContract.IEditView, IEditContract.AEditPresenter> implements IEditContract.IEditView {

    @Bind(R.id.pager)
    ViewPager mViewPager;

    private MarkdownEditorFragment mEditorFragment;
    private MarkdownPreviewFragment mPreviewFragment;

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

        Bundle bundle = getIntent().getExtras();
        String mode = bundle.getString("mode");
        if (!TextUtils.isEmpty(mode)) {
            switch (mode) {
                case Mode.MODE_EDIT:
                    mViewPager.setCurrentItem(0);
                    break;
                case Mode.MODE_PREVIEW:
                    mViewPager.setCurrentItem(1);
                    break;
                default:
                    break;
            }
        }

        mEditorFragment = MarkdownEditorFragment.getInstance(bundle);
        mPreviewFragment = MarkdownPreviewFragment.getInstance(bundle);

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

    public class EditFragmentAdapter extends FragmentPagerAdapter {

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
