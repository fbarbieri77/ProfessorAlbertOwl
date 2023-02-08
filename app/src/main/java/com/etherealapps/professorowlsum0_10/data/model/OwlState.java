package com.etherealapps.professorowlsum0_10.data.model;

public enum OwlState {
    Hello("Hi, I'm Mr. Albert Owl"),
    Waiting("\u2713 %d  :  \u2717 %d"),
    Yeah("Uh Uh"),
    Ohoh("Oh oh...  \u2260 %s"),
    Error("Ooops...Boom"),
    Bye("Bye Bye  -  \u2713 %d  :  \u2717 %d");


    public final String displayText;

    OwlState(String displayText) {
        this.displayText = displayText;
    }
}
