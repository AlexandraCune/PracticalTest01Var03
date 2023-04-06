package ro.pub.cs.systems.eim.practicaltest01var03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var03SecondaryActivity extends AppCompatActivity {

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private Button correctButton = null;
    private Button incorrectButton = null;
    private TextView textView = null;

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.correct_button:
                    setResult(RESULT_OK, new Intent());
                    break;
                case R.id.incorrect_button:
                    setResult(RESULT_CANCELED, new Intent());
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_secondary);
        correctButton = (Button)findViewById(R.id.correct_button);
        correctButton.setOnClickListener(buttonClickListener);
        incorrectButton = (Button)findViewById(R.id.incorrect_button);
        incorrectButton.setOnClickListener(buttonClickListener);
        textView = (TextView)findViewById(R.id.op_result_text_view);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("result")) {
            String op_result = intent.getStringExtra("result");
            String op = intent.getStringExtra("operation");
            String x = op + " "+ op_result;
            textView.setText(x);
        }
    }
}