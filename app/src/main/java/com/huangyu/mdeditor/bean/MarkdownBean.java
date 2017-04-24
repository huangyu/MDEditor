package com.huangyu.mdeditor.bean;

/**
 * Created by huangyu on 2017-4-24.
 */
public class MarkdownBean {

    private Type type;

    private Range range;

    public MarkdownBean(Type type, Range range) {
        this.type = type;
        this.range = range;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

}
