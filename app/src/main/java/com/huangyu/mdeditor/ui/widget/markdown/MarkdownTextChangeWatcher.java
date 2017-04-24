package com.huangyu.mdeditor.ui.widget.markdown;

import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.CharacterStyle;

import com.huangyu.mdeditor.bean.MarkdownBean;
import com.huangyu.mdeditor.bean.Range;
import com.huangyu.mdeditor.bean.Type;
import com.huangyu.mdeditor.utils.MarkdownManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyu on 2017-4-24.
 */
public class MarkdownTextChangeWatcher implements TextWatcher {

    /**
     * use to remove span, avoid removing edit span
     */
    private List<CharacterStyle> mLastStyle = new ArrayList<>();

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

    }

    /**
     * {@inheritDoc}
     *
     * @param editable
     */
    @Override
    public void afterTextChanged(Editable editable) {
        for (CharacterStyle style : mLastStyle) {
            editable.removeSpan(style);
        }
        List<MarkdownBean> models = MarkdownManager.normalizeString(editable.toString());
        if (models.size() == 0) {
            return;
        }
        mLastStyle.clear();
        for (MarkdownBean model : models) {
            Type type = model.getType();
            Range range = model.getRange();
            CharacterStyle style = MarkdownManager.getTextStyle(type);
            int start = range.getStart();
            int end = range.getEnd();
            mLastStyle.add(style);
            editable.setSpan(style, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

}
