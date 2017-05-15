package com.huangyu.mdeditor.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.huangyu.library.ui.CommonRecyclerViewAdapter;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.bean.Article;
import com.huangyu.mdeditor.bean.Mode;
import com.huangyu.mdeditor.mvp.presenter.MainPresenter;
import com.huangyu.mdeditor.mvp.view.IMainView;
import com.huangyu.mdeditor.ui.adapter.ArticleAdapter;
import com.huangyu.mdeditor.utils.SysUtils;

import butterknife.Bind;

/**
 * Created by huangyu on 2017-5-10.
 */
public class MainActivity extends BaseToolbarActivity<IMainView, MainPresenter> implements IMainView {

    @Bind(R.id.fab)
    FloatingActionButton mFab;

    @Bind(R.id.recycler_view)
    RecyclerView mRvArticle;

    private ArticleAdapter adapter;

    private long currentTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected IMainView initAttachView() {
        return this;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("mode", Mode.MODE_EDIT);
                startActivity(EditActivity.class, bundle);
            }
        });

        adapter = new ArticleAdapter(this);
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
        adapter.setOnItemLongClick(new CommonRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Article article = adapter.getItem(position);
                mPresenter.deleteArticle(article.getId(), position);
            }
        });
        mRvArticle.setLayoutManager(new LinearLayoutManager(this));
        mRvArticle.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clearData();
        adapter.setData(mPresenter.queryAllArticles());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isDoubleCheck()) {
            finish();
        } else {
            currentTime = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出软件", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isDoubleCheck() {
        return Math.abs(currentTime - System.currentTimeMillis()) < 2000;
    }

    @Override
    protected boolean hasBackButton() {
        return false;
    }

    @Override
    protected String getToolbarTitle() {
        return "MDEditor";
    }

    @Override
    public void showToast(String content) {
        SysUtils.showToast(this, content);
    }

    @Override
    public void adapterRemove(int position) {
        adapter.removeItem(position);
        adapter.notifyItemRemoved(position);
    }
}
