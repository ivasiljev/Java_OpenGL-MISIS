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
 * Stropila.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Stropila implements GLEventListener {

    final double stropDeltaX = 2;   // Расстояние между соседними стропилами
    final double s = 0.2;           // Поперечное сечение балок
    
    int stropCount = 5;
    
    public static void main(String[] args) {
        Frame frame = new Frame("Stropila");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Stropila());
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
    
    private void Balki(GL gl, double width, double height, int count)
    {
        // Верхняя центральная балка
        gl.glColor3d(0.6, 0.6, 0.6);
        double length = (count - 1) * stropDeltaX + s;
        gl.glBegin(GL.GL_QUAD_STRIP);
            gl.glVertex3d(0, -s/2, height - s);
            gl.glVertex3d(length, -s/2, height - s);
            gl.glVertex3d(0, s/2, height - s);
            gl.glVertex3d(length, s/2, height - s);
            gl.glVertex3d(0, s/2, height);
            gl.glVertex3d(length, s/2, height);
            gl.glVertex3d(0, -s/2, height);
            gl.glVertex3d(length, -s/2, height);
            gl.glVertex3d(0, -s/2, height - s);
            gl.glVertex3d(length, -s/2, height - s);
        gl.glEnd();
        gl.glBegin(GL.GL_QUADS);
            gl.glVertex3d(0, -s/2, height - s);
            gl.glVertex3d(0, s/2, height - s);
            gl.glVertex3d(0, s/2, height);
            gl.glVertex3d(0, -s/2, height);
            
            gl.glVertex3d(length, -s/2, height - s);
            gl.glVertex3d(length, s/2, height - s);
            gl.glVertex3d(length, s/2, height);
            gl.glVertex3d(length, -s/2, height);
        gl.glEnd();
        
        //2 -----------------------
        
        // Нижняя центральная балка
        gl.glBegin(GL.GL_QUAD_STRIP);
            gl.glVertex3d(0, -s/2, 0);
            gl.glVertex3d(length, -s/2, 0);
            gl.glVertex3d(0, s/2, 0);
            gl.glVertex3d(length, s/2, 0);
            gl.glVertex3d(0, s/2, s);
            gl.glVertex3d(length, s/2, s);
            gl.glVertex3d(0, -s/2, s);
            gl.glVertex3d(length, -s/2, s);
            gl.glVertex3d(0, -s/2, 0);
            gl.glVertex3d(length, -s/2, 0);
        gl.glEnd();
        gl.glBegin(GL.GL_QUADS);
            gl.glVertex3d(0, -s/2, 0);
            gl.glVertex3d(0, s/2, 0);
            gl.glVertex3d(0, s/2, s);
            gl.glVertex3d(0, -s/2, s);
            
            gl.glVertex3d(length, -s/2, 0);
            gl.glVertex3d(length, s/2, 0);
            gl.glVertex3d(length, s/2, s);
            gl.glVertex3d(length, -s/2, s);
        gl.glEnd();
        
        //3 ------------------------
        
        // Правая балка
        double b = s * width / height + s/2;
        gl.glBegin(GL.GL_QUAD_STRIP);
            gl.glVertex3d(0, width - b, s);
            gl.glVertex3d(length, width - b, s);
            gl.glVertex3d(0, width - b, s + s);
            gl.glVertex3d(length, width - b, s + s);
            gl.glVertex3d(0, width - b - s, s + s);
            gl.glVertex3d(length, width - b - s, s + s);
            gl.glVertex3d(0, width - b - s, s);
            gl.glVertex3d(length, width - b - s, s);
            gl.glVertex3d(0, width - b, s);
            gl.glVertex3d(length, width - b, s);
        gl.glEnd();
        gl.glBegin(GL.GL_QUADS);
            gl.glVertex3d(0, width - b, s);
            gl.glVertex3d(0, width - b, s + s);
            gl.glVertex3d(0, width - b - s, s + s);
            gl.glVertex3d(0, width - b - s, s);
            
            gl.glVertex3d(length, width - b, s);
            gl.glVertex3d(length, width - b, s + s);
            gl.glVertex3d(length, width - b - s, s + s);
            gl.glVertex3d(length, width - b - s, s);
        gl.glEnd();
        
        //4 --------------------------
        
        // Левая балка
        width = -width;
        b = -b;
        gl.glBegin(GL.GL_QUAD_STRIP);
            gl.glVertex3d(0, width - b, s);
            gl.glVertex3d(length, width - b, s);
            gl.glVertex3d(0, width - b, s + s);
            gl.glVertex3d(length, width - b, s + s);
            gl.glVertex3d(0, width - b + s, s + s);
            gl.glVertex3d(length, width - b + s, s + s);
            gl.glVertex3d(0, width - b + s, s);
            gl.glVertex3d(length, width - b + s, s);
            gl.glVertex3d(0, width - b, s);
            gl.glVertex3d(length, width - b, s);
        gl.glEnd();
        gl.glBegin(GL.GL_QUADS);
            gl.glVertex3d(0, width - b, s);
            gl.glVertex3d(0, width - b, s + s);
            gl.glVertex3d(0, width - b + s, s + s);
            gl.glVertex3d(0, width - b + s, s);
            
            gl.glVertex3d(length, width - b, s);
            gl.glVertex3d(length, width - b, s + s);
            gl.glVertex3d(length, width - b + s, s + s);
            gl.glVertex3d(length, width - b + s, s);
        gl.glEnd();
    }
    
    private void Stropil(GL gl, double width, double height, int count)
    {
        double x = 0;
        for (int i = 0; i < count; ++i)
        {
            // Правые стропила
            gl.glColor3d(0.7, 0.7, 0.7);
            gl.glBegin(GL.GL_QUAD_STRIP);
                gl.glVertex3d(x, width, 0);
                gl.glVertex3d(x, 0, height);
                gl.glVertex3d(x, width, s);
                gl.glVertex3d(x, 0, height + s);
                gl.glVertex3d(x + s, width, s);
                gl.glVertex3d(x + s, 0, height + s);
                gl.glVertex3d(x + s, width, 0);
                gl.glVertex3d(x + s, 0, height);
                gl.glVertex3d(x, width, 0);
                gl.glVertex3d(x, 0, height);
            gl.glEnd();
            gl.glBegin(GL.GL_QUADS);
                gl.glVertex3d(x, width, 0);
                gl.glVertex3d(x, width, s);
                gl.glVertex3d(x + s, width, s);
                gl.glVertex3d(x + s, width, 0);

                gl.glVertex3d(x, 0, height);
                gl.glVertex3d(x, 0, height + s);
                gl.glVertex3d(x + s, 0, height + s);
                gl.glVertex3d(x + s, 0, height);
            gl.glEnd();

            // Правые подпорки
            gl.glColor3d(0.65, 0.65, 0.65);
            {
                double ws = width / 2;
                double hs = (width - ws) * height / width;
                gl.glBegin(GL.GL_QUAD_STRIP);
                    gl.glVertex3d(x, s/2, s);
                    gl.glVertex3d(x, ws, hs);
                    gl.glVertex3d(x, s/2, s + s);
                    gl.glVertex3d(x, ws, hs + s);
                    gl.glVertex3d(x + s, s/2, s + s);
                    gl.glVertex3d(x + s, ws, hs + s);
                    gl.glVertex3d(x + s, s/2, s);
                    gl.glVertex3d(x + s, ws, hs);
                    gl.glVertex3d(x, s/2, s);
                    gl.glVertex3d(x, ws, hs);
                gl.glEnd();
                gl.glBegin(GL.GL_QUADS);
                    gl.glVertex3d(x, s/2, s);
                    gl.glVertex3d(x, s/2, s + s);
                    gl.glVertex3d(x + s, s/2, s + s);
                    gl.glVertex3d(x + s, s/2, s);

                    gl.glVertex3d(x, ws, hs);
                    gl.glVertex3d(x, ws, hs + s);
                    gl.glVertex3d(x + s, ws, hs + s);
                    gl.glVertex3d(x + s, ws, hs);
                gl.glEnd();
            }
                
            //--------------------------

            // Левые стропила
            width = -width;
            gl.glColor3d(0.7, 0.7, 0.7);
            gl.glBegin(GL.GL_QUAD_STRIP);
                gl.glVertex3d(x, width, 0);
                gl.glVertex3d(x, 0, height);
                gl.glVertex3d(x, width, s);
                gl.glVertex3d(x, 0, height + s);
                gl.glVertex3d(x + s, width, s);
                gl.glVertex3d(x + s, 0, height + s);
                gl.glVertex3d(x + s, width, 0);
                gl.glVertex3d(x + s, 0, height);
                gl.glVertex3d(x, width, 0);
                gl.glVertex3d(x, 0, height);
            gl.glEnd();
            gl.glBegin(GL.GL_QUADS);
                gl.glVertex3d(x, width, 0);
                gl.glVertex3d(x, width, s);
                gl.glVertex3d(x + s, width, s);
                gl.glVertex3d(x + s, width, 0);

                gl.glVertex3d(x, 0, height);
                gl.glVertex3d(x, 0, height + s);
                gl.glVertex3d(x + s, 0, height + s);
                gl.glVertex3d(x + s, 0, height);
            gl.glEnd();

            // Левые подпорки
            gl.glColor3d(0.65, 0.65, 0.65);
            {
                double ws = width / 2;
                double hs = (width - ws) * height / width;
                gl.glBegin(GL.GL_QUAD_STRIP);
                    gl.glVertex3d(x, -s/2, s);
                    gl.glVertex3d(x, ws, hs);
                    gl.glVertex3d(x, -s/2, s + s);
                    gl.glVertex3d(x, ws, hs + s);
                    gl.glVertex3d(x + s, -s/2, s + s);
                    gl.glVertex3d(x + s, ws, hs + s);
                    gl.glVertex3d(x + s, -s/2, s);
                    gl.glVertex3d(x + s, ws, hs);
                    gl.glVertex3d(x, -s/2, s);
                    gl.glVertex3d(x, ws, hs);
                gl.glEnd();
                gl.glBegin(GL.GL_QUADS);
                    gl.glVertex3d(x, -s/2, s);
                    gl.glVertex3d(x, -s/2, s + s);
                    gl.glVertex3d(x + s, -s/2, s + s);
                    gl.glVertex3d(x + s, -s/2, s);

                    gl.glVertex3d(x, ws, hs);
                    gl.glVertex3d(x, ws, hs + s);
                    gl.glVertex3d(x + s, ws, hs + s);
                    gl.glVertex3d(x + s, ws, hs);
                gl.glEnd();
            }
            
            // --------------------------
            
            // Опорный столб
            gl.glColor3d(0.6, 0.6, 0.6);
            gl.glBegin(GL.GL_QUAD_STRIP);
                gl.glVertex3d(x,-s/2, s);
                gl.glVertex3d(x,-s/2, height - s);
                gl.glVertex3d(x, s/2, s);
                gl.glVertex3d(x, s/2, height - s);
                gl.glVertex3d(x + s, s/2, s);
                gl.glVertex3d(x + s, s/2, height - s);
                gl.glVertex3d(x + s,-s/2, s);
                gl.glVertex3d(x + s,-s/2, height - s);
                gl.glVertex3d(x,-s/2, s);
                gl.glVertex3d(x,-s/2, height - s);
            gl.glEnd();
            gl.glBegin(GL.GL_QUADS);
                gl.glVertex3d(x,-s/2, s);
                gl.glVertex3d(x, s/2, s);
                gl.glVertex3d(x + s, s/2, s);
                gl.glVertex3d(x + s,-s/2, s);

                gl.glVertex3d(x,-s/2, height - s);
                gl.glVertex3d(x, s/2, height - s);
                gl.glVertex3d(x + s, s/2, height - s);
                gl.glVertex3d(x + s,-s/2, height - s);
            gl.glEnd();
            
            width = -width;
            x += stropDeltaX;
        }
        
        Balki(gl, width, height, count);
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        
        gl.glLoadIdentity();
        glu.gluPerspective(90, 1, 0.1, 100);
        glu.gluLookAt(-5, 3, 10, (stropCount - 1) * stropDeltaX, 0, 0, 0, 0, 1);
        
        Stropil(gl, 4, 3, stropCount);
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

