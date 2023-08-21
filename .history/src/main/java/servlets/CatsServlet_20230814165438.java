    
package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CatsServlet<Jsonb> extends HttpServlet {

    String[] images;
   
    public void init(){
      
      ServletContext ctx = getServletContext();
      // Get real path to images folder
      String imagePath = ctx.getRealPath("WEB-INF/images");

      images = new String[]{
        imagePath + "/dog1.jpg",
        imagePath + "/dog2.jpg"
       };


    }



	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
                Jsonb jsonb = JsonbBuilder.create();
    
                String json = jsonb.toJson(images);
                
                response.setContentType("application/json");
                response.getWriter().write(json);
	}

}

