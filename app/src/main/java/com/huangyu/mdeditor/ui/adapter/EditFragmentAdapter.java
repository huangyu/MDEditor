package com.huangyu.mdeditor.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.huangyu.mdeditor.ui.fragment.MarkdownEditorFragment;
import com.huangyu.mdeditor.ui.fragment.MarkdownPreviewFragment;

/**
 * Created by huangyu on 2017-5-10.
 */
public class EditFragmentAdapter extends FragmentPagerAdapter {

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
