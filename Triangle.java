public class Triangle extends Wireframe {

    public double[] x = new double[3];
    public double[] y = new double[3];
    public double[] z = new double[3];

    public double hZ; // highest Z value, for painter's algorithm.

    public Triangle(double[] x, double[] y, double[] z) 
    {
        this.x = x;
        this.y = y;
        this.z = z;

        // calculates the highest Z value from the three vertices.
        hZ = z[0];
        if (hZ < z[1])
            hZ = z[1];
        if (hZ < z[2])
            hZ = z[2];
    }

    public boolean backFaceCulling() 
    {    
        double[] v12 = {(x[1]-x[0]), (y[1]-y[0]), (z[1]-z[0])};
        double[] v23 = {(x[2]-x[1]), (y[2]-y[1]), (z[2]-z[1])};

        double[] n = crossProduct(v12, v23);
        double[] v = {0.0, 0.0, -1.0};

        if (dotProduct(n, v) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    private double[] crossProduct(double[] a, double[] b) 
    {
        double x = (a[1]*b[2])-(a[2]*b[1]);
        double y = (a[2]*b[0])-(a[0]*b[2]);
        double z = (a[0]*b[1])-(a[1]*b[0]);
        double[] result = {x, y, z};

        return result;
    }

    private double dotProduct(double[] a, double[] b) 
    {
        double x = a[0]*b[0];
        double y = a[1]*b[1];
        double z = a[2]*b[2];
        return (x+y+z);
    }
}