package edu.utp.dwi.sakilawebapp.model;

import java.util.HashMap;
import java.util.Map;

public class FilmRating {
    private HashMap<String, String> ratings;

    public HashMap<String, String> getRatings() {
        return ratings;
    }

    public FilmRating() {
        ratings = new HashMap<String, String>();

        ratings.put("G", "General Audience");
        ratings.put("PG", "Parental Guide Suggested");
        ratings.put("PG-13", "Parents Strongly Cautioned");
        ratings.put("R", "Restricted");
        ratings.put("NC-17", "Adults Only");
    }
}
