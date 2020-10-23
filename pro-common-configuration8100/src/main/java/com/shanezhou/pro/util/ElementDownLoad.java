package com.shanezhou.pro.util;

import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author ShaneZhou
 * @since 2020/10/15 周四 9:14:31
 */
public class ElementDownLoad {

    public static final String PATH_FILE = "D:\\element\\";
    public static final String URL_PATH = "https://unpkg.com/browse/element-ui@2.13.2/";
    public static final String URL_FILE = "https://unpkg.com/element-ui@2.13.2/";

    public static void main(String[] args) {
        try {
            getPage("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getPage(String after) throws IOException {

        String urlPath = URL_PATH + after;
        System.out.println("urlPath: " + urlPath);
        File file = new File(PATH_FILE + after);
        if (!file.exists()) {
            file.mkdir();
        }
        HttpURLConnection http = (HttpURLConnection)(new URL(urlPath)).openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3562.0 Safari/537.36");
        http.connect();
        if (http.getResponseCode() == 200) {
            InputStream is = http.getInputStream();
            byte[] b = new byte[1024];
            ArrayList<byte[]> byteList = new ArrayList<>();
            ArrayList<Integer> lengthList = new ArrayList<>();
            int length = 0;
            int totalLength = 0;
            while ((length = is.read(b)) != -1) {
                byteList.add(b);
                lengthList.add(length);
                totalLength += length;
                b = new byte[1024];
            }
            http.disconnect();
            byte[] all;
            all = new byte[totalLength];
            totalLength = 0;
            while (byteList.size() != 0) {
                System.arraycopy(byteList.get(0), 0, all,
                        totalLength,lengthList.get(0));
                totalLength += lengthList.get(0);
                byteList.remove(0);
                lengthList.remove(0);
            }
            String content = new String(all, StandardCharsets.UTF_8);
            all = null;
            content = content.split("tbody")[1];
            String[] us = content.split("href=\"");
            for (int i = 0; i < us.length; i++) {
                String href = us[i].split("\"", 2)[0];
                if ("../".equals(href)) {
                    continue;
                }
                if ("/".equals(href.charAt(href.length() - 1))) {
                    getPage(after + href);
                } else {
                    getFile(after + href);
                }
            }

        } else {
            getPage(after);
        }
    }

    private static void getFile(String url) throws IOException {
        System.out.println("url: " + url);
        HttpURLConnection http;
        http = (HttpURLConnection)new URL(URL_FILE + url).openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3562.0 Safari/537.36");
        http.connect();
        if (http.getResponseCode() == 200) {
            InputStream is = http.getInputStream();
            byte[] b = new byte[1024];
            ArrayList<byte[]> byteList = new ArrayList<>();
            ArrayList<Integer> lengthList = new ArrayList<>();
            int length;
            int totalLength = 0;
            while ((length = is.read(b)) != -1) {
                byteList.add(b);
                lengthList.add(length);
                totalLength += length;
                b =  new byte[1024];
            }
            http.disconnect();
            byte[] all;
            all = new byte[totalLength];
            totalLength = 0;
            while (lengthList.size() != 0) {
                System.arraycopy(byteList.get(0), 0,all,
                        totalLength,lengthList.get(0));
                totalLength += lengthList.get(0);
                byteList.remove(0);
                lengthList.remove(0);
            }
            File file = new File(PATH_FILE + url.replace("/", "\\\\"));
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file, false);
            fos.write(all);
            fos.flush();
            fos.close();
        } else {
            getFile(url);
        }
    }
}
