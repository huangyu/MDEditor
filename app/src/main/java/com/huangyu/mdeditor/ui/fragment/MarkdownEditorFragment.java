package com.huangyu.mdeditor.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.huangyu.library.app.ActivityManager;
import com.huangyu.library.ui.BaseFragment;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.bean.Article;
import com.huangyu.mdeditor.mvp.presenter.EditPresenter;
import com.huangyu.mdeditor.mvp.view.IEditView;
import com.huangyu.mdeditor.ui.activity.MainActivity;
import com.huangyu.mdeditor.ui.widget.HighLightEditText;
import com.huangyu.mdeditor.ui.widget.PerformEdit;
import com.huangyu.mdeditor.utils.AlertUtils;
import com.huangyu.mdeditor.utils.KeyboardUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by huangyu on 2017-4-25.
 */
public class MarkdownEditorFragment extends BaseFragment<IEditView, EditPresenter> implements IEditView {

    @Bind(R.id.ll_editor)
    LinearLayout llEditor;

    @Bind(R.id.et_title)
    AppCompatEditText mEtTitle;

    @Bind(R.id.et_content)
    HighLightEditText mEtContent;

    private PerformEdit mPeTitle;
    private PerformEdit mPeContent;

    private Article article;

    private boolean isChanged;

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

        mPeTitle = new PerformEdit(mEtTitle) {
            @Override
            protected void onTextChanged(Editable s) {
                isChanged = true;
            }
        };
        mPeContent = new PerformEdit(mEtContent) {
            @Override
            protected void onTextChanged(Editable s) {
                isChanged = true;
            }
        };

        if (article != null) {
            mPeTitle.setDefaultText(article.getTitle());
            mPeContent.setDefaultText(article.getContent());
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
                mPeContent.undo();
                return true;
            case R.id.action_redo:
                mPeContent.redo();
                return true;
            case R.id.action_save:
                save(false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean save(boolean ifExit) {
        if (isTitleEmpty()) {
            AlertUtils.showSnack(llEditor, getString(R.string.tips_title_empty));

            mEtTitle.requestFocus();
            KeyboardUtils.showSoftInput(mEtTitle);
            return false;
        }

        if (article == null) {
            mPresenter.save(ifExit, null, getTitle(), getContent(), getString(R.string.tips_save_successfully), getString(R.string.tips_save_error));
        } else {
            mPresenter.save(ifExit, article.getId(), getTitle(), getContent(), getString(R.string.tips_save_successfully), getString(R.string.tips_save_error));
        }

        isChanged = false;
        return true;
    }

    private boolean isTitleEmpty() {
        return TextUtils.isEmpty(getTitle());
    }

    public boolean canSave() {
        return isChanged;
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
    public void showTips(String content, boolean ifExit) {
        Activity activity = ActivityManager.getInstance().preActivity();
        if (ifExit && activity instanceof MainActivity) {
            AlertUtils.showSnack(ButterKnife.findById(activity, R.id.rl_main), content);
        } else {
            AlertUtils.showSnack(llEditor, content);
        }
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
