package com.cedaniel200.screenplay.restapi.util;

public enum SOAPService {
    BASE_URL("http://www.thomas-bayer.com/axis2/services"),
    CONSULT_BANK("/BLZService");

    private String uri;

    SOAPService(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return uri;
    }
}
