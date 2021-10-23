package football.Exceptions;

public class TeamNotFoundException extends RuntimeException {
    private static final String MASSAGE = "this team doesn't exist";
    public TeamNotFoundException(){super(MASSAGE);}
}
