package com.mainul35.exceptions;

public class LimitReachedException extends RuntimeException {
    public LimitReachedException(String maximum_limit_reached) {
        super(maximum_limit_reached);
    }
}
