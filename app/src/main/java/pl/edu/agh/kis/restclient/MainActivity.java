package pl.edu.agh.kis.restclient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

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
        new HttpPostTask().execute(new Student(firstName, lastName));
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

    private class HttpPostTask extends AsyncTask<Student, Void, Student> {
        @Override
        protected Student doInBackground(Student... params) {
            try {
                final String url = "http://46.101.116.241:8080/";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
                return restTemplate.postForEntity(url + "/students", params[0], Student.class, "").getBody();
            } catch (Throwable e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Student student) {
            if (student != null) {
                giveFeedback("Hello " + student.getFirstName() + " " + student.getLastName() + "\n" + "Nice to see you're such a decent student!");
            } else {
                giveFeedback("Well, your request appeared to be incorrect...");
            }
        }
    }

}
