package com.huangyu.mdeditor.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

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

    @Bind(R.id.tv_title)
    AppCompatTextView tvTitle;

    @Bind(R.id.pv_content)
    MarkdownPreviewView mMarkdownPreviewView;

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
        RxManager.getInstance().on("refreshTitle", new Action1<String>() {
            @Override
            public void call(String s) {
                refreshTitle(s);
            }
        });
        RxManager.getInstance().on("refreshContent", new Action1<String>() {
            @Override
            public void call(String s) {
                refreshContent(s);
            }
        });
    }

    public void refreshTitle(String title) {
        tvTitle.setText(title);
    }

    public void refreshContent(String content) {
        mMarkdownPreviewView.parseMarkdown(content, true);
    }

}
