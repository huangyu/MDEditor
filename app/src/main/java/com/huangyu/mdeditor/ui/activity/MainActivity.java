package com.huangyu.mdeditor.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.huangyu.library.ui.CommonRecyclerViewAdapter;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.bean.Article;
import com.huangyu.mdeditor.bean.Mode;
import com.huangyu.mdeditor.mvp.presenter.MainPresenter;
import com.huangyu.mdeditor.mvp.view.IMainView;
import com.huangyu.mdeditor.ui.adapter.ArticleAdapter;
import com.huangyu.mdeditor.utils.SysUtils;

import java.util.List;

import butterknife.Bind;

/**
 * Created by huangyu on 2017-5-10.
 */
public class MainActivity extends BaseToolbarActivity<IMainView, MainPresenter> implements IMainView, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.fab)
    FloatingActionButton mFab;

    @Bind(R.id.recycler_view)
    RecyclerView mRvArticle;

    @Bind(R.id.ll_empty)
    LinearLayout llEmpty;

    @Bind(R.id.rl_main)
    RelativeLayout rlMain;

    @Bind(R.id.srl_main)
    SwipeRefreshLayout srlMain;

    private AlertDialog alertDialog;
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

        srlMain.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        srlMain.setOnRefreshListener(this);

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
            public void onItemLongClick(View view, final int position) {
                alertDialog = SysUtils.showAlert(MainActivity.this, getString(R.string.tips_delete_article), getString(R.string.act_delete), getString(R.string.act_not_delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Article article = adapter.getItem(position);
                        mPresenter.deleteArticle(article.getId(), position, getString(R.string.tips_delete_successfully), getString(R.string.tips_delete_error));
                        dialog.dismiss();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

            }
        });
        mRvArticle.setLayoutManager(new LinearLayoutManager(this));
        mRvArticle.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.onRefresh();
    }

    @Override
    public void onRefresh() {
        refreshData();
        srlMain.setRefreshing(false);
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
            Toast.makeText(this, getString(R.string.tips_leave), Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshData() {
        adapter.clearData();
        List<Article> articleList = mPresenter.queryAllArticles();
        if (articleList.isEmpty()) {
            llEmpty.setVisibility(View.VISIBLE);
        } else {
            llEmpty.setVisibility(View.GONE);

            adapter.setData(articleList);
            adapter.notifyDataSetChanged();
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
    public void showTips(String content) {
        SysUtils.showSnack(rlMain, content);
    }

    @Override
    public void adapterRemove(int position) {
        if (position == adapter.getItemCount() - 1) {
            adapter.removeItem(position);
            adapter.notifyDataSetChanged();
        } else {
            adapter.removeItem(position);
            adapter.notifyItemRemoved(position);
        }

        if (adapter.getItemCount() == 0) {
            llEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        super.onDestroy();
    }

}
