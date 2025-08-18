package gr.aueb.AiAppGenerator.core.exceptions;

public class AppObjectNotAuthorizedException extends AppGenericException{


    private static final String DEFAULT_CODE = "Not Authorized";


    public AppObjectNotAuthorizedException(String code, String message) {
        super(code , message);
    }
}
