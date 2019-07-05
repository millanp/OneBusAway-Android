/*
* Copyright (C) 2017 Rodrigo Carvalho (carvalhorr@gmail.com)
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.onebusaway.android.io.request.bikeOnBus;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.io.request.RequestBase;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.Callable;

/**
 * Request to retrieve the number of bikes on the rack for each of a list of bus arrivals.
 *
 */
public class BikeRackRequest extends RequestBase implements Callable<BikeRackResponse> {

    protected BikeRackRequest(Uri uri) {
        super(uri);
    }

    public static class Builder extends BuilderBase {

        public Builder(Context context, String[] vehicleIds) {
            super(context, getUrl(vehicleIds));
            setIsBikeServer(true);
        }

        public BikeRackRequest build() {
            return new BikeRackRequest(buildUri());
        }

        private static String getUrl(String[] vehicleIds){
            Gson gson = new Gson();
            String jsonString = "{\"vehicleIds\": " + gson.toJson(vehicleIds) + "}";
            try {
                return "?code=8FPnxWPvytYFiXvnHLop9at6/a1vDVaUtNYvklYJ7hGKSsw/VMSjag==&queryBody=" + URLEncoder.encode(jsonString, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * Helper method to construct new instances.
     *
     * @param context The package context.
     * @param arrivals An array of the arrival objects being queried
     * @return
     */
    public static BikeRackRequest newRequest(Context context, ObaArrivalInfo[] arrivals) {
        String[] vehicleIds = new String[arrivals.length];
        for (int i = 0; i < arrivals.length; i++) {
            vehicleIds[i] = arrivals[i].getVehicleId();
        }
        return new BikeRackRequest.Builder(context, vehicleIds).build();
    }

    @Override
    public BikeRackResponse call() {

        return call(BikeRackResponse.class);
    }


    @Override
    public String toString() {
        return "BikeRackRequest [mUri=" + mUri + "]";
    }

}
