package edu.utp.dwi.sakilawebapp.model;

import java.util.ArrayList;

public class FilmSpecialFeature {
    private ArrayList<String> specialFeatures;

    public ArrayList<String> getSpecialFeatures() {
        return specialFeatures;
    }

    public FilmSpecialFeature() {
        specialFeatures = new ArrayList<String>();

        specialFeatures.add("Trailers");
        specialFeatures.add("Commentaries");
        specialFeatures.add("Deleted Scenes");
        specialFeatures.add("Behind the Scenes");
    }
}
