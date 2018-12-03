package me.chinmaya.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.kexanie.library.MathView;

public class MainActivity extends AppCompatActivity {

    public static void startAPICall(final RequestQueue requestQueue, final String question) {
        try {
            Log.d("Newton: ", "Hello");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://127.0.0.1:5000/api/v1.0" + question,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d("Newton: ", "Hooray!!!");
                            apiCallDone(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e("Newton: ", error.toString());
                }
            });
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void apiCallDone(final JSONObject response) {
        try {
            Log.d("CompInt: ", response.toString(2));
        } catch (JSONException ignored) { }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    MathView formula_two;
    String tex = "\\(Formula:\\)";
    @Override
    protected void onResume() {
        super.onResume();

        formula_two = (MathView) findViewById(R.id.formula_two);
        formula_two.setText(tex);
    }
    public void search(View view) {
        String toInput = "\\(\\int\\)";
        Toast.makeText(this, "Performing calculation...", Toast.LENGTH_LONG).show();
        formula_two = (MathView) findViewById(R.id.formula_two);
        formula_two.setText(formula_two.getText() + toInput);
        //Toast.makeText(this, api.startAPICall(), Toast.LENGTH_LONG).show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        api.startAPICall(requestQueue);
    }
}
