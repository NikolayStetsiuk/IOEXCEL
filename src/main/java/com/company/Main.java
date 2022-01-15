package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadExcels readExcels = new ReadExcels();
        WriteFile writeFile = new WriteFile();
        writeFile.writeExcel(readExcels.readFile());
    }
}
