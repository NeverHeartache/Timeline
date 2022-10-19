package org.expensive.service.impl;

import org.expensive.service.ImageFinderService;

import java.io.File;
import java.util.stream.Stream;

public class ImageFinderServiceImpl implements ImageFinderService {


    @Override
    public Stream<String> getImagesFromWebsite(String pagePath) {
        File file = new File(pagePath);

        return null;
    }
}
