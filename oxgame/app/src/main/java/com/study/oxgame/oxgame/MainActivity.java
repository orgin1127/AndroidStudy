package com.study.oxgame.oxgame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt;
    TextView tv1;
    Button btReStart;

    Numbers[][] bts = new Numbers[3][3];
    int btid;

    int playerTurn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //etContentView(R.layout.activity_main);
        setContentView(R.layout.oxlayout);
        int cnt = 1;
        ButtonHandler bh = new ButtonHandler();
        for(int i=0; i<bts.length; i++) {
            for(int y=0; y<bts.length; y++){
                btid = getResources().getIdentifier("bt"+cnt,"id","com.study.oxgame.oxgame");
                bts[i][y] = new Numbers((Button) findViewById(btid), true);
                bts[i][y].getBt().setOnClickListener(bh);
                cnt++;
            }
        }
        btReStart = (Button) findViewById(R.id.btReStart);
        btReStart.setOnClickListener(bh);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText("플레이어 1 차례입니다.");
        tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv1.setTextSize(20);
        tv1.setTextColor(Color.RED);
        playerTurn = 1;

    }



    class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == btReStart.getId()) {
                recreate();
            }
            int drawCount = 0;
            for (int i = 0; i < bts.length; i++) {
                for (int y = 0; y < bts.length; y++) {
                    if (view.getId() == bts[i][y].getBt().getId()) {
                        switch (playerTurn) {
                            case 1:
                                bts[i][y].getBt().setTextColor(Color.RED);
                                bts[i][y].setCheckable(false);
                                bts[i][y].setPlayerMark(1);
                                bts[i][y].getBt().setClickable(false);
                                if (checkWhosWin(bts[i][y].getBt())) {
                                    return;
                                }
                                else {
                                    tv1.setText("플레이어 2 차례입니다.");
                                    tv1.setTextColor(Color.BLUE);
                                    playerTurn = 2;
                                }
                                for(int x=0; x<bts.length; x++){
                                    for(int j=0; j<bts.length; j++){
                                        if (bts[x][j].isCheckable() == false) {
                                            drawCount++;
                                        }
                                    }
                                }
                                if(drawCount == 9) {
                                    tv1.setText("무승부입니다. 재시작을 눌러주세요.");
                                    return;
                                }
                                return;
                            case 2:
                                bts[i][y].getBt().setTextColor(Color.BLUE);
                                bts[i][y].setCheckable(false);
                                bts[i][y].setPlayerMark(2);
                                bts[i][y].getBt().setClickable(false);
                                if (checkWhosWin(bts[i][y].getBt())) {
                                    return;
                                }
                                for(int x=0; x<bts.length; x++){
                                    for(int j=0; j<bts.length; j++){
                                        if (bts[x][j].isCheckable() == false) {
                                            drawCount++;
                                        }
                                    }
                                }
                                if(drawCount == 9) {
                                    tv1.setText("무승부입니다. 재시작을 눌러주세요.");
                                    return;
                                }
                                tv1.setText("플레이어 1 차례입니다.");
                                tv1.setTextColor(Color.RED);
                                playerTurn = 1;
                                return;
                        }
                    }
                }
            }


        }
    }

    public boolean checkWhosWin(Button b) {
        int player1CheckCount = 0;
        int player2CheckCount = 0;
        int cnt = 0;

        boolean result = false;

        //horizontal
        for(int x=0; x<bts.length; x++) {
            player1CheckCount = 0;
            player2CheckCount = 0;
            for(int y=0; y<bts.length; y++){
                cnt++;
                if (bts[x][y].isCheckable()==false) {
                    if(bts[x][y].getPlayerMark()==1) player1CheckCount++;
                }
                if (bts[x][y].isCheckable()==false) {
                    if(bts[x][y].getPlayerMark()==2) player2CheckCount++;
                }
            }
            if (cnt==3 && player1CheckCount == 3) {
                player1CheckCount = 0;
                player2CheckCount = 0;
                Toast.makeText(MainActivity.this, "플레이어 1의 승리입니다.", Toast.LENGTH_LONG).show();
                tv1.setText("플레이어 1승리");
                result = true;
                return result;
            }
            else if(cnt==3 && player2CheckCount == 3) {
                player1CheckCount = 0;
                player2CheckCount = 0;
                Toast.makeText(MainActivity.this, "플레이어 2의 승리입니다.", Toast.LENGTH_LONG).show();
                tv1.setText("플레이어 2승리");
                result = true;
                return result;
            }
            cnt = 0;
        }

        //vertical
        for(int y=0; y<bts.length; y++) {
            player1CheckCount = 0;
            player2CheckCount = 0;
            for(int x=0; x<bts.length; x++) {
                cnt++;
                if (bts[x][y].isCheckable()==false) {
                    if(bts[x][y].getPlayerMark()==1) player1CheckCount++;
                }
                if (bts[x][y].isCheckable()==false) {
                    if(bts[x][y].getPlayerMark()==2) player2CheckCount++;
                }
            }
            if (cnt==3 && player1CheckCount == 3) {
                player1CheckCount = 0;
                player2CheckCount = 0;
                Toast.makeText(MainActivity.this, "플레이어 1의 승리입니다.", Toast.LENGTH_LONG).show();
                tv1.setText("플레이어 1승리");
                result = true;
                return result;
            }
            else if(cnt==3 && player2CheckCount == 3) {
                player1CheckCount = 0;
                player2CheckCount = 0;
                Toast.makeText(MainActivity.this, "플레이어 2의 승리입니다.", Toast.LENGTH_LONG).show();
                tv1.setText("플레이어 2승리");
                result = true;
                return result;
            }
            cnt = 0;
        }

        //diagonal-1
        player1CheckCount = 0;
        player2CheckCount = 0;
        for(int xy=0; xy<3; xy++){

            if (bts[xy][xy].isCheckable()==false) {
                if(bts[xy][xy].getPlayerMark()==1) player1CheckCount++;
            }
            if (bts[xy][xy].isCheckable()==false) {
                if(bts[xy][xy].getPlayerMark()==2) player2CheckCount++;
            }
            if (player1CheckCount == 3) {
                player1CheckCount = 0;
                player2CheckCount = 0;
                Toast.makeText(MainActivity.this, "플레이어 1의 승리입니다.", Toast.LENGTH_LONG).show();
                tv1.setText("플레이어 1승리");
                result = true;
                return result;
            }
            else if(player2CheckCount == 3) {
                player1CheckCount = 0;
                player2CheckCount = 0;
                Toast.makeText(MainActivity.this, "플레이어 2의 승리입니다.", Toast.LENGTH_LONG).show();
                tv1.setText("플레이어 2승리");
                result = true;
                return result;
            }
        }



        //diagonal-2
        player1CheckCount = 0;
        player2CheckCount = 0;
        int x = 0;
        int y = 2;
        for(int i=0; i<3; i++) {
            if (bts[x][y].isCheckable()==false) {
                if (bts[x][y].getPlayerMark() == 1) player1CheckCount++;
                else if (bts[x][y].getPlayerMark() == 2) player2CheckCount++;
            }
            x++;
            y--;
        }
        if (player1CheckCount == 3) {
            player1CheckCount = 0;
            player2CheckCount = 0;
            Toast.makeText(MainActivity.this, "플레이어 1의 승리입니다.", Toast.LENGTH_LONG).show();
            tv1.setText("플레이어 1승리");
            result = true;
            return result;
        }
        else if(player2CheckCount == 3) {
            player1CheckCount = 0;
            player2CheckCount = 0;
            Toast.makeText(MainActivity.this, "플레이어 2의 승리입니다.", Toast.LENGTH_LONG).show();
            tv1.setText("플레이어 2승리");
            result = true;
            return result;
        }
        return result;
    }
}
