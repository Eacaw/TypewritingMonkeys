package com.davidpinchen;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    private String path;
    private boolean append_to_file = false;

    public WriteFile(String path){
        this.path = path;
    }

    public WriteFile(String path, boolean append){
        this.path = path;
        this.append_to_file = append;
    }

    public void writeToFile(String textLine) throws IOException{

        FileWriter write = new FileWriter(path, append_to_file);

        write.write(textLine + "\n");
        write.close();

    }




}
