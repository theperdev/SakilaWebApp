package edu.utp.dwi.sakilawebapp.utils;

import edu.utp.dwi.sakilawebapp.model.Film;

public class XMLFormatter implements IFormatter{
    @Override
    public String formatFilm(Film film) {
        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

        xml.append("<film>\n");

        xml.append("<id>" + film.getId() + "</id>\n");
        xml.append("<title>" + film.getTitle() + "</title>\n");
        xml.append("<description>" + film.getDescription() + "</description>\n");
        xml.append("<year>" + film.getReleaseYear() + "</year>\n");
        xml.append("<language>" + film.getLanguageId() + "</language>\n");
        xml.append("<original_language_id>" + film.getOriginalLanguageId() + "</original_language_id>\n");
        xml.append("<duration>" + film.getRentalDuration() + "</duration>\n");
        xml.append("<rental_rate>" + film.getRentalRate() + "</rental_rate>\n");
        xml.append("<length>" + film.getLength() + "</length>\n");
        xml.append("<replacement_cost>" + film.getReplacementCost() + "</replacement_cost>\n");
        xml.append("<rating>" + film.getRating() + "</rating>\n");
        xml.append("<special_features>" + film.getSpecialFeatures() + "</special_features>\n");
        xml.append("<last_update>" + film.getLastUpdate() + "</last_update>\n");

        xml.append("</film>");

        return xml.toString();
    }
}
