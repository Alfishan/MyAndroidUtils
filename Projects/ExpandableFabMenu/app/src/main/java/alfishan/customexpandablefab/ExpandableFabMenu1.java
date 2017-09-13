package alfishan.customexpandablefab;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import alfishan.customexpandablefab.utils.AppConstants;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by root on 4/9/17.
 */

public class ExpandableFabMenu1 extends DialogFragment {


    public static final String TAG = "ExpandableFabMenu1";
    FabMenuListener mListener;
    View mView;
    @BindView(R.id.fab_menu_close)
    FloatingActionButton mFabMenuClose;
    List<View> mViewHashSet = new ArrayList<View>(0);

    @BindView(R.id.fab_menu_refueling)
    FloatingActionButton mFabMenuRefueling;
    @BindView(R.id.fab_menu_expense)
    FloatingActionButton mFabMenuExpense;
    @BindView(R.id.fab_menu_service)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.fab_menu_expense_4)
    FloatingActionButton mFabMenuExpense4;
    @BindView(R.id.fab_menu_expense_5)
    FloatingActionButton mFabMenuExpense5;
    @BindView(R.id.fab_menu_expense_6)
    FloatingActionButton mFabMenuExpense6;
    @BindView(R.id.fab_menu_expense_7)
    FloatingActionButton mFabMenuExpense7;
    @BindView(R.id.fab_menu_expense_8)
    FloatingActionButton mFabMenuExpense8;

    public static ExpandableFabMenu1 newInstance() {
        Bundle args = new Bundle();
        ExpandableFabMenu1 mExpandableFabMenu = new ExpandableFabMenu1();
        mExpandableFabMenu.setArguments(args);
        return mExpandableFabMenu;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setWindowAnimations(R.style.MyFullscreenDialog);
        }

        if (getContext() instanceof MainActivity) {
            mListener = ((MainActivity) getContext());
        }

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyFullscreenDialog);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.expandable_fab_menu_1, container, false);
        ButterKnife.bind(this, mView);


        for (int i = 0; i < ((ViewGroup) mView).getChildCount(); i++) {
            mViewHashSet.add(((ViewGroup) mView).getChildAt(i));
            ((ViewGroup) mView).getChildAt(i).setScaleX(0.0F);
            ((ViewGroup) mView).getChildAt(i).setScaleY(0.0F);
            ((ViewGroup) mView).getChildAt(i).setAlpha(0.0F);
            ((ViewGroup) mView).getChildAt(i).setVisibility(View.INVISIBLE);
        }
        Collections.shuffle(mViewHashSet, new Random());
        Collections.reverse(mViewHashSet);
        mFabMenuClose.setEnabled(false);

        RxView.clicks(mFabMenuClose)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(Avoid -> CloseDialogWithAction(AppConstants.FabMenuClose));


        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mView.postDelayed(() -> {
            int delay = 200;
            for (View view1 : mViewHashSet) {
                animateIn(view1, delay);
                delay = delay + 50;
            }
        }, 400);


    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        if (mListener != null) {
            mListener.onFabMenuClosed(TAG);
        }
        super.onDismiss(dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                CloseDialogWithAction(AppConstants.FabMenuClose);
            }
        };
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mView != null) {
            mView.setOnTouchListener(null);
        }

    }

    void CloseDialogWithAction(int mExpenseType) {
        int delay = 100;
        Collections.reverse(mViewHashSet);
        for (View view1 : mViewHashSet) {
            view1.setScaleX(1.0F);
            view1.setScaleY(1.0F);
            view1.setAlpha(1.0F);
            view1.setVisibility(View.VISIBLE);
            animateOut(view1, delay);
            delay = delay + 50;
        }
        new Handler().postDelayed(() -> {
            if (mListener != null) {
                mListener.onAddExpense(mExpenseType);
            }
            dismiss();
        }, delay + 200);
    }

    void CloseDialog() {
        int delay = 100;
        Collections.reverse(mViewHashSet);
        for (View view1 : mViewHashSet) {
            view1.setScaleX(1.0F);
            view1.setScaleY(1.0F);
            view1.setAlpha(1.0F);
            view1.setVisibility(View.VISIBLE);
            animateOut(view1, delay);
            delay = delay + 50;
        }
        new Handler().postDelayed(this::dismiss, delay + 200);
    }

    private void animateIn(View mView, int mdealy) {
        ViewCompat.animate(mView)
                .scaleY(1.0F)
                .scaleX(1.0F)
                .alpha(1.0F)
                .setStartDelay(mdealy)
                .setDuration(300)
                .setInterpolator(new OvershootInterpolator())
                .withStartAction(() -> {
                    mView.setVisibility(View.VISIBLE);

                })
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        //mIsAnimatingOut = true;
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        //mIsAnimatingOut = false;
                        mFabMenuClose.setEnabled(true);
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                        //mIsAnimatingOut = false;
                    }
                })
                .start();

    }


    private void animateOut(View mView, int mdealy) {
        //TransitionManager.beginDelayedTransition(mFoldingCell,mSetIn);
        ViewCompat.animate(mView)
                .scaleY(0.0F)
                .scaleX(0.0F)
                .setStartDelay(mdealy)
                .alpha(0.0F)
                .setDuration(300)
                .setInterpolator(new DecelerateInterpolator()).withLayer().start();


    }

    @OnClick({R.id.fab_menu_expense, R.id.fab_menu_refueling, R.id.fab_menu_service,R.id.fab_menu_expense_4, R.id.fab_menu_expense_5, R.id.fab_menu_expense_6, R.id.fab_menu_expense_7, R.id.fab_menu_expense_8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab_menu_expense:
                CloseDialogWithAction(AppConstants.FabMenuExpense);
                break;
            case R.id.fab_menu_refueling:
                CloseDialogWithAction(AppConstants.FabMenuRefueling);
                break;
            case R.id.fab_menu_service:
                CloseDialogWithAction(AppConstants.FabMenuService);
                break;
            case R.id.fab_menu_expense_4:
                CloseDialogWithAction(AppConstants.FabMenuExpense4);
                break;
            case R.id.fab_menu_expense_5:
                CloseDialogWithAction(AppConstants.FabMenuExpense5);
                break;
            case R.id.fab_menu_expense_6:
                CloseDialogWithAction(AppConstants.FabMenuExpense6);
                break;
            case R.id.fab_menu_expense_7:
                CloseDialogWithAction(AppConstants.FabMenuExpense7);
                break;
            case R.id.fab_menu_expense_8:
                CloseDialogWithAction(AppConstants.FabMenuExpense8);
                break;
        }
    }


}
