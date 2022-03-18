package mainpackage;

import myAddons.CameraControl;
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
 * Lekalo.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Lekalo implements GLEventListener {

    static int index = 0;
    static CameraControl CC = new CameraControl();
    static double Cpoints[] = 
    {
        -0.28, 0.16, 0.0, 
-0.55, 0.06, 0.0, 
-0.46, -0.07, 0.0, 
-0.41, -0.11, 0.0, 
    };
    
    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        CC.createKeyListener(canvas);
        createKeyListener(canvas);
        
        canvas.addGLEventListener(new Lekalo());
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
    
    public static void createKeyListener(GLCanvas canvas)
    {
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                //System.out.println("KeyCode2: "+ke.getKeyCode());
                switch (ke.getKeyCode())
                {
                    case 37: //left
                        Cpoints[index*3] -= 0.01;
                        break;
                    case 38: //up
                        Cpoints[index*3 + 1] += 0.01;
                        break;
                    case 39: //right
                        Cpoints[index*3] += 0.01;
                        break;
                    case 40: //down
                        Cpoints[index*3 + 1] -= 0.01;
                        break;
                    case 109:
                        if (index < 3) index++;
                        break;
                    case 107:
                        if (index > 0) index--;
                        break;
                }
            }
        });
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
    
    void makeCurve(GL gl, double[] Cpoints)
    {
        DoubleBuffer buf_Cpoints = DoubleBuffer.wrap(Cpoints); // Создаем буфер с массивом точек
        
        gl.glMap1d(GL.GL_MAP1_VERTEX_3, 
                0,                          // Начало обхода (от 0)
                1,                          // Конец обхода (до 1)
                3,                          // Количество координат
                4,                          // Количество точек
                buf_Cpoints);               // Буфер с точками
        
        gl.glEnable(GL.GL_MAP1_VERTEX_3);
        
        gl.glMapGrid1d(20, 0, 1);           // Строим кривую из 20 отрезков
        gl.glEvalMesh1(GL.GL_LINE, 0, 20);  // Начальная (0) и конечная (20) точки кривой
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        
        gl.glBegin(GL.GL_LINES);
            gl.glColor3d(0, 1, 0);
            gl.glVertex2d(-1, 0);
            gl.glVertex2d(1, 0);
            
            gl.glColor3d(1, 0, 0);
            gl.glVertex2d(0, 1);
            gl.glVertex2d(0, -1);
        gl.glEnd();
        
        gl.glColor3d(1, 1, 1);
        
      
        makeCurve(gl, Cpoints);
        
        double Cpoints1[] = 
        {
            -0.3, 0.26, 0.0, 
            -0.85, 0.24, 0.0, 
            -0.9, 0.1, 0.0, 
            -0.9, 0.0, 0.0
        };
        makeCurve(gl, Cpoints1);
        
        double Cpoints2[] = 
        {
            -0.3, 0.26, 0.0, 
            0.1, 0.29, 0.0, 
            0.86, 0.11, 0.0, 
            0.83, 0.0, 0.0
        };
        makeCurve(gl, Cpoints2);
        
        double Cpoints3[] = 
        {
            0.65, 0.0, 0.0, 
            0.66, -0.06, 0.0, 
            0.82, -0.11, 0.0, 
            0.83, 0.0, 0.0
        };
        makeCurve(gl, Cpoints3);
        
        double Cpoints4[] = 
        {
            0.65, 0.0, 0.0, 
            0.67, 0.03, 0.0, 
            0.7, -0.02, 0.0, 
            0.71, 0.02, 0.0
        };
        makeCurve(gl, Cpoints4);
        
        double Cpoints5[] = 
        {
            0.16, 0.02, 0.0, 
            0.18, 0.12, 0.0, 
            0.7, 0.07, 0.0, 
            0.71, 0.02, 0.0
        };
        makeCurve(gl, Cpoints5);
        
        double Cpoints6[] = 
        {
            0.16, 0.02, 0.0, 
            0.14, -0.03, 0.0, 
            0.22, 0.0, 0.0, 
            0.22, -0.04, 0.0
        };
        makeCurve(gl, Cpoints6);
        
        double Cpoints7[] = 
        {
            -0.07, -0.04, 0.0, 
            -0.02, -0.09, 0.0, 
            0.19, -0.1, 0.0, 
            0.22, -0.04, 0.0
        };
        makeCurve(gl, Cpoints7);
        
        double Cpoints8[] = 
        {
            -0.07, -0.04, 0.0, 
            -0.1, 0.01, 0.0, 
            0.0, 0.02, 0.0, 
            -0.01, 0.06, 0.0
        };
        makeCurve(gl, Cpoints8);
        
        double Cpoints9[] = 
        {
            -0.28, 0.04, 0.0, 
            -0.22, 0.13, 0.0, 
            -0.05, 0.16, 0.0, 
            -0.01, 0.06, 0.0
        };
        makeCurve(gl, Cpoints9);
        
        double Cpoints10[] = 
        {
            -0.28, 0.04, 0.0, 
            -0.17, 0.03, 0.0, 
            -0.07, -0.04, 0.0, 
            -0.18, -0.17, 0.0
        };
        makeCurve(gl, Cpoints10);
        
        double Cpoints11[] = 
        {
            -0.9, 0.0, 0.0, 
            -0.89, -0.3, 0.0, 
            -0.31, -0.28, 0.0, 
            -0.18, -0.17, 0.0
        };
        makeCurve(gl, Cpoints11);
        
        double Cpoints12[] = 
        {
            -0.28, 0.16, 0.0, 
            -0.55, 0.06, 0.0, 
            -0.46, -0.07, 0.0, 
            -0.41, -0.11, 0.0
        };
        makeCurve(gl, Cpoints12);
        
        double Cpoints13[] = 
        {
            -0.28, 0.16, 0.0, 
            -0.79, 0.11, 0.0, 
            -0.7, -0.14, 0.0, 
            -0.41, -0.11, 0.0
        };
        makeCurve(gl, Cpoints13);
        
        
        
        for (int i = 0; i < 4; i ++)
        {
            if (index == i)
            {
                gl.glColor3d(1, 0, 0);
                gl.glPointSize(6);
            }
            else
            {
                gl.glColor3d(1, 1, 1);
                gl.glPointSize(3);
            }
            gl.glBegin(GL.GL_POINTS);
                gl.glVertex3d(Cpoints[i*3], Cpoints[i*3 + 1], Cpoints[i*3 + 2]);
            gl.glEnd();
            System.out.println((double)Math.round(Cpoints[i*3]*100)/100 + ", " + (double)Math.round(Cpoints[i*3 + 1]*100)/100 + ", " + (double)Math.round(Cpoints[i*3 + 2]*100)/100 + ", ");
        }
        System.out.println();
        
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

