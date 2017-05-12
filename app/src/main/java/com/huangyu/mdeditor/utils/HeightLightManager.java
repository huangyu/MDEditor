package com.huangyu.mdeditor.utils;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.QuoteSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;

import com.huangyu.mdeditor.bean.HighLight;
import com.huangyu.mdeditor.bean.HighLightType;
import com.huangyu.mdeditor.bean.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangyu on 2017-4-24.
 */
public class HeightLightManager {

    private HeightLightManager() {
    }

    /**
     * 规范化文字信息
     *
     * @param text
     * @return
     */
    public static List<HighLight> normalizeString(String text) {
        List<HighLight> models = new ArrayList<>();
        for (HighLightType type : HighLightType.values()) {
            Pattern pattern = getMarkdownPattern(type);
            if (pattern == null) {
                continue;
            }
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                HighLight model = new HighLight(type, new Range(start, end));
                models.add(model);
            }
        }
        return models;
    }

    /**
     * 获取Span类型
     *
     * @param type
     * @return
     */
    public static List<Object> getTextSpanStyleList(HighLightType type) {
        List<Object> styleList = new ArrayList<>();
        switch (type) {
            case HEAD_LINE_1:
                styleList.add(new AbsoluteSizeSpan(34, true));
                styleList.add(new StyleSpan(Typeface.BOLD));
                break;
            case HEAD_LINE_2:
                styleList.add(new AbsoluteSizeSpan(30, true));
                styleList.add(new StyleSpan(Typeface.BOLD));
                break;
            case HEAD_LINE_3:
                styleList.add(new AbsoluteSizeSpan(26, true));
                styleList.add(new StyleSpan(Typeface.BOLD));
                break;
            case HEAD_LINE_4:
                styleList.add(new AbsoluteSizeSpan(22, true));
                styleList.add(new StyleSpan(Typeface.BOLD));
                break;
            case HEAD_LINE_5:
                styleList.add(new AbsoluteSizeSpan(18, true));
                styleList.add(new StyleSpan(Typeface.BOLD));
                break;
            case HEAD_LINE_6:
                styleList.add(new AbsoluteSizeSpan(14, true));
                styleList.add(new StyleSpan(Typeface.BOLD));
                break;
            case BLOCK_QUOTES:
                styleList.add(new QuoteSpan(Color.DKGRAY));
                styleList.add(new ForegroundColorSpan(Color.DKGRAY));
                break;
            case ITALIC:
                styleList.add(new StyleSpan(Typeface.ITALIC));
                break;
            case BOLD:
                styleList.add(new StyleSpan(Typeface.BOLD));
                break;
            case DISORDER:
                break;
            case ORDER:
                break;
            case PARAGRAPH:
                styleList.add(new ForegroundColorSpan(Color.DKGRAY));
                break;
            case CODE:
                styleList.add(new ForegroundColorSpan(Color.GRAY));
                styleList.add(new TypefaceSpan("serif"));
                break;
            case CUTTING_LINE:
                styleList.add(new ForegroundColorSpan(Color.BLUE));
                break;
            case LINK_EMAIL:
            case LINK_WEB:
                styleList.add(new URLSpan(""));
                styleList.add(new ForegroundColorSpan(Color.BLUE));
                break;
            case IMAGE:
                styleList.add(new ForegroundColorSpan(Color.RED));
                styleList.add(new UnderlineSpan());
                break;
            case ESCAPE:
                break;
            default:
                break;
        }
        return styleList;
    }

    /**
     * 获取Markdown语法
     *
     * @param type
     * @return
     */
    private static Pattern getMarkdownPattern(HighLightType type) {
        switch (type) {
            case HEAD_LINE_1:
                return HighLightSyntax.getPattern(HighLightSyntax.HEAD_LINE_1);
            case HEAD_LINE_2:
                return HighLightSyntax.getPattern(HighLightSyntax.HEAD_LINE_2);
            case HEAD_LINE_3:
                return HighLightSyntax.getPattern(HighLightSyntax.HEAD_LINE_3);
            case HEAD_LINE_4:
                return HighLightSyntax.getPattern(HighLightSyntax.HEAD_LINE_4);
            case HEAD_LINE_5:
                return HighLightSyntax.getPattern(HighLightSyntax.HEAD_LINE_5);
            case HEAD_LINE_6:
                return HighLightSyntax.getPattern(HighLightSyntax.HEAD_LINE_6);
            case BLOCK_QUOTES:
                return HighLightSyntax.getPattern(HighLightSyntax.BLOCK_QUOTES);
            case ITALIC:
                return HighLightSyntax.getPattern(HighLightSyntax.ITALIC);
            case BOLD:
                return HighLightSyntax.getPattern(HighLightSyntax.BOLD);
            case DISORDER:
                return HighLightSyntax.getPattern(HighLightSyntax.DISORDER);
            case ORDER:
                return HighLightSyntax.getPattern(HighLightSyntax.ORDER);
            case PARAGRAPH:
                return HighLightSyntax.getPattern(HighLightSyntax.PARAGRAPH);
            case CODE:
                return HighLightSyntax.getPattern(HighLightSyntax.CODE);
            case CUTTING_LINE:
                return HighLightSyntax.getPattern(HighLightSyntax.CUTTING_LINE);
            case LINK_EMAIL:
                return HighLightSyntax.getPattern(HighLightSyntax.LINK_EMAIL);
            case LINK_WEB:
                return HighLightSyntax.getPattern(HighLightSyntax.LINK_WEB);
            case IMAGE:
                return HighLightSyntax.getPattern(HighLightSyntax.IMAGE);
            case ESCAPE:
                return HighLightSyntax.getPattern(HighLightSyntax.ESCAPE);
        }
        return null;
    }

}
