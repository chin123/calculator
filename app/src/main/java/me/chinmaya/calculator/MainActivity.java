package me.chinmaya.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.github.kexanie.library.MathView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    MathView formula_two;
    String tex = "Inline \\(\\KaTeX\\): " +
            " \\(ax^2 + bx + c = 0\\)" +
            " Not inline: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$";
    @Override
    protected void onResume() {
        super.onResume();

        formula_two = (MathView) findViewById(R.id.formula_two);
        formula_two.setText(tex);
    }
}
