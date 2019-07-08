/*
* Copyright (C) 2017 Rodrigo Carvalho (carvalhorr@gmail.com)
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*l
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.onebusaway.android.io.request.bikeOnBus;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.onebusaway.android.io.elements.BikeRackElement;
import org.onebusaway.android.io.request.ObaResponse;
import org.opentripplanner.routing.bike_rental.BikeRentalStationList;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Response returned from bike_rental endpoint in OTP. The response is already defined in the
 * OTP pojos. This class was created here only to follow the convention of the other request/responses.
 *
 * Created by carvalhorr on 7/13/17.
 */
public class BikeRackResponse extends ObaResponse {

//    private static final class Data {
//
//        private static final Data EMPTY_OBJECT = new Data();
//
//        private final String[] BikeSpacesArray = new String[]{};
//    }

    private final Integer[] BikeSpacesArray;

    BikeRackResponse() {
        BikeSpacesArray = new Integer[]{};
    }

    // TODO: Make this work properly with the regions API

    public Integer[] getAvailableSpotsArray() {
        return BikeSpacesArray;
    }

}