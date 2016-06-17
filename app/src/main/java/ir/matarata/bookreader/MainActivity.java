package ir.matarata.bookreader;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ShimmerTextView shimmertvhour,shimmertvminute,shimmertvsecond;
    private FloatingActionMenu fabmenu;
    private FloatingActionButton fabstart,fabpause,fabstop,fablist;
    private Typeface font;
    private int counterActive=0;
    private Timer tm;
    private int counterSecond=0,counterMinute=0,counterHour=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shimmertvhour = (ShimmerTextView) findViewById(R.id.shimmertv_hour);
        shimmertvminute = (ShimmerTextView) findViewById(R.id.shimmertv_minute);
        shimmertvsecond = (ShimmerTextView) findViewById(R.id.shimmertv_second);
        fabmenu = (FloatingActionMenu) findViewById(R.id.fabmenu);
        fabstart = (FloatingActionButton) findViewById(R.id.fabmenu_start);
        fabpause = (FloatingActionButton) findViewById(R.id.fabmenu_pause);
        fabstop = (FloatingActionButton) findViewById(R.id.fabmenu_stop);
        fablist = (FloatingActionButton) findViewById(R.id.fabmenu_list);

        Shimmer shimmer = new Shimmer();
        Shimmer shimmer2 = new Shimmer();
        Shimmer shimmer3 = new Shimmer();
        shimmer.setDuration(1000);
        shimmer2.setDuration(1000);
        shimmer3.setDuration(1000);
        shimmer.start(shimmertvsecond);
        shimmer2.start(shimmertvminute);
        shimmer3.start(shimmertvhour);

        font = Typeface.createFromAsset(getAssets(),"caviardreams.ttf");
        shimmertvhour.setTypeface(font);
        shimmertvminute.setTypeface(font);
        shimmertvsecond.setTypeface(font);

        fabstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(counterActive == 0){
                counterActive = 1;
                tm =new Timer();
                tm.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                counterSecond++;
                                if(counterSecond == 60){
                                    counterSecond = 0;
                                    counterMinute++;
                                    if(counterMinute == 60){
                                        counterMinute = 0;
                                        counterHour++;
                                        shimmertvhour.setText(String.valueOf(counterHour) + " :");
                                    }else{
                                        shimmertvminute.setText(" " + String.valueOf(counterMinute) + " ");
                                    }
                                }else{
                                    shimmertvsecond.setText("  " + String.valueOf(counterSecond));
                                }
                            }
                        });
                    }
                }, 0, 1000);
            }else{

            }
            }
        });

    }

}
