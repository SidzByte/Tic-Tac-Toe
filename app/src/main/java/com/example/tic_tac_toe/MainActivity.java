package com.example.tic_tac_toe;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private MaterialButton[] buttons = new MaterialButton[9];
    private TextView statusText;
    private MaterialButton btnRestart;
    private int flag = 0; // 0 for X, 1 for O
    private int count = 0;
    private boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.statusText);
        btnRestart = findViewById(R.id.btnRestart);

        // Initialize buttons in a loop for cleaner code
        for (int i = 0; i < 9; i++) {
            String buttonID = "btn" + (i + 1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
        }

        btnRestart.setOnClickListener(v -> restart());
    }

    public void Check(View view) {
        MaterialButton btnCurrent = (MaterialButton) view;

        // 1. Experienced check: Prevent clicking if game over or cell filled
        if (!gameActive || !btnCurrent.getText().toString().equals("")) {
            return;
        }

        count++;

        if (flag == 0) {
            btnCurrent.setText("X");
            btnCurrent.setTextColor(ContextCompat.getColor(this, R.color.color_x));
            statusText.setText("Player O's Turn");
            flag = 1;
        } else {
            btnCurrent.setText("O");
            btnCurrent.setTextColor(ContextCompat.getColor(this, R.color.color_o));
            statusText.setText("Player X's Turn");
            flag = 0;
        }

        // Add a small scale animation for every tap
        animateButtonTap(btnCurrent);

        if (count > 4) {
            checkWinner();
        }
    }

    private void animateButtonTap(View view) {
        view.setScaleX(0.7f);
        view.setScaleY(0.7f);
        view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).setInterpolator(new AccelerateDecelerateInterpolator()).start();
    }

    private void checkWinner() {
        int[][] winPositions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Cols
                {0, 4, 8}, {2, 4, 6}             // Diagonals
        };

        for (int[] pos : winPositions) {
            String b1 = buttons[pos[0]].getText().toString();
            String b2 = buttons[pos[1]].getText().toString();
            String b3 = buttons[pos[2]].getText().toString();

            if (!b1.isEmpty() && b1.equals(b2) && b2.equals(b3)) {
                statusText.setText("Winner is " + b1 + "!");
                statusText.setTextColor(ContextCompat.getColor(this, R.color.winner_highlight));
                gameActive = false;
                
                // Animate the winning buttons
                highlightWinner(buttons[pos[0]], buttons[pos[1]], buttons[pos[2]]);
                return;
            }
        }

        if (count == 9) {
            statusText.setText("It's a Draw!");
            gameActive = false;
        }
    }

    private void highlightWinner(MaterialButton... winButtons) {
        int colorFrom = ContextCompat.getColor(this, R.color.primaryContainer);
        int colorTo = ContextCompat.getColor(this, R.color.winner_highlight);

        for (MaterialButton btn : winButtons) {
            // Background color animation
            ObjectAnimator colorAnim = ObjectAnimator.ofObject(btn, "backgroundColor", new ArgbEvaluator(), colorFrom, colorTo);
            colorAnim.setDuration(600);
            colorAnim.setRepeatCount(1);
            colorAnim.setRepeatMode(ObjectAnimator.REVERSE);
            colorAnim.start();

            // Scale animation to "pop" the winning buttons
            btn.animate().scaleX(1.15f).scaleY(1.15f).setDuration(300).withEndAction(() -> {
                btn.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
                btn.setTextColor(ContextCompat.getColor(this, R.color.on_winner_highlight));
            }).start();
        }
    }

    private void restart() {
        statusText.setTextSize(18f);
        for (MaterialButton btn : buttons) {
            btn.setText("");
            btn.setScaleX(1.0f);
            btn.setScaleY(1.0f);
            btn.setBackgroundColor(ContextCompat.getColor(this, R.color.primaryContainer));
        }
        count = 0;
        flag = 0;
        gameActive = true;
        statusText.setText("Player X's Turn");
        statusText.setTextColor(ContextCompat.getColor(this, R.color.onBackground));
    }
}