package org.onebusaway.android.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.onebusaway.android.io.elements.BikeRackElement;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.io.request.bikeOnBus.BikeRackRequest;
import org.onebusaway.android.io.request.bikeOnBus.BikeRackResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class BikeRackUtils {
    public static final int getAvailableSpots(Context context, String vehicleId) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String urlStub = "https://dummy3.azurewebsites.net/api/DummyData?code=8FPnxWPvytYFiXvnHLop9at6/a1vDVaUtNYvklYJ7hGKSsw/VMSjag==&vehicleId=";
        String url = urlStub + vehicleId;
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), future, future);
        requestQueue.add(request);
        try {
            JSONObject result = future.get();
            try {
                return result.getInt("BikeSpaces");
            } catch (JSONException e) {
                return 0;
            }
        } catch (InterruptedException e) {
            return 0;
        } catch (ExecutionException e) {
            return 0;
        }

    }

//    public static final void populateWithBikeStatus(Context context, ObaArrivalInfo[] arrivals) {
//        String[] vehicleIds = new String[arrivals.length];
//        for (int i = 0; i < arrivals.length; i++) {
//            vehicleIds[i] = arrivals[i].getVehicleId();
//        }
//        BikeRackResponse resp = BikeRackRequest.newRequest(context, vehicleIds).call();
//        String[] elemArray = resp.getAvailableSpotsArray();
//        for (int i = 0; i < elemArray.length; i++) {
//            arrivals[i].setBikeSpaces(Integer.parseInt(elemArray[i]));
//        }
//    }

}
