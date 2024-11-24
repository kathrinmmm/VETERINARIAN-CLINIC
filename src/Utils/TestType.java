package Utils;

/**
 * Enum representing different types of diagnostic tests that can be performed on animals.
 * <p>
 * Each test type corresponds to a specific diagnostic method used to assess the health
 * of an animal, identify diseases, and assist veterinarians in making treatment decisions.
 * </p>
 */
public enum TestType {

    /**
     * Blood test, used to analyze an animal's blood for signs of infections, diseases,
     * or other medical conditions affecting the body.
     */
    BLOOD,

    /**
     * X-ray test, used for visualizing the internal structures of an animal's body,
     * often to diagnose bone fractures, tumors, or other structural issues.
     */
    X_RAY,

    /**
     * Ultrasound test, used for imaging soft tissues and organs inside the body,
     * such as the heart, liver, kidneys, and abdominal organs.
     */
    ULTRASOUND,

    /**
     * Allergy test, used to identify allergens that may be causing reactions in the animal,
     * such as food allergies, environmental allergens, or other sensitivities.
     */
    ALLERGY;
}
