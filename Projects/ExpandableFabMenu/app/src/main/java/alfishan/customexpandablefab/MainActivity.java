package alfishan.customexpandablefab;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import java.util.concurrent.TimeUnit;

import alfishan.customexpandablefab.utils.AnimationUtils;
import alfishan.customexpandablefab.utils.AppConstants;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class MainActivity extends AppCompatActivity implements FabMenuListener {

    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.fab2)
    FloatingActionButton mFab2;
    private boolean mIsAnimatingOut = false;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RxView.clicks(mFab)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(Avoid -> {
                    if (!mIsAnimatingOut) {
                        if (mFab.getVisibility() == View.VISIBLE) {
                            AnimationUtils.shrinkIn(mFab, new ViewPropertyAnimatorListener() {
                                @Override
                                public void onAnimationStart(View view) {
                                    mIsAnimatingOut = true;
                                }

                                @Override
                                public void onAnimationEnd(View view) {
                                    mIsAnimatingOut = false;
                                    ExpandableFabMenu.newInstance().show(getSupportFragmentManager(), ExpandableFabMenu.TAG);
                                }

                                @Override
                                public void onAnimationCancel(View view) {
                                    mIsAnimatingOut = false;
                                }
                            });
                        }

                    }

                });

        RxView.clicks(mFab2)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(Avoid -> {
                    if (!mIsAnimatingOut) {
                        if (mFab2.getVisibility() == View.VISIBLE) {
                            AnimationUtils.shrinkIn(mFab2, new ViewPropertyAnimatorListener() {
                                @Override
                                public void onAnimationStart(View view) {
                                    mIsAnimatingOut = true;
                                }

                                @Override
                                public void onAnimationEnd(View view) {
                                    mIsAnimatingOut = false;
                                    ExpandableFabMenu1.newInstance().show(getSupportFragmentManager(), ExpandableFabMenu.TAG);
                                }

                                @Override
                                public void onAnimationCancel(View view) {
                                    mIsAnimatingOut = false;
                                }
                            });
                        }

                    }

                });


    }


    @Override
    public void onAddExpense(int mExpenseType) {

        switch (mExpenseType) {

            case AppConstants.FabMenuClose:
                showToest("Close");
                break;

            case AppConstants.FabMenuService:
                showToest("Service");
                break;

            case AppConstants.FabMenuRefueling:
                showToest("Refueling");
                break;

            case AppConstants.FabMenuExpense:
                showToest("Expense");
                break;

            case AppConstants.FabMenuExpense4:
                showToest("FabMenuExpense4");
                break;

            case AppConstants.FabMenuExpense5:
                showToest("FabMenuExpense5");
                break;

            case AppConstants.FabMenuExpense6:
                showToest("FabMenuExpense6");
                break;

            case AppConstants.FabMenuExpense7:
                showToest("FabMenuExpense7");
                break;

            case AppConstants.FabMenuExpense8:
                showToest("FabMenuExpense8");
                break;
        }


    }

    @Override
    public void onFabMenuClosed(String mTag) {

        switch (mTag) {
            case ExpandableFabMenu.TAG:
                if (mFab.getVisibility() == View.INVISIBLE) {
                    AnimationUtils.shrinkOut(mFab, new ViewPropertyAnimatorListener() {
                        @Override
                        public void onAnimationStart(View view) {
                            mFab.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(View view) {

                        }

                        @Override
                        public void onAnimationCancel(View view) {

                        }
                    });
                }
                break;
            case ExpandableFabMenu1.TAG:
                if (mFab2.getVisibility() == View.INVISIBLE) {
                    AnimationUtils.shrinkOut(mFab2, new ViewPropertyAnimatorListener() {
                        @Override
                        public void onAnimationStart(View view) {
                            mFab2.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(View view) {

                        }

                        @Override
                        public void onAnimationCancel(View view) {

                        }
                    });
                }
                break;
        }

    }

    private void showToest(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
