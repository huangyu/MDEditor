package com.huangyu.mdeditor.mvp.contract;

import com.huangyu.library.mvp.BasePresenter;
import com.huangyu.library.mvp.IBaseModel;
import com.huangyu.library.mvp.IBaseView;

/**
 * Created by huangyu on 2017-4-11.
 */
public interface IEditContract {

    interface IEditView extends IBaseView {
    }

    interface IEditModel extends IBaseModel {
    }

    abstract class AEditPresenter extends BasePresenter<IEditView> {
    }

}
