package com.huangyu.mdeditor.bean;

/**
 * Created by huangyu on 2017-4-24.
 */
public class HighLight {

    private HighLightType type;

    private Range range;

    public HighLight(HighLightType type, Range range) {
        this.type = type;
        this.range = range;
    }

    public HighLightType getHighLightType() {
        return type;
    }

    public void setType(HighLightType type) {
        this.type = type;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

}
