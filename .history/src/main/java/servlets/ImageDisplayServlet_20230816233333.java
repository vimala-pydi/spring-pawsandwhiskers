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
            System.out.println(imagePath);
            List<String> imageUrls= getImageUrls(request, response, imagePath);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{\"images\":" + imageUrls + "}");
            out.flush();
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category");
        }
    }

    private void displayImages(HttpServletRequest request, HttpServletResponse response, String imagePath) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String realPath = getServletContext().getRealPath(imagePath);
        File imageDirectory = new File(realPath);
        File[] imageFiles = imageDirectory.listFiles();
        System.out.println("image files are "+ imageFiles);
    
        // if (imageFiles != null) {
        //     for (File imageFile : imageFiles) {
        //         if (imageFile.isFile() && imageFile.getName().endsWith(".jpg")) {
        //             FileInputStream inputStream = new FileInputStream(imageFile);
        //             byte[] imageBytes = new byte[(int) imageFile.length()];
    
        //             int bytesRead;
        //             while ((bytesRead = inputStream.read(imageBytes)) != -1) {
        //                 response.getOutputStream().write(imageBytes, 0, bytesRead);
        //             }
    
        //             inputStream.close();
        //         }
        //     }
        }


        private List<String> getImageUrls(HttpServletRequest request, HttpServletResponse response, String imagePath) 
        throws ServletException{
            List<String> imageUrls = new ArrayList<>();
            String basePath = getServletContext().getRealPath(imagePath);
        
            File folder = new File(basePath);
            File[] listOfFiles = folder.listFiles();
        
            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        imageUrls.add("images/Dogs/" + file.getName());
                    }
                }
            }
        
            return imageUrls;
        }
    }
    

