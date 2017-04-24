package com.huangyu.mdeditor.utils;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.huangyu.mdeditor.bean.MarkdownBean;
import com.huangyu.mdeditor.bean.Range;
import com.huangyu.mdeditor.bean.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangyu on 2017-4-24.
 */
public class MarkdownManager {

    private MarkdownManager() {
    }

    /**
     * 规范化文字信息
     *
     * @param text
     * @return
     */
    public static List<MarkdownBean> normalizeString(String text) {
        List<MarkdownBean> models = new ArrayList<>();
        for (Type type : Type.values()) {
            Pattern pattern = getMarkdownPattern(type);
            if (pattern == null) {
                continue;
            }
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                MarkdownBean model = new MarkdownBean(type, new Range(start, end));
                models.add(model);
            }
        }
        return models;
    }

    public static CharacterStyle getTextStyle(Type type) {
        switch (type) {
            case NEW_LINE:
                break;
            case HEAD_LINE:
                return new AbsoluteSizeSpan(30, true);
            case HEAD_LINE_UNDER_1:
                break;
            case HEAD_LINE_UNDER_2:
                break;
            case BLOCK_QUOTES:
                return new ForegroundColorSpan(Color.LTGRAY);
            case ITALIC:
                return new StyleSpan(Typeface.ITALIC);
            case BOLD:
                return new StyleSpan(Typeface.BOLD);
            case DISORDER:
                break;
            case ORDER:
                break;
            case PARAGRAPH:
                return new ForegroundColorSpan(Color.GRAY);
            case CODE:
                return new ForegroundColorSpan(Color.DKGRAY);
            case CUTTING_LINE:
                break;
            case LINK:
            case LINK_EMAIL:
            case LINK_WEB:
                return new ForegroundColorSpan(Color.BLUE);
            case IMAGE:
                break;
            case ESCAPE:
                break;
            default:
                break;
        }
        return null;
    }

    /**
     * 获取Markdown语法
     *
     * @param type
     * @return
     */
    private static Pattern getMarkdownPattern(Type type) {
        switch (type) {
            case NEW_LINE:
                return MarkdownSyntax.getPattern(MarkdownSyntax.NEW_LINE);
            case HEAD_LINE:
                return MarkdownSyntax.getPattern(MarkdownSyntax.HEAD_LINE);
            case HEAD_LINE_UNDER_1:
                return MarkdownSyntax.getPattern(MarkdownSyntax.HEAD_LINE_UNDER_1);
            case HEAD_LINE_UNDER_2:
                return MarkdownSyntax.getPattern(MarkdownSyntax.HEAD_LINE_UNDER_2);
            case BLOCK_QUOTES:
                return MarkdownSyntax.getPattern(MarkdownSyntax.BLOCK_QUOTES);
            case ITALIC:
                return MarkdownSyntax.getPattern(MarkdownSyntax.ITALIC);
            case BOLD:
                return MarkdownSyntax.getPattern(MarkdownSyntax.BOLD);
            case DISORDER:
                return MarkdownSyntax.getPattern(MarkdownSyntax.DISORDER);
            case ORDER:
                return MarkdownSyntax.getPattern(MarkdownSyntax.ORDER);
            case PARAGRAPH:
                return MarkdownSyntax.getPattern(MarkdownSyntax.PARAGRAPH);
            case CODE:
                return MarkdownSyntax.getPattern(MarkdownSyntax.CODE);
            case CUTTING_LINE:
                return MarkdownSyntax.getPattern(MarkdownSyntax.CUTTING_LINE);
            case LINK:
                return MarkdownSyntax.getPattern(MarkdownSyntax.LINK);
            case LINK_EMAIL:
                return MarkdownSyntax.getPattern(MarkdownSyntax.LINK_EMAIL);
            case LINK_WEB:
                return MarkdownSyntax.getPattern(MarkdownSyntax.LINK_WEB);
            case IMAGE:
                return MarkdownSyntax.getPattern(MarkdownSyntax.IMAGE);
            case ESCAPE:
                return MarkdownSyntax.getPattern(MarkdownSyntax.ESCAPE);
        }
        return null;
    }

}
