package fun.puppet17.mqttim.service;

/**
 * @author xyx
 * @date 2024/9/25
 */
public interface IQRCodeGenerator {
    String uploadQrCodeToMinio(byte[] qrCodeBytes, String objectName);
}
