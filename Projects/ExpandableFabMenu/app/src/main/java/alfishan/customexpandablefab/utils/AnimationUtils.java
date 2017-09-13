package alfishan.customexpandablefab.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by root on 4/9/17.
 */

public class AnimationUtils {



    private static boolean mIsAnimatingOut = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void doCircularReveal(View view, long AnimationDuration, AnimatorListenerAdapter mAnimatorListenerAdapter) {

        // get the center for the clipping circle
        int centerX = (view.getLeft() + view.getRight()) / 2;
        int centerY = (view.getTop() + view.getBottom()) / 2;

        int startRadius = 0;
        // get the final radius for the clipping circle
        int endRadius = Math.max(view.getWidth(), view.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(view,
                        centerX, centerY, startRadius, endRadius);
        anim.setDuration(AnimationDuration);
        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimatorListenerAdapter.onAnimationEnd(animation);
            }
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mAnimatorListenerAdapter.onAnimationStart(animation);
            }
        });

        view.setVisibility(View.VISIBLE);

        if (ViewCompat.isAttachedToWindow(view)) {
            anim.start();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void doCircularRevealBRTL(View view, long AnimationDuration, AnimatorListenerAdapter mAnimatorListenerAdapter) {

        // get the center for the clipping circle
        int centerX = view.getRight();
        int centerY = view.getBottom();

        int startRadius = 0;
        // get the final radius for the clipping circle
        int endRadius = (int) Math
                .hypot(view.getWidth(), view.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(view,
                        centerX, centerY, startRadius, endRadius);
        anim.setDuration(AnimationDuration);
        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimatorListenerAdapter.onAnimationEnd(animation);
            }
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mAnimatorListenerAdapter.onAnimationStart(animation);
            }
        });

        view.setVisibility(View.VISIBLE);

        if (ViewCompat.isAttachedToWindow(view)) {
            anim.start();
        }


    }

    public static void shrinkOut(View mView,ViewPropertyAnimatorListener mViewPropertyAnimatorListener) {
        //mView.setVisibility(View.VISIBLE);
        ViewCompat.animate(mView).scaleX(1.0F).scaleY(1.0F).alpha(1.0F).setStartDelay(200)
                .setInterpolator(new DecelerateInterpolator()).withLayer().setListener(mViewPropertyAnimatorListener)
                .start();

    }


    public static void shrinkIn(View mView,ViewPropertyAnimatorListener mViewPropertyAnimatorListener) {

        if (!mIsAnimatingOut) {
            ViewCompat.animate(mView).scaleX(0.0F).scaleY(0.0F).setStartDelay(200).alpha(0.0F).setInterpolator(new DecelerateInterpolator()).withLayer()
                    .setListener(new ViewPropertyAnimatorListener() {
                        public void onAnimationStart(View view) {
                            mViewPropertyAnimatorListener.onAnimationStart(mView);
                            mIsAnimatingOut = true;
                        }

                        public void onAnimationCancel(View view) {
                            mViewPropertyAnimatorListener.onAnimationCancel(mView);
                            mIsAnimatingOut = false;
                        }

                        public void onAnimationEnd(View view) {
                            mIsAnimatingOut = false;
                            mView.setVisibility(View.INVISIBLE);
                            mViewPropertyAnimatorListener.onAnimationEnd(mView);
                        }
                    }).start();
        }

    }

}
