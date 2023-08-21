 package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageDisplayServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");

        if (category != null && (category.equals("cats") || category.equals("dogs"))) {
            String imagePath = "./WEB-INF/images" + category + "/";
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
                    String imageUrl = contextPath + imagePath + imageFile.getName();
                    response.getWriter().println("<div><img src='" + imageUrl + "' alt='Image' /></div>");
                }
            }
        }
    }
}
