package be.belgiplast.notes.model;

public interface NoteProvider {
    int getCount();
    void addNode(Note note);
    void updateNode(Note note);
    void deleteNode(Note note);
    Note findNote(String id);
    Note getNote(int id);

}
