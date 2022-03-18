package mainpackage;

import myAddons.CameraControl;
import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.nio.DoubleBuffer;



/**
 * Botinok.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Botinok implements GLEventListener {
    static CameraControl CC = new CameraControl(); // Объект для управления камерой сцены
    
    public static void main(String[] args) {
        Frame frame = new Frame("Botinok");
        GLCanvas canvas = new GLCanvas();

        CC.createKeyListener(canvas, frame);
        
        canvas.addGLEventListener(new Botinok());
        frame.add(canvas);
        frame.setSize(1000, 1000);
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
    
    public void drawPlane(GL gl, double Cpoints[])
    {
        DoubleBuffer buffer = DoubleBuffer.wrap(Cpoints);
        
        gl.glMap2d(GL.GL_MAP2_VERTEX_3, 0, 1, 3, 4, 0, 1, 12, 4, buffer);
        gl.glEnable(GL.GL_MAP2_VERTEX_3);

        gl.glMapGrid2d(20, 0, 1, 20, 0, 1);
        gl.glEvalMesh2(GL.GL_FILL, 0, 20, 0, 20);
    }

    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
    }
    
    void fillCpoints1(double Cpoints1[][])
    {
        Cpoints1[0] = new double[]
        {
            0.0, -0.9, 1.1,     0.31, -0.9, 1.1,     0.74, -0.9, 1.1,     1.29, -0.9, 0.0,    
            0.0, -0.35, 1.1,    0.35, -0.35, 1.1,    0.77, -0.38, 1.1,    1.15, -0.28, 0.0,    
            0.0, 0.28, 1.1,     0.34, 0.24, 1.1,     0.72, 0.23, 1.1,     1.05, 0.15, 0.0,    
            0.0, 0.92, 0.0,     0.26, 0.93, 0.0,     0.55, 0.86, 0.0,     0.76, 0.53, 0.0,    
        };
        
        Cpoints1[1] = new double[]
        {
            0.0, -0.9, 1.1,    -0.31, -0.9, 1.1,    -0.74, -0.9, 1.1,    -1.29, -0.9, 0.0,    
            0.0, -0.35, 1.1,   -0.35, -0.35, 1.1,   -0.77, -0.38, 1.1,   -1.15, -0.28, 0.0,    
            0.0, 0.28, 1.1,    -0.34, 0.24, 1.1,    -0.72, 0.23, 1.1,    -1.05, 0.15, 0.0,    
            0.0, 0.92, 0.0,    -0.26, 0.93, 0.0,    -0.55, 0.86, 0.0,    -0.76, 0.53, 0.0,    
        };
        
        Cpoints1[2] = new double[]
        {
            0.41, -1.14, 1.1,    0.62, -0.82, 0.84,   0.85, -0.79, 0.96,   1.29, -0.9, 0.0,    
           -0.08, -1.71, 1.2,    0.66, -1.71, 1.2,    0.99, -1.66, 1.2,    1.49, -1.65, 0.0,    
            0.03, -4.21, 1.3,    0.34, -3.32, 1.3,    0.74, -3.33, 1.3,    1.17, -2.83, 0.0,    
            0.11, -4.44, 1.4,    0.42, -4.45, 1.4,    0.81, -4.45, 1.4,    1.27, -4.42, 0.0,     
        };
        
        Cpoints1[3] = new double[]
        {
           -0.41, -1.14, 1.1,   -0.62, -0.82, 0.84,   -0.85, -0.79, 0.96,   -1.29, -0.9, 0.0,    
            0.08, -1.71, 1.2,   -0.66, -1.71, 1.2,    -0.99, -1.66, 1.2,    -1.39, -1.65, 0.0,    
           -0.03, -4.21, 1.3,   -0.34, -3.32, 1.3,    -0.74, -3.33, 1.3,    -0.94, -3.32, 0.0,    
           -0.11, -4.44, 1.4,   -0.42, -4.45, 1.4,    -0.81, -4.45, 1.4,    -1.17, -4.42, 0.0,   
        };
        
        Cpoints1[4] = new double[]
        {
            1.19, -5.6, 1.5,     1.26, -5.6, 1.5,     1.3, -5.6, 1.5,      1.34, -5.6, 0.0,    
            0.97, -4.33, 1.5,    1.2, -5.29, 1.5,     1.28, -5.32, 1.5,    1.34, -5.35, 0.0,    
            0.07, -4.59, 1.5,    0.99, -4.87, 1.5,    1.17, -4.89, 1.5,    1.3, -4.97, 0.0,    
            0.11, -4.44, 1.4,    0.42, -4.45, 1.4,    0.81, -4.45, 1.4,    1.27, -4.42, 0.0,     
        };
        
        Cpoints1[5] = new double[]
        {
           -1.08, -5.6, 1.5,    -1.13, -5.6, 1.5,    -1.18, -5.6, 1.5,    -1.23, -5.6, 0.0,    
           -0.97, -4.33, 1.5,   -1.2, -5.29, 1.5,    -1.28, -5.32, 1.5,   -1.24, -5.35, 0.0,    
           -0.07, -4.59, 1.5,   -0.99, -4.87, 1.5,   -1.17, -4.89, 1.5,   -1.18, -4.97, 0.0,    
           -0.11, -4.44, 1.4,   -0.42, -4.45, 1.4,   -0.81, -4.45, 1.4,   -1.17, -4.42, 0.0,  
        };
        
        Cpoints1[6] = new double[]
        {
            1.19, -5.6, 1.5,    1.26, -5.6, 1.5,    1.3, -5.6, 1.5,      1.34, -5.6, 0.0,    
            1.03, -6.35, 1.5,   1.26, -5.8, 1.3,    1.31, -5.8, 1.0,     1.35, -5.8, 0.0,    
            0.89, -6.62, 1.5,   1.1, -6.12, 1.1,    1.16, -6.13, 0.6,    1.22, -6.81, 0.0,    
            0.0, -6.53, 1.5,    0.0, -6.57, 1.1,    0.0, -6.65, 0.6,     0.0, -6.72, 0.0,       
        };
        
        Cpoints1[7] = new double[]
        {
            -1.08, -5.6, 1.5,     -1.13, -5.6, 1.5,     -1.18, -5.6, 1.5,     -1.23, -5.6, 0.0,    
            -0.99, -5.98, 1.5,    -1.13, -5.8, 1.3,     -1.16, -5.8, 1.0,     -1.19, -5.8, 0.0,    
            -0.85, -6.72, 1.5,    -0.97, -6.12, 1.1,    -1.09, -6.13, 0.6,    -1.21, -6.82, 0.0,    
             0.0, -6.53, 1.5,      0.0, -6.57, 1.1,     0.0, -6.65, 0.6,       0.0, -6.72, 0.0,       
        };
    }
    
    void fillCpoints2(double Cpoints2[][])
    {
        Cpoints2[0] = new double[]
        {
            0.0,-0.9, 0.0,     0.31, -0.9, 0.0,    0.74, -0.9, 0.0,    1.29, -0.9, 0.0,    
            0.0,-0.35, 0.0,    0.35, -0.35, 0.0,   0.77, -0.38, 0.0,   1.15, -0.28, 0.0,    
            0.0, 0.28, 0.0,    0.34, 0.24, 0.0,    0.72, 0.23, 0.0,    1.05, 0.15, 0.0,    
            0.0, 0.92, 0.0,    0.26, 0.93, 0.0,    0.55, 0.86, 0.0,    0.76, 0.53, 0.0,    
        };
        
        Cpoints2[1] = new double[]
        {
            0.0,-0.9, 0.0,     -0.31, -0.9, 0.0,    -0.74, -0.9, 0.0,    -1.29, -0.9, 0.0,    
            0.0,-0.35, 0.0,    -0.35, -0.35, 0.0,   -0.77, -0.38, 0.0,   -1.15, -0.28, 0.0,    
            0.0, 0.28, 0.0,    -0.34, 0.24, 0.0,    -0.72, 0.23, 0.0,    -1.05, 0.15, 0.0,    
            0.0, 0.92, 0.0,    -0.26, 0.93, 0.0,    -0.55, 0.86, 0.0,    -0.76, 0.53, 0.0,    
        };
        
        Cpoints2[2] = new double[]
        {
            0.0, -0.9, 0.0,     0.31, -0.9, 0.0,     0.77, -0.9, 0.0,     1.29, -0.9, 0.0,    
            0.0, -1.51, 0.0,    0.33, -1.5, 0.0,     0.76, -1.5, 0.0,     1.49, -1.65, 0.0,    
            0.0, -3.32, 0.0,    0.34, -3.32, 0.0,    0.74, -3.33, 0.0,    1.17, -2.83, 0.0,    
            0.0, -4.44, 0.0,    0.42, -4.45, 0.0,    0.81, -4.45, 0.0,    1.27, -4.42, 0.0,     
        };
        
        Cpoints2[3] = new double[]
        {
            0.0, -0.9, 0.0,     -0.31, -0.9, 0.0,     -0.77, -0.9, 0.0,     -1.29, -0.9, 0.0,    
            0.0, -1.51, 0.0,    -0.33, -1.5, 0.0,     -0.76, -1.5, 0.0,     -1.39, -1.65, 0.0,    
            0.0, -3.32, 0.0,    -0.34, -3.32, 0.0,    -0.74, -3.33, 0.0,    -0.94, -3.32, 0.0,    
            0.0, -4.44, 0.0,    -0.42, -4.45, 0.0,    -0.81, -4.45, 0.0,    -1.17, -4.42, 0.0,     
        };
        
        Cpoints2[4] = new double[]
        {
            0.0, -5.6, 0.0,     0.31, -5.6, 0.0,     0.77, -5.6, 0.0,     1.34, -5.6, 0.0,    
            0.0, -5.41, 0.0,    0.33, -5.4, 0.0,     0.76, -5.4, 0.0,     1.34, -5.35, 0.0,    
            0.0, -4.82, 0.0,    0.34, -4.82, 0.0,    0.74, -4.83, 0.0,    1.3, -4.97, 0.0,    
            0.0, -4.44, 0.0,    0.42, -4.45, 0.0,    0.81, -4.45, 0.0,    1.27, -4.42, 0.0,     
        };
        
        Cpoints2[5] = new double[]
        {
            0.0, -5.6, 0.0,    -0.31, -5.6, 0.0,    -0.77, -5.6, 0.0,    -1.23, -5.6, 0.0,    
            0.0, -5.41, 0.0,   -0.33, -5.4, 0.0,    -0.76, -5.4, 0.0,    -1.24, -5.35, 0.0,    
            0.0, -4.82, 0.0,   -0.34, -4.82, 0.0,   -0.74, -4.83, 0.0,   -1.18, -4.97, 0.0,    
            0.0, -4.44, 0.0,   -0.42, -4.45, 0.0,   -0.81, -4.45, 0.0,   -1.17, -4.42, 0.0,     
        };
        
        Cpoints2[6] = new double[]
        {
            0.0, -5.6, 0.0,     0.31, -5.6, 0.0,     0.77, -5.6, 0.0,     1.34, -5.6, 0.0,    
            0.0, -5.81, 0.0,    0.33, -5.8, 0.0,     0.76, -5.8, 0.0,     1.35, -5.8, 0.0,    
            0.0, -6.12, 0.0,    0.34, -6.12, 0.0,    0.74, -6.13, 0.0,    1.26, -6.08, 0.0,    
            0.0, -6.72, 0.0,    0.36, -6.72, 0.0,    0.7, -6.74, 0.0,     1.07, -6.37, 0.0,      
        };
        
        Cpoints2[7] = new double[]
        {
            0.0, -5.6, 0.0,    -0.31, -5.6, 0.0,    -0.77, -5.6, 0.0,    -1.23, -5.6, 0.0,    
            0.0, -5.81, 0.0,   -0.33, -5.8, 0.0,    -0.76, -5.8, 0.0,    -1.2, -5.8, 0.0,    
            0.0, -6.12, 0.0,   -0.34, -6.12, 0.0,   -0.74, -6.13, 0.0,   -1.2, -6.08, 0.0,    
            0.0, -6.72, 0.0,   -0.36, -6.72, 0.0,   -0.7, -6.74, 0.0,    -1, -6.37, 0.0,      
        };
    }

    void fillCpoints3(double Cpoints3[][])
    {
        Cpoints3[0] = new double[]
        {
            0.0, -0.9, 1.1,     0.31, -0.9, 1.1,     0.74, -0.9, 1.1,     1.29, -0.9, 0.0,    
            0.0, -2.35, 1.2,    0.35, -2.35, 1.2,    0.77, -2.38, 1.2,    1.15, -2.28, 0.0,    
            0.0, -3.72, 1.3,    0.34, -3.76, 1.3,    0.62, -3.77, 1.3,    0.97, -3.85, 0.8,    
            0.0, -4.9, 1.4,     0.23, -4.88, 1.4,    0.48, -4.93, 1.4,    0.7, -4.53, 1.1,    
        };
        
        Cpoints3[1] = new double[]
        {
            0.0, -0.9, 1.1,    -0.31, -0.9, 1.1,    -0.74, -0.9, 1.1,    -1.29, -0.9, 0.0,    
            0.0, -2.35, 1.2,   -0.35, -2.35, 1.2,   -0.77, -2.38, 1.2,   -1.15, -2.28, 0.0,    
            0.0, -3.72, 1.3,   -0.34, -3.76, 1.3,   -0.62, -3.77, 1.3,   -0.97, -3.85, 0.5,    
            0.0, -4.9, 1.4,    -0.23, -4.88, 1.4,   -0.48, -4.93, 1.4,   -0.7, -4.53, 1.1,    
        };
    }
    
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        
        // Координатные оси
        gl.glBegin(GL.GL_LINES);
            gl.glColor3d(1, 0, 0);
            gl.glVertex3d(-2, 0, 0);
            gl.glVertex3d(2, 0, 0);
            
            gl.glColor3d(0, 1, 0);
            gl.glVertex3d(0, -2, 0);
            gl.glVertex3d(0, 2, 0);
            
            gl.glColor3d(0, 0, 1);
            gl.glVertex3d(0, 0, -2);
            gl.glVertex3d(0, 0, 2);
        gl.glEnd();
        
        CC.setCamera(gl, glu);  // управление камерой
        
        
        double Cpoints1[][] = new double[8][];
        double Cpoints2[][] = new double[8][];
        double Cpoints3[][] = new double[2][];
        
        fillCpoints1(Cpoints1);
        fillCpoints2(Cpoints2);
        fillCpoints3(Cpoints3);
        
        for (int i = 0; i < Cpoints1.length; ++i)
        {
            gl.glColor3d(0.6, 0.6, 0.6);
            drawPlane(gl, Cpoints1[i]);
        }
        for (int i = 0; i < Cpoints2.length; ++i)
        {
            gl.glColor3d(0.4, 0.4, 0.4);
            drawPlane(gl, Cpoints2[i]);
        }
        for (int i = 0; i < Cpoints3.length; ++i)
        {
            gl.glColor3d(0.5, 0.5, 0.5);
            drawPlane(gl, Cpoints3[i]);
        }
        
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

