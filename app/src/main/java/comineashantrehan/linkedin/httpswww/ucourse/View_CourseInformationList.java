package comineashantrehan.linkedin.httpswww.ucourse;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class View_CourseInformationList extends AppCompatActivity {
    /*
        SQLCourseInformation myDB;
        ArrayList<CourseInformationItems> userList;
        ListView listView;
        CourseInformationItems CourseInformationItems;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.course_list_view);

            myDB = new SQLCourseInformation(this);

            userList = new ArrayList<>();
            Cursor data = myDB.getListContents();
            int numRows = data.getCount();
            if(numRows == 0){
                Toast.makeText(View_CourseInformationList.this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
            }else{
                int i=0;
                while(data.moveToNext()){
                    CourseInformationItems = new CourseInformationItems(data.getString(1),data.getString(2),data.getString(3));
                    userList.add(i,CourseInformationItems);
                    System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getString(3));
                    System.out.println(userList.get(i).getCode());
                    i++;
                }
                CourseInformation_ListAdapter adapter =  new CourseInformation_ListAdapter(this,R.layout.course_list_view, userList);
                listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(adapter);
            }
        }
    }
    */
    SQLManager myDB;
    ArrayList<CourseInformationItems> userList;
    ListView listView;
    CourseInformationItems user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coursedetails);

        myDB = new SQLManager(this);

        userList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(View_CourseInformationList.this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
        }else{
            int i=0;
            while(data.moveToNext()){
                user = new CourseInformationItems(data.getString(1),data.getString(2),data.getString(3));
                userList.add(i,user);
                System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getString(3));
                System.out.println(userList.get(i).getFirstName());
                i++;
            }
            CourseInformation_ListAdapter adapter =  new CourseInformation_ListAdapter(this, R.layout.course_list_view, userList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }
}
