package adapter;

import model.Automobile;

public interface FixAuto {
    // Fixing the errors and returning resultant object.
    public Automobile fix(int errno);
}
