package com.project.springboot.practice.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class QrCodeUtil {

    public static String getQrCodeImage(String codeInformation, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(codeInformation, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(pngOutputStream.toByteArray());
    }
}
