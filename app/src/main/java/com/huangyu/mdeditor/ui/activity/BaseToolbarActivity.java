package com.huangyu.mdeditor.ui.activity;

import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;

import com.huangyu.library.mvp.BasePresenter;
import com.huangyu.library.mvp.IBaseView;
import com.huangyu.library.ui.BaseActivity;
import com.huangyu.mdeditor.R;

import java.lang.reflect.Method;

import butterknife.Bind;

/**
 * Created by huangyu on 2017/5/12.
 */

public abstract class BaseToolbarActivity<V extends IBaseView, P extends BasePresenter<V>> extends BaseActivity<V, P> {

    @Bind(R.id.appbar)
    AppBarLayout mAppBar;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    protected boolean isHiddenAppBar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setOverflowIconVisible(menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 显示菜单图标
     *
     * @param menu menu
     */
    @SuppressWarnings("unchecked")
    private void setOverflowIconVisible(Menu menu) {
        try {
            Class clazz = Class.forName("android.support.v7.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);
            m.invoke(menu, true);
        } catch (Exception e) {
            Log.d("OverflowIconVisible", e.getMessage());
        }
    }

    /**
     * 切换appBarLayout的显隐
     */
    protected void hideOrShowToolbar() {
        if (mAppBar == null) {
            return;
        }
        mAppBar.animate()
                .translationY(isHiddenAppBar ? 0 : -mAppBar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        isHiddenAppBar = !isHiddenAppBar;
    }

    /**
     * 设置appBar的透明度
     * Sets app bar alpha.
     *
     * @param alpha the alpha 0-1.0
     */
    protected void setAppBarAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (mAppBar != null) {
            mAppBar.setAlpha(alpha);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mToolbar.setTitle(getToolbarTitle());
        setSupportActionBar(mToolbar);
        if (hasBackButton()) {//如果需要返回按钮
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }

    /**
     * 是否有左上角返回按钮
     *
     * @return 返回true则表示需要左上角返回按钮
     */
    protected abstract boolean hasBackButton();

    /**
     * 返回Toolbar标题
     *
     * @return 获取Toolbar标题
     */
    protected abstract String getToolbarTitle();

}
