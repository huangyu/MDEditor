package com.huangyu.mdeditor.mvp.presenter;

import com.huangyu.library.mvp.BasePresenter;
import com.huangyu.mdeditor.bean.Article;
import com.huangyu.mdeditor.mvp.model.EditModel;
import com.huangyu.mdeditor.mvp.view.IMainView;

import java.util.List;

import io.realm.Realm;

/**
 * Created by huangyu on 2017-5-10.
 */
public class MainPresenter extends BasePresenter<IMainView> {

    private EditModel mEditModel;

    @Override
    public void create() {
        mEditModel = new EditModel();
    }

    public List<Article> queryAllArticles() {
        return mEditModel.queryAllArticles();
    }

    public List<Article> queryArticlesBySearch(String search) {
        return mEditModel.queryArticlesBySearch(search);
    }

    public void deleteArticle(String id, final int position) {
        mEditModel.deleteArticle(id, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                mView.adapterRemove(position);
                mView.showToast("删除成功！");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                mView.showToast("删除失败！" + error.toString());
            }
        });
    }

}
