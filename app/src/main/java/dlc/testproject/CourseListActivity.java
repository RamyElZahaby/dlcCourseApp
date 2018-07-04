package dlc.testproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

public class CourseListActivity extends AppCompatActivity {

    private static final String TAG = "CourseListActivity";
    private ArrayList<Course> courses = new ArrayList<>();
    SharedPreferences prefs;
    private CoursesAdapter coursesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        Log.d(TAG, "onCreate: started");
        prefs = getSharedPreferences("db", MODE_PRIVATE);


        FloatingActionButton createCrs = findViewById(R.id.create_fab);
        createCrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseListActivity.this, CreateCourseActivity.class);
                startActivity(intent);
            }
        });

        initCourses();
    }

    //First I initialize the list of courses
    private void initCourses()
    {
        Log.d(TAG, "initCourses: Initializing");

        //Use Gson library to convert objects from and to JSON to be able to store it in shared preferences as strings
        Gson gson = new Gson();

        //Get values back from db
        Map<String, ?> storedData =  prefs.getAll();
        ArrayList<String> jsonArray = new ArrayList<>(storedData.keySet());
        for(String courseJson: jsonArray)
            courses.add(gson.fromJson(courseJson, Course.class));

        //After initializing the list of courses, I initialize the recycler view
        initRecyclerView();
    }

    private void initRecyclerView()
    {
        Log.d(TAG, "initRecyclerView: initialized");

        //Choose the the recycler view by its ID from the layout
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        //Create a courseList adapter which handles the recycler view (or any list view)
        coursesAdapter = new CoursesAdapter(this, courses);

        //Setting the View's layout Manager as Linear Layout manager and passing the activity as a context.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Set the adapter managing the recyler view
        recyclerView.setAdapter(coursesAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        courses.clear();
        initCourses();

    }
}
