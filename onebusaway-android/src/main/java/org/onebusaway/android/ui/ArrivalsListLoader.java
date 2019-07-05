/*
 * Copyright (C) 2012-2013 Paul Watts (paulcwatts@gmail.com)
 * and individual contributors.
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
package org.onebusaway.android.ui;

import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.io.request.ObaArrivalInfoRequest;
import org.onebusaway.android.io.request.ObaArrivalInfoResponse;
import org.onebusaway.android.io.request.bikeOnBus.BikeRackRequest;
import org.onebusaway.android.io.request.bikeOnBus.BikeRackResponse;
import org.onebusaway.android.util.BikeRackUtils;

import android.content.Context;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import java.time.Duration;
import java.time.Instant;


public class ArrivalsListLoader extends AsyncTaskLoader<ObaArrivalInfoResponse> {

    private final String mStopId;

    private ObaArrivalInfoResponse mLastGoodResponse;

    private long mLastResponseTime = 0;

    private long mLastGoodResponseTime = 0;

    // Shows vehicles arriving or departing in the next "mMinutesAfter" minutes
    private int mMinutesAfter = DEFAULT_MINUTES_AFTER;

    public static final int DEFAULT_MINUTES_AFTER = 65;

    private static final int MINUTES_INCREMENT = 60; // minutes

    public ArrivalsListLoader(Context context, String stopId) {
        super(context);
        mStopId = stopId;
    }

    @Override
    public ObaArrivalInfoResponse loadInBackground() {
        ObaArrivalInfoResponse resp = ObaArrivalInfoRequest
                .newRequest(getContext(), mStopId, mMinutesAfter).call();
        ObaArrivalInfo[] arrivals = resp.getArrivalInfo();
        double start = System.currentTimeMillis();
//        for (ObaArrivalInfo arrival : arrivals) {
////            TODO: Use the proper request format rather than a util function
////            BikeRackResponse bikeResp = BikeRackRequest
////                    .newRequest(getContext(), arrival.getVehicleId()).call();
////            Log.e("ArrivalsListAdapterStyleA", "bikeResp: " + bikeResp.getAvailableSpots());
////            arrival.setBikeSpaces(bikeResp.getAvailableSpots(getContext(), arrival.getVehicleId()));
//
//            arrival.setBikeSpaces(BikeRackUtils.getAvailableSpots(getContext(), arrival.getVehicleId()));
//        }
        BikeRackUtils.populateWithBikeStatus(getContext(), arrivals);
        double end = System.currentTimeMillis();
        Log.e("ArrivalsListLoader", "elapsed time to load: " + (end - start) / 1000.0);
        resp.setArrivalInfo(arrivals);
        return resp;
    }

    @Override
    public void deliverResult(ObaArrivalInfoResponse data) {
        mLastResponseTime = System.currentTimeMillis();
        if (data.getCode() == ObaApi.OBA_OK) {
            mLastGoodResponse = data;
            mLastGoodResponseTime = mLastResponseTime;
        }
        super.deliverResult(data);
    }

    public long getLastResponseTime() {
        return mLastResponseTime;
    }

    public ObaArrivalInfoResponse getLastGoodResponse() {
        return mLastGoodResponse;
    }

    public long getLastGoodResponseTime() {
        return mLastGoodResponseTime;
    }

    public void incrementMinutesAfter() {
        mMinutesAfter = mMinutesAfter + MINUTES_INCREMENT;
    }

    public int getMinutesAfter() {
        return mMinutesAfter;
    }

    /**
     * Handles a request to stop the Loader.
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    /**
     * Handles a request to completely reset the Loader.
     */
    @Override
    protected void onReset() {
        super.onReset();
        mLastGoodResponse = null;
        mLastGoodResponseTime = 0;
        // Ensure the loader is stopped
        onStopLoading();
    }
}
