/*
 * Copyright (c) 2015 Hannes Dorfmann.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package alfishan.funwithrecyclerview.delegates;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

import alfishan.funwithrecyclerview.R;
import alfishan.funwithrecyclerview.models.ColorItem;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ColorItemSingleDelegate extends AdapterDelegate<List<Object>> {



    private LayoutInflater inflater;
    private SingleItemHelper mItemListener;

    public ColorItemSingleDelegate(Context mContext, SingleItemHelper mListener) {
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemListener = mListener;
    }

    @Override
    protected boolean isForViewType(@NonNull List<Object> items, int position) {
        return items.get(position) instanceof ColorItem;

    }


    @Override
    protected void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

    }

    @Override
    protected void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ColorItemViewHolder(inflater.inflate(R.layout.single_choice_color_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull List<Object> items, int position, @NonNull RecyclerView.ViewHolder holder, @Nullable List<Object> payloads) {
        configureSelectionItemCardViewHolder((ColorItemViewHolder) holder, items.get(position));
    }


    private void configureSelectionItemCardViewHolder(ColorItemViewHolder holder, Object mValue) {

        if (mValue instanceof ColorItem) {
            holder.bind(((ColorItem) mValue));
        }
    }


    class ColorItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.color_item)
        AppCompatImageView mColorItem;
        private ColorItem mItem;
        @BindView(R.id.main_card)
        CardView mMainCard;
        final View mView;

        ColorItemViewHolder(View view) {
            super(view);
            mView=view;
            ButterKnife.bind(this, view);
            mView.setOnClickListener(this);

        }

        void bind(ColorItem mItem) {
            this.mItem = mItem;
            mMainCard.setCardBackgroundColor(this.mItem.getColor());
            if (mItemListener != null) {
                if (mItemListener.isSelected(this.mItem.getId())) {
                    mColorItem.setImageResource(R.drawable.ic_selected);
                } else {
                    mColorItem.setImageDrawable(null);
                }
            }
        }

        @Override
        public void onClick(View view) {
            if (mItemListener != null) {
                mItemListener.selectedItem(this.mItem, getAdapterPosition());
                YoYo.with(Techniques.Pulse).duration(300).playOn(mView);
            }
        }
    }

    public interface SingleItemHelper {

        void selectedItem(ColorItem mColorItem, int Pos);

        boolean isSelected(int position);
    }

}
