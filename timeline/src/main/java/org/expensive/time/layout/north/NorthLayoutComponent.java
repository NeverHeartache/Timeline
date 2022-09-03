package org.expensive.time.layout.north;

import org.expensive.time.toolkit.DateUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class NorthLayoutComponent extends JPanel {
    private JLabel timeLabel, displayArea;
    private String time;

    public NorthLayoutComponent() {
        //动态时间
        Calendar calendar = Calendar.getInstance();
        String date = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, new Locale("ZH"));
        timeLabel = new JLabel(date + ":");
        timeLabel.setHorizontalTextPosition(JLabel.LEFT);// 水平对其方式：居中
        timeLabel.setVerticalTextPosition(JLabel.CENTER);//垂直对其方式：顶部
        Font font1 = new Font("宋体", Font.PLAIN, 60);
        timeLabel.setForeground(new Color(000000));
        timeLabel.setFont(font1);
        displayArea = new JLabel("" + System.lineSeparator());//trailing
        displayArea.setHorizontalTextPosition(JLabel.CENTER);// 水平对其方式：居中
        displayArea.setVerticalTextPosition(JLabel.CENTER);//垂直对其方式：顶部
        Font font2 = new Font("仿宋", Font.BOLD, 60);
        displayArea.setFont(font2);
        displayArea.setForeground(new Color(0x000000));
        //新建对象的时候就开始跑线程
        runTimePanel(); //  添加一个动态的时间
        add(timeLabel);
        add(displayArea);

        //设置
        setBackground(new Color(0xE5E5E5));
    }

    private void runTimePanel() {
        java.util.Timer tmr = new Timer();
        tmr.scheduleAtFixedRate(new JLabelTimerTask(), new Date(), 1000L);
    }

    protected class JLabelTimerTask extends TimerTask {
        @Override
        public void run() {
            time = DateUtils.format(Calendar.getInstance().getTime());
            displayArea.setText(time);
        }
    }
}
