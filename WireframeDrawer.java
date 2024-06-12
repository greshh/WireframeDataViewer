import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class WireframeDrawer {

    public static void draw(Graphics2D g2, Wireframe wired, boolean antiAlias, double scale, Transform3d transform) 
    {
        wired.toView(transform.tmx, scale);

        ArrayList<Triangle> triangles = new ArrayList<>();

        for (int i = 0; i < wired.numTriangles; i++) 
        {
            double[] x = {wired.nxv[wired.vtx1[i]], wired.nxv[wired.vtx2[i]], wired.nxv[wired.vtx3[i]]};
            double[] y = {wired.nyv[wired.vtx1[i]], wired.nyv[wired.vtx2[i]], wired.nyv[wired.vtx3[i]]};
            double[] z = {wired.nzv[wired.vtx1[i]], wired.nzv[wired.vtx2[i]], wired.nzv[wired.vtx3[i]]};
            Triangle newTriangle = new Triangle(x, y, z);
            triangles.add(newTriangle);
        }

        // implements painter's algorithm.
        VerticesComparator comparator = new VerticesComparator();
        Collections.sort(triangles, comparator);

        if (antiAlias) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        } else {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        }

        for (int i = 0; i < triangles.size(); i++) 
        {
            int xPoints[] = new int[3];
            int yPoints[] = new int[3];

            for (int j = 0; j < 3; j++) 
            {
                xPoints[j] = (int)triangles.get(i).x[j];
                yPoints[j] = (int)triangles.get(i).y[j];
            }

            if (triangles.get(i).backFaceCulling()) 
            {
                g2.setColor(Color.BLACK);
                g2.drawPolygon(new Polygon(xPoints, yPoints, 3));
                g2.setColor(Color.LIGHT_GRAY);
                g2.fillPolygon(new Polygon(xPoints, yPoints, 3));
            }
        }
    }
}