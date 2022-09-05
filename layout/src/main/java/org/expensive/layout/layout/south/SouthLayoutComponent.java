package org.expensive.layout.layout.south;


import org.expensive.common.utils.DateUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class SouthLayoutComponent extends JPanel {

    public SouthLayoutComponent() {
        Date bootTime = new Date();
        //标题
        JLabel titleLabel = new JLabel();
        titleLabel.setText("打开本程序时间为：" + DateUtils.format(bootTime) + System.lineSeparator());
        titleLabel.setHorizontalAlignment(JLabel.CENTER);// 水平对其方式：居中
        titleLabel.setVerticalAlignment(JLabel.TOP);//垂直对其方式：顶部
        Font font = new Font("SansSerif", Font.PLAIN, 40);
        titleLabel.setFont(font);
        add(titleLabel);
    }
}
