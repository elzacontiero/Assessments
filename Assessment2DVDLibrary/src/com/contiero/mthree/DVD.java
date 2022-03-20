package com.contiero.mthree;

public class DVD {

    // I populated with attributes and used access modifier private to provide encapsulation.
    // We do this because the state should not be made visible to the outside.
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String note;

    // Next I provided getters and setters because the properties are private.
    // It gets properties and set new values for the properties.

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
