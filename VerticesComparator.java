import java.util.Comparator;

// compares the triangles based on the highest z value from the three vertices. 
public class VerticesComparator implements Comparator<Triangle> 
{   
    @Override
    public int compare(Triangle o1, Triangle o2) 
    {
        return (int)(o1.hZ)-(int)(o2.hZ);
    }
}
