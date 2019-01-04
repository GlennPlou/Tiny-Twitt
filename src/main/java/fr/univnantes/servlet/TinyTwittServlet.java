package fr.univnantes.servlet;

import com.googlecode.objectify.ObjectifyService;
import fr.univnantes.beans.Twitt;
import fr.univnantes.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "TinyTwittServlet",
        urlPatterns = {"/test"}
)
public class TinyTwittServlet extends HttpServlet {

    static {
        ObjectifyService.register(Twitt.class);
        ObjectifyService.register(User.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().print("Bienvenue sur Tiny Twitt , votre réseau favori!\r\n");

    }
}
