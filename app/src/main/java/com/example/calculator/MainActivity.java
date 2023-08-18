package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
TextView resultsTv,solutionTv;
MaterialButton cbtn , openbrac, btnbracclose;
    MaterialButton div,mul,plus,minus,equals;
    MaterialButton one,two,three,four,five,six,seven,eight,nine,zero;
    MaterialButton AC,point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultsTv = findViewById(R.id.results_Tv);
        solutionTv = findViewById(R.id.solution_Tv);
        assignId(cbtn, R.id.button_C);
        assignId(openbrac, R.id.openbracket);
        assignId(btnbracclose, R.id.closebracket);
        assignId(div, R.id.dividebtn);
        assignId(mul, R.id.mulbtn);
        assignId(equals, R.id.equalbtn);
        assignId(one, R.id.onebtn);
        assignId(two, R.id.twobtn);
        assignId(three, R.id.threebtn);
        assignId(four, R.id.fourbtn);
        assignId(five, R.id.fivebtn);
        assignId(six, R.id.sixbtn);
        assignId(seven, R.id.sevenbtn);
        assignId(eight, R.id.eightbtn);
        assignId(nine, R.id.ninebtn);
        assignId(zero, R.id.zerobtn);
        assignId(point, R.id.dotbtn);
        assignId(AC, R.id.ACbtn);
        assignId(plus, R.id.plusbtn);
        assignId(minus, R.id.minusbtn);






    }
    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();
        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultsTv.setText("");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultsTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }


        solutionTv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("Err")) {
            resultsTv.setText(finalResult);
        }
     //   solutionTv.setText(buttonText);


    }
    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }
       // return "calculated";
    }
