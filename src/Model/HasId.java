package Model;

import java.io.Serializable;

/**
 * The {@code HasID} interface is a marker interface that ensures implementing classes
 * have a unique identifier and can be serialized. This interface provides a contract
 * for retrieving an ID, which can be useful for objects that need a unique identity
 * in various data operations.
 */
public interface HasId extends Serializable {

    /**
     * Retrieves the unique identifier of the object.
     *
     * @return the unique ID of the object
     */
    Integer getId();
}