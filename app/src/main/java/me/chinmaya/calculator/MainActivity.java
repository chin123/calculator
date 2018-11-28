package me.chinmaya.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.github.kexanie.library.MathView;

public class MainActivity extends AppCompatActivity {

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
    public void integral(View view) {
        String toInput = "\\(\\int\\)";
        Toast.makeText(this, "Pressed Integral", Toast.LENGTH_LONG).show();
        formula_two = (MathView) findViewById(R.id.formula_two);
        formula_two.setText(formula_two.getText() + toInput);
    }
}
