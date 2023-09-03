package com.t4zb.self_improvement.ui.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.t4zb.self_improvement.R;
import com.t4zb.self_improvement.ui.lıstener.ItemClickedListener;

import java.util.ArrayList;
import java.util.List;

public class DailyCheckAdapter extends RecyclerView.Adapter<DailyCheckAdapter.ViewHolder> {

    private Context mContext;
    private final ItemClickedListener itemClickedListener;
    private List<EmotionItem> emotionList = new ArrayList<>();
    private int selectedPos = -1;

    public DailyCheckAdapter(List<EmotionItem> emotionList, ItemClickedListener itemClickedListener) {
        this.emotionList = emotionList;
        this.itemClickedListener = itemClickedListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_chech_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EmotionItem emotionItem = emotionList.get(position);
        holder.emotion_text_view.setText(emotionItem.getEmotionValue());
        holder.emotion_text_view.setSelected(emotionItem.isSelected());

        holder.itemView.setOnClickListener(view -> {
            itemClickedListener.onItemClicked(position, emotionItem.emotionValue);
            emotionItem.setSelected(true);
            setItemSelected(position);
        });
    }

    public void setItemSelected(int position) {

        if (position == selectedPos)
            return;

        if (selectedPos != -1) {
            EmotionItem oldEmotionItem = emotionList.get(selectedPos);
            oldEmotionItem.setSelected(false);
            emotionList.set(selectedPos, oldEmotionItem);
            notifyItemChanged(selectedPos);
        }

        EmotionItem emotionItem = emotionList.get(position);
        emotionItem.setSelected(true);
        emotionList.set(position, emotionItem);

        selectedPos = position;
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return emotionList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView emotion_text_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            emotion_text_view = itemView.findViewById(R.id.emotion_text_view);
        }
    }

    public static class EmotionItem {
        String emotionValue;
        boolean isSelected;

        public EmotionItem(String emotionValue, boolean isSelected) {
            this.emotionValue = emotionValue;
            this.isSelected = isSelected;
        }

        public EmotionItem() {
        }

        public String getEmotionValue() {
            return emotionValue;
        }

        public void setEmotionValue(String emotionValue) {
            this.emotionValue = emotionValue;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}