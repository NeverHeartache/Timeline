package org.expensive.time.layout.south;

import org.expensive.time.toolkit.DateUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SouthLayoutComponent extends JPanel {
    private Date bootTime = new Date();
    //  private JPanel  timePanel;
    private JLabel titleLabel;
    private ImageIcon image = null;
    private String time;

    public SouthLayoutComponent() {
        //标题
        titleLabel = new JLabel();
        titleLabel.setText("打开本程序时间为：" + DateUtils.format(bootTime) + System.lineSeparator());
        titleLabel.setHorizontalAlignment(JLabel.CENTER);// 水平对其方式：居中
        titleLabel.setVerticalAlignment(JLabel.TOP);//垂直对其方式：顶部
        Font font = new Font("SansSerif", Font.PLAIN, 40);
        titleLabel.setFont(font);
//        titleLabel.se
        add(titleLabel);
//        add(timeLabel);
//        add(displayArea);

    }
}
