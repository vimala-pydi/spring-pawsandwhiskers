package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ImageDisplayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String contextPath = getServletContext().getRealPath("/");
        List<String> dogImageUrls = new ArrayList<>();
        List<String> catImageUrls = new ArrayList<>();

        // Find dog and cat image URLs
        File dogsFolder = new File(contextPath + "/images/dogs");
        File catsFolder = new File(contextPath + "/images/cats");

        if (dogsFolder.exists() && dogsFolder.isDirectory()) {
            findImageUrls(dogsFolder, dogImageUrls);
        }

        if (catsFolder.exists() && catsFolder.isDirectory()) {
            findImageUrls(catsFolder, catImageUrls);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Image List</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Dog Images</h1>");
        out.println("<ul>");

        for (String imageUrl : dogImageUrls) {
            out.println("<li><img src='\" + imageUrl + \"' alt='Dog Image'></li>");
        }

        out.println("</ul>");
        out.println("<h1>Cat Images</h1>");
        out.println("<ul>");

        for (String imageUrl : catImageUrls) {
            out.println("<li><img src='\" + imageUrl + \"' alt='Cat Image'></li>");
        }

        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }

    private void findImageUrls(File folder, List<String> imageUrls) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    findImageUrls(file, imageUrls);
                } else if (isImageFile(file)) {
                    imageUrls.add(file.getPath().replace(getServletContext().getRealPath("/"), ""));
                }
            }
        }
    }

    private boolean isImageFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif");
    }
}
    

