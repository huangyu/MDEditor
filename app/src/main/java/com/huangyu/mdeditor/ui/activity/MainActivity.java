package com.huangyu.mdeditor.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.huangyu.library.ui.BaseActivity;
import com.huangyu.library.ui.CommonRecyclerViewAdapter;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.bean.Article;
import com.huangyu.mdeditor.bean.Mode;
import com.huangyu.mdeditor.mvp.contract.IMainContract;
import com.huangyu.mdeditor.mvp.presenter.MainPresenter;
import com.huangyu.mdeditor.ui.adapter.ArticleFileAdapter;

import butterknife.Bind;

/**
 * Created by huangyu on 2017-5-10.
 */
public class MainActivity extends BaseActivity<IMainContract.IMainView, MainPresenter> implements IMainContract.IMainView {

    @Bind(R.id.toolbar)
    Toolbar mTb;

    @Bind(R.id.fab)
    FloatingActionButton mFab;

    @Bind(R.id.recyclerview)
    RecyclerView mRvArticle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected IMainContract.IMainView initAttachView() {
        return this;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setSupportActionBar(mTb);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("mode", Mode.MODE_EDIT);
                startActivity(EditActivity.class, bundle);
            }
        });

        final ArticleFileAdapter adapter = new ArticleFileAdapter(this);
        mRvArticle.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClick(new CommonRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Article article = adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("article", article);
                bundle.putString("mode", Mode.MODE_PREVIEW);
                startActivity(EditActivity.class, bundle);
            }
        });
        mRvArticle.setAdapter(adapter);
    }

}
