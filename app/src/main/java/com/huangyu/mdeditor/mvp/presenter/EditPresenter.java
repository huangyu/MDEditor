package com.huangyu.mdeditor.mvp.presenter;

import com.huangyu.mdeditor.mvp.contract.IEditContract;
import com.huangyu.mdeditor.mvp.model.EditModel;

/**
 * Created by huangyu on 2017-4-11.
 */
public class EditPresenter extends IEditContract.AEditPresenter {

    private EditModel mEditModel;

    @Override
    public void create() {
        mEditModel = new EditModel();
    }

}
