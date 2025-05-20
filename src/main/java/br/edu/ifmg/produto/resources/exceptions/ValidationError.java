package br.edu.ifmg.produto.resources.exceptions;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private List<FieldMessage> fieldMessage = new ArrayList<FieldMessage>();

    public ValidationError() {}


    public List<FieldMessage> getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(List<FieldMessage> fieldMessage) {
        this.fieldMessage = fieldMessage;
    }

    public void addFieldMessage(String field, String message) {
        this.fieldMessage.add(new FieldMessage(field, message));
    }


}
