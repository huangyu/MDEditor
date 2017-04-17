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
     *
     */

}
