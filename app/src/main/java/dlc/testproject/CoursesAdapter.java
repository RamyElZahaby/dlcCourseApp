package dlc.testproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.courseViewHolder> {
    private static final String TAG = "CoursesAdapter";

    private ArrayList<Course> courses = new ArrayList<>();
    private Context context;

    public CoursesAdapter(Context context, ArrayList<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public courseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new courseViewHolder(view);
    }

    //Function that is called whenever a new object is added to the recycler's view
    @Override
    public void onBindViewHolder(@NonNull courseViewHolder holder, final int position)
    {
        Log.d(TAG, "onBindViewHolder: Called");
        Course crs = courses.get(position);
        holder.getCourseName().setText(crs.name);

        Picasso.get()
                .load(courses.get(position).img)
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CourseDetailsActivity.class);
                intent.putExtra("course", courses.get(position));
                context.startActivity(intent);

                //Toast.makeText(context, courses.get(position).description, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class courseViewHolder extends ViewHolder{

        CircleImageView image;
        TextView courseName;

        public courseViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            courseName = itemView.findViewById(R.id.image_name);

        }

        public CircleImageView getImage() {
            return image;
        }

        public void setImage(CircleImageView image) {
            this.image = image;
        }

        public TextView getCourseName() {
            return courseName;
        }

        public void setCourseName(TextView courseName) {
            this.courseName = courseName;
        }
    }
}
