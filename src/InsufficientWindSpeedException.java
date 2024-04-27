public class InsufficientWindSpeedException extends Exception {
    public InsufficientWindSpeedException() {
        super("A storm does not become a hurricane until it reaches a minimum 74 mph sustained winds.");
    }
}
