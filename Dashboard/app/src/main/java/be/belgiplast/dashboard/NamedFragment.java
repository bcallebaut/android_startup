package be.belgiplast.dashboard;

import android.support.v4.app.Fragment;

public abstract class NamedFragment extends Fragment {
    private String name;

    protected NamedFragment(String name) {
        this.name = name;
    }

    public NamedFragment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
