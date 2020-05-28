package ServicesScolarite;

import Entites.Menu;
import Entites.Transport;
import Entites.utlisateur;

/**
 *
 * @author jaret_000
 */
public interface ControllerClass {
    //lmethode eli twarini kifech kenou data mtaa el user
    public abstract void preloadData(Menu menu);
    public abstract void preloadData(Transport transport);

}
