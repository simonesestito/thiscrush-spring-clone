package it.simonesestito.tsclone.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Getter
@NoArgsConstructor
public class SimpleResult {
    private Boolean success;
    private String message;

    public SimpleResult setBindingError(BindingResult result){
        setSuccess(false);
        FieldError error = result.getFieldErrors().get(0);
        String msg = error.getField() + ": " + error.getDefaultMessage();
        setMessage(msg);
        return this;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
        if (message == null)
            message = "Success.";
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
