/*__
 * ect 14-08-17 Copyright.
 */

package tests2;

import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

import one.empty3.library.*;
import one.empty3.library.core.nurbs.SurfaceParametricPolygonalBezier;
import one.empty3.library.core.testing.TestObjetSub;

import java.io.File;
import java.io.IOException;

public class TestThierryJaspart extends TestObjetSub {
    private final Point3D[][] coeff = new Point3D[][]{
            {Point3D.n(2, -2, 0), Point3D.n(2, -1, 0), Point3D.n(2, 0, 0), Point3D.n(2, 1, 0), Point3D.n(2, 2, 0)},
            {Point3D.n(1, -2, 0), Point3D.n(1, -1, 0), Point3D.n(1, 0, 0), Point3D.n(1, 1, 0), Point3D.n(1, 2, 0)},
            {Point3D.n(0, -2, 0), Point3D.n(0, -1, 0), Point3D.n(0, 0, 0), Point3D.n(0, 1, 0), Point3D.n(0, 2, 0)},
            {Point3D.n(-1, -2, 0), Point3D.n(-1, -1, 0), Point3D.n(-1, 0, 0), Point3D.n(-1, 1, 0), Point3D.n(-1, 2, 0)},
            {Point3D.n(-2, -2, 0), Point3D.n(-2, -1, 0), Point3D.n(-2, 0, 0), Point3D.n(-2, 1, 0), Point3D.n(-2, 2, 0)}
    };
    ITexture texture;
    private SurfaceParametricPolygonalBezier s = new SurfaceParametricPolygonalBezier(coeff);

    public TestThierryJaspart() {
        setMaxFrames(25 * 60 * 5);
    }


    @Override
    public void testScene(File f) {
    }

    @Override
    public void ginit() {
        s.texture(texture);
        scene().add(s);
        scene().cameraActive().setEye(Point3D.Z.mult(-5d));
        scene().cameraActive(new Camera(Point3D.Z.mult(-5d), Point3D.O0));
        try {
            texture = new TextureImg(ECBufferedImage.getFromFile(
                    new File("resources/img/2018-03-31 11.51.58.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.setIncrU(0.1);
        s.setIncrV(0.1);
        s.texture(texture);
    }


    @Override
    public void testScene() {
        for (int i = 0; i < s.getCoefficients().getData2d().size(); i++)
            for (int j = 0; j < s.getCoefficients().getData2d().get(i).size(); j++) {
                Point3D point3D = Point3D.random2(0.1);
                for (int k = 0; k < 3; k++)
                    s.getCoefficients().getElem(i,j).set(k, s.getCoefficients().getElem(i,j).get(k)+point3D.get(k));
            }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void finit() throws Exception {
        super.finit();
        scene().texture(new TextureCol(Color.valueOf(1,1,1)));

    }

    public static void main(String[] args) {
        new Thread(new TestThierryJaspart()).start();


    }
}
