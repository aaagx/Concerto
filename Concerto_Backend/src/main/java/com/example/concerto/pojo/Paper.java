package com.example.concerto.pojo;

public class Paper {
    int paperID;
    String title;
    String abstractContent;
    String year;
    String authors;
    String meeting;
    String link;

    @Override
    public String toString() {
        return "Paper{" +
                "paperID=" + paperID +
                ", title='" + title + '\'' +
                ", abstractContent='" + abstractContent + '\'' +
                ", year='" + year + '\'' +
                ", authors='" + authors + '\'' +
                ", meeting='" + meeting + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public int getPaperID() {
        return paperID;
    }

    public void setPaperID(int id) {
        this.paperID = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
