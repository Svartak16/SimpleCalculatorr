package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ipsec.ike.TunnelModeChildSessionParams;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
 TextView resultTv,solutionTv;
 MaterialButton btn_cancel,btnOpenBracket, btnCloseBracket;
 MaterialButton btnDivide, btnAdd, btnSub, btnMul, btnEqual, btnAc, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero, btnDot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);

        assignId(btn_cancel, R.id.btn_Cancel);
        assignId(btnOpenBracket, R.id.btn_OpenBracket);
        assignId(btnCloseBracket, R.id.btn_CloseBracket);
        assignId(btnZero, R.id.btn_Zero);
        assignId(btnOne, R.id.btn_One);
        assignId(btnTwo, R.id.btn_Two);
        assignId(btnThree, R.id.btn_Three);
        assignId(btnFour, R.id.btn_Four);
        assignId(btnFive, R.id.btn_Five);
        assignId(btnSix, R.id.btn_Six);
        assignId(btnSeven, R.id.btn_Seven);
        assignId(btnEight, R.id.btn_Eight);
        assignId(btnNine, R.id.btn_Nine);
        assignId(btnEqual, R.id.btn_Equal);
        assignId(btnSub, R.id.btn_Subtract);
        assignId(btnMul, R.id.btn_multiply);
        assignId(btnDivide, R.id.btn_divide);
        assignId(btnDot, R.id.btn_Dot);
        assignId(btnAdd, R.id.btn_Add);
        assignId(btnAc, R.id.btn_Ac);

    }
 void assignId(MaterialButton btn,int id)
 {
     btn=findViewById(id);
     btn.setOnClickListener(this);
 }
    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        solutionTv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Err")) {
            resultTv.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            Context context= Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }


    }
}