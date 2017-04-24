package com.huangyu.mdeditor.ui.widget.markdown;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by huangyu on 2017-4-24.
 */
public class MarkdownEditText extends EditText {

    private MarkdownTextChangeWatcher mTextWatcher;

    public MarkdownEditText(Context context) {
        super(context);
        init();
    }

    public MarkdownEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MarkdownEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTextWatcher = new MarkdownTextChangeWatcher();
        this.addTextChangedListener(mTextWatcher);
    }

    public void destroy() {
        this.removeTextChangedListener(mTextWatcher);
    }

}
