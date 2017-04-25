package com.huangyu.mdeditor.utils;

import java.util.regex.Pattern;

/**
 * Heightlight工具类
 * Created by huangyu on 2017-4-17.
 */
public class HighLightSyntax {

    private HighLightSyntax() {
    }

    /**
     * 获取样式
     *
     * @param markdownString
     * @return
     */
    public static Pattern getPattern(String markdownString) {
        return Pattern.compile(markdownString, Pattern.MULTILINE);
    }

    /**
     * 标题
     * # H1
     * ## H2
     * ### H3
     * #### H4
     * ##### H5
     * ###### H6
     */
    public static String HEAD_LINE_1 = "^ *#{1} +[^#]* *(?:\\n+|$)";
    public static String HEAD_LINE_2 = "^ *#{2} +[^#]* *(?:\\n+|$)";
    public static String HEAD_LINE_3 = "^ *#{3} +[^#]* *(?:\\n+|$)";
    public static String HEAD_LINE_4 = "^ *#{4} +[^#]* *(?:\\n+|$)";
    public static String HEAD_LINE_5 = "^ *#{5} +[^#]* *(?:\\n+|$)";
    public static String HEAD_LINE_6 = "^ *#{6} +[^#]* *(?:\\n+|$)";

    /**
     * 标题
     * ----
     */
    public static String HEAD_LINE_UNDER_1 = "^[^-\\n][^\\n]*\\n-+$";

    /**
     * 标题
     * ====
     */
    public static String HEAD_LINE_UNDER_2 = "^[^=\\n][^\\n]*\\n=+$";

    /**
     * 区块引用
     * > This is a blockquote with two paragraphs. Lorem ipsum dolor sit amet,
     * > consectetuer adipiscing elit. Aliquam hendrerit mi posuere lectus.
     * > Vestibulum enim wisi, viverra nec, fringilla in, laoreet vitae, risus.
     * >
     * > Donec sit amet nisl. Aliquam semper ipsum sit amet velit. Suspendisse
     * > id sem consectetuer libero luctus adipiscing.
     */
    public static String BLOCK_QUOTES = "^( *>[^\\n]+(\\n(?!^ *\\[([^\\]]+)\\]: *<?([^\\s>]+)>?(?: +[\"(]([^\\n]+)[\")])? *(?:\\n+|$))[^\\n]+)*)+";

    /**
     * 斜体
     * _italic_
     * *italic*
     */
    public static String ITALIC = "(\\*|_)(.*?)\\1";

    /**
     * 粗体
     * __bold__
     * **bold**
     */
    public static String BOLD = "(\\*\\*|__)(.*?)\\1";

    /**
     * 无序符号
     * * Red
     * * Green
     * * Blue
     * + Red
     * + Green
     * + Blue
     * - Red
     * - Green
     * - Blue
     */
    public static String DISORDER = "((^\\*)|(^\\+)|(^-))\\s+(.*)";

    /**
     * 有序符号
     * 1. Bird
     * 2. McHale
     * 3. Parish
     */
    public static String ORDER = "^[0-9]+\\.\\s+(.*)";

    /**
     * 段落
     * This is a list item with two paragraphs.         Lorem ipsum dolor
     * sit amet, consectetuer adipiscing elit. Aliquam hendrerit
     * mi posuere lectus.
     * Vestibulum enim wisi, viverra nec, fringilla in, laoreet
     * vitae, risus. Donec sit amet nisl. Aliquam semper ipsum
     * sit amet velit.
     * Suspendisse id sem consectetuer libero luctus adipiscing.
     */
    public static String PARAGRAPH = "((( {4}|\\t).*(\\n|\\z))|(^\\\\s*$\\\\n))+";

    /**
     * 代码块
     * ```
     * public class MyClass {
     * ;
     * }
     * ```
     */
    public static String CODE = "``\\`([\\s\\S]*?)``\\`[\\s]?";

    /**
     * 分割线
     * * * *
     * ***
     * - - -
     * ---
     */
    public static String CUTTING_LINE = "^[ \\t]*([*-])[ \\t]*((\\1)[ \\t]*){2,}[ \\t]*$";

    /**
     * 链接
     * address@example.com
     */
    public static String LINK_EMAIL = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";

    /**
     * 链接
     * https://github.com/huangyu0522
     */
    public static String LINK_WEB = "[a-zA-z]+://[^\\s]*";

    /**
     * 图片
     * ![alt text](/path/to/img.jpg)
     */
    public static String IMAGE = "!?\\[([^\\[\\]]+)\\](\\(([^\\(\\)]+)\\)|\\[([^\\[\\]]+)\\])";

    /**
     * 转义
     * \\   反斜线
     * \`   反引号
     * \*   星号
     * \_   底线
     * \{\}  花括号
     * \[\]  方括号
     * \(\)  括弧
     * \#   井字号
     * \+   加号
     * \-   减号
     * \.   英文句点
     * \!   惊叹号
     */
    public static String ESCAPE = "";

}
