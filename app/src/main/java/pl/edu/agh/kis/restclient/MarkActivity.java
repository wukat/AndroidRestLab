package pl.edu.agh.kis.restclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by wukat on 12.12.15.
 */
public class MarkActivity extends Activity {

    // TODO add GSON Converter Factory hare
    private StudentApi rest = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(here add gson factory)
            .build().create(StudentApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void checkMarks(View button) {
        String id = getStringFromEditField(R.id.idOfStudent);
        if (id.isEmpty()) {
            giveFeedback("Fill in your id!");
            return;
        }

        rest.getStudent(id).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Response<Student> response, Retrofit retrofit) {
                if (response.body() != null && response.body().getFirstName() != null) {
                    giveFeedback("Your current mark is " + response.body().getMark());
                } else {
                    giveFeedback("It seems like student you're looking for doesn't exist.");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                giveFeedback("Well, it seems not to work!");
            }
        });
    }

    public void changeMark(View button) {
        String id = getStringFromEditField(R.id.idOfStudent);
        if (id.isEmpty()) {
            giveFeedback("Fill in your id!");
            return;
        }

        String mark = getStringFromEditField(R.id.newMark);
        float markF;
        try {
            markF = Float.parseFloat(mark);
        } catch (Exception e) {
            giveFeedback("Fill in proper mark!");
            return;
        }

        rest.changeMark(id, new MarkUpdate(markF)).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Response<Student> response, Retrofit retrofit) {
                if (response.code() == 200 || response.code() == 204) {
                    giveFeedback("Ok, check your mark again!");
                } else {
                    giveFeedback("It seems like student you're looking for doesn't exist.");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                giveFeedback("Well, it seems not to work!");
            }
        });
    }

    private String getStringFromEditField(int id) {
        final EditText field = (EditText) findViewById(id);
        return field.getText().toString();
    }

    private void giveFeedback(String feedback) {
        TextView feedbackField = (TextView) findViewById(R.id.feedback);
        feedbackField.setText(feedback);
    }
}
