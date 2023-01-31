package rob.myappcompany.myeggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    SeekBar timerSetSeekBar;
    Boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void resetTimer() {
        timerTextView.setText("0:30");
        timerSetSeekBar.setProgress(30);
        timerSetSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("Start");
        counterIsActive = false;
    }

    public void buttonClicked(View view) {

        if (counterIsActive) {

            resetTimer();


        } else {


            counterIsActive = true;
            timerSetSeekBar.setEnabled(false);
            goButton.setText("STOP");

            countDownTimer = new CountDownTimer(timerSetSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) (l / 1000));
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();
                }

            }.start();
        }
    }

    public void updateTimer(int secondLeft) {
        int minutes = secondLeft / 60;
        int seconds = secondLeft - (minutes * 60);

        String secondStrings = Integer.toString(seconds);

        if (seconds <= 9) {
            secondStrings = "0" + secondStrings;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondStrings);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SeekBar
        timerSetSeekBar = findViewById(R.id.timerSetSeekBar);
        // timer表示
        timerTextView = findViewById(R.id.timerTextView);
        // button
        goButton = findViewById(R.id.startButton);

        // 初期値
        timerSetSeekBar.setProgress(30);
        // 最大値
        timerSetSeekBar.setMax(600);

        timerSetSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        updateTimer(i);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }
}


