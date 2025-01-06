package com.blog.react_spring_blog.common.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    String resourceName;
    String fieldName;
    String fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s is not found[%s : %s]", resourceName, fieldName, fieldValue));


    }


}