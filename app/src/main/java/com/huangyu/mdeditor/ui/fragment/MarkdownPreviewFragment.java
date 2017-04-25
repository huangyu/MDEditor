package com.huangyu.mdeditor.ui.fragment;

import android.os.Bundle;

import com.huangyu.library.mvp.IBaseView;
import com.huangyu.library.ui.BaseFragment;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.ui.widget.MarkdownPreviewView;

import butterknife.Bind;

/**
 * Created by huangyu on 2017-4-25.
 */
public class MarkdownPreviewFragment extends BaseFragment {

    @Bind(R.id.markdown_preview)
    protected MarkdownPreviewView mMarkdownPreviewView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_preview;
    }

    @Override
    protected IBaseView initAttachView() {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    public void refresh(String content) {
        mMarkdownPreviewView.parseMarkdown(content, true);
    }

}
