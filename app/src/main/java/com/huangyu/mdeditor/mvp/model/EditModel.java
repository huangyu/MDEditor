package com.huangyu.mdeditor.mvp.model;

import com.huangyu.mdeditor.bean.Article;
import com.huangyu.mdeditor.utils.SysUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

/**
 * Created by huangyu on 2017-4-11.
 */
public class EditModel {

    List<RealmAsyncTask> taskList;

    public EditModel() {
        taskList = Collections.synchronizedList(new ArrayList<RealmAsyncTask>());
    }

    public void destroy() {
        for (RealmAsyncTask task : taskList) {
            if (!task.isCancelled()) {
                task.cancel();
            }
        }
        taskList.clear();
        taskList = null;
    }

    public void saveArticle(final String id, final String title, final String content, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        RealmAsyncTask task = Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                insertOrUpdateArticle(realm, id, title, content);
            }
        }, onSuccess, onError);
        taskList.add(task);
    }

    public void deleteArticle(final String id, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        RealmAsyncTask task = Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                deleteArticle(id);
            }
        }, onSuccess, onError);
        taskList.add(task);
    }

    public List<Article> queryAllArticles() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Article> articleResults = realm.where(Article.class).findAll();
        return realm.copyFromRealm(articleResults);
    }

    public List<Article> queryArticlesBySearch(String search) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Article> articleResults = realm.where(Article.class).like("title", search).like("content", search).findAll();
        return realm.copyFromRealm(articleResults);
    }

    public Article queryArticleById(String id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Article.class).equalTo("id", id).findFirst();
    }

    private void insertOrUpdateArticle(Realm realm, String id, String title, String content) {
        Article article = null;

        if (id != null) {
            article = queryArticleById(id);
        }

        if (article == null) {
            article = new Article();
            article.setId(UUID.randomUUID().toString());
        }

        article.setTitle(title);
        article.setContent(content);
        article.setModifyTime(SysUtils.dateToString(new Date()));

        realm.insertOrUpdate(article);
    }

    private void deleteArticle(String id) {
        final Article article = queryArticleById(id);

        if (article == null) {
            return;
        }

        article.deleteFromRealm();
    }

}
