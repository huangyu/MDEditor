package com.huangyu.mdeditor.mvp.view;

import com.huangyu.library.mvp.IBaseView;

/**
 * Created by huangyu on 2017/5/15.
 */

public interface IMainView extends IBaseView {
    void showTips(String content);
    void removeData(int position);
}
