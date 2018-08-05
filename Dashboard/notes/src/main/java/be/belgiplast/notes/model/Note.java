package be.belgiplast.notes.model;

import java.util.Date;

public interface Note {
    String getId();
    void setId(String id);
    String getText();
    void setText(String text);
    void setTimestamp(Date date);
    Date getTimestamp();
}
