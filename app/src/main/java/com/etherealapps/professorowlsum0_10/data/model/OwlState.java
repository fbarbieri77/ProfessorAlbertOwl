package com.etherealapps.professorowlsum0_10.data.model;

public enum OwlState {
    Hello("Oiiiiii"),
    Waiting("Ehhh??"),
    Yeah("Acertou"),
    Ohoh("Oh oh...  \u2260 %s"),
    Error("Ooops...Boom"),
    Bye("Bye Bye  -  \u2713 %d  :  \u2717 %d");


    public final String displayText;

    OwlState(String displayText) {
        this.displayText = displayText;
    }
}
