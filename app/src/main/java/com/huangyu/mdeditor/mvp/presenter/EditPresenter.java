package com.huangyu.mdeditor.mvp.presenter;

import com.huangyu.library.mvp.BasePresenter;
import com.huangyu.mdeditor.mvp.model.EditModel;
import com.huangyu.mdeditor.mvp.view.IEditView;

import io.realm.Realm;

/**
 * Created by huangyu on 2017-4-11.
 */
public class EditPresenter extends BasePresenter<IEditView> {

    private EditModel mEditModel;

    @Override
    public void create() {
        mEditModel = new EditModel();
    }

    public void save(String id, String title, String content) {
        mEditModel.saveArticle(id, title, content, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                mView.showToast("保存成功！");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                mView.showToast("保存失败！" + error.toString());
            }
        });
    }

    @Override
    public void destroy() {
        mEditModel.destroy();
        super.destroy();
    }

}
