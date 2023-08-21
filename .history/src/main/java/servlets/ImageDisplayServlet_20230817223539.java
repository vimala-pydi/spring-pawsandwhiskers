package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;esponse;
import javax.servlet.ServletContext; // Make sure this import is present

@WebServlet("/ImageDisplayServlet")
public class ImageDisplayServlet extends HttpServlet {
    private List<String> getImageUrls(String category, ServletContext context) {
        List<String> imageUrls = new ArrayList<>();
        
        String imagesFolder = "/images/" + category;
        String[] imageNames = context.getResourcePaths(imagesFolder);
        
        if (imageNames != null) {
            for (String imageName : imageNames) {
                imageUrls.add(imageName);
            }
        }
        
        return imageUrls;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        List<String> imageUrls = getImageUrls(category, request.getServletContext());

        request.setAttribute("imageUrls", imageUrls);
        request.getRequestDispatcher("/display.jsp").forward(request, response);
    }
}
