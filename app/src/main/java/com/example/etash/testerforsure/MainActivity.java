package com.example.etash.testerforsure;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.os.Vibrator;

public class MainActivity extends AppCompatActivity {

    Button startAnimation, newActivity;
    ImageView picture, secondPanda, thirdPanda, heartOne, heartTwo, heartThree, plusone, fallingHeart, fallingHeartTwo;
    TextView counterText, finalScoreText;
    int counter, numHearts, screenWidth, screenHeight, sign;
    float currPosition, newPosition, floatingHeartHeight, currPositionTwo, currPositionThree, newPositionTwo, newPositionThree;
    boolean firstHeartFalling, secondHeartFalling;
    double timeConstant;
    long timeFirstPandaHits, timeSecondPandaHits, timeThirdPandaHits;
    SeekBar slider;
    Vibrator vibrator;
    TranslateAnimation primaryAnimation, secondaryAnimation, tertiaryAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        startAnimation = (Button) (findViewById(R.id.startAnimation));
        counter = 0;
        numHearts = 3;
        firstHeartFalling = false;
        secondHeartFalling = false;
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        picture = (ImageView) (findViewById(R.id.picture));
        finalScoreText = (TextView)(findViewById(R.id.finalScore));
        secondPanda = (ImageView)(findViewById(R.id.pictureTwo));
        thirdPanda = (ImageView)(findViewById(R.id.pictureThree));
        fallingHeartTwo = (ImageView)(findViewById(R.id.fallingHeartTwo));
        picture.measure(0,0);
        secondPanda.measure(0,0);
        thirdPanda.measure(0,0);
        timeConstant = 1;
        currPosition = (float)(Math.random() * (screenWidth - 2 * picture.getMeasuredWidth()));
        currPositionTwo = (float)(Math.random() * (screenWidth - 2 * secondPanda.getMeasuredWidth()));
        currPositionThree = (float)(Math.random() * (screenWidth - 2 * thirdPanda.getMeasuredWidth()));
        picture.setX(currPosition + picture.getMeasuredWidth());
        finalScoreText.setVisibility(View.INVISIBLE);
        secondPanda.setX(currPositionTwo + secondPanda.getMeasuredWidth());
        thirdPanda.setX(currPositionThree + thirdPanda.getMeasuredWidth());
        heartOne = (ImageView)(findViewById(R.id.heartOne));
        fallingHeart = (ImageView)(findViewById(R.id.fallingHeart));
        plusone = (ImageView)(findViewById(R.id.plusone));
        heartTwo = (ImageView)(findViewById(R.id.heartTwo));
        heartThree = (ImageView)(findViewById(R.id.heartThree));
        picture.setVisibility(View.INVISIBLE);
        secondPanda.setVisibility(View.INVISIBLE);
        thirdPanda.setVisibility(View.INVISIBLE);
        heartOne.setVisibility(View.INVISIBLE);
        heartTwo.setVisibility(View.INVISIBLE);
        heartThree.setVisibility(View.INVISIBLE);
        fallingHeart.setVisibility(View.INVISIBLE);
        fallingHeartTwo.setVisibility(View.INVISIBLE);
        plusone.setVisibility(View.INVISIBLE);
        picture.measure(0,0);
        secondPanda.measure(0,0);
        thirdPanda.measure(0,0);
        picture.setY((float) (-1.0 * picture.getMeasuredHeight()));
        secondPanda.setY((float)(-1.0 * secondPanda.getMeasuredHeight()));
        thirdPanda.setY((float)(-1.0 * thirdPanda.getMeasuredHeight()));
        newActivity = (Button) (findViewById(R.id.startNewActivity));
        counterText = (TextView) (findViewById(R.id.counter));
        counterText.setVisibility(View.INVISIBLE);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        counter = 0;
        slider = (SeekBar) (findViewById(R.id.cart));
        slider.setPadding(0,0,0,0);
        TranslateAnimation moveSplash = new TranslateAnimation(0, 0, 0, 3000);
        moveSplash.setDuration(5000);
        moveSplash.setFillAfter(true);
        slider.setProgressDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        timeFirstPandaHits = 0;
        timeSecondPandaHits = 0;
        timeThirdPandaHits = 0;
        startAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalScoreText.setVisibility(View.INVISIBLE);
                heartOne.setVisibility(View.VISIBLE);
                counterText.setVisibility(View.VISIBLE);
                heartTwo.setVisibility(View.VISIBLE);
                counterText.setText("Points: " + counter);
                heartThree.setVisibility(View.VISIBLE);
                slider.setVisibility(View.VISIBLE);
                picture.setVisibility(View.VISIBLE);
                finalScoreText.setText("");
                picture.clearAnimation();
                secondPanda.clearAnimation();
                thirdPanda.clearAnimation();
                fallingHeart.clearAnimation();
                fallingHeartTwo.clearAnimation();
                finalScoreText.clearAnimation();
                numHearts = 3;
                startAnimation.setVisibility(View.INVISIBLE);
                startAnimation.setX(screenWidth/2 - startAnimation.getWidth()/2);
                startAnimation.setY(screenHeight);
                slider.measure(0,0);
                primaryAnimation = new TranslateAnimation(0, 0, 0, screenHeight);
                primaryAnimation.setInterpolator(new LinearInterpolator());
                primaryAnimation.setDuration(8000);
                primaryAnimation.setFillAfter(false);
                secondaryAnimation = new TranslateAnimation(0, 0, 0, screenHeight);
                secondaryAnimation.setInterpolator(new LinearInterpolator());
                secondaryAnimation.setDuration(8000);
                secondaryAnimation.setFillAfter(false);
                tertiaryAnimation = new TranslateAnimation(0, 0, 0, screenHeight);
                tertiaryAnimation.setInterpolator(new LinearInterpolator());
                tertiaryAnimation.setDuration(8000);
                tertiaryAnimation.setFillAfter(false);
                Animation.AnimationListener primaryAnimationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        picture.setRotation((float)Math.random());
                        if(Math.random() < .5){
                            sign = 1;
                        } else {
                            sign = -1;
                        }
                        picture.animate().rotation(sign * 10000 + picture.getRotation()).setDuration(75000);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if ((Math.abs(slider.getProgress() * (screenWidth/100.0) - picture.getX()) < .15 * screenWidth)) {
                            plusone.measure(0,0);
                            plusone.setX((float)((slider.getProgress() * (screenWidth/100.0)) - plusone.getMeasuredWidth()/2));
                            fadeOutAndHideImage(plusone);
                            counter++;
                            counterText.setText("Points: " + counter);
                            primaryAnimation.setDuration(8000 - (long) ((8000 * Math.pow(counter, timeConstant)) / (Math.pow(counter, timeConstant) + 10)));
                            setPosition();
                            if(counter == 10)
                            {
                                secondPanda.startAnimation(secondaryAnimation);
                            }
                            if(counter == 20)
                            {
                                thirdPanda.startAnimation(tertiaryAnimation);
                            }
                            picture.startAnimation(primaryAnimation);
                        } else {
                            numHearts--;
                            switch (numHearts) {
                                case 2:
                                    heartThree.setVisibility(View.INVISIBLE);
                                    primaryAnimation.setDuration(8000 - (long) ((8000 * Math.pow(counter, timeConstant)) / (Math.pow(counter, timeConstant) + 10)));
                                    setPosition();
                                    picture.startAnimation(primaryAnimation);
                                    animateHeartsFalling(fallingHeart);
                                    break;
                                case 1:
                                    heartTwo.setVisibility(View.INVISIBLE);
                                    primaryAnimation.setDuration(8000 - (long) ((8000 * Math.pow(counter, timeConstant)) / (Math.pow(counter, timeConstant) + 10)));
                                    setPosition();
                                    picture.startAnimation(primaryAnimation);
                                    animateHeartsFalling(fallingHeartTwo);
                                    break;
                                case 0:
                                    heartOne.setVisibility(View.INVISIBLE);
                                    slider.setVisibility(View.INVISIBLE);
                                    counterText.setVisibility(View.INVISIBLE);
                                    finalScoreText.setText("Points: " + counter);
                                    picture.setVisibility(View.INVISIBLE);
                                    secondPanda.setVisibility(View.INVISIBLE);
                                    thirdPanda.setVisibility(View.INVISIBLE);
                                    fallingHeart.setVisibility(View.INVISIBLE);
                                    fallingHeartTwo.setVisibility(View.INVISIBLE);
                                    counter = 0;
                                    picture.clearAnimation();
                                    secondPanda.clearAnimation();
                                    thirdPanda.clearAnimation();
                                    fallingHeart.clearAnimation();
                                    fallingHeartTwo.clearAnimation();
                                    finalScoreText.clearAnimation();

                                    startAnimation.setVisibility(View.VISIBLE);

                                    vibrator.vibrate(100);
                                    startAnimation.animate().translationY((float)(-.1*screenHeight)).setDuration(1000).setListener(new Animator.AnimatorListener() {
                                        @Override
                                        public void onAnimationStart(Animator animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            startAnimation.animate().translationY((float)(0 * screenHeight)).setDuration(500).setListener(new Animator.AnimatorListener() {
                                                @Override
                                                public void onAnimationStart(Animator animation) {

                                                }

                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    fadeInTextView(finalScoreText);
                                                }

                                                @Override
                                                public void onAnimationCancel(Animator animation) {
                                                    startAnimation.measure(0,0);
                                                    startAnimation.setX(screenWidth - startAnimation.getMeasuredWidth()/2);
                                                    startAnimation.setY(screenHeight/2);
                                                }

                                                @Override
                                                public void onAnimationRepeat(Animator animation) {

                                                }
                                            }).start();
                                        }

                                        @Override
                                        public void onAnimationCancel(Animator animation) {
                                            startAnimation.measure(0,0);
                                            startAnimation.setX(screenWidth - startAnimation.getMeasuredWidth()/2);
                                            startAnimation.setY(screenHeight/2);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animator animation) {

                                        }
                                    }).start();
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };
                Animation.AnimationListener secondaryAnimationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        secondPanda.setRotation((float)Math.random());
                        if(Math.random() < .5){
                            sign = 1;
                        } else {
                            sign = -1;
                        }
                        secondPanda.animate().rotation(sign * 10000 + secondPanda.getRotation()).setDuration(75000);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if ((Math.abs(slider.getProgress() * (screenWidth/100.0) - secondPanda.getX()) < .15 * screenWidth)) {
                            plusone.measure(0,0);
                            plusone.setX((float)((slider.getProgress() * (screenWidth/100.0)) - plusone.getMeasuredWidth()/2));
                            fadeOutAndHideImage(plusone);
                            counter++;
                            counterText.setText("Points: " + counter);
                            secondaryAnimation.setDuration(8000 - (long) ((8000 * Math.pow(counter - 10, timeConstant)) / (Math.pow(counter - 10, timeConstant) + 10)));
                            setPositionTwo();
                            if(counter == 20)
                            {
                                thirdPanda.startAnimation(tertiaryAnimation);
                            }
                            secondPanda.startAnimation(secondaryAnimation);
                        } else {
                            numHearts--;
                            switch (numHearts) {
                                case 2:
                                    heartThree.setVisibility(View.INVISIBLE);
                                    animation.setDuration(8000 - (long) ((8000 * Math.pow(counter - 10, timeConstant)) / (Math.pow(counter - 10, timeConstant) + 10)));
                                    setPositionTwo();
                                    secondPanda.startAnimation(secondaryAnimation);
                                    animateHeartsFalling(fallingHeart);
                                    break;
                                case 1:
                                    heartTwo.setVisibility(View.INVISIBLE);
                                    animation.setDuration(8000 - (long) ((8000 * Math.pow(counter - 10, timeConstant)) / (Math.pow(counter - 10, timeConstant) + 10)));
                                    setPositionTwo();
                                    secondPanda.startAnimation(secondaryAnimation);
                                    animateHeartsFalling(fallingHeartTwo);
                                    break;
                                case 0:
                                    heartOne.setVisibility(View.INVISIBLE);
                                    slider.setVisibility(View.INVISIBLE);
                                    finalScoreText.setText("Points: " + counter);
                                    counterText.setVisibility(View.INVISIBLE);
                                    picture.setVisibility(View.INVISIBLE);
                                    secondPanda.setVisibility(View.INVISIBLE);
                                    thirdPanda.setVisibility(View.INVISIBLE);
                                    fallingHeart.setVisibility(View.INVISIBLE);
                                    fallingHeartTwo.setVisibility(View.INVISIBLE);
                                    picture.clearAnimation();
                                    secondPanda.clearAnimation();
                                    thirdPanda.clearAnimation();
                                    fallingHeart.clearAnimation();
                                    fallingHeartTwo.clearAnimation();
                                    finalScoreText.clearAnimation();
                                    counter = 0;
                                    startAnimation.setVisibility(View.VISIBLE);

                                    vibrator.vibrate(100);
                                    startAnimation.animate().translationY((float)(-.1 * screenHeight)).setDuration(1000).setListener(new Animator.AnimatorListener() {
                                        @Override
                                        public void onAnimationStart(Animator animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            startAnimation.animate().translationY((float)(0 * screenHeight)).setDuration(500).setListener(new Animator.AnimatorListener() {
                                                @Override
                                                public void onAnimationStart(Animator animation) {

                                                }

                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    fadeInTextView(finalScoreText);
                                                }

                                                @Override
                                                public void onAnimationCancel(Animator animation) {
                                                    startAnimation.measure(0,0);
                                                    startAnimation.setX(screenWidth - startAnimation.getMeasuredWidth()/2);
                                                    startAnimation.setY(screenHeight/2);
                                                }

                                                @Override
                                                public void onAnimationRepeat(Animator animation) {

                                                }
                                            }).start();
                                        }

                                        @Override
                                        public void onAnimationCancel(Animator animation) {
                                            startAnimation.measure(0,0);
                                            startAnimation.setX(screenWidth - startAnimation.getMeasuredWidth()/2);
                                            startAnimation.setY(screenHeight/2);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animator animation) {

                                        }
                                    }).start();
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };
                Animation.AnimationListener tertiaryAnimationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        secondPanda.setRotation((float)Math.random());
                        if(Math.random() < .5){
                            sign = 1;
                        } else {
                            sign = -1;
                        }
                        thirdPanda.animate().rotation(sign * 10000 + thirdPanda.getRotation()).setDuration(75000);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if ((Math.abs(slider.getProgress() * (screenWidth/100.0) - thirdPanda.getX()) < .15 * screenWidth)) {
                            plusone.measure(0,0);
                            plusone.setX((float)((slider.getProgress() * (screenWidth/100.0)) - plusone.getMeasuredWidth()/2));
                            fadeOutAndHideImage(plusone);
                            counter++;
                            counterText.setText("Points: " + counter);
                            tertiaryAnimation.setDuration(8000 - (long) ((8000 * Math.pow(counter - 20, timeConstant)) / (Math.pow(counter - 20, timeConstant) + 10)));
                            setPositionThree();
                            thirdPanda.startAnimation(tertiaryAnimation);
                        } else {
                            numHearts--;
                            switch (numHearts) {
                                case 2:
                                    heartThree.setVisibility(View.INVISIBLE);
                                    tertiaryAnimation.setDuration(8000 - (long) ((8000 * Math.pow(counter - 20, timeConstant)) / (Math.pow(counter - 20, timeConstant) + 10)));
                                    setPositionThree();
                                    thirdPanda.startAnimation(tertiaryAnimation);
                                    animateHeartsFalling(fallingHeart);
                                    break;
                                case 1:
                                    heartTwo.setVisibility(View.INVISIBLE);
                                    animation.setDuration(8000 - (long) ((8000 * Math.pow(counter - 20, timeConstant)) / (Math.pow(counter - 20, timeConstant) + 10)));
                                    setPositionThree();
                                    picture.startAnimation(tertiaryAnimation);
                                    animateHeartsFalling(fallingHeartTwo);
                                    break;
                                case 0:
                                    heartOne.setVisibility(View.INVISIBLE);
                                    slider.setVisibility(View.INVISIBLE);
                                    finalScoreText.setText("Points: " + counter);
                                    counterText.setVisibility(View.INVISIBLE);
                                    picture.setVisibility(View.INVISIBLE);
                                    secondPanda.setVisibility(View.INVISIBLE);
                                    thirdPanda.setVisibility(View.INVISIBLE);
                                    fallingHeart.setVisibility(View.INVISIBLE);
                                    fallingHeartTwo.setVisibility(View.INVISIBLE);
                                    picture.clearAnimation();
                                    secondPanda.clearAnimation();
                                    thirdPanda.clearAnimation();
                                    fallingHeart.clearAnimation();
                                    fallingHeartTwo.clearAnimation();
                                    finalScoreText.clearAnimation();
                                    counter = 0;
                                    startAnimation.setVisibility(View.VISIBLE);
                                    vibrator.vibrate(100);

                                   startAnimation.animate().translationY((float)(-.1 * screenHeight)).setDuration(1000).setListener(new Animator.AnimatorListener() {
                                        @Override
                                        public void onAnimationStart(Animator animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            startAnimation.animate().translationY((float)(0 * screenHeight)).setDuration(500).setListener(new Animator.AnimatorListener() {
                                                @Override
                                                public void onAnimationStart(Animator animation) {

                                                }

                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    fadeInTextView(finalScoreText);
                                                }

                                                @Override
                                                public void onAnimationCancel(Animator animation) {
                                                    startAnimation.measure(0,0);
                                                    startAnimation.setX(screenWidth - startAnimation.getMeasuredWidth()/2);
                                                    startAnimation.setY(screenHeight/2);
                                                }

                                                @Override
                                                public void onAnimationRepeat(Animator animation) {

                                                }
                                            }).start();
                                        }

                                        @Override
                                        public void onAnimationCancel(Animator animation) {
                                            startAnimation.measure(0,0);
                                            startAnimation.setX(screenWidth - startAnimation.getMeasuredWidth()/2);
                                            startAnimation.setY(screenHeight/2);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animator animation) {

                                        }
                                    }).start();
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };
                primaryAnimation.setAnimationListener(primaryAnimationListener);
                secondaryAnimation.setAnimationListener(secondaryAnimationListener);
                tertiaryAnimation.setAnimationListener(tertiaryAnimationListener);
                picture.startAnimation(primaryAnimation);
            }
        });
    }

    public void animateHeartsFalling(final ImageView heart)
    {
        if(heart.equals(fallingHeart)) {
            if (!firstHeartFalling) {
                fallingHeart.setVisibility(View.VISIBLE);
                floatingHeartHeight = ((float) ((Math.random() * 10 * screenHeight) + screenHeight));
                heart.setY((float) (-1.0 * floatingHeartHeight));
                heart.measure(0, 0);
                firstHeartFalling = true;
                TranslateAnimation heartFall = new TranslateAnimation(0, 0, 0, floatingHeartHeight + screenHeight - 2 * slider.getMeasuredHeight());
                heartFall.setDuration((long) (floatingHeartHeight / screenHeight * 3000));
                heartFall.setInterpolator(new LinearInterpolator());
                heartFall.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (Math.abs(slider.getProgress() * (screenWidth / 100.0) - heart.getX()) < .0926 * screenWidth) {
                            numHearts++;
                            if (numHearts == 2) {
                                heartTwo.setVisibility(View.VISIBLE);
                            } else if (numHearts == 3) {
                                heartThree.setVisibility(View.VISIBLE);
                            }
                        }
                        firstHeartFalling = false;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                heart.startAnimation(heartFall);
            }
        } else {
            if(!secondHeartFalling)
            {
                fallingHeartTwo.setVisibility(View.VISIBLE);
                floatingHeartHeight = ((float) ((Math.random() * 10 * screenHeight) + screenHeight));
                heart.setY((float) (-1.0 * floatingHeartHeight));
                heart.measure(0, 0);
                TranslateAnimation heartFall = new TranslateAnimation(0, 0, 0, floatingHeartHeight + screenHeight - 2 * slider.getMeasuredHeight());
                heartFall.setDuration((long) (floatingHeartHeight / screenHeight * 3000));
                heartFall.setInterpolator(new LinearInterpolator());
                secondHeartFalling = true;
                heartFall.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (Math.abs(slider.getProgress() * (screenWidth / 100.0) - heart.getX()) < .0926 * screenWidth) {
                            numHearts++;
                            if (numHearts == 2) {
                                heartTwo.setVisibility(View.VISIBLE);
                            } else if (numHearts == 3) {
                                heartThree.setVisibility(View.VISIBLE);
                            }
                        }
                        secondHeartFalling = false;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                heart.startAnimation(heartFall);
            }
        }
    }

    public void fadeOutAndHideImage(final ImageView img)
    {
        img.setVisibility(View.VISIBLE);
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);

        fadeOut.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
                img.setVisibility(View.GONE);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });

        img.startAnimation(fadeOut);
    }

    public void fadeInTextView(final TextView textView)
    {
        Animation fadeOut = new AlphaAnimation(0,1);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);

        fadeOut.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
                textView.setVisibility(View.VISIBLE);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });

        textView.startAnimation(fadeOut);
    }
    public void setPosition()
    {
        picture.measure(0,0);
        newPosition = (float)(Math.random() * (screenWidth - 2 * picture.getMeasuredWidth()));
        while(Math.abs(newPosition - currPosition) < .25 * screenWidth)
        {
            newPosition = (float)(Math.random() * (screenWidth - 2 * picture.getMeasuredWidth()));
        }
        picture.setX(newPosition);
        currPosition = newPosition;
    }

    public void setPositionTwo()
    {
        picture.measure(0,0);
        newPositionTwo = (float)(Math.random() * (screenWidth - 2 * picture.getMeasuredWidth()));
        while(Math.abs(newPositionTwo - currPositionTwo) < .25 * screenWidth)
        {
            newPositionTwo = (float)(Math.random() * (screenWidth - picture.getMeasuredWidth()));
        }
        secondPanda.setX(newPositionTwo);
        currPositionTwo = newPositionTwo;

    }

    public void setPositionThree()
    {
        picture.measure(0,0);
        newPositionThree = (float)(Math.random() * (screenWidth - 2 * picture.getMeasuredWidth()));
        while(Math.abs(newPositionThree - currPositionThree) < .25 * screenWidth)
        {
            newPositionThree = (float)(Math.random() * (screenWidth - picture.getMeasuredWidth()));
        }
        thirdPanda.setX(newPositionThree);
        currPositionThree = newPositionThree;
    }
}
