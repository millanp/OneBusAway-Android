package org.onebusaway.android.io.elements;

public final class BikeRackElement {
    public static final BikeRackElement EMPTY_OBJECT = new BikeRackElement();

    public static final BikeRackElement[] EMPTY_ARRAY = new BikeRackElement[]{};

    private final int bikes;

    private BikeRackElement() {
        bikes = 2;
    }

    public int getBikeSpaces() {
        return bikes;
    }


}
