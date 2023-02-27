package com.etherealapps.professorowlsum0_10.data.model;

public class Problem {
    private final Integer _number1;
    private final Integer _number2;
    private Integer _solution;
    private final String _mathOperator;
    private Boolean _solved;
    private Boolean _hasTried;

    public Problem(MathOperation mathOperation, Integer _number1, Integer _number2) {
        this._number1 = _number1;
        this._number2 = _number2;
        _mathOperator = mathOperation.operator();
        getResultForCurrentOperation(mathOperation, _number1, _number2);
        _solved = false;
        _hasTried = false;
    }

    private void getResultForCurrentOperation(MathOperation mathOperation, Integer _number1, Integer _number2) {
        switch (mathOperation) {
            case Sum:
                _solution = _number1 + _number2;
                break;
            case Subtraction:
                _solution = _number1 - _number2;
                break;
            case Multiplication:
                _solution = _number1 * _number2;
                break;
            case Division:
                if (_number2 != 0) {
                    _solution = _number1 / _number2;
                }
                break;
            default:
                break;
        }
    }

    public Integer number1() {
        return _number1;
    }

    public Integer number2() {
        return _number2;
    }

    public Integer solution() {
        return _solution;
    }

    public String mathOperator() {
        return _mathOperator;
    }

    public Boolean solved() {
        return _solved;
    }

    public void setSolved(Boolean s) {
        _solved = s;
        _hasTried = true;
    }

    public Boolean hasTried() {
        return _hasTried;
    }

    public void setHasTried(Boolean s) {
        _hasTried = s;
    }
}