package com.contiero.mthree;

import java.util.ArrayList;
import java.util.Locale;

public class DVDLibrary implements DVDLibraryInterface {

    private ArrayList<DVD> dvdLibrary = new ArrayList<>();

    @Override
    public void addDvd(DVD dvd) {
        dvdLibrary.add(dvd);
    }

    @Override
    public void removeDvd(int index) {
        dvdLibrary.remove(index);
    }

    @Override
    public DVD getDvd(int index) {
        return dvdLibrary.get(index);
    }

    @Override
    public DVD searchByTitle(String title) {
        for (int i = 0; i<dvdLibrary.size(); i++) {
            DVD dvd = dvdLibrary.get(i);
            if (dvd.getTitle().equals(title)) {
                // Found the title, now just return the dvd here.
                return dvd;
            }
        }
        // If we haven't found that title in the loop, return null.
        return null;
    }

    @Override
    public void updateDvd(int index, DVD dvd) {
        DVD libDvd = dvdLibrary.get(index);
        libDvd.setDirectorName(dvd.getDirectorName());
        libDvd.setNote(dvd.getNote());
        libDvd.setMpaaRating(dvd.getMpaaRating());
        libDvd.setStudio(dvd.getStudio());
        libDvd.setReleaseDate(dvd.getReleaseDate());
        libDvd.setTitle(dvd.getTitle());
    }

    @Override
    public int librarySize() {
        return dvdLibrary.size();
    }

    @Override
    public void trashAllDvds() {
        dvdLibrary.clear();
    }
}
