package com.example.fontdetect;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.List;

public class FontAdapter extends RecyclerView.Adapter<FontAdapter.FontViewHolder> {

    private List<String> fontNames;
    private List<String> fontPaths;

    public FontAdapter(List<String> fontNames, List<String> fontPaths) {
        this.fontNames = fontNames;
        this.fontPaths = fontPaths;
    }

    @NonNull
    @Override
    public FontViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.font_item, parent, false);
        return new FontViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FontViewHolder holder, int position) {
        String fontName = fontNames.get(position);
        String fontPath = fontPaths.get(position);

        holder.fontName.setText(fontName);

        // Apply the font if it exists in the system
        if (!fontPath.isEmpty() && new File(fontPath).exists()) {
            try {
                Typeface typeface = Typeface.createFromFile(fontPath);
                holder.fontSample.setTypeface(typeface);
            } catch (Exception e) {
                // If there's an error loading the font, use default
                holder.fontSample.setTypeface(Typeface.DEFAULT);
                holder.fontName.setText(fontName + " (Error loading font)");
            }
        } else {
            // Try to create a typeface with the font name
            try {
                Typeface typeface = Typeface.create(fontName, Typeface.NORMAL);
                if (typeface != null) {
                    holder.fontSample.setTypeface(typeface);
                } else {
                    holder.fontSample.setTypeface(Typeface.DEFAULT);
                }
            } catch (Exception e) {
                holder.fontSample.setTypeface(Typeface.DEFAULT);
            }
        }

        // Set the sample text to show the font
        holder.fontSample.setText("The quick brown fox jumps over the lazy dog 0123456789\n用于中文字体测试的文字通常是包含丰富笔画和偏旁的常用汉字组合");
    }

    @Override
    public int getItemCount() {
        return fontNames.size();
    }

    static class FontViewHolder extends RecyclerView.ViewHolder {
        TextView fontName;
        TextView fontSample;

        FontViewHolder(View itemView) {
            super(itemView);
            fontName = itemView.findViewById(R.id.fontName);
            fontSample = itemView.findViewById(R.id.fontSample);
        }
    }
}