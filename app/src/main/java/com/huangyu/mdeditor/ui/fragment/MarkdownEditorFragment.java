package com.huangyu.mdeditor.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;

import com.huangyu.library.mvp.IBaseView;
import com.huangyu.library.rx.RxManager;
import com.huangyu.library.ui.BaseFragment;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.bean.Article;
import com.huangyu.mdeditor.ui.widget.HighLightEditText;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * Created by huangyu on 2017-4-25.
 */
public class MarkdownEditorFragment extends BaseFragment {

    @Bind(R.id.et_title)
    AppCompatEditText mEtTitle;

    @Bind(R.id.et_content)
    HighLightEditText mEtContent;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_editor;
    }

    @Override
    protected IBaseView initAttachView() {
        return null;
    }

    public static MarkdownEditorFragment getInstance(Bundle bundle) {
        MarkdownEditorFragment markdownEditorFragment = new MarkdownEditorFragment();
        markdownEditorFragment.setArguments(bundle);
        return markdownEditorFragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        Article article = (Article) bundle.getSerializable("article");

        if (article != null) {
            mEtTitle.setText(article.getTitle());
            mEtContent.setText(article.getContent());
        }

        initRxCallback();
    }

    private void initRxCallback() {
        RxManager.getInstance().on("getTitle", new Action1<Object>() {
            @Override
            public void call(Object s) {
                RxManager.getInstance().post("refreshTitle", mEtTitle.getText().toString());
            }
        });
        RxManager.getInstance().on("getContent", new Action1<Object>() {
            @Override
            public void call(Object s) {
                RxManager.getInstance().post("refreshContent", mEtContent.getText().toString());
            }
        });
    }

}
