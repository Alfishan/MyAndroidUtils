package alfishan.funwithrecyclerview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import java.util.ArrayList;
import java.util.List;

import alfishan.funwithrecyclerview.delegates.ColorItemSingleDelegate;
import alfishan.funwithrecyclerview.models.ColorItem;
import alfishan.funwithrecyclerview.models.SingleChoiceItem;

/**
 * Created by root on 17/8/17.
 */

public class SingleChoiceAdapter extends RecyclerView.Adapter implements ColorItemSingleDelegate.SingleItemHelper {
    private AdapterDelegatesManager<List<Object>> delegatesManager;
    private Helper mListener;
    private int singleSelectionPos = 987456;

    public void setValues(List<Object>  values) {
        mValues.addAll(values);
        notifyDataSetChanged();
    }

    private List<Object> mValues = new ArrayList<>();

    public SingleChoiceAdapter(Context mContext, Helper mListener) {
        this.mListener = mListener;
        mValues = new ArrayList<>(0);
        delegatesManager = new AdapterDelegatesManager<>();
        delegatesManager.addDelegate(new ColorItemSingleDelegate(mContext,this));


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
    public void selectedItem(ColorItem mColorItem,int pos) {
        singleSelectionPos=pos;
        if (mListener != null) {
            mListener.onItemSelected(mColorItem);
        }
        notifyDataSetChanged();
    }

    @Override
    public boolean isSelected(int position) {
        return singleSelectionPos == position;
    }


    public interface Helper {

        void onItemSelected(SingleChoiceItem mSingleChoiceItem);

    }



}
