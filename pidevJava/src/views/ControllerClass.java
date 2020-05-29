package views;

import models.Menu;
import models.Transport;
import models.utlisateur;

/**
 *
 * @author jaret_000
 */
public interface ControllerClass {
    //lmethode eli twarini kifech kenou data mtaa el user
    public abstract void preloadData(Menu menu);
    public abstract void preloadData(Transport transport);

}
