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

    private StudentApi rest = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                    .create()))
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
        String lastName = getStringFromEditField(R.id.lastName);
        if (lastName.isEmpty()) {
            giveFeedback("Fill in your name!");
            return;
        }

        rest.getStudentByLastName(lastName).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Response<Student> response, Retrofit retrofit) {
                System.out.println(response.raw());
                giveFeedback("Your current mark is " + response.body());
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
