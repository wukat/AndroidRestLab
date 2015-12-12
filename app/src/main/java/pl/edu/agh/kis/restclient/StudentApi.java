package pl.edu.agh.kis.restclient;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by wukat on 12.12.15.
 */
public interface StudentApi {

    @GET("/students/{id}")
    Call<Student> getStudent(@Path("id") String id);

    @GET("/students/search/findByLastName")
    Call<Student> getStudentByLastName(@Query("name") String lastName);

}
