package edu.jsu.mcis.cs408.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.jsu.mcis.cs408.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final int KEYS_HEIGHT = 4;
    private static final int KEYS_WIDTH = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void initLayout() {

        int[][] horizontals = new int[KEYS_HEIGHT][KEYS_WIDTH];
        int[][] verticals = new int[KEYS_WIDTH][KEYS_HEIGHT];
        int[]btnIds = new int[KEYS_HEIGHT];
        ConstraintLayout layout = binding.layout;

        for(int i = 0; i <= KEYS_HEIGHT; i++) {

            Button btn = new Button(this);
            int id = View.generateViewId(); // generate new ID

            btn.setId(id); // assign ID
            btn.setTag("btn " + i); // assign tag (for acquiring references later)
            btn.setText("TextView Chain Element " + i); // set text (using a string resource)
            btn.setTextSize(24); // set size
            layout.addView(btn); // add to layout
            btnIds[i] = id; // store ID to collection

        }
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        for (int id : btnIds) {
            set.connect(id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            set.connect(id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        }
        set.createVerticalChain(ConstraintSet.PARENT_ID, ConstraintSet.TOP, ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM, btnIds, null, ConstraintSet.CHAIN_PACKED);



    }
}