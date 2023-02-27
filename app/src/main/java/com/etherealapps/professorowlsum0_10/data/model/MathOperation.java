package com.etherealapps.professorowlsum0_10.data.model;

enum MathOperation {
    Sum("+"),
    Subtraction("-"),
    Multiplication("\u00D7"),
    Division("\u00F7");

    private final String _operator;

    MathOperation(String operator) {
        _operator = operator;
    }

    String operator() {
        return _operator;
    }
}
