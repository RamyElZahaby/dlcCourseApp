package dlc.testproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class CourseListActivity extends AppCompatActivity {

    private static final String TAG = "CourseListActivity";
    private ArrayList<Course> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        Log.d(TAG, "onCreate: started");

        initCourses();
    }

    //First I initialize the list of courses
    private void initCourses()
    {
        Log.d(TAG, "initCourses: Initializing");
        courses.add(new Course("Computer Science", "CS description", "https://cdn.britannica.com/700x450/77/170477-004-B774BDDF.jpg"));
        courses.add(new Course("Maths", "Maths description",
                "https://www.tes.com/sites/default/files/styles/news_article_hero/public/maths_revision.png?itok=PkFnF-cj"));

        //After initializing the list of courses, I initialize the recycler view
        initRecyclerView();
    }

    private void initRecyclerView()
    {
        Log.d(TAG, "initRecyclerView: initialized");

        //Choose the the recycler view by its ID from the
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        CoursesAdapter coursesAdapter = new CoursesAdapter(this, courses);
        recyclerView.setAdapter(coursesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
