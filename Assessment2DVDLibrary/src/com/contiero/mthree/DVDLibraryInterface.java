package com.contiero.mthree;

public interface DVDLibraryInterface {
    public void addDvd(DVD dvd);
    public void removeDvd(int index);
    public DVD getDvd(int index);
    public DVD searchByTitle(String title);
    public void updateDvd(int index, DVD dvd);
    public int librarySize();
    public void trashAllDvds();
}
