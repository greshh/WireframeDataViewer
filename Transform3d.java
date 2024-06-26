public class Transform3d {
    private static final int SIZE = 3;

    // Transformation matrices
    private final double[][] tXY = new double[SIZE][SIZE];
    private final double[][] tYZ = new double[SIZE][SIZE];
    private final double[][] tXZ = new double[SIZE][SIZE];
    private final double[][] temp = new double[SIZE][SIZE];

    // Transformation matrix that is visible to users of the class
    public double[][] tmx = new double[SIZE][SIZE];

    private static void setIdentity(double[][] mx) 
    {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                mx[i][j] = 0.0;
            }
            mx[i][i] = 1.0;
        }
    }

    private static void multiply(double[][] mx1, double[][] mx2, double[][] result) 
    {
        double sum;
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                sum = 0;
                for (int k = 0; k < SIZE; ++k) {
                    sum += mx1[i][k] * mx2[k][j];
                }
                result[i][j] = sum;
            }
        }
    }

    public Transform3d() 
    {
        setIdentity(tXY);
        setIdentity(tXZ);
        setIdentity(tYZ);
        calculate();
    }

    void setXY(double angle) 
    {
        tXY[0][0] = Math.cos(angle);
        tXY[1][0] = -(Math.sin(angle));
        tXY[0][1] = Math.sin(angle);
        tXY[1][1] = Math.cos(angle);
    }

    void setXZ(double angle) 
    {
        tXZ[0][0] = Math.cos(angle);
        tXZ[0][2] = Math.sin(angle);
        tXZ[2][0] = -(Math.sin(angle));
        tXZ[2][2] = Math.cos(angle);
    }

    void setYZ(double angle) 
    {
        tYZ[1][1] = Math.cos(angle);
        tYZ[2][1] = -(Math.sin(angle));
        tYZ[1][2] = Math.sin(angle);
        tYZ[2][2] = Math.cos(angle);
    }

    // Compute full transformation matrix from the 3 rotation matrices
    // R = R(x) * R(y) * R(z)
    void calculate() 
    {
        multiply(tYZ, tXZ, temp);
        multiply(temp, tXY, tmx);
    }

}
