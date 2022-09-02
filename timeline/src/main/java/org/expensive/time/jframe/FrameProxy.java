package org.expensive.time.jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class FrameProxy {
    private JFrame globalFrame = null;
    private final Timer timer = new Timer("Timeline");

    public FrameProxy() throws IOException {
        if (globalFrame == null) {
            globalFrame = new GlobalFrame();
        }
        initTray();
    }

    public JFrame getGlobalFrame() {
        return globalFrame;
    }

    public void setGlobalFrame(GlobalFrame globalFrame) {
        this.globalFrame = globalFrame;
    }

    public boolean isShow() {
        return globalFrame.isShowing();
    }

    private void initTray() throws IOException {
        if (SystemTray.isSupported()) {
            Font trayFont = new Font("Helvetica", Font.CENTER_BASELINE, 16);
            SystemTray systemTray = SystemTray.getSystemTray();//创建托盘
            PopupMenu popupMenu = new PopupMenu();

            MenuItem openItem = new MenuItem("open");
            openItem.setFont(trayFont);
            MenuItem exitItem = new MenuItem("exit");
            exitItem.setFont(trayFont);
            openItem.addActionListener(e -> {
                if (!globalFrame.isShowing()) {
                    globalFrame.setVisible(true);
                }
            });
            exitItem.addActionListener(e -> {
                System.exit(0);
            });
            popupMenu.add(openItem);
            popupMenu.add(exitItem);
            TrayIcon trayIcon = new TrayIcon(createImage("/ico.jpg", ""), "Timeline！Let's have a rest!", popupMenu);
            // 托盘图标自适应尺寸
            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("托盘图标被右键点击");
                }
            });
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    switch (e.getButton()) {
                        case MouseEvent.BUTTON1: {
                            System.out.println("托盘图标被鼠标左键被点击");
                            break;
                        }
                        case MouseEvent.BUTTON2: {
                            System.out.println("托盘图标被鼠标中键被点击");
                            break;
                        }
                        case MouseEvent.BUTTON3: {
                            System.out.println("托盘图标被鼠标右键被点击");
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
            });
            // 添加托盘图标到系统托盘
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("当前系统不支持系统托盘");
        }
    }

    protected static Image createImage(String path, String description) {
        URL imageURL = FrameProxy.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

    public void frameShow() {
        globalFrame.setVisible(true);
        //最大化
        globalFrame.toFront();//
    }

    public void frameHide() {
        globalFrame.setVisible(false);
    }

    public void initLoopShow(FrameProxy frame) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                frame.frameShow();
                java.awt.Toolkit.getDefaultToolkit().beep();
                try {
                    Thread.sleep(1000L * 5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frame.frameHide();
                try {
                    Thread.sleep(1000L * 60 * 10L - 5000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //
        Long delay = 0l;
        //x minutes 之后继续执行
        Long period = 1000L * 60 * 50L;
        // delay 展示5秒，间隔50分钟
        timer.scheduleAtFixedRate(timerTask, delay, period);
    }
}
