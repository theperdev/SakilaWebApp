package edu.utp.dwi.sakilawebapp.utils;

import edu.utp.dwi.sakilawebapp.model.Film;
import edu.utp.dwi.sakilawebapp.model.dao.FilmDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "FormatController", value = "/FormatController")
public class FormatController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        String format = "TXT";
        if (request.getParameter("format") != null) {
            format = request.getParameter("format").toUpperCase(Locale.ROOT);
        }

        try {
            ExportFacade exportFacade = new ExportFacade();
            exportFacade.exportFilm(id, format, getServletContext(), response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
