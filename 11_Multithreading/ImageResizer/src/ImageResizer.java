import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer implements Runnable {

    private File file;
    private String dstFolder;
    private long start;

    public ImageResizer(File file, String dstFolder, long start) {
        this.file = file;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        try {
                BufferedImage image = ImageIO.read(file);
                if(image == null){
                    return;
                }
                int newWidth = 300;
                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );

                BufferedImage newImage;
                BufferedImage tempImage;
                double scale = (double) newWidth/ image.getWidth(); // scale 20%

                AffineTransform tr = AffineTransform.getScaleInstance(scale * 2, scale * 2);
                AffineTransformOp op = new AffineTransformOp(tr, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                AffineTransform tr2 = AffineTransform.getScaleInstance(0.5, 0.5);
                AffineTransformOp op2 = new AffineTransformOp(tr2, AffineTransformOp.TYPE_BICUBIC);

                tempImage = new BufferedImage(newWidth * 2, newHeight * 2, image.getType());
                op.filter(image, tempImage);

                newImage = new BufferedImage(newWidth, newHeight, image.getType());
                op2.filter(tempImage, newImage);

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Thread " + Thread.currentThread().getName() + " is finished in: " + (double)(System.currentTimeMillis() - start)/1000 + " sec");

    }
}
