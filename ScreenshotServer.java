import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;

public class ScreenshotServer {
    public static void main(String[] args) {
        int port = 5001;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            // Receive the screenshot
            InputStream inputStream = socket.getInputStream();
            BufferedImage receivedImage = ImageIO.read(inputStream);

            if (receivedImage != null) {
                File outputFile = new File("received_screenshot.png");
                ImageIO.write(receivedImage, "png", outputFile);
                System.out.println("Screenshot received and saved as received_screenshot.png");
            } else {
                System.out.println("No image received from client.");
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

