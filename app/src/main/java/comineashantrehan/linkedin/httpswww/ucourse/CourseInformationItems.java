package comineashantrehan.linkedin.httpswww.ucourse;




public class CourseInformationItems {

    public String FirstName;
    public  String LastName;
    public  String Favfood;



    public static final String COLCODE = "FIRSTNAME";
    public static final String COLGRADE = "LASTNAME";
    public static final String COLCOMMENT = "FAVFOOD";
    public static final String TABLE_NAME = "courseInformation";


    public CourseInformationItems(String fName,String lName, String fFood){
        this.FirstName = fName;
        this.LastName = lName;
        this.Favfood = fFood;
    }

    public CourseInformationItems(){

    }

    public String getFirstName() {
        return this.FirstName;
    }

    public void setFirstName(String fName) {
        this.FirstName = fName;
    }

    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getFavFood() {
        return this.Favfood;
    }

    public void setFavFood(String favFood) {
        this.Favfood = favFood;
    }
}

