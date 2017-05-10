package com.huangyu.mdeditor.ui.widget;

import android.content.Context;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.huangyu.mdeditor.bean.HighLight;
import com.huangyu.mdeditor.bean.HighLightType;
import com.huangyu.mdeditor.bean.Range;
import com.huangyu.mdeditor.utils.HeightLightManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyu on 2017-4-24.
 */
public class HighLightEditText extends EditText {

    private HighLightChangeWatcher mTextWatcher;

    public HighLightEditText(Context context) {
        super(context);
        init();
    }

    public HighLightEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HighLightEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        setMovementMethod(LinkMovementMethod.getInstance());
        mTextWatcher = new HighLightChangeWatcher();
        this.addTextChangedListener(mTextWatcher);
    }

    private class HighLightChangeWatcher implements TextWatcher {

        private List<Object> mLastStyleList = new ArrayList<>();

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            for (Object style : mLastStyleList) {
                editable.removeSpan(style);
            }
            List<HighLight> models = HeightLightManager.normalizeString(editable.toString());
            if (models.size() == 0) {
                return;
            }

            mLastStyleList.clear();

            for (HighLight model : models) {
                setSpan(model, editable);
            }
        }

        private void setSpan(HighLight model, Editable editable) {
            HighLightType type = model.getHighLightType();
            Range range = model.getRange();
            int start = range.getStart();
            int end = range.getEnd();
            List<Object> styleList = HeightLightManager.getTextSpanStyleList(type);
            mLastStyleList.addAll(styleList);

            for (Object style : styleList) {
                editable.setSpan(style, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }
    }

}
