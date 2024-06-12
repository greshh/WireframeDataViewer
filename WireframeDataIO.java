import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class WireframeDataIO 
{
    public static Wireframe read(File myFile) 
    {
        Wireframe wired = new Wireframe();

        try 
        {
            Scanner scanner = new Scanner(myFile);

            wired.numVertices = Integer.parseInt(scanner.nextLine());

            wired.xv = new double[wired.numVertices];
            wired.yv = new double[wired.numVertices];
            wired.zv = new double[wired.numVertices];

            wired.nxv = new double[wired.numVertices];
            wired.nyv = new double[wired.numVertices];
            wired.nzv = new double[wired.numVertices];

            String line;
            StringTokenizer s;

            for (int i = 0; i < wired.numVertices; i++) 
            {
                line = scanner.nextLine();
                s = new StringTokenizer(line, " ");
                int n = Integer.parseInt(s.nextToken());
                wired.xv[n] = Double.parseDouble(s.nextToken());
                wired.nxv[n] = wired.xv[n];
                wired.yv[n] = Double.parseDouble(s.nextToken());
                wired.nyv[n] = wired.yv[n];
                wired.zv[n] = Double.parseDouble(s.nextToken());
                wired.nzv[n] = wired.zv[n];
            }

            wired.numTriangles = Integer.parseInt(scanner.nextLine());

            wired.vtx1 = new int[wired.numTriangles];
            wired.vtx2 = new int[wired.numTriangles];
            wired.vtx3 = new int[wired.numTriangles];

            for (int i = 0; i < wired.numTriangles; i++) 
            {
                line = scanner.nextLine();
                s = new StringTokenizer(line, " ");
                int n = Integer.parseInt(s.nextToken());
                wired.vtx1[n] = Integer.parseInt(s.nextToken());
                wired.vtx2[n] = Integer.parseInt(s.nextToken());
                wired.vtx3[n] = Integer.parseInt(s.nextToken());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return wired;
    }
}
