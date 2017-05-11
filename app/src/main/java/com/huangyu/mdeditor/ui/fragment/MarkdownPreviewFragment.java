package com.huangyu.mdeditor.ui.fragment;

import android.os.Bundle;

import com.huangyu.library.mvp.IBaseView;
import com.huangyu.library.rx.RxManager;
import com.huangyu.library.ui.BaseFragment;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.ui.widget.MarkdownPreviewView;

import butterknife.Bind;
import rx.functions.Action1;

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

    public static MarkdownPreviewFragment getInstance(Bundle bundle) {
        MarkdownPreviewFragment markdownPreviewFragment = new MarkdownPreviewFragment();
        markdownPreviewFragment.setArguments(bundle);
        return markdownPreviewFragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        RxManager.getInstance().on("refresh", new Action1<String>() {
            @Override
            public void call(String s) {
                refresh(s);
            }
        });
    }

    public void refresh(String content) {
        mMarkdownPreviewView.parseMarkdown(content, true);
    }

}
