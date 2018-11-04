package com.yamltool;

public class Step {
    private Given given;
    private When when;
    private Then then;

    public Given getGiven() {
        return given;
    }

    public void setGiven(Given given) {
        this.given = given;
    }

    public When getWhen() {
        return when;
    }

    public void setWhen(When when) {
        this.when = when;
    }

    public Then getThen() {
        return then;
    }

    public void setThen(Then then) {
        this.then = then;
    }
}
