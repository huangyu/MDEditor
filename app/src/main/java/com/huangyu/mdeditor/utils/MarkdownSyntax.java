package com.huangyu.mdeditor.utils;

import java.util.regex.Pattern;

/**
 * Markdown语法
 * Created by huangyu on 2017-4-17.
 */
public class MarkdownSyntax {

    /**
     * 标题
     * # H1
     * ## H2
     * ### H3
     * #### H4
     * ##### H5
     * ###### H6
     */
    public static Pattern HEADERS = Pattern.compile("(#+) (.*)", Pattern.CASE_INSENSITIVE);

    /**
     * 块注释
     * > This is a blockquote with two paragraphs. Lorem ipsum dolor sit amet,
     * > consectetuer adipiscing elit. Aliquam hendrerit mi posuere lectus.
     * > Vestibulum enim wisi, viverra nec, fringilla in, laoreet vitae, risus.
     * >
     * > Donec sit amet nisl. Aliquam semper ipsum sit amet velit. Suspendisse
     * > id sem consectetuer libero luctus adipiscing.
     */
    public static Pattern BLOCKS = Pattern.compile("\\n(&gt;|>)(.*)", Pattern.CASE_INSENSITIVE);

    /**
     * 斜体
     * _italic_
     * *italic_
     */
    public static Pattern ITALIC = Pattern.compile("(\\*|_)(.*?)\\1", Pattern.CASE_INSENSITIVE);

    /**
     * 粗体
     * __bold__
     * *bold**
     */
    public static Pattern BOLD = Pattern.compile("(\\*\\*|__)(.*?)\\1", Pattern.CASE_INSENSITIVE);

    /**
     * 无序符号
     */
    public static Pattern DISORDER = Pattern.compile("((^\\*)|(^\\+)|(^-))\\s+(.*)", Pattern.CASE_INSENSITIVE);

    /**
     * 有序符号
     * 1. Bird
     * 2. McHale
     * 3. Parish
     */
    public static Pattern ORDER = Pattern.compile("^[0-9]+\\.(.*)", Pattern.CASE_INSENSITIVE);

    /**
     * 段落
     *  This is a list item with two paragraphs.         Lorem ipsum dolor
     *  sit amet, consectetuer adipiscing elit. Aliquam hendrerit
     *  mi posuere lectus.
     *  Vestibulum enim wisi, viverra nec, fringilla in, laoreet
     *  vitae, risus. Donec sit amet nisl. Aliquam semper ipsum
     *  sit amet velit.
     *  Suspendisse id sem consectetuer libero luctus adipiscing.
     */
    public static Pattern PARAGRAPH = Pattern.compile("", Pattern.CASE_INSENSITIVE);

    /**
     * 代码块
     * ```
     * public class MyClass {
     * ;
     * }
     * ```
     */
    public static Pattern CODE = Pattern.compile("```([\\\\s\\\\S]*?)```", Pattern.CASE_INSENSITIVE);

    /**
     * 分割线
     * * * *
     * ***
     * - - -
     * ---
     */
    public static Pattern LINE = Pattern.compile("", Pattern.CASE_INSENSITIVE);

    /**
     * 链接
     * address@example.com
     *
     */
    public static Pattern LINK1 = Pattern.compile("", Pattern.CASE_INSENSITIVE);

    /**
     * 链接
     * <https://github.com/huangyu0522>
     *
     */
    public static Pattern LINK2 = Pattern.compile("", Pattern.CASE_INSENSITIVE);

    /**
     * 链接
     * [github](https://github.com/huangyu0522)
     *
     */
    public static Pattern LINK3 = Pattern.compile("", Pattern.CASE_INSENSITIVE);

    /**
     * 图片
     * ![alt text](/path/to/img.jpg "Title")
     * ![alt text][id]
     */
    public static Pattern IMAGE = Pattern.compile("", Pattern.CASE_INSENSITIVE);

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
    public static Pattern ESCAPE = Pattern.compile("", Pattern.CASE_INSENSITIVE);

}
