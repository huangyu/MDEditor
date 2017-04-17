package com.huangyu.mdeditor.mvp.presenter;

import com.huangyu.mdeditor.mvp.contract.IMainContract;
import com.huangyu.mdeditor.mvp.model.MainModel;

/**
 * Created by huangyu on 2017-4-11.
 */
public class NewsListPresenter extends IMainContract.AMainPresenter {

    private MainModel mMainModel;

    @Override
    public void create() {
        mMainModel = new MainModel();
    }

}
