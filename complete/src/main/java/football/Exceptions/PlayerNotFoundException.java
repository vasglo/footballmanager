package football.Exceptions;

public class PlayerNotFoundException extends RuntimeException {
    private static final String MASSAGE = "This player doesnt exist";

    public PlayerNotFoundException() {
        super(MASSAGE);
    }
}
