public class Wireframe 
{
    public int numTriangles = 0;
    public int numVertices = 0;

    // Vertex coordinates in the world scene for each vertex of the triangle (scaling inclusive).
    public double[] xv, yv, zv;

    // New vertex coordinates after rotation and scaling.
    public double[] nxv, nyv, nzv;

    // Triangle mapping
    public int[] vtx1, vtx2, vtx3;

    // Here we apply the rotations and scaling before drawing the display
    public void toView(double[][] tmx, double scale) 
    {
      for (int i = 0; i < numVertices; i++) {
        double[] temp = new double[3];

        // implies matrix multiplication to calculate individual vertex coordinates - tmx*[x,y,z].
        temp[0] = (xv[i] * tmx[0][0]) + (yv[i] * tmx[1][0]) + (zv[i] * tmx[2][0]);
        temp[1] = (xv[i] * tmx[0][1]) + (yv[i] * tmx[1][1]) + (zv[i] * tmx[2][1]);
        temp[2] = (xv[i] * tmx[0][2]) + (yv[i] * tmx[1][2]) + (zv[i] * tmx[2][2]);

        nxv[i] = temp[0];
        nyv[i] = temp[1];
        nzv[i] = temp[2];

        nxv[i]*= scale;
        nyv[i]*= scale;
        nzv[i]*= scale;
        
        xv[i]*= scale;
        yv[i]*= scale;
        zv[i]*= scale;
      }
    }
}
