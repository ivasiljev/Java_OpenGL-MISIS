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
 * Walls.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Walls implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Walls");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Walls());
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
    
    private void Wall(GL gl, double xPos1, double yPos1, double xPos2, double yPos2)
    {
        final double width = 0.2;
        final double height = 2;
        
        double k = 1;
        gl.glColor3d(0.5, 0.5, 0.5);
        gl.glBegin(GL.GL_QUAD_STRIP);
            if (xPos1 == xPos2)
            {
                if (yPos2 > yPos1) k = 1; else k = -1;
                
                gl.glVertex3d(xPos1 + width / 2, yPos1 - k * width / 2, 0);
                gl.glVertex3d(xPos1 + width / 2, yPos1 - k * width / 2, height);
                
                gl.glVertex3d(xPos1 + width / 2, yPos2 + k * width / 2, 0);
                gl.glVertex3d(xPos1 + width / 2, yPos2 + k * width / 2, height);
                
                gl.glVertex3d(xPos1 - width / 2, yPos2 + k * width / 2, 0);
                gl.glVertex3d(xPos1 - width / 2, yPos2 + k * width / 2, height);
                
                gl.glVertex3d(xPos1 - width / 2, yPos1 - k * width / 2, 0);
                gl.glVertex3d(xPos1 - width / 2, yPos1 - k * width / 2, height);
                
                gl.glVertex3d(xPos1 + width / 2, yPos1 - k * width / 2, 0);
                gl.glVertex3d(xPos1 + width / 2, yPos1 - k * width / 2, height);
            }
            if (yPos1 == yPos2)
            {
                if (xPos2 > xPos1) k = 1; else k = -1;
                
                gl.glVertex3d(xPos1 - k * width / 2, yPos1 + width / 2, 0);
                gl.glVertex3d(xPos1 - k * width / 2, yPos1 + width / 2, height);
                
                gl.glVertex3d(xPos2 + k * width / 2, yPos1 + width / 2, 0);
                gl.glVertex3d(xPos2 + k * width / 2, yPos1 + width / 2, height);
                
                gl.glVertex3d(xPos2 + k * width / 2, yPos1 - width / 2, 0);
                gl.glVertex3d(xPos2 + k * width / 2, yPos1 - width / 2, height);
                
                gl.glVertex3d(xPos1 - k * width / 2, yPos1 - width / 2, 0);
                gl.glVertex3d(xPos1 - k * width / 2, yPos1 - width / 2, height);
                
                gl.glVertex3d(xPos1 - k * width / 2, yPos1 + width / 2, 0);
                gl.glVertex3d(xPos1 - k * width / 2, yPos1 + width / 2, height);
            }
        gl.glEnd();
        gl.glColor3d(0.3, 0.3, 0.3);
        gl.glBegin(GL.GL_QUADS);
            if (xPos1 == xPos2)
            {
                gl.glVertex3d(xPos1 + width / 2, yPos1 - k * width / 2, height);
                gl.glVertex3d(xPos1 + width / 2, yPos2 + k * width / 2, height);
                gl.glVertex3d(xPos1 - width / 2, yPos2 + k * width / 2, height);
                gl.glVertex3d(xPos1 - width / 2, yPos1 - k * width / 2, height);
                
                gl.glVertex3d(xPos1 + width / 2, yPos1 - k * width / 2, 0);
                gl.glVertex3d(xPos1 + width / 2, yPos2 + k * width / 2, 0);
                gl.glVertex3d(xPos1 - width / 2, yPos2 + k * width / 2, 0);
                gl.glVertex3d(xPos1 - width / 2, yPos1 - k * width / 2, 0);
            }
            if (yPos1 == yPos2)
            {
                gl.glVertex3d(xPos1 - k * width / 2, yPos1 + width / 2, height);
                gl.glVertex3d(xPos2 + k * width / 2, yPos1 + width / 2, height);
                gl.glVertex3d(xPos2 + k * width / 2, yPos1 - width / 2, height);
                gl.glVertex3d(xPos1 - k * width / 2, yPos1 - width / 2, height);
                
                gl.glVertex3d(xPos1 - k * width / 2, yPos1 + width / 2, 0);
                gl.glVertex3d(xPos2 + k * width / 2, yPos1 + width / 2, 0);
                gl.glVertex3d(xPos2 + k * width / 2, yPos1 - width / 2, 0);
                gl.glVertex3d(xPos1 - k * width / 2, yPos1 - width / 2, 0);
            }
        gl.glEnd();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        
        gl.glLoadIdentity();
        glu.gluPerspective(90, 1, 0.1, 100);
        glu.gluLookAt(8, 8, -10, 5, 5, 0, 0, 0, -1);
        
        double walls[] =
        {
            0, 0, 8, 0,
            9, 0, 10, 0,
            10, 0, 10, 10,
            10, 10, 0, 10,
            0, 10, 0, 0,
            
            7, 0, 7, 4,
            7, 4, 6, 4,
            5, 4, 4, 4,
            4, 0, 4, 4,
            0, 4, 2, 4,
            
            10, 6, 6, 6,
            6, 6, 6, 7,
            6, 8, 6, 10
        };
        
        for (int i = 0; i < 13; ++i)
        {
            Wall(gl, walls[i*4], walls[i*4 + 1], walls[i*4 + 2], walls[i*4 + 3]);
        }
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

