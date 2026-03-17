package Ex_5;

import java.util.ArrayList;
import java.util.List;

public interface Subject {

    void attach(Observer observer);

    void notifyObserver();
}
