package one.empty3.feature.selection;

import one.empty3.feature.PixM;
import one.empty3.io.ProcessFile;
import one.empty3.library.*;
import one.empty3.library.core.nurbs.ParametricCurve;

import javax.imageio.ImageIO;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class Paste extends ProcessFile {

    /***
     *
     * @param points Sélection de points
     * @param img Image sur laquelle dessiner
     * @param col Couleur ou texture de dessin
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void pasteList(List<Point3D> points, PixM img, ITexture col) {

        for (int i = 0; i < points.size(); i++) {

            int x = (int) (double) points.get(i).getX();
            int y = (int) (double) points.get(i).getY();

            int rgb = col.getColorAt(
                    points.get(i).getX() / img.getColumns(),
                    points.get(i).getY() / img.getLines());

            double[] rgbD = Lumiere.getDoubles(rgb);
            for (int i1 = 0; i1 < 3; i1++) {
                img.setCompNo(i1);
                img.set(x, y, rgbD[i1]);
            }

        }

    }

    /***
     *
     * @param points Sélection de points
     * @param img Image sur laquelle dessiner
     * @param objets Objets à dessiner sur l'image (3d-2d)
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void pasteList(List<Point3D> points, PixM img, Scene objets) {

        for (int i = 0; i < points.size(); i++) {

            int x = (int) (double) points.get(i).getX();
            int y = (int) (double) points.get(i).getY();

            objets.getObjets().getData1d().forEach(new Consumer<Representable>() {
                @Override
                public void accept(Representable representable) {
                    if (representable instanceof ParametricCurve) ;
                        //img.plotCurve((ParametricCurve) representable, representable.getTexture());///Clip curve for moving, rotating, scaling
                    /*else if (representable instanceof Point3D) {
                        img.setValues((int) (double) ((Point3D) representable).getX(),
                                (int) (double) ((Point3D) representable).getY(),
                                Lumiere.getDoubles(representable.getTexture().getColorAt(0.5, 0.5)));
                    }*/
                }
            });

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean process(File in, File out) {
        Bitmap read = ImageIO.read(in);
        PixM pixM = PixM.getPixM(read, maxRes);
        return true;
    }
}
