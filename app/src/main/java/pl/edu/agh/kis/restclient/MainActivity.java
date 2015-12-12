package pl.edu.agh.kis.restclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by wukat on 12.12.15.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void register(View button) {
        String firstName = getStringFromEditField(R.id.firstName);
        String lastName = getStringFromEditField(R.id.lastName);
        if (firstName.isEmpty() || lastName.isEmpty()) {
            giveFeedback("Fill in your name!");
            return;
        }
        giveFeedback("You should have registered by now... You'd better check your marks!");
    }

    public void goToMarks(View button) {
        Intent intent = new Intent(this, MarkActivity.class);
        startActivity(intent);
        finish();
    }

    private String getStringFromEditField(int id) {
        final EditText field = (EditText) findViewById(id);
        return field.getText().toString();
    }

    private String getStringFromEditFieldWithFeedback(int id, String feedbackIfEmpty) {
        String value = getStringFromEditField(id);
        if (value.isEmpty()) {
            giveFeedback(feedbackIfEmpty);
        }
        return value;
    }

    private void giveFeedback(String feedback) {
        TextView feedbackField = (TextView) findViewById(R.id.feedback);
        feedbackField.setText(feedback);
    }
}