package dlc.testproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CourseDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        Intent intent = getIntent();
        Course course = (Course) intent.getExtras().get("course");

        TextView courseName = findViewById(R.id.course_name);
        TextView courseInfo = findViewById(R.id.course_description);

        courseName.setText(course.name);
        courseInfo.setText(course.description);
    }
}
