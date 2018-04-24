package com.widerspiel.fortnatawallpapers;


public class preview {

    private int uri;
    private String name;

    private boolean isNew = false;

    protected int uri() {
        return uri;
    }

    protected String name() {
        return name;
    }


    protected boolean isNew() {
        return isNew;
    }

    public preview(int IMAGE_URL, String NAME, boolean IS_NEW) {
        uri = IMAGE_URL;
        name = NAME;
        isNew = IS_NEW;
    }
}
