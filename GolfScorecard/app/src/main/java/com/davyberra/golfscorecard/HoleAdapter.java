package com.davyberra.golfscorecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HoleAdapter extends RecyclerView.Adapter<HoleAdapter.ViewHolder> {

    private Hole[] holes;

    public HoleAdapter(Hole[] holes) {
        this.holes = holes;
    }

    @Override
    public HoleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View holeView = inflater.inflate(R.layout.hole_row_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(holeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hole hole = holes[position];

        TextView textView1 = holder.holeNumTextView;
        String text1 = String.format("Hole: %s", position + 1);
        textView1.setText(text1);
        TextView strokesTextView = holder.holeStrokesTextView;
        strokesTextView.setText(String.format("%s", hole.getStrokes()));
        Button button1 = holder.cntDecreaseButton;
        button1.setText("-");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int strokes = hole.getStrokes() -  1;
                hole.setStrokes(strokes);
                strokesTextView.setText(String.format("%s", strokes));
            }
        });
        Button button2 = holder.cntIncreaseButton;
        button2.setText("+");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int strokes = hole.getStrokes() + 1;
                hole.setStrokes(strokes);
                strokesTextView.setText(String.format("%s", strokes));
            }
        });
    }

    @Override
    public int getItemCount() {
        return holes.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView holeNumTextView;
        public TextView holeStrokesTextView;
        public Button cntDecreaseButton;
        public Button cntIncreaseButton;

        public ViewHolder(View itemView) {
            super(itemView);

            holeNumTextView = (TextView) itemView.findViewById(R.id.hole_item_text1);
            holeStrokesTextView = (TextView) itemView.findViewById(R.id.hole_item_text2);
            cntDecreaseButton = (Button) itemView.findViewById(R.id.hole_item_button1);
            cntIncreaseButton = (Button) itemView.findViewById(R.id.hole_item_button2);
        }
    }
}
