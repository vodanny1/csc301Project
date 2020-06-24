package comineashantrehan.linkedin.httpswww.ucourse;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder{

    public TextView code,grade,comment;

    public SearchViewHolder(View itemView){
        super(itemView);
        code = (TextView) itemView.findViewById(R.id.name);
        grade = (TextView) itemView.findViewById(R.id.grade);
        comment = (TextView) itemView.findViewById(R.id.comment);

    }

}
public class SearchCourseAdapter extends RecyclerView.Adapter<SearchViewHolder>{

    private Context context;
    private List<CourseInformationItems> courseitems;

    public SearchCourseAdapter(Context context, List<CourseInformationItems> courseitems) {
        this.context = context;
        this.courseitems = courseitems;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_searchcourse,parent,false);

        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.code.setText(courseitems.get(position).getFirstName());
        holder.grade.setText(courseitems.get(position).getLastName());
        holder.comment.setText(courseitems.get(position).getFavFood());;

    }

    @Override
    public int getItemCount() {
        return courseitems.size();
    }
}

