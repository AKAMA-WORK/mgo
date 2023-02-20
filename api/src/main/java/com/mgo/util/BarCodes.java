package com.mgo.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;

public class BarCodes {
    /*public static final Float DEFAULT_OVERLAY_TRANSPARENCY = 1f;
    public static final Float DEFAULT_OVERLAY_TO_QRCODE_RATIO = 0.25f;
    private static BufferedImage overlay;

    static {

        try {

            System.out.println("PRINT PATH ");
            System.out.println(BarCodes.class.getClassLoader().getResource("/").toURI().getPath());
            System.out.println("END PATH");
            overlay = ImageIO
                    .read(BarCodes.class.getResourceAsStream("/resources/barcodes/overlay.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static byte[] bufferedImage2ByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }

    /*private static BufferedImage getQRCodeWithOverlay(BufferedImage qrcode) {
        BufferedImage scaledOverlay = scaleOverlay(qrcode);

        Integer deltaHeight = qrcode.getHeight() - scaledOverlay.getHeight();
        Integer deltaWidth = qrcode.getWidth() - scaledOverlay.getWidth();

        BufferedImage combined = new BufferedImage(qrcode.getWidth(), qrcode.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) combined.getGraphics();
        g2.drawImage(qrcode, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, DEFAULT_OVERLAY_TRANSPARENCY));
        g2.drawImage(scaledOverlay, Math.round(deltaWidth / 2), Math.round(deltaHeight / 2), null);
        return combined;
    }

    private static BufferedImage scaleOverlay(BufferedImage qrcode) {
        Integer scaledWidth = Math.round(qrcode.getWidth() * DEFAULT_OVERLAY_TO_QRCODE_RATIO);
        Integer scaledHeight = Math.round(qrcode.getHeight() * DEFAULT_OVERLAY_TO_QRCODE_RATIO);

        BufferedImage imageBuff = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics g = imageBuff.createGraphics();
        g.drawImage(overlay.getScaledInstance(scaledWidth, scaledHeight, BufferedImage.SCALE_SMOOTH), 0, 0,
                new Color(0, 0, 0), null);
        g.dispose();
        return imageBuff;
    }*/

}
