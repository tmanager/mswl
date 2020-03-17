package com.frank.mswl.mini.constant;

public enum CheckType {

    TURNITIN("0", "turnitin国际"), TURNITINUK("1", "turnitinUK"), GRAMMARLY("2", "Grammarly");

    private String value;
    private String name;

    private CheckType(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
