package com.huangyu.mdeditor.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.huangyu.library.ui.BaseFragment;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.bean.Article;
import com.huangyu.mdeditor.mvp.presenter.EditPresenter;
import com.huangyu.mdeditor.mvp.view.IEditView;
import com.huangyu.mdeditor.ui.widget.HighLightEditText;
import com.huangyu.mdeditor.utils.SysUtils;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * Created by huangyu on 2017-4-25.
 */
public class MarkdownEditorFragment extends BaseFragment<IEditView, EditPresenter> implements IEditView {

    @Bind(R.id.et_title)
    AppCompatEditText mEtTitle;

    @Bind(R.id.et_content)
    HighLightEditText mEtContent;

    private Article article;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_editor;
    }

    @Override
    protected IEditView initAttachView() {
        return this;
    }

    public static MarkdownEditorFragment getInstance(Bundle bundle) {
        MarkdownEditorFragment markdownEditorFragment = new MarkdownEditorFragment();
        markdownEditorFragment.setArguments(bundle);
        return markdownEditorFragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        article = (Article) bundle.getSerializable("article");

        if (article != null) {
            mEtTitle.setText(article.getTitle());
            mEtContent.setText(article.getContent());
        }

        initRxCallback();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_editor, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_undo:
                return true;
            case R.id.action_redo:
                return true;
            case R.id.action_save:
                save();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void save() {
        if (article == null) {
            mPresenter.save(null, getTitle(), getContent(), getString(R.string.tips_save_successfully), getString(R.string.tips_save_error));
        } else {
            mPresenter.save(article.getId(), getTitle(), getContent(), getString(R.string.tips_save_successfully), getString(R.string.tips_save_error));
        }
    }

    private void initRxCallback() {
        mRxManager.on("getTitle", new Action1<String>() {
            @Override
            public void call(String s) {
                mRxManager.post("refreshTitle", getTitle());
            }
        });
        mRxManager.on("getContent", new Action1<String>() {
            @Override
            public void call(String s) {
                mRxManager.post("refreshContent", getContent());
            }
        });
    }

    @Override
    public void showToast(String content) {
        SysUtils.showToast(getContext(), content);
    }

    @Override
    public String getTitle() {
        return mEtTitle.getText().toString();
    }

    @Override
    public String getContent() {
        return mEtContent.getText().toString();
    }

}
