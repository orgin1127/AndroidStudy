package com.study.calcul.calculrator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button bts[] = new Button[10];
    Button b0, b1, b2;
    Button b3, b4, b5;
    Button b6, b7, b8;
    Button b9, bplus, bminus;
    Button clear, calculate, multiply;
    Button division;
    TextView text1;
    TextView etResult;
    int btid;
    int calcResult = 0;
    String inputN = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.calclayout);

        ButtonHandler bh = new ButtonHandler();

        for (int i = 0; i <= 9; i++) {
            btid = getResources().getIdentifier("bt"+i, "id","com.study.calcul.calculrator");
            bts[i] = (Button) findViewById(btid);
            bts[i].setOnClickListener(bh);
        }

        etResult = (TextView) findViewById(R.id.inputT2);

        text1 = (TextView) findViewById(R.id.inputT);

        clear = (Button) findViewById(R.id.clear);
        calculate = (Button) findViewById(R.id.calculate);
        division = (Button) findViewById(R.id.division);
        bplus = (Button) findViewById(R.id.plus);
        bminus = (Button) findViewById(R.id.minus);
        multiply = (Button) findViewById(R.id.multiply);

        OperatorHandler oh = new OperatorHandler();

        clear.setOnClickListener(oh);
        calculate.setOnClickListener(oh);
        division.setOnClickListener(oh);
        bplus.setOnClickListener(oh);
        bminus.setOnClickListener(oh);
        multiply.setOnClickListener(oh);

    }

    class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String input = text1.getText().toString();

            for (int i = 0; i < bts.length; i++) {

                if (view == bts[i]) {
                    //etResult.setText(bts[i].getText().toString());
                    if (input.isEmpty() && bts[i].getText().toString().equals("0")) {
                        return;
                    }
                    inputN += bts[i].getText().toString();
                    Log.d("inputN",inputN);
                    text1.setText(input + bts[i].getText().toString());
                }
            }
        }
    }

    class OperatorHandler implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String input = text1.getText().toString();
            int lastNum = 0;
            if (input.isEmpty()) {
                return;
            }
            switch (view.getId()){
                case R.id.clear:
                    etResult.setText("");
                    text1.setText("");
                    calcResult = 0;
                    inputN = "";
                    break;
                case R.id.calculate:

                    break;
                case R.id.division:
                    if (calcResult == 0) {
                        text1.setText(input+"/");
                        lastNum = Integer.parseInt(inputN);
                        calcResult = lastNum;
                        inputN = "";
                        break;

                    }
                    else {
                        text1.setText(input+"/");
                        lastNum = Integer.parseInt(inputN);
                        if(input.substring(input.length()-2).contains("+")) {
                            calcResult += lastNum;
                        }
                        else if(input.substring(input.length()-2).contains("-")) {
                            calcResult -= lastNum;
                        }
                        else if(input.substring(input.length()-2).contains("*")) {
                            calcResult *= lastNum;
                        }
                        else if(input.substring(input.length()-2).contains("/")) {
                            calcResult /= lastNum;
                        }
                        etResult.setText(calcResult+"");
                    }
                    inputN = "";
                    break;
                case R.id.plus:
                    if (calcResult == 0) {
                        text1.setText(input+"+");
                        lastNum = Integer.parseInt(inputN);
                        calcResult = lastNum;
                        inputN = "";
                        break;
                    }
                    else {
                        text1.setText(input+"+");
                        lastNum = Integer.parseInt(inputN);
                        Log.d("lastN",lastNum+"");
                        if(input.substring(input.length()-2).contains("+")) {
                            calcResult += lastNum;
                        }
                        else if(input.substring(input.length()-2).contains("-")) {
                            calcResult -= lastNum;
                        }
                        else if(input.substring(input.length()-2).contains("*")) {
                            calcResult *= lastNum;
                        }
                        else if(input.substring(input.length()-2).contains("/")) {
                            calcResult /= lastNum;
                        }
                        etResult.setText(calcResult+"");
                    }
                    inputN = "";
                    break;
                case R.id.minus:

                    if (calcResult == 0) {
                        text1.setText(input+"-");
                        lastNum = Integer.parseInt(inputN);
                        calcResult = lastNum;
                        inputN = "";
                        break;
                    }
                    else {
                        text1.setText(input+"-");
                        lastNum = Integer.parseInt(inputN);
                        if(input.substring(input.length()-2).contains("+")) {
                            calcResult += lastNum;
                        }
                        else if(input.substring(input.length()-2).contains("-")) {
                            calcResult -= lastNum;
                        }
                        else if(input.substring(input.length()-2).contains("*")) {
                            calcResult *= lastNum;
                        }
                        else if(input.substring(input.length()-2).contains("/")) {
                            calcResult /= lastNum;
                        }
                        etResult.setText(calcResult+"");
                    }
                    inputN = "";
                    break;
                case R.id.multiply:
                    if (calcResult == 0) {
                        text1.setText(input+"*");
                        lastNum = Integer.parseInt(inputN);
                        calcResult = lastNum;
                        inputN = "";
                        break;
                    }
                    else {
                        text1.setText(input+"*");
                        lastNum = Integer.parseInt(inputN);
                        if(input.substring(input.length()-2).contains("+")) {
                            calcResult += lastNum;
                        }
                        else if(input.substring(input.length()-2).contains("-")) {
                            calcResult -= lastNum;
                        }
                        else if(input.substring(input.length()-2).contains("*")) {
                            calcResult *= lastNum;
                        }
                        else if(input.substring(input.length()-2).contains("/")) {
                            calcResult /= lastNum;
                        }
                        etResult.setText(calcResult+"");
                    }
                    inputN = "";
                    break;
                default:
                    break;
            }

        }
    }

}
