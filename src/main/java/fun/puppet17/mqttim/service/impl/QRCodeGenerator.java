package fun.puppet17.mqttim.service.impl;

import fun.puppet17.mqttim.service.IQRCodeGenerator;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 二维码生成工具类
 * @author xyx
 * @date 2024/9/25
 */
@Service
public class QRCodeGenerator implements IQRCodeGenerator {
    
    private final MinioClient minioClient;
    private final String bucketName;
    
    public QRCodeGenerator(MinioClient minioClient,
                           @Value("${minio.bucket.name}") String bucketName) {
        this.minioClient = minioClient;
        this.bucketName = bucketName;
    }
    
    /**
     * 上传二维码到 MinIO
     *
     * @param qrCodeBytes 二维码字节数组
     * @param objectName  MinIO 中存储的对象名称
     * @return
     */
    @Override
    public String uploadQrCodeToMinio(byte[] qrCodeBytes, String objectName) {
        try (InputStream qrCodeStream = new ByteArrayInputStream(qrCodeBytes)) {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(qrCodeStream, qrCodeBytes.length, -1)
                    .contentType("image/png")
                    .build());
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .build());
        } catch (Exception e) {
            throw new MinioUploadException("上传到 MinIO 失败", e);
        }
    }
    
    public static class MinioUploadException extends RuntimeException {
        public MinioUploadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
