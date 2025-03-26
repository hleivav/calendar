package se.lexicon.model;

import java.time.LocalDateTime;

public class Event {
    private int id;
    private int calendarId;
    private String title;
    private String description;
    private LocalDateTime dateTime;

    //Constructors
    public Event(int id, int calendarId, String title, String description, LocalDateTime dateTime) {
        this.id = id;
        this.calendarId = calendarId;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
    }

    public Event(int calendarId, String title, String description, LocalDateTime dateTime) {
        this.calendarId = calendarId;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
    }

    //Setters & Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    // override methods
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", calendarId=" + calendarId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
