package me.chinmaya.calculator;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;


public class api {

    private static RequestQueue requestQueue;
    public static void startAPICall() {

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://newton.now.sh" + "/factor/x^2-1",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            apiCallDone(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e("Error:", error.toString());
                }
            });

            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    static void apiCallDone(final JSONObject response) {
        try {
            Log.d("Newton: ", response.toString(2));
        } catch (JSONException ignored) { }
    }
}
