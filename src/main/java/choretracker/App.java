package choretracker;
import jssc.SerialPortException;
import uk.co.caprica.picam.NativeLibraryException;
import javax.imageio.ImageIO;

import java.io.IOException;

/**
 * Hello world!
 */


public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws NativeLibraryException,SerialPortException,IOException {

        AWTAccumulator acc = new AWTAccumulator();
    }
}
