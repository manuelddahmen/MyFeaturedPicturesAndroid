/*
 * 2013 Manuel Dahmen
 */
package tests2.horloge;

import android.graphics.Color;

import androidx.annotation.Dimension;

import one.empty3.library.*;
import one.empty3.library.core.tribase.TRISphere;
import one.empty3.library.core.tribase.TubulaireN2;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Horloge  {
    Color h;
    Color m;
    Color s;
    Dimension res;
    Scene sc;
    private boolean montre = true;
    private Sphere s0;
    private Sphere sH;
    private Sphere sS;
    private Sphere sM;
    private LineSegment droite2;
    private LineSegment droite0;
    private LineSegment droite1;

    public Horloge(Color h, Color m, Color s) {
        this.h = h;
        this.m = m;
        this.s = s;

    }

    public void initTime() {

        double f = 2 * Math.PI;

        Date d = new Date();

        sc = new Scene();

        s0 = new Sphere(Point3D.O0, 10);
        sH = new Sphere(position(f * d.getHours() / 12)
                .mult(80d), 12);
        sM = new Sphere(position(f * d.getMinutes() / 60)
                .mult(80d), 8);
        sS = new Sphere(position(f * d.getSeconds() / 60)
                .mult(80d), 6);
        Sphere sG0 = new Sphere(position(f * 0.0 / 12)
                .mult(80d), 10);
        Sphere sG3 = new Sphere(position(f * 3.0 / 12)
                .mult(80d), 10);
        Sphere sG6 = new Sphere(position(f * 6.0 / 12)
                .mult(80d), 10);
        Sphere sG9 = new Sphere(position(f * 9.0 / 12)
                .mult(80d), 10);
        sG0.texture(new TextureCol(Color.GREEN));
        sG3.texture(new TextureCol(Color.GREEN));
        sG6.texture(new TextureCol(Color.GREEN));
        sG9.texture(new TextureCol(Color.GREEN));
        s0.texture(new TextureCol(Color.WHITE));
        sH.texture(new TextureCol(Color.MAGENTA));
        sM.texture(new TextureCol(Color.BLUE));
        sS.texture(new TextureCol(Color.RED));
        s0.texture(new TextureCol(Color.RED));
        sH.texture(new TextureCol(Color.GREEN));
        sM.texture(new TextureCol(Color.BLUE));
        sS.texture(new TextureCol(Color.YELLOW));

        for (int i = 0; i < 12; i++) {
            TRISphere sGm = new TRISphere(position(f * i / 12)
                    .mult(80d), 6);
            sGm.texture(new TextureCol(Color.BLUE));
            sc.add(sGm);
        }
        sc.add(s0);
        sc.add(sH);
        sc.add(sM);
        sc.add(sS);
        sc.add(sG0);
        sc.add(sG3);
        sc.add(sG6);
        sc.add(sG9);
        droite0 = new LineSegment(
                position(f * d.getHours() / 12).mult(60d),
                Point3D.O0, new TextureCol(Color.GREEN));
        droite1 = new LineSegment(position(f * d.getHours() / 12).mult(60d),
                Point3D.O0, new TextureCol(Color.BLUE));
        droite2 = new LineSegment(
                position(f * d.getHours() / 12).mult(60d),
                Point3D.O0, new TextureCol(Color.RED));
        sc.add(droite0);
        sc.add(droite1);
        sc.add(droite2);

        TubulaireN2<LineSegment> segmentDroiteTubulaireN20 = new TubulaireN2<>();
        segmentDroiteTubulaireN20.curve(droite0);
        segmentDroiteTubulaireN20.diam(20);
        sc.add(segmentDroiteTubulaireN20);
        TubulaireN2<LineSegment> segmentDroiteTubulaireN21 = new TubulaireN2<>();
        segmentDroiteTubulaireN21.curve(droite1);
        sc.add(segmentDroiteTubulaireN21);
        segmentDroiteTubulaireN20.diam(20);
        TubulaireN2<LineSegment> segmentDroiteTubulaireN22 = new TubulaireN2<>();
        segmentDroiteTubulaireN22.curve(droite2);
        sc.add(segmentDroiteTubulaireN22);
        segmentDroiteTubulaireN20.diam(20);

        sc.cameraActive(new Camera(Point3D.Z.mult(-200d), Point3D.O0));
        sc.cameraActive().calculerMatrice(Point3D.Y);
    }

    public void time() {
        double f = 2 * Math.PI;
        Date d = new Date();

        sH.getCircle().getAxis().getElem().getP1().setElem(position(f * d.getHours() / 12).mult(60d));
        sM.getCircle().getAxis().getElem().getP1().setElem(position(f * d.getMinutes() / 60).mult(80d));
        sS.getCircle().getAxis().getElem().getP1().setElem(position(f * d.getSeconds() / 60).mult(100d));
        droite0.setOrigine(position(f * d.getHours() / 12).mult(60d));
        droite1.setOrigine(position(f * d.getMinutes() / 60).mult(80d));
        droite2.setOrigine(position(f * d.getSeconds() / 60).mult(100d));
    }

    public Point3D position(double angle) {

        Point3D p0
                = new Point3D(
                -Math.sin(angle),
                Math.cos(angle),
                0d
        );

        return p0;
    }
}
