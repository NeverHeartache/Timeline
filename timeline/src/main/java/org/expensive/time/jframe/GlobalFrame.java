package org.expensive.time.jframe;

//import toolkit.DateUtils;

import org.expensive.time.toolkit.DateUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.*;

public class GlobalFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;
    private static final int X_Position = 781;
    private static final int Y_Position = 292;
    private Date bootTime;
    private JPanel  timePanel;
    private JLabel timeLabel, displayArea, titleLabel;
    private String time;
    private ImageIcon image = null;
    public List<File> picList = new ArrayList<>();
    private int fileIndex = 0;

    public GlobalFrame(){
        bootTime = new Date();
        //标题
        titleLabel = new JLabel("打开本程序时间为："+ DateUtils.format(bootTime)+System.lineSeparator());
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
        //
        setTitle("Timeline!Time to have a rest!");
        setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setLocation(X_Position, Y_Position);
        setExtendedState(JFrame.MAXIMIZED_BOTH);// JFrame最大化
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    protected class JLabelTimerTask extends TimerTask {
        @Override
        public void run() {
            time = DateUtils.format(Calendar.getInstance().getTime());
            displayArea.setText(time);
        }
    }
    public void runTimePanel(){
        Timer tmr = new Timer();
        tmr.scheduleAtFixedRate(new JLabelTimerTask(), new Date(), 1000L);
    }

}
