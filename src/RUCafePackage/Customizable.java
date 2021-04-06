package RUCafePackage;

/**
 * This interface contains the shell methods add and remove to be implements in other classes.
 * @author Isha Vora, Kathleen Eife
 */
public interface Customizable {

    /**
     * This method adds based on the parameter passed and the class it is implemented in.
     * @param obj object
     * @return true is added successfully, otherwise false
     */
    boolean add(Object obj);

    /**
     * This method removes based on the parameter passed and the class it is implemented in.
     * @param obj object
     * @return true is removed successfully, otherwise false
     */
    boolean remove(Object obj);
}
