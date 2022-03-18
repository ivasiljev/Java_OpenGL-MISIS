/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myAddons;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Frame;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

/**
 *
 * @author Work
 */
public class CameraControl {
    private double alphaScreen = 0.6, betaScreen = 1.3;
    private final double deltaAlphaScreen = 0.1, deltaBetaScreen = 0.1;
    private double radiusScreen = 7;
    private final double deltaRadiusScreen = 0.1;

    public double cameraPosX = radiusScreen * Math.cos(betaScreen) * Math.cos(alphaScreen);
    public double cameraPosY = radiusScreen * Math.cos(betaScreen) * Math.sin(alphaScreen);
    public double cameraPosZ = radiusScreen * Math.sin(betaScreen);
    
    public void setCamera(GL gl, GLU glu)
    {
        gl.glLoadIdentity();
        glu.gluPerspective(120, 1, 0.1, 100);
        glu.gluLookAt(cameraPosX, cameraPosY, cameraPosZ, 0, 0, 0, 0, 0, 1);
    }
    
    public void setCamera(GL gl, GLU glu, double lookAtX, double lookAtY, double lookAtZ)
    {
        gl.glLoadIdentity();
        glu.gluPerspective(120, 1, 0.1, 100);
        glu.gluLookAt(cameraPosX, cameraPosY, cameraPosZ, lookAtX, lookAtY, lookAtZ, 0, 0, 1);
    }
    
    public void createKeyListener(GLCanvas canvas, Frame frame)
    {
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                switch (ke.getKeyCode())
                {
                    case 37: // Стрелка влево
                        alphaScreen -= deltaAlphaScreen;
                        if (alphaScreen < -2 * Math.PI) alphaScreen += 2 * Math.PI;
                        break;
                    case 38: // Стрелка вверх
                        betaScreen += deltaBetaScreen;
                        if (betaScreen > Math.PI / 2) betaScreen = Math.PI / 2;
                        break;
                    case 39: // Стрелка вправо
                        alphaScreen += deltaAlphaScreen;
                        if (alphaScreen > 2 * Math.PI) alphaScreen -= 2 * Math.PI;
                        break;
                    case 40: // Стрелка вниз
                        betaScreen -= deltaBetaScreen;
                        if (betaScreen < -Math.PI / 2) betaScreen = -Math.PI / 2;
                        break;
                    case 109: // +
                        radiusScreen += deltaRadiusScreen;
                        break;
                    case 107: // -
                        radiusScreen -= deltaRadiusScreen;
                        if (radiusScreen <= 0) radiusScreen = 0.001;
                        break;
                }
                cameraPosX = radiusScreen * Math.cos(betaScreen) * Math.cos(alphaScreen);
                cameraPosY = radiusScreen * Math.cos(betaScreen) * Math.sin(alphaScreen);
                cameraPosZ = radiusScreen * Math.sin(betaScreen);
            }
        });
        
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                switch (ke.getKeyCode())
                {
                    case 37: // Стрелка влево
                        alphaScreen -= deltaAlphaScreen;
                        if (alphaScreen < -2 * Math.PI) alphaScreen += 2 * Math.PI;
                        break;
                    case 38: // Стрелка вверх
                        betaScreen += deltaBetaScreen;
                        if (betaScreen > Math.PI / 2) betaScreen = Math.PI / 2;
                        break;
                    case 39: // Стрелка вправо
                        alphaScreen += deltaAlphaScreen;
                        if (alphaScreen > 2 * Math.PI) alphaScreen -= 2 * Math.PI;
                        break;
                    case 40: // Стрелка вниз
                        betaScreen -= deltaBetaScreen;
                        if (betaScreen < -Math.PI / 2) betaScreen = -Math.PI / 2;
                        break;
                    case 109: // +
                        radiusScreen += deltaRadiusScreen;
                        break;
                    case 107: // -
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
