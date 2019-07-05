/*
 * Copyright (C) Sean J. Barbeau (sjbarbeau@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.map.googlemapsv2;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Location;
import android.util.SparseArray;

/**
 * Class to hold simple markers added to the map by classes external to this package.  Note that
 * this object uses a SparseArray internally, and therefore should only be used for small numbers
 * (e.g., less than several hundred) of markers.
 *
 * @author barbeau
 */
public class SimpleMarkerOverlay {

    GoogleMap mMap;

    /**
     * A map between unique IDs generated by this class per marker and the Marker object returned
     * by the GoogleMap.  The unique ID can be saved by external classes to remove the marker
     * at any time via removeMarker().
     */
    SparseArray<Marker> mMarkers = new SparseArray<>();

    int mMarkerId = 0;

    public SimpleMarkerOverlay(GoogleMap map) {
        mMap = map;
    }

    /**
     * Adds a generic marker to the map and returns the ID associated with that marker, which can
     * be used to remove the marker via removeMarker().
     *
     * @param l Location at which the marker should be added
     * @param hue The hue (color) of the marker. Value must be greater or equal to 0 and less than 360, or null if the default color should be used.
     * @return the ID associated with the marker that was just added
     */
    public synchronized int addMarker(Location l, Float hue) {
        MarkerOptions options = new MarkerOptions().position(MapHelpV2.makeLatLng(l));
        if (hue != null) {
            options.icon(BitmapDescriptorFactory.defaultMarker(hue));
        }
        Marker m = mMap.addMarker(options);
        mMarkers.put(mMarkerId, m);
        int temp = mMarkerId;
        mMarkerId++;
        return temp;
    }

    /**
     * Removes the marker from the map that has the given ID, which was previously generated by
     * addMarker() in this class.  This method is a no-op if no such marker exists for the given
     * markerId.
     *
     * @param markerId the ID for the marker that should be removed from the map
     */
    public void removeMarker(int markerId) {
        Marker m = mMarkers.get(markerId);
        if (m != null) {
            m.remove();
            mMarkers.remove(markerId);
        }
    }
}
