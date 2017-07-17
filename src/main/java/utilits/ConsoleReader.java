package utilits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Для консоли
 */
public class ConsoleReader implements AutoCloseable {

    private BufferedReader reader = null;
    private InputStreamReader inputStreamReader = null;

    /**
     *
     * @return с консоли
     */
    public String readString(){
        inputStreamReader = new InputStreamReader(System.in);
        reader = new BufferedReader(inputStreamReader);
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    /**
     *
     * @return с консоли
     */
    public int readInt(){
        int result = 0;
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
        try {
            result = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        inputStreamReader.close();
        reader.close();
    }
}
