package mainpackage;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



/**
 * CoordinateSystem.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class CoordinateSystem implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Coordinate system");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new CoordinateSystem());
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
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
    }
    
    private void drawGrid(GL gl)
    {
        final double width = 2;
        final double length = 2;
        
        int n = 10;
        int m = 10;
        
        double xStep = width / n;
        double yStep = length / m;
        double x = -width / 2;
        double y = -length / 2;
        gl.glLineWidth(1);
        gl.glColor3d(0.6, 0.6, 0.6);
        gl.glBegin(GL.GL_LINES);
            for (int i = 0; i < n + 1; ++i)
            {
                if (Math.abs(x) > 0.001)
                {
                    gl.glVertex3d(x, length / 2, 0);
                    gl.glVertex3d(x, -length / 2, 0);
                }
                x+= xStep;
            }
            for (int i = 0; i < n + 1; ++i)
            {
                if (Math.abs(y) > 0.001)
                {
                    gl.glVertex3d(width / 2, y, 0);
                    gl.glVertex3d(-width / 2, y, 0);
                }
                y+= yStep;
            }
        gl.glEnd();
        
        // Black middle 2 lines
        gl.glColor3d(0, 0, 0);
        gl.glBegin(GL.GL_LINES);
            gl.glVertex3d(0, length / 2, 0);
            gl.glVertex3d(0,-length / 2, 0);
            gl.glVertex3d( width / 2, 0, 0);
            gl.glVertex3d(-width / 2, 0, 0);
        gl.glEnd();
    }
    
    private void drawAxis(GL gl)
    {
        final double length = 1.5;
        final double radius = 0.04;
        final int segmentsCount = 10;
        final double arrowLength = 0.13;
        
        // x arrow
        gl.glLineWidth(1.5f);
        gl.glColor3d(1, 0, 0);
        gl.glBegin(GL.GL_LINES);
            gl.glVertex3d(0, 0, 0);
            gl.glVertex3d(length, 0, 0);
        gl.glEnd();
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(length, 0, 0);
            {
                double x = 0;
                double y = 0;
                double z = 0;
                double angle = 0;
                double deltaAngle = 2 * Math.PI / segmentsCount;
                for (int i = 0; i < segmentsCount + 1; ++i)
                {
                    x = length - arrowLength;
                    y = radius * Math.cos(angle);
                    z = radius * Math.sin(angle);
                    gl.glVertex3d(x, y, z);
                    angle += deltaAngle;
                }
            }
        gl.glEnd();
        
        // y arrow
        gl.glColor3d(0, 1, 0);
        gl.glBegin(GL.GL_LINES);
            gl.glVertex3d(0, 0, 0);
            gl.glVertex3d(0, length, 0);
        gl.glEnd();
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(0, length, 0);
            {
                double x = 0;
                double y = 0;
                double z = 0;
                double angle = 0;
                double deltaAngle = 2 * Math.PI / segmentsCount;
                for (int i = 0; i < segmentsCount + 1; ++i)
                {
                    x = radius * Math.cos(angle);
                    y = length - arrowLength;
                    z = radius * Math.sin(angle);
                    gl.glVertex3d(x, y, z);
                    angle += deltaAngle;
                }
            }
        gl.glEnd();
        
        // z arrow
        gl.glColor3d(0, 0, 1);
        gl.glBegin(GL.GL_LINES);
            gl.glVertex3d(0, 0, 0);
            gl.glVertex3d(0, 0, length);
        gl.glEnd();
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(0, 0, length);
            {
                double x = 0;
                double y = 0;
                double z = 0;
                double angle = 0;
                double deltaAngle = 2 * Math.PI / segmentsCount;
                for (int i = 0; i < segmentsCount + 1; ++i)
                {
                    x = radius * Math.cos(angle);
                    y = radius * Math.sin(angle);
                    z = length - arrowLength;
                    gl.glVertex3d(x, y, z);
                    angle += deltaAngle;
                }
            }
        gl.glEnd();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        gl.glClearColor(0.2f, 0.2f, 0.2f, 1f);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        
        gl.glLoadIdentity();
        glu.gluPerspective(90, 1, 0.1, 10);
        glu.gluLookAt(1, 0.9, 1.8, 0, 0, 0, 0, 0, 1);
        
        drawAxis(gl);
        drawGrid(gl);
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

