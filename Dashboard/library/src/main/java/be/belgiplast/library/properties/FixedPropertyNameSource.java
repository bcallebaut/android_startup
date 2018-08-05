package be.belgiplast.library.properties;

import java.util.Collection;
import java.util.Map;

public final class FixedPropertyNameSource implements PropertyNameSource {
    private String[] names = new String[0];
    private Class[] types = new Class[0];

    public FixedPropertyNameSource(String... names) {
        this.names = new String[names.length];
        System.arraycopy(names,0,this.names,0,names.length);
        this.types = new Class[this.names.length];
        for (int i = 0; i < this.types.length ; i++)
            types[i] = String.class;
    }

    public FixedPropertyNameSource(Collection<String> names) {
        this.names = new String[names.size()];
        int i = 0;
        for (String s : names) this.names[i++] = s;
        this.types = new Class[this.names.length];
        for ( i = 0; i < this.types.length ; i++)
            types[i] = String.class;
    }

    public FixedPropertyNameSource(Map<String,Class> names) {
        this.names = new String[names.size()];
        int i = 0;
        this.types = new Class[this.names.length];
        for (String s : names.keySet()){
            this.names[i] = s;
            this.types[i++] = names.get(s);
        }
    }

    @Override
    public final String getName(int position) {
        return names[position % names.length];
    }

    public Class  getType(int position){
        return String.class;
    }

    @Override
    public final int getCount() {
        return names.length;
    }
}
