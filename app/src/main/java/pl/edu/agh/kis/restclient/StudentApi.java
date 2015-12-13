package pl.edu.agh.kis.restclient;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PATCH;
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

    // TODO use PATCH method and /students/{id} path to create changeMark mathod
    Call<Student> changeMark(here insert 2 parameters - Path and Body);
}
