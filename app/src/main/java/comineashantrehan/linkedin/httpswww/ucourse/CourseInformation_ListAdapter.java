package comineashantrehan.linkedin.httpswww.ucourse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseInformation_ListAdapter extends ArrayAdapter<CourseInformationItems> {

    /*
    private LayoutInflater mInflater;
    private ArrayList<CourseInformationItems> courseItems;
    private int mViewResourceId;

    public CourseInformation_ListAdapter(Context context, int textViewResourceId, ArrayList<CourseInformationItems> users) {
        super(context, textViewResourceId, users);
        this.courseItems = courseItems;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        CourseInformationItems couseitems = courseItems.get(position);

        if (couseitems != null) {
            TextView firstName = (TextView) convertView.findViewById(R.id.textFirstName);
            TextView lastName = (TextView) convertView.findViewById(R.id.textLastName);
            TextView favFood = (TextView) convertView.findViewById(R.id.textFavFood);
            if (firstName != null) {
                firstName.setText(couseitems.getCode());
            }
            if (lastName != null) {
                lastName.setText((couseitems.getGrade()));
            }
            if (favFood != null) {
                favFood.setText((couseitems.getComments()));
            }
        }

        return convertView;
    }
}
*/
    private LayoutInflater mInflater;
    private ArrayList<CourseInformationItems> users;
    private int mViewResourceId;

    public CourseInformation_ListAdapter(Context context, int textViewResourceId, ArrayList<CourseInformationItems> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        CourseInformationItems user = users.get(position);

        if (user != null) {
            TextView firstName = (TextView) convertView.findViewById(R.id.textFirstName);
            TextView lastName = (TextView) convertView.findViewById(R.id.textLastName);
            TextView favFood = (TextView) convertView.findViewById(R.id.textFavFood);
            if (firstName != null) {
                firstName.setText(user.getFirstName());
            }
            if (lastName != null) {
                lastName.setText((user.getLastName()));
            }
            if (favFood != null) {
                favFood.setText((user.getFavFood()));
            }
        }

        return convertView;
    }
}