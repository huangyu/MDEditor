package com.huangyu.mdeditor.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.huangyu.library.ui.CommonRecyclerViewAdapter;
import com.huangyu.library.ui.CommonRecyclerViewHolder;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.bean.Article;
import com.huangyu.mdeditor.utils.SysUtils;

/**
 * Created by huangyu on 2017-5-10.
 */
public class ArticleAdapter extends CommonRecyclerViewAdapter<Article> {

    public ArticleAdapter(Context context) {
        super(context);
    }

    @Override
    public void convert(CommonRecyclerViewHolder holder, Article data, int position) {
        TextView tvName = holder.getView(R.id.tv_title);
        TextView tvSize = holder.getView(R.id.tv_content);
        TextView tvTime = holder.getView(R.id.tv_time);
        View divider = holder.getView(R.id.divider);

        tvName.setText(data.getTitle());
        tvSize.setText(data.getContent());
        tvTime.setText(SysUtils.getFormatDate(SysUtils.stringToDate(data.getModifyTime())));
        if (position == getItemCount() - 1) {
            divider.setVisibility(View.GONE);
        } else {
            divider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_file_list;
    }

}
