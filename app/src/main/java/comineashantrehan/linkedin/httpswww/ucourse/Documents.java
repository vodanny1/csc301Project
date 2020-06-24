//package comineashantrehan.linkedin.httpswww.ucourse_sprint_3;
package comineashantrehan.linkedin.httpswww.ucourse;
import java.sql.Blob;

public class Documents
{
    public final String documentName;

    public static final String COL_DOCNAME = "name";
    public static final String COL_IMAGE = "image";
    public static final String TABLE_NAME = "documents";

    public Documents(String documentName)
    {
        this.documentName = documentName;

    }

}
