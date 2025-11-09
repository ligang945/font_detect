package com.example.fontdetect;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FontDetector";
    private RecyclerView fontsRecyclerView;
    private FontAdapter fontAdapter;
    private ArrayList<String> fontNames;
    private ArrayList<String> fontPaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fontsRecyclerView = findViewById(R.id.fontsRecyclerView);
        fontsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        detectSystemFonts();

        fontAdapter = new FontAdapter(fontNames, fontPaths);
        fontsRecyclerView.setAdapter(fontAdapter);
    }

    private void detectSystemFonts() {
        ArrayList<String> tempNames = new ArrayList<>();
        ArrayList<String> tempPaths = new ArrayList<>();
        Set<String> uniqueFonts = new HashSet<>();

        // Detect fonts from system font directory
        File fontDir = new File("/system/fonts");
        if (fontDir.exists() && fontDir.isDirectory()) {
            File[] fontFiles = fontDir.listFiles();
            if (fontFiles != null) {
                for (File fontFile : fontFiles) {
                    if (fontFile.getName().toLowerCase().endsWith(".ttf") ||
                        fontFile.getName().toLowerCase().endsWith(".otf") ||
                        fontFile.getName().toLowerCase().endsWith(".ttc")) {

                        String fontName = fontFile.getName().substring(0, fontFile.getName().lastIndexOf('.'));

                        // Only add if not already in the list
                        if (uniqueFonts.add(fontName)) {
                            tempNames.add(fontName);
                            tempPaths.add(fontFile.getAbsolutePath());
                        }
                    }
                }
            }
        }

        // Add some standard generic font names as fallbacks
        String[] defaultFonts = {
            "default", "default-bold", "sans", "sans-serif",
            "serif", "monospace", "casual", "cursive", "sans-serif-condensed"
        };

        for (String font : defaultFonts) {
            if (uniqueFonts.add(font)) {
                tempNames.add(font);
                tempPaths.add("");
            }
        }

        // Sort both lists together based on font names
        for (int i = 0; i < tempNames.size(); i++) {
            for (int j = i + 1; j < tempNames.size(); j++) {
                if (tempNames.get(i).compareTo(tempNames.get(j)) > 0) {
                    // Swap font names
                    String tempName = tempNames.get(i);
                    tempNames.set(i, tempNames.get(j));
                    tempNames.set(j, tempName);

                    // Swap corresponding paths
                    String tempPath = tempPaths.get(i);
                    tempPaths.set(i, tempPaths.get(j));
                    tempPaths.set(j, tempPath);
                }
            }
        }

        fontNames = tempNames;
        fontPaths = tempPaths;

        Log.d(TAG, "Detected " + fontNames.size() + " fonts");
    }
}