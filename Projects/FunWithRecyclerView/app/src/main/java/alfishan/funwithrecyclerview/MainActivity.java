package alfishan.funwithrecyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import alfishan.funwithrecyclerview.adapters.MultiChoiceAdapter;
import alfishan.funwithrecyclerview.adapters.SingleChoiceAdapter;
import alfishan.funwithrecyclerview.adapters.SingleChoiceWithDefaultAdapter;
import alfishan.funwithrecyclerview.models.ColorItem;
import alfishan.funwithrecyclerview.models.SingleChoiceItem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SingleChoiceAdapter.Helper, MultiChoiceAdapter.Helper, SingleChoiceWithDefaultAdapter.Helper {

    @BindView(R.id.single_choice_rv)
    RecyclerView mSingleChoiceRv;
    @BindView(R.id.multi_choice_rv)
    RecyclerView mMultiChoiceRv;
    @BindView(R.id.single_choice_default_rv)
    RecyclerView mSingleChoiceDefaultRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        InitColorsAdapter();
    }

    private void InitColorsAdapter() {
        Random rnd = new Random();
        List<Object> mColors = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            mColors.add(new ColorItem(i, color));
        }

        // to set 1st color as selected
        ((ColorItem) mColors.get(0)).setSelected(true);


        SingleChoiceAdapter mSingleChoiceAdapter = new SingleChoiceAdapter(this, this);
        SingleChoiceWithDefaultAdapter mSingleChoiceWithDefaultAdapter = new SingleChoiceWithDefaultAdapter(this, this);
        MultiChoiceAdapter mMultiChoiceAdapter = new MultiChoiceAdapter(this, this);

        mSingleChoiceRv.setAdapter(mSingleChoiceAdapter);
        mMultiChoiceRv.setAdapter(mMultiChoiceAdapter);
        mSingleChoiceDefaultRv.setAdapter(mSingleChoiceWithDefaultAdapter);

        mSingleChoiceAdapter.setValues(mColors);
        mMultiChoiceAdapter.setValues(mColors);
        mSingleChoiceWithDefaultAdapter.setValues(mColors);

        mSingleChoiceRv.setHasFixedSize(true);
        mMultiChoiceRv.setHasFixedSize(true);
        mSingleChoiceDefaultRv.setHasFixedSize(true);


    }

    @Override
    public void onItemSelected(SingleChoiceItem mSingleChoiceItem) {
        Toast.makeText(this, "Selected colorID :" + mSingleChoiceItem.getId(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(SingleChoiceItem mSingleChoiceItem, boolean isSelected) {
        Toast.makeText(this, "colorID :" + mSingleChoiceItem.getId() + " selected:" + isSelected, Toast.LENGTH_SHORT).show();
    }
}
