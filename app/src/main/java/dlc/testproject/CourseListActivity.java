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


        //**************** Store courses to db ****************//
        //create a shared preferences editor
//        SharedPreferences.Editor editor = prefs.edit();




//        Course crs = new Course("Computer Science", "CS description", "https://cdn.britannica.com/700x450/77/170477-004-B774BDDF.jpg");
//        String json = gson.toJson(crs);
//        editor.putString(json, json);
//
//        crs = new Course("Maths", "Maths description",
//                "https://www.tes.com/sites/default/files/styles/news_article_hero/public/maths_revision.png?itok=PkFnF-cj");
//        json = gson.toJson(crs);
//        editor.putString(json, json);
//
//        //Put the courses list's JSON in the shared preferences with its corresponding storage key
//        //editor.putString(storageKey, json);
//
//        //Apply changes made to the shared preferences
//        editor.apply();


        //Use Gson library to convert the courses list to Json
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

        //Choose the the recycler view by its ID from the
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        coursesAdapter = new CoursesAdapter(this, courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(coursesAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        courses.clear();
        initCourses();

    }
}
