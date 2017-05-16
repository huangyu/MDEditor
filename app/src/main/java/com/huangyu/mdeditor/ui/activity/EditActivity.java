package com.huangyu.mdeditor.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.MenuItem;

import com.huangyu.library.mvp.IBaseView;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.bean.Mode;
import com.huangyu.mdeditor.ui.fragment.MarkdownEditorFragment;
import com.huangyu.mdeditor.ui.fragment.MarkdownPreviewFragment;
import com.huangyu.mdeditor.utils.SysUtils;

import butterknife.Bind;

/**
 * Created by huangyu on 2017/5/12.
 */
public class EditActivity extends BaseToolbarActivity {

    @Bind(R.id.pager)
    ViewPager mViewPager;

    private MarkdownEditorFragment mEditorFragment;
    private MarkdownPreviewFragment mPreviewFragment;

    private AlertDialog alertDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit;
    }

    @Override
    protected IBaseView initAttachView() {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
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
                    mRxManager.post("getTitle", "");
                    mRxManager.post("getContent", "");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setOffscreenPageLimit(2);
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                alertDialog = SysUtils.showAlert(this, getString(R.string.tips_save_before_exit), getString(R.string.act_save), getString(R.string.act_not_save), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mEditorFragment.save();
                        onBackPressed();
                        dialog.dismiss();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                        dialog.dismiss();
                    }
                });
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected String getToolbarTitle() {
        return "MDEditor";
    }

    private class EditFragmentAdapter extends FragmentPagerAdapter {

        private EditFragmentAdapter(FragmentManager fm) {
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

    @Override
    protected void onDestroy() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        super.onDestroy();
    }

}
