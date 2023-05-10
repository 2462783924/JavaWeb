package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@WebServlet(name = "savepicServlet", value = "/savepicServlet")
public class savepicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fileName");
        System.out.println(fileName);
        String uploadPath = request.getSession().getServletContext().getRealPath("/file");

        File saveDir = new File(uploadPath);
        System.out.println(uploadPath);
        if (!saveDir.exists()) {
            return;
        }
        File file = new File(uploadPath + "/" + fileName);
        System.out.println(file);

        int fileLength = (int) file.length();
        response.setContentType("application/x-download");
        String displayName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + displayName);
        response.setContentLength(fileLength);

        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = new FileInputStream(file.getAbsolutePath());
        byte[] buffer = new byte[1024];
        int i = 0;
        while ((i = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, i);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
