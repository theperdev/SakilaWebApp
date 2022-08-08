package edu.utp.dwi.sakilawebapp.utils;

import java.util.Locale;

public class FormatterFactory {
    public static IFormatter getFormatter(String format) {
        switch (format.toUpperCase(Locale.ROOT)){
            case "TXT": return new TextFormatter();
            case "XML": return new XMLFormatter();
        }
        return null;
    }
}
