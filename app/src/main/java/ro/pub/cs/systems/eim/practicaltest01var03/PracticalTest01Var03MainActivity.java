package ro.pub.cs.systems.eim.practicaltest01var03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {

    private Button add_button = null;
    private Button diff_button = null;
    private EditText first_term_edit_text = null;
    private EditText second_term_edit_text = null;
    private EditText result_edit_text = null;
    private Button navigate_button = null;
    String operation = null;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private void StartPracticalService(int first_term, int second_term){

        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
        intent.putExtra("first_number", first_term);
        intent.putExtra("second_number", second_term);
        getApplicationContext().startService(intent);

    }
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(first_term_edit_text.getText().toString().matches("[0-9]+") && second_term_edit_text.getText().toString().matches("[0-9]+") ) {
                switch (view.getId()) {
                    case R.id.add_button:
                        operation = "add";

                        Integer first_term = Integer.parseInt(first_term_edit_text.getText().toString());
                        Integer second_term = Integer.parseInt(second_term_edit_text.getText().toString());

                        StartPracticalService(first_term, second_term);
                        if (first_term_edit_text.getText().toString().matches("[0-9]+") && second_term_edit_text.getText().toString().matches("[0-9]+")) {
                            result_edit_text.setText(String.valueOf(first_term + second_term));
                        } else {
                            Toast.makeText(getApplicationContext(), "Not int value", Toast.LENGTH_LONG).show();

                        }

                        break;
                    case R.id.diff_button:
                        operation = "diff";
                        first_term = Integer.parseInt(first_term_edit_text.getText().toString());
                        second_term = Integer.parseInt(second_term_edit_text.getText().toString());
                        StartPracticalService(first_term, second_term);
                        if (first_term_edit_text.getText().toString().matches("[0-9]+") && second_term_edit_text.getText().toString().matches("[0-9]+")) {
                            result_edit_text.setText(String.valueOf(first_term - second_term));
                        } else {
                            Toast.makeText(getApplicationContext(), "Not int value", Toast.LENGTH_LONG).show();

                        }
//                    result_edit_text.setText(String.valueOf(first_term - second_term));
                        break;
                    case R.id.navigate_button:
                        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03SecondaryActivity.class);
                        intent.putExtra("operation", operation);
                        intent.putExtra("result", result_edit_text.getText().toString());
                        startActivityForResult(intent, 2016);
                        break;
                }
            }else{
                Toast.makeText(getApplicationContext(), "Not int value", Toast.LENGTH_LONG).show();
            }
        }
    }
    protected void onDestroy(){
        super.onDestroy();
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
        getApplicationContext().stopService(intent);
    }

    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.BROADCAST_RECEIVER_EXTRA,intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
        }
    }
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);

        add_button = (Button)findViewById(R.id.add_button);
        add_button.setOnClickListener(buttonClickListener);
        diff_button = (Button)findViewById(R.id.diff_button);
        diff_button.setOnClickListener(buttonClickListener);
        navigate_button = (Button)findViewById(R.id.navigate_button);
        navigate_button.setOnClickListener(buttonClickListener);
        first_term_edit_text = (EditText)findViewById(R.id.first_term_edit_text);
        second_term_edit_text = (EditText)findViewById(R.id.second_term_edit_text);
        result_edit_text = (EditText)findViewById(R.id.result_edit_text);

        if(savedInstanceState == null){
            first_term_edit_text.setText(String.valueOf(0));
            second_term_edit_text.setText(String.valueOf(0));
            result_edit_text.setText(String.valueOf(0));
        } else {
            String final_elem = "";
            if (savedInstanceState.containsKey("first_term")) {
                final_elem += savedInstanceState.getString("first_term");
                first_term_edit_text.setText(savedInstanceState.getString("first_term"));
            } else {
                first_term_edit_text.setText(String.valueOf(0));
            }
            if (savedInstanceState.containsKey("second_term")) {
                final_elem += " ";
                final_elem += savedInstanceState.getString("second_term");
                second_term_edit_text.setText(savedInstanceState.getString("second_term"));
            } else {
                second_term_edit_text.setText(String.valueOf(0));
            }

            if (savedInstanceState.containsKey("result")) {
                final_elem += " ";
                final_elem += savedInstanceState.getString("result");

                result_edit_text.setText(savedInstanceState.getString("result"));
            } else {
                result_edit_text.setText(String.valueOf(0));
            }

            Toast.makeText(getApplicationContext(), final_elem, Toast.LENGTH_LONG).show();

        }

        intentFilter.addAction(Constants.actionTypes[0]);

//        for(int index = 0; index < Constants.actionTypes.length; index++) {
//            intentFilter.addAction(Constants.actionTypes[index]);
//        }
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("first_term", first_term_edit_text.getText().toString());
        savedInstanceState.putString("second_term", second_term_edit_text.getText().toString());
        savedInstanceState.putString("result", result_edit_text.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String final_elem = "";

        if (savedInstanceState.containsKey("first_term")) {
            final_elem += savedInstanceState.getString("first_term");

            first_term_edit_text.setText(savedInstanceState.getString("first_term"));
        } else {
            first_term_edit_text.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("second_term")) {
            final_elem += " ";
            final_elem += savedInstanceState.getString("second_term");

            second_term_edit_text.setText(savedInstanceState.getString("second_term"));
        } else {
            second_term_edit_text.setText(String.valueOf(0));
        }

        if (savedInstanceState.containsKey("result")) {
            final_elem += " ";
            final_elem += savedInstanceState.getString("result");
            result_edit_text.setText(savedInstanceState.getString("result"));
        } else {
            result_edit_text.setText(String.valueOf(0));
        }
        Toast.makeText(getApplicationContext(), final_elem, Toast.LENGTH_LONG).show();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch(requestCode) {
            case 2016:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "Activity returned with result Correct" , Toast.LENGTH_LONG).show();
                }
                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Activity returned with result Incorrect" , Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

}