package com.davidpinchen;

import java.io.FileWriter;
import java.io.PrintWriter;
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
        PrintWriter print_Line = new PrintWriter(write);

        write.write(textLine + "\n");
        write.close();
//
//        print_Line.printf("%s\n", textLine);
//        print_Line.close();

    }




}
