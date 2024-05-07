package edu.esprit.freelancejobs.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.util.Builder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRGenerator {

    private final BitMatrix bitMatrix;

    private BufferedImage qrImage;

    public QRGenerator(String data, BarcodeFormat format, int width, int height, int imageType) throws WriterException {

        BitMatrixBuilder builder = new BitMatrixBuilder(data, format, width, height);
        this.bitMatrix = builder.build();

        this.qrImageCreate(width, height, imageType);

    }

    private void qrImageCreate(int width, int height, int imageType) {

        // Convert BitMatrix to BufferedImage
        this.qrImage = new BufferedImage(width, height, imageType);

        for (int x = 0; x < width; x++) {

            for (int y = 0; y < height; y++) {

                qrImage.setRGB(x, y, this.bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);

            }

        }

    }

    public void save(String pathname, String formatName) throws IOException {
        ImageIO.write(qrImage, formatName, new File(pathname));
    }

    public BufferedImage qrImage() {
        return this.qrImage;
    }

    private static class BitMatrixBuilder implements Builder<BitMatrix> {

        private final BitMatrix bitMatrix;

        public BitMatrixBuilder(String data, BarcodeFormat format, int width, int height) throws WriterException {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            this.bitMatrix = qrCodeWriter.encode(data, format, width, height);
        }

        @Override
        public BitMatrix build() {
            return this.bitMatrix;
        }

    }

}

