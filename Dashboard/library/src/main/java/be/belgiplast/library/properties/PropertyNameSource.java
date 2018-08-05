package be.belgiplast.library.properties;

public interface PropertyNameSource {
    String getName(int position);
    Class  getType(int position);
    int getCount();
}
