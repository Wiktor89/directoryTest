package utilits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Утилитарный класс для работы с консолью
 */
public class ConsoleReader implements AutoCloseable {

    private BufferedReader reader = null;
    private InputStreamReader inputStreamReader = null;

    public ConsoleReader() {
        this.inputStreamReader = new InputStreamReader(System.in);
        this.reader = new BufferedReader(inputStreamReader);
    }

    /**
     * @return с консоли
     */
    public String readString(){
        String line = null;
        try {
            line = this.reader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public void close() throws Exception {
        inputStreamReader.close();
        reader.close();
    }
}
