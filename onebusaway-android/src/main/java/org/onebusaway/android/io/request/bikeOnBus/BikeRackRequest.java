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

import org.onebusaway.android.io.request.RequestBase;

import java.util.concurrent.Callable;

/**
 * Request to retrieve the bike stations from OTP server.
 *
 * Created by carvalhorr on 7/13/17.
 */
public class BikeRackRequest extends RequestBase implements Callable<BikeRackResponse> {

    protected BikeRackRequest(Uri uri) {
        super(uri);
    }

    public static class Builder extends BuilderBase {

        public Builder(Context context, String vehicleId) {
            super(context, getUrl(vehicleId));
            setIsBikeServer(true);
        }

        public BikeRackRequest build() {
            return new BikeRackRequest(buildUri());
        }

        private static String getUrl(String vehicleId){
            return "bikecount/bus/" + vehicleId;
        }
    }

    /**
     * Helper method to construct new instances.
     *
     * @param context The package context.
     * @param vehicleId The ID of the bus being queried; for example "1_7416"
     * @return
     */
    public static BikeRackRequest newRequest(Context context, String vehicleId) {
        return new BikeRackRequest.Builder(context, vehicleId).build();
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
