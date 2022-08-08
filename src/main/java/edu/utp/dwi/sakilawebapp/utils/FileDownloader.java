package edu.utp.dwi.sakilawebapp.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileDownloader {
    public void AttachFile(String filePath, ServletContext context, HttpServletResponse response) throws IOException {
        File downloadFile = new File(filePath);
        FileInputStream stream = new FileInputStream(downloadFile);

        String mimeType = context.getMimeType(filePath);

        response.setContentType(mimeType);
        response.setContentLength((int)downloadFile.length());
        response.setHeader("Content-Disposition", "attachment; filename=" + downloadFile.getName());

        OutputStream output = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while((bytesRead = stream.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }

        stream.close();
        output.close();
    }
}
