package com.basis.utils;

import com.basis.common.ResponseCode;
import com.basis.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;

/*
 * @Author IT 派同学
 * @Description 图像工具类
 * @Date 2024/12/8
 **/
@SuppressWarnings("all")
@Component
public class ImageUtils {


    @Value("${file.path}")
    private String path;

    /**
     * 将图片转换为base64字符串
     * @param imagePath
     * @return
     */
    public String convertImageToBase64(final String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(path + imagePath));
            ByteArrayOutputStream base = new ByteArrayOutputStream();
            ImageIO.write(image, getImageFormat(imagePath), base);
            byte[] imageBytes = base.toByteArray();
            String base64String = Base64.getEncoder().encodeToString(imageBytes);
            return "data:image/png;base64," + base64String;
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.UPLOAD_FILE_ERROR);
        }
    }

    /**
     * 获取图片后缀
     * @param imagePath
     * @return
     */
    private String getImageFormat(String imagePath) {
        String extension = imagePath.substring(imagePath.lastIndexOf(".") + 1);
        return extension.equalsIgnoreCase("jpg") ? "jpeg" : extension;
    }


}
