import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ScreenshotClient {
    public static void main(String[] args) {
        String serverAddress = "10.61.192.63"; // Change to server IP if needed
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to server. Receiving screenshot...");

            // Receive screenshot
            InputStream inputStream = socket.getInputStream();
            BufferedImage image = ImageIO.read(inputStream);

            // Save image
            File outputfile = new File("received_screenshot.png");
            ImageIO.write(image, "png", outputfile);

            System.out.println("Screenshot received and saved as received_screenshot.png");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

