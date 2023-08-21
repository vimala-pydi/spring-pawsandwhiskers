package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ImageDisplayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");

        if (category != null && (category.equals("cats") || category.equals("dogs"))) {
            String imagePath = "/WEB-INF/images/" + category + "/";
            displayImages(request, response, imagePath);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category");
        }
    }

    private void displayImages(HttpServletRequest request, HttpServletResponse response, String imagePath) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String realPath = getServletContext().getRealPath(imagePath);
        File imageDirectory = new File(realPath);
        File[] imageFiles = imageDirectory.listFiles();

        if (imageFiles != null) {
            for (File imageFile : imageFiles) {
                if (imageFile.isFile() && imageFile.getName().endsWith(".jpg")) {
                    FileInputStream inputStream = new FileInputStream(imageFile);
                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    response.setContentType("image/jpeg");
                    OutputStream outputStream = response.getOutputStream();

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    inputStream.close();
                    outputStream.close();
                }
            }
        }
    }
}
