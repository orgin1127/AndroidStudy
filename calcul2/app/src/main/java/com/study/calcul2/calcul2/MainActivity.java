package com.study.calcul2.calcul2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button[] bts = new Button[10];
    Button clear, calculate, multiply;
    Button division, bplus, bminus;;
    TextView input1, input2;
    int btid;
    int total = 0;
    String giho = "";

    //수식 표시
    String inputNumber = "";
    //입력값, 계산값 표시
    String inputNumber2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.calcullayout);
        NumberButtonHandler nbh = new NumberButtonHandler();

        Log.d("확인",bts.length+"");
        for (int i=0; i < bts.length; i++) {
            btid = getResources().getIdentifier("bt"+i, "id", "com.study.calcul2.calcul2");
            bts[i] = (Button) findViewById(btid);
            bts[i].setOnClickListener(nbh);

        }
        clear = (Button) findViewById(R.id.clear);
        calculate = (Button) findViewById(R.id.calculate);
        division = (Button) findViewById(R.id.division);
        bplus = (Button) findViewById(R.id.plus);
        bminus = (Button) findViewById(R.id.minus);
        multiply = (Button) findViewById(R.id.multiply);

        clear.setOnClickListener(nbh);
        calculate.setOnClickListener(nbh);
        division.setOnClickListener(nbh);
        bplus.setOnClickListener(nbh);
        bminus.setOnClickListener(nbh);
        multiply.setOnClickListener(nbh);

        input1 = (TextView) findViewById(R.id.input1);
        input2 = (TextView) findViewById(R.id.input2);
        input2.setText(total+"");
    }

    class NumberButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Button b = (Button) view;
            inputNumber = input1.getText().toString();

            if(Pattern.matches("^[0-9]*$",b.getText())) {
                inputNumber2 += b.getText()+"";
                input2.setText(inputNumber2);
                Log.d("NumberCheck", b.getText()+"");
            }

            else if (b.getText().equals("지우기")) {
                total = 0;
                input1.setText("");
                input2.setText("");
                inputNumber = "";
                inputNumber2 = "";
                giho = "";
            }
            else if (b.getText().equals("=")){
                if (giho.equals("+")) {
                    total += Integer.parseInt(inputNumber2);
                    input1.setText("");
                    input2.setText(total+"");
                    giho = "";
                    total = 0;
                    inputNumber = "";
                    inputNumber2 = "";
                }
                else if(giho.equals("-")) {
                    total -= Integer.parseInt(inputNumber2);
                    input1.setText("");
                    input2.setText(total+"");
                    giho = "";
                    total = 0;
                    inputNumber = "";
                    inputNumber2 = "";
                }
                else if(giho.equals("*")) {
                    total *= Integer.parseInt(inputNumber2);
                    input1.setText("");
                    input2.setText(total+"");
                    giho = "";
                    total = 0;
                    inputNumber = "";
                    inputNumber2 = "";
                }
                else if(giho.equals("/")) {
                    total /= Integer.parseInt(inputNumber2);
                    input1.setText("");
                    input2.setText(total+"");
                    giho = "";
                    total = 0;
                    inputNumber = "";
                    inputNumber2 = "";
                }
            }
            else {
                switch (b.getId()) {
                    case R.id.plus :
                        if (giho.equals("+")) {
                            total += Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 + "+");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "+";
                            break;
                        }
                        else if (giho.equals("-")) {
                            total -= Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"+");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "+";
                            break;
                        }
                        else if (giho.equals("*")) {
                            total *= Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"+");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "+";
                            break;
                        }
                        else if (giho.equals("/")) {
                            total /= Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"+");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "+";
                            break;
                        }
                        else {
                            total += Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 + "+");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "+";
                            break;
                        }
                    case R.id.minus :
                        if (giho.equals("+")) {
                            total += Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"-");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "-";
                            break;
                        }
                        else if (giho.equals("-")) {
                            total -= Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"-");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "-";
                            break;
                        }
                        else if (giho.equals("*")) {
                            total *= Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"-");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "-";
                            break;
                        }
                        else if (giho.equals("/")) {
                            total /= Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"-");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "-";
                            break;
                        }
                        else {
                            total = Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 + "-");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "-";
                            break;
                        }
                    case R.id.multiply :
                        if (giho.equals("+")) {
                            total += Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"*");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "*";
                            break;
                        }
                        else if (giho.equals("-")) {
                            total -= Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"*");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "-";
                            break;
                        }
                        else if (giho.equals("*")) {
                            total *= Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"*");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "*";
                            break;
                        }
                        else if (giho.equals("/")) {
                            total /= Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"*");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "*";
                            break;
                        }
                        else {
                            total = Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 + "*");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "*";
                            break;
                        }
                    case R.id.division :
                        if (giho.equals("+")) {
                            total += Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"/");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "/";
                            break;
                        }
                        else if (giho.equals("-")) {
                            total -= Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"/");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "/";
                            break;
                        }
                        else if (giho.equals("*")) {
                            total *= Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"/");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "/";
                            break;
                        }
                        else if (giho.equals("/")) {
                            total /= Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 +"/");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "/";
                            break;
                        }
                        else {
                            total = Integer.parseInt(inputNumber2);
                            input1.setText(inputNumber + inputNumber2 + "/");
                            input2.setText(total+"");
                            inputNumber2 = "";
                            giho = "/";
                            break;
                        }
                }
            }
        }
    }


}
