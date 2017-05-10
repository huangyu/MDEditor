package com.huangyu.mdeditor.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.huangyu.library.ui.CommonRecyclerViewAdapter;
import com.huangyu.library.ui.CommonRecyclerViewHolder;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.bean.Article;

/**
 * Created by huangyu on 2017-5-10.
 */
public class ArticleFileAdapter extends CommonRecyclerViewAdapter<Article> {

    public ArticleFileAdapter(Context context) {
        super(context);
    }

    @Override
    public void convert(CommonRecyclerViewHolder holder, Article data, int position) {
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvSize = holder.getView(R.id.tv_size);
        TextView tvTime = holder.getView(R.id.tv_time);

        tvName.setText(data.getName());
        tvSize.setText(data.getSize());
        tvTime.setText(data.getModifyTime());
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_file_list;
    }

}
