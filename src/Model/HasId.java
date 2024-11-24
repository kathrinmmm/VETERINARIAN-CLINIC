package Model;

import java.io.Serializable;

/**
 * The {@code HasId} interface is a marker interface that ensures implementing classes
 * have a unique identifier and can be serialized. This interface provides a contract
 * for retrieving an ID, which can be useful for objects that need a unique identity
 * in various data operations, such as storage or comparison.
 * <p>
 * Classes that implement this interface are guaranteed to have a unique identifier,
 * which is typically used for entity tracking, storage, or retrieval purposes.
 * </p>
 *
 * @see Serializable
 */
public interface HasId extends Serializable {

    /**
     * Retrieves the unique identifier of the object.
     * <p>
     * This method should return a unique ID that is used to identify the object.
     * The ID can be used in databases, collections, or any context where an object
     * needs to be uniquely identified.
     * </p>
     *
     * @return the unique ID of the object
     */
    Integer getId();
}
