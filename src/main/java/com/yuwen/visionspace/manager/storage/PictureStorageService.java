package com.yuwen.visionspace.manager.storage;

import cn.hutool.core.io.FileUtil;
import com.yuwen.visionspace.exception.BusinessException;
import com.yuwen.visionspace.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * 统一存储服务，底层使用 x-file-storage
 */
@Slf4j
@Component
public class PictureStorageService {

    @Resource
    private FileStorageService fileStorageService;

    /**
     * 上传图片（支持 WebP 压缩 + 缩略图）
     *
     * @param file 文件
     * @param key  存储路径 key
     * @return FileInfo 包含 url, thumbnailUrl 等
     */
    public FileInfo putPictureObject(File file, String key) {
        try {
            FileInfo fileInfo = fileStorageService.of(file)
                    .setPath(FileUtil.mainName(key))
                    .setSaveFilename(UUID.randomUUID().toString().replace("-", "") + "." + FileUtil.getSuffix(key))
                    .upload();
            return fileInfo;
        } catch (Exception e) {
            log.error("图片上传失败: key={}", key, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "图片上传失败");
        }
    }

    /**
     * 上传原始文件
     */
    public FileInfo putObject(File file, String key) {
        try {
            FileInfo fileInfo = fileStorageService.of(file)
                    .setPath(key)
                    .upload();
            return fileInfo;
        } catch (Exception e) {
            log.error("文件上传失败: key={}", key, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "文件上传失败");
        }
    }

    /**
     * 下载文件
     */
    public InputStream getObject(String key) {
        try {
            byte[] bytes = fileStorageService.download(key).bytes();
            return new ByteArrayInputStream(bytes);
        } catch (Exception e) {
            log.error("文件读取失败: key={}", key, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "文件读取失败");
        }
    }

    /**
     * 删除文件
     */
    public boolean deleteObject(String key) {
        try {
            return fileStorageService.delete(key);
        } catch (Exception e) {
            log.error("文件删除失败: key={}", key, e);
            return false;
        }
    }
}