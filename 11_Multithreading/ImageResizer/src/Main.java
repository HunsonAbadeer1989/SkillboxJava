import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main
{
    private static final int COUNT_OF_PROCESSORS = Runtime.getRuntime(). availableProcessors();

    public static void main(String[] args)
    {
        String srcFolder = "/users/hunsonabadeer/Desktop/LentaImages";
        String dstFolder = "/users/hunsonabadeer/Desktop/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        for(int i =0; i < COUNT_OF_PROCESSORS; i++){
                Thread first = new Thread(() -> {
                    resizeImage(dstFolder, files);
                });
                first.start();
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }

    private static void resizeImage(String dstFolder, File[] files) {
        try
        {
            for(File file : files)
            {
                BufferedImage image = ImageIO.read(file);
                if(image == null) {
                    continue;
                }

                int newWidth = 300;
                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );
                BufferedImage newImage = new BufferedImage(
                        newWidth, newHeight, BufferedImage.TYPE_INT_RGB
                );

                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;

                for (int x = 0; x < newWidth; x++)
                {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
