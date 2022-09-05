package org.expensive.layout.layout.center;

import javax.swing.*;
import java.awt.*;

public class CenterLayoutComponent extends JPanel {
    private JLabel imageLabel;
    public CenterLayoutComponent() {
        imageLabel = new JLabel();
        ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("static/img/180822080212.png"));
        image.setDescription("图片测试");
        imageLabel.setIcon(image);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
//        imageLabel.setForeground(Color.GREEN);
        add(imageLabel);
    }
}
