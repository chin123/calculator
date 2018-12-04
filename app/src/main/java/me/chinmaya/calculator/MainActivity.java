package me.chinmaya.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

import io.github.kexanie.library.MathView;

public class MainActivity extends AppCompatActivity {

    public void startAPICall(final RequestQueue requestQueue, final String question) {
        Toast.makeText(this, "Before: " + question, Toast.LENGTH_LONG).show();
        try {
            Log.d("compint: ", "Start api call try block");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://10.0.2.2:5000/api/v1.0/" + question,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d("Newton: ", "OnResponse reached");
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

    void apiCallDone(final JSONObject response) {
        try {
            Log.d("CompInt: ", response.toString(2));
            Toast.makeText(this, response.get("solution").toString(), Toast.LENGTH_LONG).show();
            formula_two = (MathView) findViewById(R.id.formula_two);
            formula_two.setText("$$" + response.get("solution").toString() + "$$");
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
        TextView t = findViewById(R.id.query);
        startAPICall(requestQueue, t.getText().toString());
    }
}
