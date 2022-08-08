package edu.utp.dwi.sakilawebapp.utils;

import javax.servlet.ServletContext;
import java.io.*;

public class FileGenerator {
    public String exportFile(ServletContext context, String fileName, String content) throws IOException {
        File file = new File(context.getRealPath("/WEB-INF/") + fileName);

        FileOutputStream output = new FileOutputStream(file);
        new PrintStream(output).print(content);
        output.close();

        return file.getAbsolutePath();
    }
}

