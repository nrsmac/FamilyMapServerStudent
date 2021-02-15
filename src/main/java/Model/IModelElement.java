package Model;

/**
 * Represents any object in the model. Can be instantiated as a User, Person, or Event object.
 */
public interface IModelElement {

    String username = null;
    /**
     * @return the associated username with the element.
     */
    default String getUsername(){
        return this.username;
    }
    String toString();
    int hashCode();
}
