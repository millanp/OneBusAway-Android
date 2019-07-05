package org.onebusaway.android.io.elements;

public final class BikeRackElement {
    public static final BikeRackElement EMPTY_OBJECT = new BikeRackElement();

    public static final BikeRackElement[] EMPTY_ARRAY = new BikeRackElement[]{};

    private String[] BikeSpacesArray;

    private BikeRackElement() {
        BikeSpacesArray = new String[0];
    }

    public String[] getBikeSpaces() {
        return BikeSpacesArray;
    }


}
