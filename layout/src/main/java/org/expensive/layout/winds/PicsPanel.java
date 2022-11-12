package org.expensive.layout.winds;

import lombok.extern.slf4j.Slf4j;
import org.expensive.service.ImageFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class PicsPanel extends JPanel {

    @Autowired
    private ImageFinderService imageFinderService;
    private int width;
    private int height;
    @Autowired
    private PicsPanel picsPanel;

    public PicsPanel() {
        width = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
        height = 460;
        setSize(width, height);
        initPanel();
    }

    @Override
    public void setLayout(LayoutManager mgr) {
        super.setLayout(mgr);
    }

    public void initPanel() {
        //
        JTextField pageAddressInput = new JTextField("图片页面", 20);
        add(pageAddressInput);

        JButton confirmBtn = new JButton("Search");
        confirmBtn.addActionListener(click -> {
            try {
                String localPath = imageFinderService.getImagesPageFromWebsite(pageAddressInput.getText());
                String[] imgs = imageFinderService.filterImagesFromFile(localPath);
                List<String> values = imageFinderService.filterSrcValueFromImgLabel(imgs);
                for (String url:values
                ) {
                    log.info(url);
                    imageFinderService.downloadRemoteImgFile(url, null, "");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        add(confirmBtn);
        setLayout(new FlowLayout());
    }
}