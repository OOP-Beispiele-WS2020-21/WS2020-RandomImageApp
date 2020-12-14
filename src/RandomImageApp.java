import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.graphics.Rectangle;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

import java.util.Random;

public class RandomImageApp extends GraphicsApp implements AppConfig {

    Rectangle[] pixelsToDraw;

    @Override
    public void initialize() {
        setFrameRate(FRAME_RATE);
        setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
        setRandomPixelSquares();
    }

    private void setRandomPixelSquares() {
        boolean[][] pixel = createRandomPixelMask();
        pixelsToDraw = new Rectangle[0];
        for (int x = 0; x < pixel.length; x++) {
            for (int y = 0; y < pixel[0].length; y++) {
                boolean shouldDrawPixel = pixel[x][y];
                if (shouldDrawPixel) {
                    Rectangle[] extendedArray = new Rectangle[pixelsToDraw.length + 1];
                    System.arraycopy(pixelsToDraw, 0, extendedArray, 0, pixelsToDraw.length);
                    extendedArray[extendedArray.length - 1] = new Rectangle(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, SQUARE_COLOR);
                    pixelsToDraw = extendedArray;
                }
            }
        }
    }

    private boolean[][] createRandomPixelMask() {
        Random random = new Random();
        int numberOfPixel = CANVAS_SIZE / SQUARE_SIZE;
        boolean[][] pixel = new boolean[numberOfPixel][numberOfPixel];
        for (int x = 0; x < pixel.length; x++) {
            for (int y = 0; y < pixel[0].length; y++) {
                pixel[x][y] = random.nextBoolean();
            }
        }
        return pixel;
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        if(event.getKeyCode() == KeyPressedEvent.VK_SPACE) {
            setRandomPixelSquares();
        }
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        for(int i = 0; i < pixelsToDraw.length; i++) {
            pixelsToDraw[i].draw();
        }
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}
