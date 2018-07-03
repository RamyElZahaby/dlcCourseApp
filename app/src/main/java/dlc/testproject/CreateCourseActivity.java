package dlc.testproject;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class CreateCourseActivity extends AppCompatActivity {

    SharedPreferences createPrefs;
    private final static String storageKey = "coursesArray";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        Button createButton = findViewById(R.id.create_course_button);

        createPrefs =  getSharedPreferences("db", MODE_PRIVATE);


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String courseName = ((EditText)findViewById(R.id.course_name_input)).getText().toString();
                String courseDescription = ((EditText)findViewById(R.id.course_description_input)).getText().toString();
                Course newCourse = new Course(courseName, courseDescription);

                //create a shared preferences editor
                SharedPreferences.Editor editor = createPrefs.edit();

                //Use Gson library to convert the courses list to Json
                Gson gson = new Gson();
                String json = gson.toJson(newCourse);

                //Put the courses list's JSON in the shared preferences with its corresponding storage key
                editor.putString(json, json);

                //Apply changes made to the shared preferences
                editor.apply();

                //Pop up confirming the course creation.
                Toast.makeText(CreateCourseActivity.this, "Course Created Successfully.", Toast.LENGTH_LONG );


                CreateCourseActivity.this.onBackPressed();

            }
        });
    }


}
