package org.expensive.time.jframe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

public class FrameProxy {
    private JFrame globalFrame = null;
    public FrameProxy() throws IOException {
        if(globalFrame == null ){
            globalFrame = new GlobalFrame();
        }
        initTray();
        frameHide();
    }

    public JFrame getGlobalFrame() {
        return globalFrame;
    }

    public void setGlobalFrame(GlobalFrame globalFrame) {
        this.globalFrame = globalFrame;
    }

    public void frameShow(){
        globalFrame.setVisible(true);
        //最大化
        globalFrame.toFront();//
    }

    public void frameHide(){
        globalFrame.setVisible(false);
    }

    public boolean isShow(){
        return globalFrame.isShowing();
    }

    private void initTray() throws IOException {
        if(SystemTray.isSupported()){
            SystemTray systemTray = SystemTray.getSystemTray();//创建托盘
            PopupMenu popupMenu = new PopupMenu();

            MenuItem openItem = new MenuItem("open");
            MenuItem exitItem = new MenuItem("exit");
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
            TrayIcon trayIcon = new TrayIcon(createImage("/ico.jpg", ""), "Timeline！Let's have a rest!",popupMenu);
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
}
