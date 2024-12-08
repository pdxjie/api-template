package com.basis.utils;


import com.basis.common.ResponseCode;
import com.basis.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.UUID;


/**
 * @Author: IT 派同学
 * @Date: 2024/12/7
 * @Description: 文件上传
 */
@Component
@SuppressWarnings("all")
@Slf4j

public class UploadUtil {

    @Value(value = "${file.path}")
    private String path;

    /**
     * 上传文件
     * @param file 文件
     * @param path 路径
     * @return
     */
    public Pair<String, String> uploadFile(final MultipartFile file, final String path) {
        String uri = "";
        UUID uuid = UUID.randomUUID();
        String originalFileName = file.getOriginalFilename();
        String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf('.'));
        // 文件实际存储路径
        final String subpath = path + "/" + uuid + fileSuffix;
        File dest = new File(this.path + subpath);
        if (!dest.getParentFile().exists()) {
            // 创建文件夹
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error("上传文件失败 原因:{} 位置类:{}",e.getMessage(),e.getClass());
            throw new BusinessException(ResponseCode.UPLOAD_FILE_ERROR);
        }
        uri = "/upload/" + uuid + fileSuffix;

        return Pair.of(subpath, uri);
    }

    /**
     * 下载图片
     * @param base64 base64字符串
     * @param path 路径
     * @return
     * @throws IOException
     */
    public Pair<String, String> downloadImage(final String base64, String path) throws IOException {
        try {
            // 解码base64字符串为字节数组
            byte[] decodedBytes = Base64.getDecoder().decode(base64);
            // 生成随机的图片文件名
            String fileName = UUID.randomUUID().toString() + ".jpg";
            // 拼接文件路径
            String folderPath = this.path + path;
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs(); // 创建文件夹
            }
            String filePath = folderPath + "/" + fileName;
            // 将字节数组保存为图片文件
            Path _path = Path.of(filePath);
            Files.write(_path, decodedBytes, StandardOpenOption.CREATE);
            return Pair.of((path + "/" + fileName), ("/upload/" + fileName));
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.DOWN_LOAD_FILE_ERROR);
        }
    }

    /**
     * 删除文件
     * @param path 路径
     */
    public void deletedFile(final String path) {
        File file = new File(this.path + path);
        // 判断是否为视频文件
        try {
            FileUtils.forceDelete(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ResponseCode.DELETED_FILE_ERROR);
        }
    }

    /**
     * 校验是否是视频文件
     * @param file 文件
     * @return true or false
     */
    private boolean isVideoFile(File file) {
        String fileName = file.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileExtension.equalsIgnoreCase("mp4");
    }

}
