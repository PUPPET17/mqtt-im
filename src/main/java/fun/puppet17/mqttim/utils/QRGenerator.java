package fun.puppet17.mqttim.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import fun.puppet17.mqttim.service.impl.QRCodeGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author xyx
 * @date 2024/9/25
 */
public class QRGenerator {
    
    /**
     * 生成二维码图像，并返回图片的字节数组
     * @param url 二维码包含的URL
     * @param width 宽度
     * @param height 高度
     * @return 二维码图片的字节数组
     */
    public static byte[] generateImageByte(String url, int width, int height) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);
            
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
                ImageIO.write(image, "png", baos);
                return baos.toByteArray();
            }
            
        } catch (WriterException | IOException e) {
            throw new QRCodeGenerationException("生成二维码失败", e);
        }
    }
    
    public static class QRCodeGenerationException extends RuntimeException {
        public QRCodeGenerationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
