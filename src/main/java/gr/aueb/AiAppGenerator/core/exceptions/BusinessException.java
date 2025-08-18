package gr.aueb.AiAppGenerator.core.exceptions;

import lombok.Getter;

@Getter
public class BusinessException extends Exception{

    private final Integer errorCode;

    public BusinessException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
