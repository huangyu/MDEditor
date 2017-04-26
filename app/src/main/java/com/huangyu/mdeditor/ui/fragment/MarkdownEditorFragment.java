package com.huangyu.mdeditor.ui.fragment;

import android.os.Bundle;

import com.huangyu.library.mvp.IBaseView;
import com.huangyu.library.rx.RxManager;
import com.huangyu.library.ui.BaseFragment;
import com.huangyu.mdeditor.R;
import com.huangyu.mdeditor.ui.widget.HighLightEditText;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * Created by huangyu on 2017-4-25.
 */
public class MarkdownEditorFragment extends BaseFragment {

    @Bind(R.id.et_highlight)
    HighLightEditText mEtHighLight;

    private final String content = "**1. 标题**\n" +
            "# H1\n" +
            "## H2\n" +
            "### H3 \n" +
            "#### H4\n" +
            "##### H5\n" +
            "###### H6\n" +
            "---\n" +
            "**2. 块注释**\n" +
            "> This is a blockquote with two paragraphs. Lorem ipsum dolor sit amet,\n" +
            "> consectetuer adipiscing elit. Aliquam hendrerit mi posuere lectus.\n" +
            "> Vestibulum enim wisi, viverra nec, fringilla in, laoreet vitae, risus.\n" +
            "> \n" +
            "> Donec sit amet nisl. Aliquam semper ipsum sit amet velit. Suspendisse\n" +
            "> id sem consectetuer libero luctus adipiscing.\n" +
            "\n" +
            "**3. 斜体**\n" +
            "\n" +
            "_italic_\n" +
            "*italic*\n" +
            "\n" +
            "**4. 粗体**\n" +
            "\n" +
            "__bold__\n" +
            "**bold**\n" +
            "\n" +
            "**5. 无序列表**\n" +
            "\n" +
            "* Red\n" +
            "* Green\n" +
            "* Blue\n" +
            "\n" +
            "+ Red\n" +
            "+ Green\n" +
            "+ Blue\n" +
            "\n" +
            "- Red\n" +
            "- Green\n" +
            "- Blue \n" +
            "\n" +
            "---\n" +
            "**6. 有序列表**\n" +
            "\n" +
            "1. Bird\n" +
            "2. McHale\n" +
            "3. Parish\n" +
            "\n" +
            "---\n" +
            "**7. 段落**\n" +
            "\n" +
            "    This is a list item with two paragraphs.         Lorem ipsum dolor\n" +
            "    sit amet, consectetuer adipiscing elit. Aliquam hendrerit\n" +
            "    mi posuere lectus.\n" +
            "\n" +
            "    Vestibulum enim wisi, viverra nec, fringilla in, laoreet\n" +
            "    vitae, risus. Donec sit amet nisl. Aliquam semper ipsum\n" +
            "    sit amet velit.\n" +
            "\n" +
            "    Suspendisse id sem consectetuer libero luctus adipiscing.\n" +
            "    \n" +
            "---\n" +
            "**8. 代码**\n" +
            "        \n" +
            "```\n" +
            "public class MyClass {\n" +
            "        \n" +
            "}\n" +
            "```\n" +
            "\n" +
            "---    \n" +
            "**9. 分割线**\n" +
            "\n" +
            "* * *\n" +
            "\n" +
            "***\n" +
            "\n" +
            "- - -\n" +
            "\n" +
            "---\n" +
            "\n" +
            "---\n" +
            "**10. 链接**\n" +
            "\n" +
            "address@example.com\n" +
            "\n" +
            "https://github.com/huangyu0522\n" +
            "\n" +
            "[github](https://github.com/huangyu0522)\n" +
            "\n" +
            "---\n" +
            "**11. 图片**\n" +
            "\n" +
            "![图片](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493205521823&di=63bc447616ee02db8af616b9e8f66427&imgtype=0&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201505%2F2015052508.jpg)\n" +
            "\n" +
            "---\n" +
            "\n";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_editor;
    }

    @Override
    protected IBaseView initAttachView() {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mEtHighLight.setText(content);

        RxManager.getInstance().on("getContent", new Action1<String>() {
            @Override
            public void call(String s) {
                RxManager.getInstance().post("refresh", mEtHighLight.getText().toString());
            }
        });
    }

}
