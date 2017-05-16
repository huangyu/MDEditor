package com.huangyu.mdeditor.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.huangyu.library.ui.CommonRecyclerViewAdapter;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.bean.Article;
import com.huangyu.mdeditor.bean.Mode;
import com.huangyu.mdeditor.mvp.presenter.MainPresenter;
import com.huangyu.mdeditor.mvp.view.IMainView;
import com.huangyu.mdeditor.ui.adapter.ArticleAdapter;
import com.huangyu.mdeditor.utils.AlertUtils;

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
    LinearLayout mLlEmpty;

    @Bind(R.id.rl_main)
    RelativeLayout mRlMain;

    @Bind(R.id.srl_main)
    SwipeRefreshLayout mSrlMain;

    private AlertDialog mAlertDialog;
    private ArticleAdapter mAdapter;

    private SearchView mSearchView;
    private boolean isSearchViewShow;

    private long mCurrentTime;

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

        mSrlMain.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSrlMain.setOnRefreshListener(this);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("mode", Mode.MODE_EDIT);
                startActivity(EditActivity.class, bundle);
            }
        });

        mAdapter = new ArticleAdapter(this);
        mAdapter.setOnItemClick(new CommonRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Article article = mAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("article", article);
                bundle.putString("mode", Mode.MODE_PREVIEW);
                startActivity(EditActivity.class, bundle);
            }
        });
        mAdapter.setOnItemLongClick(new CommonRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, final int position) {
                mAlertDialog = AlertUtils.showAlert(MainActivity.this, getString(R.string.tips_delete_article), getString(R.string.act_delete), getString(R.string.act_not_delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Article article = mAdapter.getItem(position);
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
        mRvArticle.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.onRefresh();
    }

    @Override
    public void onRefresh() {
        refreshData(null);
        mSrlMain.setRefreshing(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        initSearchView(menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initSearchView(Menu menu) {
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                mSearchView.setIconified(false);
                return true;
            }

            public boolean onQueryTextChange(String text) {
                refreshData(text);
                return false;
            }
        });
        mSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isSearchViewShow = true;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mSearchView != null && mSearchView.isShown() && isSearchViewShow) {
            mSearchView.onActionViewCollapsed();
            mSearchView.setQuery("", false);
            supportInvalidateOptionsMenu();
            isSearchViewShow = false;
            return;
        }

        if (isDoubleCheck()) {
            finish();
        } else {
            mCurrentTime = System.currentTimeMillis();
            AlertUtils.showSnack(mRlMain, getString(R.string.tips_leave));
        }
    }

    private void refreshData(String search) {
        mAdapter.clearData();
        List<Article> articleList;
        if (TextUtils.isEmpty(search)) {
            articleList = mPresenter.queryAllArticles();
        } else {
            articleList = mPresenter.queryArticlesBySearch(search);
        }
        if (articleList == null || articleList.isEmpty()) {
            mLlEmpty.setVisibility(View.VISIBLE);
        } else {
            mLlEmpty.setVisibility(View.GONE);

            mAdapter.setData(articleList);
            mAdapter.notifyDataSetChanged();
        }
    }

    private boolean isDoubleCheck() {
        return Math.abs(mCurrentTime - System.currentTimeMillis()) < 1000;
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
        AlertUtils.showSnack(mRlMain, content);
    }

    @Override
    public void removeData(int position) {
        if (position == mAdapter.getItemCount() - 1) {
            mAdapter.removeItem(position);
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.removeItem(position);
            mAdapter.notifyItemRemoved(position);
        }

        if (mAdapter.getItemCount() == 0) {
            mLlEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
        super.onDestroy();
    }

}
