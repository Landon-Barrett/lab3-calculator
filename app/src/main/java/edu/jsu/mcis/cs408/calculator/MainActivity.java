package edu.jsu.mcis.cs408.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;


import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.beans.PropertyChangeEvent;

import edu.jsu.mcis.cs408.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AbstractView {
    private ActivityMainBinding binding;
    private static final int KEYS_HEIGHT = 4;
    private static final int KEYS_WIDTH = 5;
    //private TextView output;
    private String[][] btnText;
    private String[][] btnTags;
    private StringBuilder output = new StringBuilder();
    private CalculatorState calState;
    private DefaultController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /* Create Controller and Model */

        controller = new DefaultController();
        DefaultModel model = new DefaultModel();

        /* Register Activity View and Model with Controller */

        controller.addView(this);
        controller.addModel(model);

        /* Initialize Model to Default Values */

        model.initDefault();

        /* Associate Click Handler with Input Buttons */

        //DefaultClickHandler click = new DefaultClickHandler();
        btnText = getButtonText();
        btnTags = getButtonTags();

        initLayout();
    }
    @Override
    public void modelPropertyChange(final PropertyChangeEvent evt) {

        /*
         * This method is called by the "propertyChange()" method of AbstractController
         * when a change is made to an element of a Model.  It identifies the element that
         * was changed and updates the View accordingly.
         */

        String propertyName = evt.getPropertyName();
        String propertyValue = evt.getNewValue().toString();
        ConstraintLayout layout = binding.layout;
        TextView output = new TextView(this);
        //Log.i(TAG, "New " + propertyName + " Value from Model: " + propertyValue);
        for (int i = 0; i < layout.getChildCount(); ++i) {
            View child = layout.getChildAt(i);
            if(child instanceof TextView && !(child instanceof Button)) {
                output = (TextView) child;
            }
        }
        if ( propertyName.equals(DefaultController.ELEMENT_OUTPUT_PROPERTY) ) {

            String oldPropertyValue = (output.getText().toString());
            if ( !oldPropertyValue.equals(propertyValue) ) {
                output.setText(propertyValue);
            }

        }
        /*
        else if ( propertyName.equals(DefaultController.ELEMENT_TEXT2_PROPERTY) ) {

            String oldPropertyValue = binding.outputText2.getText().toString();

            if ( !oldPropertyValue.equals(propertyValue) ) {
                binding.outputText2.setText(propertyValue);
            }

        }
        */
    }

    class CalculatorClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            String tag = view.getTag().toString();
            Toast toast = Toast.makeText(binding.getRoot().getContext(), tag, Toast.LENGTH_SHORT);
            toast.show();

            /*
            Column 0 button logic
             */

            if (tag.equals("btn7")) {
                String newText = btnText[0][0];
                controller.changeElementOutput(newText);
                //controller.changeElementCalState(CalculatorState.LHS);
            }
            else if (tag.equals("btn4")) {
                String newText = btnText[0][1];
                controller.changeElementOutput(newText);
            }
            else if (tag.equals("btn1")) {
                String newText = btnText[0][2];
                controller.changeElementOutput(newText);
            }
            else if (tag.equals("btnSign")) {
                String newText = btnText[0][3];
                controller.changeElementOutput(newText);
            }
            /*
            Column 1
             */
            else if (tag.equals("btn8")) {
                String newText = btnText[1][0];
                controller.changeElementOutput(newText);
            }
            else if (tag.equals("btn5")) {
                String newText = btnText[1][1];
                controller.changeElementOutput(newText);
            }
            else if (tag.equals("btn2")) {
                String newText = btnText[1][2];
                controller.changeElementOutput(newText);
            }
            else if (tag.equals("btn0")) {
                String newText = btnText[1][3];
                controller.changeElementOutput(newText);
            }
            /*
            Column 2
             */
            else if (tag.equals("btn9")) {
                String newText = btnText[2][0];
                controller.changeElementOutput(newText);
            }
            else if (tag.equals("btn6")) {
                String newText = btnText[2][1];
                controller.changeElementOutput(newText);
            }
            else if (tag.equals("btn3")) {
                String newText = btnText[2][2];
                controller.changeElementOutput(newText);
            }
            else if (tag.equals("btnDecimal")) {
                String newText = btnText[2][3];
                controller.changeElementOutput(newText);
            }
            /*
            Column 3
             */
            else if (tag.equals("btnSquare")) {
                String newText = btnText[3][0];
                controller.changeElementOutput(newText);
            }
            else if (tag.equals("btnDivide")) {
                String newText = btnText[3][1];
                controller.changeElementOutput(newText);
            }
            else if (tag.equals("btnMultiply")) {
                String newText = btnText[3][2];
                controller.changeElementOutput(newText);
            }
            else if (tag.equals("btnAdd")) {
                String newText = btnText[3][3];
                controller.changeElementCalState(CalculatorState.OP_SELECTED);
                controller.changeElementOutput(newText);

                controller.changeElementOperator(Operator.ADD);
            }
            /*
            Column 4
             */
            else if (tag.equals("btnClear")) {
                //String newText = (output.append(btnText[4][0])).toString();
                calState = CalculatorState.CLEAR;
                controller.changeElementCalState(calState);
            }
            else if (tag.equals("btnPercent")) {
                String newText = btnText[4][1];
                controller.changeElementOutput(newText);
            }
            else if (tag.equals("btnSubtract")) {
                String newText = btnText[4][2];
                controller.changeElementOutput(newText);
            }
            else if (tag.equals("btnEquals")) {
                String newText = btnText[4][3];
                //controller.changeElementOutput(newText);
                controller.changeElementCalState(CalculatorState.RESULT);
            }

        }

    }


    private void initLayout() {

        CalculatorClickHandler click = new CalculatorClickHandler();
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

        for(int i = 0; i < KEYS_WIDTH; i++) {

            for (int j = 0; j < KEYS_HEIGHT; j++) {

                Button btn = new Button(this);
                id = View.generateViewId();

                btn.setId(id);
                btn.setText(btnText[i][j]);
                btn.setTag(btnTags[i][j]);
                btn.setTextSize(24);
                layout.addView(btn);
                btn.setOnClickListener(click);

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

    /*
    public String findButtonText(String target) {

        String[][] btnText = getButtonText();

        for(int i = 0; i < KEYS_WIDTH; i++) {
            for(int j = 0; j < KEYS_HEIGHT; j++) {
                String value = btnText[i][j];
                value = value[3]
                if(target.equals(btnText[i][j]))
            }

        }

    }
    */
}