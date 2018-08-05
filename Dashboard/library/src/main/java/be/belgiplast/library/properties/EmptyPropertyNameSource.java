package be.belgiplast.library.properties;

public final class EmptyPropertyNameSource implements PropertyNameSource {

    @Override
    public String getName(int position) {
        return "";
    }

    public Class  getType(int position){
        return String.class;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
