package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.InsetDialogOnTouchListener;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultadoV, solucionV;
    MaterialButton buttonC, buttonAp, buttonCp, buttonSlash;
    MaterialButton buttonMulti, buttonDivi, buttonRest, buttonSum, buttonIgual;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAc, buttonPunto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultadoV = findViewById(R.id.resultadoV);
        solucionV = findViewById(R.id.solucionV);

        buttonC = findViewById(R.id.button_C);
        buttonAp = findViewById(R.id.button_parentesis_abierto);
        buttonCp = findViewById(R.id.button_parentesis_Cerrado);
        buttonSlash = findViewById(R.id.button_parentesis_slash);
        buttonMulti = findViewById(R.id.button_multiplicar);
        buttonRest = findViewById(R.id.button_restar);
        buttonSum = findViewById(R.id.button_sumar);
        buttonIgual = findViewById(R.id.button_igual);
        button0 = findViewById(R.id.button_0);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        buttonAc = findViewById(R.id.button_ac);
        buttonPunto = findViewById(R.id.button_punto);

        buttonC.setOnClickListener(this);
        buttonAp.setOnClickListener(this);
        buttonCp.setOnClickListener(this);
        buttonSlash.setOnClickListener(this);
        buttonMulti.setOnClickListener(this);
        buttonRest.setOnClickListener(this);
        buttonSum.setOnClickListener(this);
        buttonIgual.setOnClickListener(this);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonAc.setOnClickListener(this);
        buttonPunto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solucionV.getText().toString();

        if (buttonText.equals("AC")) {
            solucionV.setText("");
            resultadoV.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solucionV.setText(resultadoV.getText());
            return;
        }
        if (buttonText.equals("c")) {
            if (!dataToCalculate.equals("0")) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }


        solucionV.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Error"))
            resultadoV.setText(finalResult);
        else
            resultadoV.setText("");
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            Object result = context.evaluateString(scriptable, data, "Javascript", 1, null);

            String finalResult;
            if (result == null || result == Context.getUndefinedValue()) {
                finalResult = "";
            } else {
                finalResult = Context.toString(result);
                if (finalResult.endsWith(".0")) {
                    finalResult = finalResult.replace(".0", "");
                }
            }

            return finalResult;
        } catch (Exception e) {
            return "Error";
        }
    }

}
