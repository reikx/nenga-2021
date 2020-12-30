package nenga;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.io.*;

public class Config{
    public static final Config INSTANCE;
    public Address from;
    public ArrayList<Address> to;
    public String background;
    private Config(){

    }

    static {
        Config config = null;
        try {
            config = new ObjectMapper().readValue(new File("./config.json"),Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        INSTANCE = config;
    }
}
