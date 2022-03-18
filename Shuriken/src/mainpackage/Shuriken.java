package mainpackage;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import javax.annotation.Generated;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import java.nio.DoubleBuffer;



/**
 * MyJOGLProject.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Shuriken implements GLEventListener {
    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();
        
        canvas.addGLEventListener(new Shuriken());
        frame.add(canvas);
        frame.setSize(640, 640);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        
        gl.glLoadIdentity();
        glu.gluPerspective(120, 1, 0.1, 10);
        glu.gluLookAt(0.3, 0.3, 1.3, 0, 0, 0, 0, 0, 1);
        
        double PI = 3.14;
        double x = 0, 
               y = 0, 
               z = 0;
        double r1 = 0.3, 
               r2 = 1, 
               r  = 0.1;
        double h = 0.15;
        double alpha = 0, deltaAlpha = PI / 2;
        
        gl.glBegin(GL.GL_QUAD_STRIP);
            while (alpha < 2 * PI)
            {
                gl.glColor3d(0.3, 0.3, 0.3);
                x = r * Math.cos(alpha);
                y = r * Math.sin(alpha);
                z = h;
                gl.glVertex3d(x, y, z);
                
                gl.glColor3d(0.8, 0.8, 0.8);
                x = r2 * Math.cos(alpha);
                y = r2 * Math.sin(alpha);
                z = 0;
                gl.glVertex3d(x, y, z);
                
                gl.glColor3d(0.3, 0.3, 0.3);
                x = r * Math.cos(alpha + deltaAlpha / 2);
                y = r * Math.sin(alpha + deltaAlpha / 2);
                z = h;
                gl.glVertex3d(x, y, z);
                
                gl.glColor3d(0.6, 0.6, 0.6);
                x = r1 * Math.cos(alpha + deltaAlpha / 2);
                y = r1 * Math.sin(alpha + deltaAlpha / 2);
                z = 0;
                gl.glVertex3d(x, y, z);
                
                alpha += deltaAlpha;
            }
            gl.glColor3d(0.3, 0.3, 0.3);
            x = r * Math.cos(0);
            y = r * Math.sin(0);
            z = h;
            gl.glVertex3d(x, y, z);

            gl.glColor3d(0.8, 0.8, 0.8);
            x = r2 * Math.cos(0);
            y = r2 * Math.sin(0);
            z = 0;
            gl.glVertex3d(x, y, z);
        gl.glEnd();
       
        alpha = 0;
        gl.glBegin(GL.GL_QUAD_STRIP);
            while (alpha < 2 * PI)
            {
                gl.glColor3d(0.3, 0.3, 0.3);
                x = r * Math.cos(alpha);
                y = r * Math.sin(alpha);
                z = -h;
                gl.glVertex3d(x, y, z);
            
                gl.glColor3d(0.8, 0.8, 0.8);
                x = r2 * Math.cos(alpha);
                y = r2 * Math.sin(alpha);
                z = 0;
                gl.glVertex3d(x, y, z);
                
                gl.glColor3d(0.3, 0.3, 0.3);
                x = r * Math.cos(alpha + deltaAlpha / 2);
                y = r * Math.sin(alpha + deltaAlpha / 2);
                z = -h;
                gl.glVertex3d(x, y, z);
                
                gl.glColor3d(0.6, 0.6, 0.6);
                x = r1 * Math.cos(alpha + deltaAlpha / 2);
                y = r1 * Math.sin(alpha + deltaAlpha / 2);
                z = 0;
                gl.glVertex3d(x, y, z);
                
                alpha += deltaAlpha;
            }
            gl.glColor3d(0.3, 0.3, 0.3);
            x = r * Math.cos(0);
            y = r * Math.sin(0);
            z = -h;
            gl.glVertex3d(x, y, z);

            gl.glColor3d(0.8, 0.8, 0.8);
            x = r2 * Math.cos(0);
            y = r2 * Math.sin(0);
            z = 0;
            gl.glVertex3d(x, y, z);
        gl.glEnd();
        
        alpha = 0;
        gl.glColor3d(0.25, 0.25, 0.25);
        gl.glBegin(GL.GL_QUAD_STRIP);
            while (alpha - deltaAlpha < 2 * PI)
            {
                x = r * Math.cos(alpha);
                y = r * Math.sin(alpha);
                z = -h;
                gl.glVertex3d(x, y, z);
                
                x = r * Math.cos(alpha);
                y = r * Math.sin(alpha);
                z = h;
                gl.glVertex3d(x, y, z);
                
                alpha += deltaAlpha / 2;
            }
        gl.glEnd();
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

