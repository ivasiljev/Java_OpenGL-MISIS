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
 * Trenazher.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Trenazher implements GLEventListener {
    
    public static void main(String[] args) {
        Frame frame = new Frame("Trenazher");
        GLCanvas canvas = new GLCanvas();
        
        canvas.addGLEventListener(new Trenazher());
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

    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
    }
    
    void drawBox(GL gl, double centerX, double centerY, double centerZ, // Центр нижней плоскости бокса
                        double width,   double length,  double height,  // Ширина, длина, высота
                        double dx,      double dy,      double dz,      // Смещение верхней плоскости относительно нижней
                        double r,       double g,       double b)       // Цвет бокса
    {
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3d(r, g, b);
            gl.glVertex3d(centerX - width / 2, centerY - length / 2, centerZ);
            gl.glVertex3d(centerX - width / 2 + dx, centerY - length / 2 + dy, centerZ + height);
            gl.glVertex3d(centerX + width / 2 + dx, centerY - length / 2 + dy, centerZ + height + dz);
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + dz);
            
            gl.glColor3d(r + 0.1, g + 0.1, b + 0.1);
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + dz);
            gl.glVertex3d(centerX + width / 2 + dx, centerY - length / 2 + dy, centerZ + height + dz);
            gl.glVertex3d(centerX + width / 2 + dx, centerY + length / 2 + dy, centerZ + height + dz);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + dz);
            
            gl.glColor3d(r + 0.05, g + 0.05, b + 0.05);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + dz);
            gl.glVertex3d(centerX + width / 2 + dx, centerY + length / 2 + dy, centerZ + height + dz);
            gl.glVertex3d(centerX - width / 2 + dx, centerY + length / 2 + dy, centerZ + height);
            gl.glVertex3d(centerX - width / 2, centerY + length / 2, centerZ);
            
            gl.glColor3d(r, g, b);
            gl.glVertex3d(centerX - width / 2, centerY + length / 2, centerZ);
            gl.glVertex3d(centerX - width / 2 + dx, centerY + length / 2 + dy, centerZ + height);
            gl.glVertex3d(centerX - width / 2 + dx, centerY - length / 2 + dy, centerZ + height);
            gl.glVertex3d(centerX - width / 2, centerY - length / 2, centerZ);
            
            gl.glColor3d(r - 0.05, g - 0.05, b - 0.05);
            gl.glVertex3d(centerX - width / 2, centerY - length / 2, centerZ);
            gl.glVertex3d(centerX - width / 2, centerY + length / 2, centerZ);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + dz);
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + dz);
            
            gl.glColor3d(r + 0.15, g + 0.15, b + 0.15);
            gl.glVertex3d(centerX - width / 2 + dx, centerY - length / 2 + dy, centerZ + height);
            gl.glVertex3d(centerX - width / 2 + dx, centerY + length / 2 + dy, centerZ + height);
            gl.glVertex3d(centerX + width / 2 + dx, centerY + length / 2 + dy, centerZ + height + dz);
            gl.glVertex3d(centerX + width / 2 + dx, centerY - length / 2 + dy, centerZ + height + dz);
        gl.glEnd();
    }
    
    void drawSpecialBox(GL gl)
    {
        double centerX = -0.5, 
               centerY = 0, 
               centerZ = 0;
        double width = 0.8,
               length = 0.6,
               height = 4;
        
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3d(0.1, 0.1, 0.5);
            gl.glVertex3d(centerX - width / 2, centerY - length / 2, centerZ);
            gl.glVertex3d(centerX - width / 2, centerY - length / 2, centerZ + height);
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + height);
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ);
            
            gl.glColor3d(0.1, 0.1, 0.6);

            // Передняя часть
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ);
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + height / 9);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + height / 9);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ); 
            
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + height / 9 + height / 14);
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + 2 * height / 9 );
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + 2 * height / 9);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + height / 9 + height / 14);
            
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + 2 * height / 9 + height / 14);
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + 7 * height / 9 );
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + 7 * height / 9);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + 2 * height / 9 + height / 14);
            
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + 8 * height / 9);
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + height );
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + height);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + 8 * height / 9);
            
            
            // дырка 1
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + height / 9 + height / 14);
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + height / 9);
            gl.glVertex3d(centerX + width / 2, centerY - length / 8, centerZ + height / 9);
            gl.glVertex3d(centerX + width / 2, centerY - length / 8, centerZ + height / 9 + height / 14); 
            
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + height / 9 + height / 14);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + height / 9);
            gl.glVertex3d(centerX + width / 2, centerY + length / 8, centerZ + height / 9);
            gl.glVertex3d(centerX + width / 2, centerY + length / 8, centerZ + height / 9 + height / 14); 
            
            // дырка 2
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + 2 * height / 9 + height / 14);
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + 2 * height / 9 );
            gl.glVertex3d(centerX + width / 2, centerY - length / 8, centerZ + 2 * height / 9);
            gl.glVertex3d(centerX + width / 2, centerY - length / 8, centerZ + 2 * height / 9 + height / 14);
            
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + 2 * height / 9 + height / 14);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + 2 * height / 9 );
            gl.glVertex3d(centerX + width / 2, centerY + length / 8, centerZ + 2 * height / 9);
            gl.glVertex3d(centerX + width / 2, centerY + length / 8, centerZ + 2 * height / 9 + height / 14);
            
            // дырка 3
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + 8 * height / 9);
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + 7 * height / 9);
            gl.glVertex3d(centerX + width / 2, centerY - length / 8, centerZ + 7 * height / 9);
            gl.glVertex3d(centerX + width / 2, centerY - length / 8, centerZ + 8 * height / 9);
            
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + 8 * height / 9);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + 7 * height / 9);
            gl.glVertex3d(centerX + width / 2, centerY + length / 8, centerZ + 7 * height / 9);
            gl.glVertex3d(centerX + width / 2, centerY + length / 8, centerZ + 8 * height / 9);
            
            
            
            gl.glColor3d(0.1, 0.1, 0.55);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + height);
            gl.glVertex3d(centerX - width / 2, centerY + length / 2, centerZ + height);
            gl.glVertex3d(centerX - width / 2, centerY + length / 2, centerZ);
            
            gl.glColor3d(0.1, 0.1, 0.5);
            gl.glVertex3d(centerX - width / 2, centerY + length / 2, centerZ);
            gl.glVertex3d(centerX - width / 2, centerY + length / 2, centerZ + height);
            gl.glVertex3d(centerX - width / 2, centerY - length / 2, centerZ + height);
            gl.glVertex3d(centerX - width / 2, centerY - length / 2, centerZ);
            
            gl.glColor3d(0.1, 0.1, 0.45);
            gl.glVertex3d(centerX - width / 2, centerY - length / 2, centerZ);
            gl.glVertex3d(centerX - width / 2, centerY + length / 2, centerZ);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ);
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ);
            
            gl.glColor3d(0.1, 0.1, 0.65);
            gl.glVertex3d(centerX - width / 2, centerY - length / 2, centerZ + height);
            gl.glVertex3d(centerX - width / 2, centerY + length / 2, centerZ + height);
            gl.glVertex3d(centerX + width / 2, centerY + length / 2, centerZ + height);
            gl.glVertex3d(centerX + width / 2, centerY - length / 2, centerZ + height);
        gl.glEnd();
    }
    
    void chair(GL gl)
    {
        drawBox(gl, 0.8, 0, 0.06, 0.2, 0.2, 1, 0, 0, 0, 0.7, 0.7, 0.7);
        
        drawBox(gl, 0.8, 0, 1.06, 0.55, 0.5, 0.05, 0, 0, 0, 0, 0, 0.5);
        
        drawBox(gl, 0.7, 0.15, 1.01, 0.6, 0.1, 0.05, 0, 0, 0, 0.7, 0.7, 0.7);
        drawBox(gl, 0.7,-0.15, 1.01, 0.6, 0.1, 0.05, 0, 0, 0, 0.7, 0.7, 0.7);
        
        drawBox(gl, 0.4, 0.15, 0.95, 0.05, 0.1, 1.1,-0.07, 0, 0, 0.7, 0.7, 0.7);
        drawBox(gl, 0.4,-0.15, 0.95, 0.05, 0.1, 1.1,-0.07, 0, 0, 0.7, 0.7, 0.7);
        
        drawBox(gl, 0.43, 0, 1.3, 0.05, 0.5, 0.8,-0.07, 0, 0, 0, 0, 0.5);
        
        drawBox(gl, 0.15, 0, 0.53, 1.1, 0.13, 0.13, 0, 0, -0.2, 0.7, 0.7, 0.7);
        drawBox(gl, 0.15, 0, 0.97, 1.1, 0.13, 0.13, 0, 0, -0.2, 0.7, 0.7, 0.7);
    }
    
    void crossbar(GL gl, double pointZ)
    {
        double x, y = 0.5, z;
        gl.glBegin(GL.GL_QUAD_STRIP);
            int segmentsCount = 16;
            double angle = 0;
            double deltaAngle = 2 * Math.PI / segmentsCount;
            double radius = 0.05;
            for (int i = 0; i <= segmentsCount; ++i)
            {
                gl.glColor3d(Math.cos(angle) / 8 + 0.7, Math.cos(angle) / 8 + 0.7, Math.cos(angle) / 8 + 0.7);
                x = radius * Math.cos(angle);
                z = pointZ + radius * Math.sin(angle);
                gl.glVertex3d(x, y, z);
                gl.glVertex3d(x, -y, z);
                angle += deltaAngle;
            }
        gl.glEnd();        
    
        angle = 0;
        double Radius = 0.3;
        for (int i = 0; i <= segmentsCount; ++i)
        {
            double x1 = radius * Math.sin(angle);
            double y1 = y;
            double z1 = pointZ + radius * Math.cos(angle);
            
            double x2 = radius * Math.sin(angle + deltaAngle);
            double y2 = y;
            double z2 = pointZ + radius * Math.cos(angle + deltaAngle);
            

            gl.glBegin(GL.GL_QUAD_STRIP);
                double alpha = Math.PI;
                for (int j = 0; j <= segmentsCount / 4; ++j)
                {
                    double xx1 = x1 * Math.cos(alpha) - 0 * Math.sin(alpha) + Radius;
                    double yy1 = x1 * Math.sin(alpha) + 0 * Math.cos(alpha);
                    double xx2 = x2 * Math.cos(alpha) - 0 * Math.sin(alpha) + Radius;
                    double yy2 = x2 * Math.sin(alpha) + 0 * Math.cos(alpha);
                    xx1 = xx1 + Radius * Math.cos(alpha);
                    yy1 = y1 + yy1 + Radius * Math.sin(alpha);
                    xx2 = xx2 + Radius * Math.cos(alpha);
                    yy2 = y2 + yy2 + Radius * Math.sin(alpha);

                    gl.glColor3d(Math.cos(angle + Math.PI / 2) / 8 + 0.7, Math.cos(angle + Math.PI / 2) / 8 + 0.7, Math.cos(angle + Math.PI / 2) / 8 + 0.7);
                    gl.glVertex3d(xx1, yy1, z1);
                    gl.glColor3d(Math.cos(angle + Math.PI / 2 + deltaAngle) / 8 + 0.7, Math.cos(angle + Math.PI / 2 + deltaAngle) / 8 + 0.7, Math.cos(angle + Math.PI / 2 + deltaAngle) / 8 + 0.7);
                    gl.glVertex3d(xx2, yy2, z2);

                    alpha -= deltaAngle;
                }
            gl.glEnd();
            
            angle += deltaAngle;
        }
        
        angle = 0;
        Radius = 0.3;
        for (int i = 0; i <= segmentsCount; ++i)
        {
            double x1 = radius * Math.sin(angle);
            double y1 = -y;
            double z1 = pointZ + radius * Math.cos(angle);
            
            double x2 = radius * Math.sin(angle + deltaAngle);
            double y2 = -y;
            double z2 = pointZ + radius * Math.cos(angle + deltaAngle);
            

            gl.glBegin(GL.GL_QUAD_STRIP);
                double alpha = Math.PI;
                for (int j = 0; j <= segmentsCount / 4; ++j)
                {
                    double xx1 = x1 * Math.cos(alpha) - 0 * Math.sin(alpha) + Radius;
                    double yy1 = x1 * Math.sin(alpha) + 0 * Math.cos(alpha);
                    double xx2 = x2 * Math.cos(alpha) - 0 * Math.sin(alpha) + Radius;
                    double yy2 = x2 * Math.sin(alpha) + 0 * Math.cos(alpha);
                    xx1 = xx1 + Radius * Math.cos(alpha);
                    yy1 = y1 + yy1 + Radius * Math.sin(alpha);
                    xx2 = xx2 + Radius * Math.cos(alpha);
                    yy2 = y2 + yy2 + Radius * Math.sin(alpha);

                    gl.glColor3d(Math.cos(angle + Math.PI / 2) / 8 + 0.7, Math.cos(angle + Math.PI / 2) / 8 + 0.7, Math.cos(angle + Math.PI / 2) / 8 + 0.7);
                    gl.glVertex3d(xx1, yy1, z1);
                    gl.glColor3d(Math.cos(angle + Math.PI / 2 + deltaAngle) / 8 + 0.7, Math.cos(angle + Math.PI / 2 + deltaAngle) / 8 + 0.7, Math.cos(angle + Math.PI / 2 + deltaAngle) / 8 + 0.7);
                    gl.glVertex3d(xx2, yy2, z2);

                    alpha += deltaAngle;
                }
            gl.glEnd();
            
            angle += deltaAngle;
        }
        
        x = Radius;
        y += Radius;
        gl.glBegin(GL.GL_QUAD_STRIP);
            angle = 0;
            radius = 0.05;
            for (int i = 0; i <= segmentsCount; ++i)
            {
                gl.glColor3d(Math.sin(angle - Math.PI / 2) / 8 + 0.7, Math.sin(angle - Math.PI / 2) / 8 + 0.7, Math.sin(angle - Math.PI / 2) / 8 + 0.7);
                double yy = y + radius * Math.cos(angle);
                z = pointZ + radius * Math.sin(angle);
                gl.glVertex3d(x, yy, z);
                gl.glVertex3d(x + 1, yy, z);
                angle += deltaAngle;
            }
        gl.glEnd(); 
        
        gl.glBegin(GL.GL_QUAD_STRIP);
            angle = 0;
            radius = 0.05;
            for (int i = 0; i <= segmentsCount; ++i)
            {
                gl.glColor3d(Math.cos(angle) / 8 + 0.7, Math.cos(angle) / 8 + 0.7, Math.cos(angle) / 8 + 0.7);
                double yy = -y + radius * Math.cos(angle);
                z = pointZ + radius * Math.sin(angle);
                gl.glVertex3d(x, yy, z);
                gl.glVertex3d(x + 1, yy, z);
                angle += deltaAngle;
            }
        gl.glEnd(); 
        
        gl.glColor3d(0.75, 0.75, 0.75);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(x + 1, y, pointZ);
            angle = 0;
            radius = 0.05;
            for (int i = 0; i <= segmentsCount; ++i)
            {
                double yy = y + radius * Math.cos(angle);
                z = pointZ + radius * Math.sin(angle);
                gl.glVertex3d(x + 1, yy, z);
                angle += deltaAngle;
            }
        gl.glEnd(); 
        
        gl.glColor3d(0.75, 0.75, 0.75);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(x + 1, -y, pointZ);
            angle = 0;
            radius = 0.05;
            for (int i = 0; i <= segmentsCount; ++i)
            {
                double yy = -y + radius * Math.cos(angle);
                z = pointZ + radius * Math.sin(angle);
                gl.glVertex3d(x + 1, yy, z);
                angle += deltaAngle;
            }
        gl.glEnd();
        
        // Ручки -------
        
        // Левая
        
        x = Radius;
        gl.glBegin(GL.GL_QUAD_STRIP);
            angle = 0;
            radius = 0.05;
            for (int i = 0; i <= segmentsCount; ++i)
            {
                gl.glColor3d(0.1, 0.1, Math.cos(angle) / 8 + 0.4);
                double xx = x + radius * Math.cos(angle) + 0.9;
                z = pointZ + radius * Math.sin(angle);
                gl.glVertex3d(xx, y, z);
                gl.glVertex3d(xx, y - 0.3, z);
                angle += deltaAngle;
            }
        gl.glEnd(); 
        
        // Крышка для левой ручки
        
        gl.glColor3d(0.1, 0.1, 0.55);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(x + 0.9, y - 0.3, pointZ);
            angle = 0;
            radius = 0.05;
            for (int i = 0; i <= segmentsCount; ++i)
            {
                double xx = x + radius * Math.cos(angle) + 0.9;
                z = pointZ + radius * Math.sin(angle);
                gl.glVertex3d(xx, y - 0.3, z);
                angle += deltaAngle;
            }
        gl.glEnd(); 
        
        // Правая 
        
        gl.glBegin(GL.GL_QUAD_STRIP);
            angle = 0;
            radius = 0.05;
            for (int i = 0; i <= segmentsCount; ++i)
            {
                gl.glColor3d(0.1, 0.1, Math.cos(angle) / 8 + 0.4);
                double xx = x + radius * Math.cos(angle) + 0.9;
                z = pointZ + radius * Math.sin(angle);
                gl.glVertex3d(xx, -y, z);
                gl.glVertex3d(xx, -y + 0.3, z);
                angle += deltaAngle;
            }
        gl.glEnd(); 
        
        // Крышка для правой ручки
        
        gl.glColor3d(0.1, 0.1, 0.55);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(x + 0.9, -y + 0.3, pointZ);
            angle = 0;
            radius = 0.05;
            for (int i = 0; i <= segmentsCount; ++i)
            {
                double xx = x + radius * Math.cos(angle) + 0.9;
                z = pointZ + radius * Math.sin(angle);
                gl.glVertex3d(xx, -y + 0.3, z);
                angle += deltaAngle;
            }
        gl.glEnd(); 
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        
        gl.glLoadIdentity();
        glu.gluPerspective(90, 1, 0.01, 100);
        glu.gluLookAt(3, 3, 2.5, 0, 0, 2, 0, 0, 1);
        
        drawBox(gl, 0, 0, 0, 2, 0.7, 0.05, 0, 0, 0, 0, 0, 0.5);
        drawSpecialBox(gl);
        chair(gl);
        drawBox(gl, -0.1, 0, 3.35, 0.7, 0.13, 0.13, 0, 0, 0.05, 0.7, 0.7, 0.7);
        crossbar(gl, 3.45);

        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

