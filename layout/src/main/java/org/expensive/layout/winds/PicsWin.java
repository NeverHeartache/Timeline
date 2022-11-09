package org.expensive.layout.winds;

import javax.swing.*;
import java.awt.*;

/**
 * 布局：
 * 顶部是输入框，输入要展示图片的网址页面
 * 下边是图片展览框，带滚动条
 * 每个图片有两个按钮“下载”、“删除”
 */
public class PicsWin extends JDialog {

    public PicsWin(Frame owner) {
        super(owner);
    }
}
