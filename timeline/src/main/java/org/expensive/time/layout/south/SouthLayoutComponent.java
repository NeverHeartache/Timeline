package org.expensive.time.layout.south;

import org.expensive.time.jframe.GlobalFrame;
import org.expensive.time.toolkit.DateUtils;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SouthLayoutComponent extends JPanel {
    private Date bootTime = new Date();
    private JPanel  timePanel;
    private JLabel timeLabel, displayArea, titleLabel;
    private ImageIcon image = null;
    private String time;

    public SouthLayoutComponent() {
        //标题
        titleLabel = new JLabel("打开本程序时间为："+DateUtils.format(bootTime)+System.lineSeparator());
        //动态时间
        timePanel = new JPanel();
        timeLabel = new JLabel("Current time is :");
        displayArea = new JLabel(""+System.lineSeparator());//trailing
        //新建对象的时候就开始跑线程
        runTimePanel(); //  添加一个动态的时间
        timePanel.add(titleLabel);
        timePanel.add(timeLabel);
        timePanel.add(displayArea);
        add(timePanel);
        image = new ImageIcon(getClass().getClassLoader().getResource("static/img/180822080212.png"));
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(image);
        timePanel.add(imageLabel);
    }

    private void runTimePanel(){
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
