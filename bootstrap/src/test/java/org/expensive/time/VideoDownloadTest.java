package org.expensive.time;

import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class VideoDownloadTest {

    private String videoPath = "https://www.youtube.com/58d2157c-0566-43a8-a93f-da4ec153d3c6";

    private String localePath = "F:\\PgDownload\\video\\";

    @Test
    public void downloadVideo() throws IOException {
        String videoName = localePath + UUID.randomUUID().toString().replace("-", "") + ".mp4";
        File file = new File(videoName);
        URL url = new URL(videoPath);
        InputStream in = url.openStream();
        OutputStream out = new FileOutputStream(file);
        int c;
        while ( (c = in.read()) != -1 )
            out.write(c);
        in.close();
        out.close();
    }
}
