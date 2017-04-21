package com.huangyu.mdeditor.utils;

import java.util.regex.Pattern;

/**
 * Markdown语法
 * Created by huangyu on 2017-4-17.
 */
public class MarkdownSyntax {

    private MarkdownSyntax() {
    }

    /**
     * 获取样式
     *
     * @param markdownString
     * @return
     */
    public static Pattern getPattern(String markdownString, boolean isMultiLine) {
        if (isMultiLine) {
            return Pattern.compile(markdownString, Pattern.MULTILINE);
        }
        return Pattern.compile(markdownString, Pattern.CASE_INSENSITIVE);
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
    public static String HEAD_LINE = "^ *(#{1,6}) *([^\\n]+?) *#* *(?:\\n+|$)";
//    public static String HEAD_LINE = "^ *(#{1,6}) (.*)";

    /**
     * 区块引用
     * > This is a blockquote with two paragraphs. Lorem ipsum dolor sit amet,
     * > consectetuer adipiscing elit. Aliquam hendrerit mi posuere lectus.
     * > Vestibulum enim wisi, viverra nec, fringilla in, laoreet vitae, risus.
     * >
     * > Donec sit amet nisl. Aliquam semper ipsum sit amet velit. Suspendisse
     * > id sem consectetuer libero luctus adipiscing.
     */
    public static String BLOCK_QUOTES = "^( *>[^\\n]+(\\n(?!def)[^\\n]+)*\\n*)+";
//    public static String BLOCK_QUOTES = "\\n(&gt;|>)(.*)";

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
    public static String ORDER = "((^\\*)|(^\\+)|(^-))\\s+(.*)";

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
    public static String PARAGRAPH = "";

    /**
     * 代码块
     * ```
     * public class MyClass {
     * ;
     * }
     * ```
     */
    public static String CODE = "";

    /**
     * 分割线
     * * * *
     * ***
     * - - -
     * ---
     */
    public static String CUTTIN_LINE = "";

    /**
     * 链接
     * address@example.com
     */
    public static String LINK1 = "";

    /**
     * 链接
     * <https://github.com/huangyu0522>
     */
    public static String LINK2 = "";

    /**
     * 链接
     * [github](https://github.com/huangyu0522)
     */
    public static String LINK3 = "";

    /**
     * 图片
     * ![alt text](/path/to/img.jpg "Title")
     * ![alt text][id]
     */
    public static String IMAGE = "";

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
