import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer implements Runnable {

    private File[] files;
    private String dstFolder;
    private long start;

    public ImageResizer(File[] files, String dstFolder, long start) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newWidth = 300;
                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );
//                BufferedImage newImage = new BufferedImage(
//                        newWidth, newHeight, BufferedImage.TYPE_INT_RGB
//                );
//
//                int widthStep = (image.getWidth() / newWidth);
//                int heightStep = (image.getHeight() / newHeight);
//
//                for (int x = 0; x < newWidth; x++) {
//                    for (int y = 0; y < newHeight; y++) {
//                        int rgb = image.getRGB(x * widthStep, y * heightStep);
//                        newImage.setRGB(x, y, rgb);
//                    }
//                }

                BufferedImage newImage;
                BufferedImage tempImage;
                double scale = 1.2; // scale 20%

                AffineTransform tr = AffineTransform.getScaleInstance(scale, scale);
                AffineTransformOp op = new AffineTransformOp(tr, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                AffineTransformOp op2 = new AffineTransformOp(tr, AffineTransformOp.TYPE_BICUBIC);

                tempImage = new BufferedImage(newWidth * 2, newHeight * 2, image.getType());
                op.filter(image, tempImage);

                newImage = new BufferedImage(newWidth, newHeight, image.getType());
                op2.filter(tempImage, newImage);

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Thread is finished in: " + (double)(System.currentTimeMillis() - start)/1000 + " sec");

    }
}
