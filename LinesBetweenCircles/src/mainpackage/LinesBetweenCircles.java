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
 * LinesBetweenCircles.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class LinesBetweenCircles implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new LinesBetweenCircles());
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
        System.err.println("INIT GL IS: " + gl.getClass().getName());
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClearColor(1, 1, 1, 1);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        int linesCount = 10000;
        
        gl.glLineWidth(1);
        gl.glBegin(GL.GL_LINES);
        for (int i = 0; i < linesCount; i++)
        {
            gl.glColor3d(Math.random(), Math.random(), Math.random());
            
            double r = Math.random() * 0.3 + 0.4;
            double alpha = Math.random() * 2 * Math.PI;
            double x1 = r * Math.sin(alpha);
            double y1 = r * Math.cos(alpha);
            
            r = Math.random() * 0.3 + 0.4;
            alpha = Math.random() * 2 * Math.PI;
            double x2 = r * Math.sin(alpha);
            double y2 = r * Math.cos(alpha);
            
            double k = (y1 - y2) / (x1 - x2);
            double b = (y1*x2 - y2*x1) / (x2 - x1);
            double x = -(b*k) / (k*k + 1);
            double y = b / (k*k + 1);
            double dist2 = x*x + y*y;
            if (x > Math.min(x1, x2) && x < Math.max(x1, x2) && dist2 < r*r)
            {
                i--;
            }
            else
            {
                gl.glVertex2d(x1, y1);
                gl.glVertex2d(x2, y2);
            }
        }
        gl.glEnd();
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

