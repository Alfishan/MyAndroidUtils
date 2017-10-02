package alfishan.funwithrecyclerview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import alfishan.funwithrecyclerview.delegates.ColorItemMultiDelegate;
import alfishan.funwithrecyclerview.models.ColorItem;
import alfishan.funwithrecyclerview.models.SingleChoiceItem;

/**
 * Created by root on 17/8/17.
 */

public class MultiChoiceAdapter extends RecyclerView.Adapter implements ColorItemMultiDelegate.MultiItemHelper {
    private AdapterDelegatesManager<List<Object>> delegatesManager;
    private Helper mListener;
    private HashSet<Integer> mSelectedIndexes = new HashSet<>();
    private List<Object> mValues = new ArrayList<>();

    public MultiChoiceAdapter(Context mContext, Helper mListener) {
        this.mListener = mListener;
        mValues = new ArrayList<>(0);
        delegatesManager = new AdapterDelegatesManager<>();
        delegatesManager.addDelegate(new ColorItemMultiDelegate(mContext, this));


    }

    public void setValues(List<Object> values) {
        mValues.addAll(values);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(mValues, position, holder);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        delegatesManager.onBindViewHolder(mValues, position, holder, payloads);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(mValues, position);
    }


    @Override
    public boolean isSelected(int position) {
        return mSelectedIndexes.contains(position);
    }

    private void registerSelection(int position) {
        mSelectedIndexes.add(position);
    }

    private void deregisterSelection(int position) {
        mSelectedIndexes.remove(position);
    }

    @Override
    public void selectionToggle(ColorItem mColorItem, int position) {
        if (!mSelectedIndexes.contains(position)) {
            registerSelection(position);
            mListener.onItemSelected(mColorItem, true);
        } else {
            mListener.onItemSelected(mColorItem, false);
            deregisterSelection(position);
        }
    }

    public interface Helper {

        void onItemSelected(SingleChoiceItem mSingleChoiceItem, boolean isSelected);

    }

}
