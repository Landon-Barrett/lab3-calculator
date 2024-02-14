package edu.jsu.mcis.cs408.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import edu.jsu.mcis.cs408.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final int KEYS_HEIGHT = 4;
    private static final int KEYS_WIDTH = 5;
    private String[][] btnText;
    private String[][] btnTags;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initLayout();
    }

    private void initLayout() {

        ConstraintLayout layout = binding.layout;
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);

        int[][] horizontals = new int[KEYS_HEIGHT][KEYS_WIDTH];
        int[][] verticals = new int[KEYS_WIDTH][KEYS_HEIGHT];

        Guideline north = binding.guideNorth;
        Guideline south = binding.guideSouth;
        Guideline east = binding.guideEast;
        Guideline west = binding.guideWest;

        int id = View.generateViewId();
        TextView output = new TextView(this);
        output.setId(id);
        output.setTag(getResources().getString(R.string.placeHolderNumTag));
        output.setText(getResources().getString(R.string.placeHolderNum));
        output.setTextSize(48);
        output.setGravity(Gravity.RIGHT);

        layout.addView(output);
        ViewGroup.LayoutParams params = output.getLayoutParams();

        btnText = getButtonText();
        btnTags = getButtonTags();

        for(int i = 0; i < KEYS_WIDTH; i++) {

            for (int j = 0; j < KEYS_HEIGHT; j++) {

                Button btn = new Button(this);
                id = View.generateViewId();

                btn.setId(id);
                btn.setText(btnText[i][j]);
                btn.setTag(btnTags[i][j]);
                btn.setTextSize(24);
                layout.addView(btn);

                horizontals[j][i] = id;
                verticals[i][j] = id;
            }

        }

        for(int i = 0; i < KEYS_WIDTH; i++) {

            set.createVerticalChain(output.getId(), ConstraintSet.BOTTOM, south.getId(),
                    ConstraintSet.BOTTOM, verticals[i], null, ConstraintSet.CHAIN_PACKED);
        }

        for(int i = 0; i < KEYS_HEIGHT; i++) {

            set.createHorizontalChain(west.getId(), ConstraintSet.LEFT, east.getId(),
                    ConstraintSet.RIGHT, horizontals[i], null, ConstraintSet.CHAIN_PACKED);
        }

        set.connect(output.getId(), ConstraintSet.LEFT, west.getId(), ConstraintSet.LEFT, 0);
        set.connect(output.getId(), ConstraintSet.RIGHT, east.getId(), ConstraintSet.RIGHT, 0);
        set.connect(output.getId(), ConstraintSet.TOP, north.getId(), ConstraintSet.TOP, 0);
        set.connect(output.getId(), ConstraintSet.BOTTOM, (horizontals[0][2]), ConstraintSet.TOP, 0);
        set.applyTo(layout);

        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        output.setLayoutParams(params);
    }

    public String[][] getButtonText() {

        String[] column1 = getResources().getStringArray(R.array.buttonsColumn1);
        String[] column2 = getResources().getStringArray(R.array.buttonsColumn2);
        String[] column3 = getResources().getStringArray(R.array.buttonsColumn3);
        String[] column4 = getResources().getStringArray(R.array.buttonsColumn4);
        String[] column5 = getResources().getStringArray(R.array.buttonsColumn5);
        String[][] btnText = {column1, column2, column3, column4, column5};

        return btnText;
    }

    public String[][] getButtonTags() {

        String[] column1 = getResources().getStringArray(R.array.buttonsColumn1Tags);
        String[] column2 = getResources().getStringArray(R.array.buttonsColumn2Tags);
        String[] column3 = getResources().getStringArray(R.array.buttonsColumn3Tags);
        String[] column4 = getResources().getStringArray(R.array.buttonsColumn4Tags);
        String[] column5 = getResources().getStringArray(R.array.buttonsColumn5Tags);
        String[][] btnTags = {column1, column2, column3, column4, column5};

        return btnTags;
    }

}