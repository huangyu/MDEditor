package com.huangyu.mdeditor.mvp.contract;

import com.huangyu.library.mvp.BasePresenter;
import com.huangyu.library.mvp.IBaseModel;
import com.huangyu.library.mvp.IBaseView;

/**
 * Created by huangyu on 2017-4-11.
 */
public interface IMainContract {

    interface IMainView extends IBaseView {
    }

    interface IMainModel extends IBaseModel {
    }

    abstract class AMainPresenter extends BasePresenter<IMainView> {
    }

}
