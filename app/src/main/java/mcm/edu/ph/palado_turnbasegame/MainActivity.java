package mcm.edu.ph.palado_turnbasegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtHeroName, txtMonsName, txtHeroHP, txtMonsHP, txtHeroMP, txtMonsMP, txtHeroDPS, txtMonsDPS, txtLog;
    Button btnNextTurn;
    ImageButton skill1;

    //Hero Stats
    String heroName = "Daddy";
    int heroHP = 1500;
    int heroMP = 1000;
    int heroMinDamage = 100;
    int heroMaxDamage = 150;

    //Monster Stats
    String monsName = "Mommy";
    int monsterHP = 3000;
    int monsterMP = 400;
    int monsterMinDamage = 40;
    int monsterMaxDamage = 55;
    //Game Turn
    int turnNumber = 1;
    int buttoncounter = 2;

    boolean disabledstatus = false;
    int statuscounter = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableFullscreen();
        setContentView(R.layout.activity_main);
        //XML ids for text and button

        txtHeroName = findViewById(R.id.txtHeroName);
        txtMonsName = findViewById(R.id.txtMonsName);
        txtHeroHP = findViewById(R.id.txtHeroHP);
        txtMonsHP = findViewById(R.id.txtMonsHP);
        txtHeroMP = findViewById(R.id.txtHeroMP);
        txtMonsMP = findViewById(R.id.txtMonsMP);
        btnNextTurn = findViewById(R.id.btnNxtTurn);
        txtHeroDPS = findViewById(R.id.txtHeroDPS);
        txtMonsDPS = findViewById(R.id.txtMonsDPS);

        txtLog = findViewById(R.id.txtCombatLog);

        txtHeroName.setText(heroName);
        txtHeroHP.setText(String.valueOf(heroHP));
        txtHeroMP.setText(String.valueOf(heroMP));

        txtMonsName.setText(monsName);
        txtMonsHP.setText(String.valueOf(monsterHP));
        txtMonsMP.setText(String.valueOf(monsterMP));

        txtHeroDPS.setText(String.valueOf(heroMinDamage)+ " ~ "+ String.valueOf(heroMaxDamage));
        txtMonsDPS.setText(String.valueOf(monsterMinDamage)+ " ~ "+ String.valueOf(monsterMaxDamage));





        //button onClick Listener
        btnNextTurn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        txtLog.findViewById(R.id.txtCombatLog);

        Random randomizer = new Random();
        int herodps = randomizer.nextInt(heroMaxDamage - heroMinDamage) + heroMinDamage ;
        int monsdps = randomizer.nextInt(monsterMaxDamage - monsterMinDamage) + monsterMinDamage ;

        if(turnNumber % 2 != 1){//if it is enemy's turn, disable button
        }
        else if(buttoncounter==0){
        }

        switch(v.getId()) {
            case R.id.btnNxtTurn:
                //

                if(turnNumber % 2 == 1){ //odd
                    monsterHP = monsterHP - herodps;
                    turnNumber++;
                    txtMonsHP.setText(String.valueOf(monsterHP));
                    btnNextTurn.setText("Next Turn ("+ String.valueOf(turnNumber)+")");

                    txtLog.setText("Our Hero "+String.valueOf(heroName) +" dealt "+String.valueOf(herodps) + " damage to the enemy.");

                    if(monsterHP < 0){ //even
                        txtLog.setText("Our Hero "+String.valueOf(heroName) +" dealt "+String.valueOf(herodps) + " damage to the enemy. The player is victorious!");
                        heroHP = 1500;
                        monsterHP = 3000;
                        turnNumber= 1;
                        buttoncounter=0;
                        btnNextTurn.setText("Reset Game");
                    }

                    buttoncounter--;
                }
                else if(turnNumber%2 != 1){ //even

                    if(disabledstatus==true){
                        txtLog.setText("The enemy is still stunned for "+String.valueOf(statuscounter)+ "turns");
                        statuscounter--;
                        turnNumber++;
                        btnNextTurn.setText("Next Turn ("+ String.valueOf(turnNumber)+")");
                        if(statuscounter==0){
                            disabledstatus=false;
                        }
                    }
                    else{
                        heroHP = heroHP - monsdps;
                        turnNumber++;
                        txtHeroHP.setText(String.valueOf(heroHP));
                        btnNextTurn.setText("Next Turn ("+ String.valueOf(turnNumber)+")");

                        txtLog.setText("The monster "+String.valueOf(monsName)+" dealt "+String.valueOf(monsdps)+ " damage to the enemy.");

                        if(heroHP < 0){
                            txtLog.setText("The monster "+String.valueOf(monsName)+" dealt "+String.valueOf(monsdps)+ " damage to the enemy. Game Over");
                            heroHP = 1500;
                            monsterHP = 3000;
                            turnNumber= 1;
                            buttoncounter=0;
                            btnNextTurn.setText("Reset Game");
                        }
                    }

                }
                break;
        }
    }
    private void enableFullscreen() {
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            );
        }
    }

}