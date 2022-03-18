/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myAddons;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

/**
 *
 * @author Work
 */
public class CameraControl {
    private static double alphaScreen = 1, betaScreen = 1;
    private static final double deltaAlphaScreen = 0.1, deltaBetaScreen = 0.1;
    private static double radiusScreen = 2;
    private static final double deltaRadiusScreen = 0.1;

    public static double cameraPosX = radiusScreen * Math.cos(betaScreen) * Math.cos(alphaScreen);
    public static double cameraPosY = radiusScreen * Math.cos(betaScreen) * Math.sin(alphaScreen);
    public static double cameraPosZ = radiusScreen * Math.sin(betaScreen);
    
    public static void setCamera(GL gl, GLU glu)
    {
        gl.glLoadIdentity();
        glu.gluPerspective(120, 1, 0.1, 10);
        glu.gluLookAt(cameraPosX, cameraPosY, cameraPosZ, 0, 0, 0, 0, 0, 1);
    }
    
    public static void setCamera(GL gl, GLU glu, double lookAtX, double lookAtY, double lookAtZ)
    {
        gl.glLoadIdentity();
        glu.gluPerspective(120, 1, 0.1, 10);
        glu.gluLookAt(cameraPosX, cameraPosY, cameraPosZ, lookAtX, lookAtY, lookAtZ, 0, 0, 1);
    }
    
    public static void createKeyListener(GLCanvas canvas)
    {
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                //System.out.println("KeyCode2: "+ke.getKeyCode());
                switch (ke.getKeyCode())
                {
                    case 37:
                        alphaScreen -= deltaAlphaScreen;
                        if (alphaScreen < -2 * Math.PI) alphaScreen += 2 * Math.PI;
                        break;
                    case 38:
                        betaScreen += deltaBetaScreen;
                        if (betaScreen > Math.PI / 2) betaScreen = Math.PI / 2;
                        break;
                    case 39:
                        alphaScreen += deltaAlphaScreen;
                        if (alphaScreen > 2 * Math.PI) alphaScreen -= 2 * Math.PI;
                        break;
                    case 40:
                        betaScreen -= deltaBetaScreen;
                        if (betaScreen < -Math.PI / 2) betaScreen = -Math.PI / 2;
                        break;
                    case 109:
                        radiusScreen += deltaRadiusScreen;
                        break;
                    case 107:
                        radiusScreen -= deltaRadiusScreen;
                        if (radiusScreen <= 0) radiusScreen = 0.001;
                        break;
                }
                cameraPosX = radiusScreen * Math.cos(betaScreen) * Math.cos(alphaScreen);
                cameraPosY = radiusScreen * Math.cos(betaScreen) * Math.sin(alphaScreen);
                cameraPosZ = radiusScreen * Math.sin(betaScreen);
            }
        });
    }
}
