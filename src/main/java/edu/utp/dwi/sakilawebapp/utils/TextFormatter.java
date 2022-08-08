package edu.utp.dwi.sakilawebapp.utils;

import edu.utp.dwi.sakilawebapp.model.Film;

public class TextFormatter implements IFormatter {
    @Override
    public String formatFilm(Film film) {
        StringBuilder txt = new StringBuilder();

        txt.append("Id: " + film.getId() + "\n");
        txt.append("Title: " + film.getTitle() + "\n");
        txt.append("Description: " + film.getDescription() + "\n");
        txt.append("Year: " + film.getReleaseYear() + "\n");
        txt.append("Language Id: " + film.getLanguageId() + "\n");
        txt.append("Original Language Id: " + film.getOriginalLanguageId() + "\n");
        txt.append("Duration: " + film.getRentalDuration() + "\n");
        txt.append("Rental rate: " + film.getRentalRate() + "\n");
        txt.append("Length: " + film.getLength() + "\n");
        txt.append("Replacement cost: " + film.getReplacementCost() + "\n");
        txt.append("Rating: " + film.getRating() + "\n");
        txt.append("Special features: " + film.getSpecialFeatures() + "\n");
        txt.append("Last update: " + film.getLastUpdate());

        return txt.toString();
    }
}
