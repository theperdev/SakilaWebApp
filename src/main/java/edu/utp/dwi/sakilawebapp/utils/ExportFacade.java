package edu.utp.dwi.sakilawebapp.utils;

import edu.utp.dwi.sakilawebapp.model.Film;
import edu.utp.dwi.sakilawebapp.model.dao.FilmDAO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

public class ExportFacade {
    public void exportFilm(int filmId, String format, ServletContext context,
                           HttpServletResponse response) throws IOException, SQLException {
        Film film = new FilmDAO().getFilm(filmId);

        String fileName = film.getTitle().replace(" ", "_").toLowerCase(Locale.ROOT) +
                            "." + format.toLowerCase();

        FormatManager formatManager = new FormatManager();
        String fileContent = formatManager.formatFilm(format, film);

        FileGenerator exporter = new FileGenerator();
        String filePath = exporter.exportFile(context, fileName, fileContent);

        FileDownloader downloader = new FileDownloader();
        downloader.AttachFile(filePath, context, response);
    }
}
