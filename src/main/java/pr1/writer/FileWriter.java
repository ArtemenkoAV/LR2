package pr1.writer;

import pr1.model.ConnectorType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileWriter implements Writer{
    private String path = "log.txt";
    private  PrintWriter writer;
    @Override
    public void execute(String msg) {
        msg = addInfo(msg);
        writer.println(msg);
        writer.close();
    }

    @Override
    public void configure(List<String> args) {
        if (args.size() < 1 ){
            System.err.println("cfg2 wrtiter args must contain path to file");
        } else {
            path = args.get(0);
        }
        try {
            writer = new PrintWriter(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ConnectorType getType() {
        return ConnectorType.File;
    }

}
