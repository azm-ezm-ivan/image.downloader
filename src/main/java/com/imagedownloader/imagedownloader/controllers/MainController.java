package com.imagedownloader.imagedownloader.controllers;

import com.imagedownloader.imagedownloader.dto.Image;
import com.imagedownloader.imagedownloader.dto.ImageReq;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.imageio.ImageIO.write;

@RestController
@RequestMapping(value = "/api/v1")
public class MainController {

    @PostMapping(value = "/")
    public ResponseEntity getImages(@RequestBody() ImageReq imageReq) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<Image> imageList = new ArrayList<>();
        if (!Objects.isNull(imageReq) && imageReq.getUrls().length >= 1) {
            for (String url : imageReq.getUrls()) {
                String[] splitUrl = new URL(url).getPath().split("/");
                String fileName = splitUrl[splitUrl.length - 1];
                splitUrl = fileName.split("\\.");
                String formatName = splitUrl[splitUrl.length - 1];
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                BufferedImage image = ImageIO.read(connection.getInputStream());
                image = Thumbnails.of(image).forceSize(100, 100).asBufferedImage();
                write(image, formatName, outputStream);
                imageList.add(new Image(fileName, outputStream.toByteArray()));
            }
            return ResponseEntity.ok(imageList);
        } else {
            return (ResponseEntity) ResponseEntity.badRequest();
        }
    }
}
