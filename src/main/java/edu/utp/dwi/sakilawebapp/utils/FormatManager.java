package edu.utp.dwi.sakilawebapp.utils;

import edu.utp.dwi.sakilawebapp.model.Film;

public class FormatManager {
    public String formatFilm(String format, Film film) {
        IFormatter formatter = FormatterFactory.getFormatter(format);

        String content = "";
        if (formatter != null) {
            content = formatter.formatFilm(film);
        }

        return content;
    }
}
