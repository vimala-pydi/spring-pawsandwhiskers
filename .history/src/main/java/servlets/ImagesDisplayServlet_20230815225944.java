import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.File;

public class ImagesDisplayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Image Display</title>");
        out.println("<style>");
        out.println(".image-container { display: flex; justify-content: space-between; }");
        out.println(".image-column { flex: 1; padding: 10px; }");
        out.println(".image-column img { max-width: 100%; height: auto; display: block; margin: 0 auto; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<h1>Image Gallery</h1>");
        out.println("<div class='image-container'>");

        String category = request.getParameter("category");
        if (category != null && (category.equals("cats") || category.equals("dogs"))) {
            String imagePath = "/WEB-INF/images/" + category + "/";
            displayImages(request, response, imagePath);
        }

        out.println("</div>");
        out.println("</body></html>");

        out.close();
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
