
public abstract interface SortedADT {

    public class NotFoundException extends Exception{}

    public class NotUniqueException extends Exception{}

    public abstract void insert (String object) throws NotUniqueException;

    public abstract String remove(String object) throws NotFoundException;

    public abstract String find(String object) throws NotFoundException;

    public abstract PetNode rNode(String object) throws NotFoundException;
}
