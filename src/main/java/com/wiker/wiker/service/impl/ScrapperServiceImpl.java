package com.wiker.wiker.service.impl;

import com.wiker.wiker.entity.Article;
import com.wiker.wiker.repository.ArticleRepository;
import com.wiker.wiker.service.ScrapperService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;

@Service
public class ScrapperServiceImpl implements ScrapperService {

    private static String OUTPUT_FOLDER = "C:\\images";


    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void scrapData(String url) {

        Document doc = null;
        String name = "";
        String imageUrl = "";

        try {
            doc = Jsoup.connect(url).get();
            //doc = Jsoup.connect("https://en.wikipedia.org/wiki/YouTube").get();
            name = doc.select("#firstHeading").text();
            imageUrl = doc.select(".infobox a img").attr("src");


        } catch (IOException e) {
            e.printStackTrace();
        }

        Article article = new Article();
        article.setName(name);
        Article savedArticle = articleRepository.save(article);
        String imageName = savedArticle.getId().toString();
        downloadImage(imageUrl, imageName);

    }


    private static void downloadImage(String strImageURL, String imageName) {

        String imgName = imageName + strImageURL.substring(strImageURL.lastIndexOf("."));

        try {
            String imgUrl = "https:" + strImageURL;

            URL urlImage = new URL(imgUrl);
            InputStream in = urlImage.openStream();

            byte[] buffer = new byte[4096];
            int n = -1;

            OutputStream os = new FileOutputStream(OUTPUT_FOLDER + "/" + imgName);

            while ((n = in.read(buffer)) != -1) {
                os.write(buffer, 0, n);
            }
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
