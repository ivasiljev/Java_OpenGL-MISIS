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
 * Polzun.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Polzun implements GLEventListener {
    double height = 0;
    double width = 0;
    byte maskArray[] =
    { 
        (byte)0x81, (byte)0x81, (byte)0x81, (byte)0x81, (byte)0x81, (byte)0x81, (byte)0x81, (byte)0x81, 
        (byte)0xC0, (byte)0xC0, (byte)0xC0, (byte)0xC0, (byte)0xC0, (byte)0xC0, (byte)0xC0, (byte)0xC0, 
        (byte)0x60, (byte)0x60, (byte)0x60, (byte)0x60, (byte)0x60, (byte)0x60, (byte)0x60, (byte)0x60,
        (byte)0x30, (byte)0x30, (byte)0x30, (byte)0x30, (byte)0x30, (byte)0x30, (byte)0x30, (byte)0x30,  
        (byte)0x18, (byte)0x18, (byte)0x18, (byte)0x18, (byte)0x18, (byte)0x18, (byte)0x18, (byte)0x18, 
        (byte)0x0C, (byte)0x0C, (byte)0x0C, (byte)0x0C, (byte)0x0C, (byte)0x0C, (byte)0x0C, (byte)0x0C,
        (byte)0x06, (byte)0x06, (byte)0x06, (byte)0x06, (byte)0x06, (byte)0x06, (byte)0x06, (byte)0x06,  
        (byte)0x03, (byte)0x03, (byte)0x03, (byte)0x03, (byte)0x03, (byte)0x03, (byte)0x03, (byte)0x03,
        (byte)0x81, (byte)0x81, (byte)0x81, (byte)0x81, (byte)0x81, (byte)0x81, (byte)0x81, (byte)0x81, 
        (byte)0xC0, (byte)0xC0, (byte)0xC0, (byte)0xC0, (byte)0xC0, (byte)0xC0, (byte)0xC0, (byte)0xC0, 
        (byte)0x60, (byte)0x60, (byte)0x60, (byte)0x60, (byte)0x60, (byte)0x60, (byte)0x60, (byte)0x60,
        (byte)0x30, (byte)0x30, (byte)0x30, (byte)0x30, (byte)0x30, (byte)0x30, (byte)0x30, (byte)0x30,  
        (byte)0x18, (byte)0x18, (byte)0x18, (byte)0x18, (byte)0x18, (byte)0x18, (byte)0x18, (byte)0x18, 
        (byte)0x0C, (byte)0x0C, (byte)0x0C, (byte)0x0C, (byte)0x0C, (byte)0x0C, (byte)0x0C, (byte)0x0C,
        (byte)0x06, (byte)0x06, (byte)0x06, (byte)0x06, (byte)0x06, (byte)0x06, (byte)0x06, (byte)0x06,  
        (byte)0x03, (byte)0x03, (byte)0x03, (byte)0x03, (byte)0x03, (byte)0x03, (byte)0x03, (byte)0x03,
    };
    
    public static void main(String[] args) {
        Frame frame = new Frame("Polzun");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Polzun());
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
        
        this.height = height;
        this.width = width;
    }
    
    void Color1(GL gl)
    {
        double color  = 0.72;
        gl.glColor3d(color, color, color);
    }
    
    void Color2(GL gl)
    {
        double color  = 0.7;
        gl.glColor3d(color, color, color);
    }
    
    void Color3(GL gl)
    {
        double color  = 0.4;
        gl.glColor3d(color, color, color);
    }
    
    void Color4(GL gl)
    {
        double color  = 0.53;
        gl.glColor3d(color, color, color);
    }
    
    void Color5(GL gl)
    {
        double color  = 0.75;
        gl.glColor3d(color, color, color);
    }
    
    void Color6(GL gl)
    {
        double color  = 0.45;
        gl.glColor3d(color, color, color);
    }

    public void viewport1(GL gl)
    {
        gl.glViewport(0, (int)(height / 2) + 3, (int)(width / 2) - 3, (int)(height / 2));
        gl.glScissor(0, (int)(height / 2) + 3, (int)(width / 2) - 3, (int)(height / 2));
        
        gl.glClearColor(0.95f, 0.95f, 0.95f, 1);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        gl.glLoadIdentity();
        gl.glScaled(0.17, 0.17, 0.17);
        gl.glLineWidth(2);
        
        gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(-5, -4);
            gl.glVertex2d(5, -4);
            
            gl.glVertex2d(-5, -4);
            gl.glVertex2d(-5, -2);
            
            gl.glVertex2d(5, -4);
            gl.glVertex2d(5, -2);
            
            gl.glVertex2d(-5, -2);
            gl.glVertex2d(0, -2);
            
            gl.glVertex2d(2.2, -2);
            gl.glVertex2d(5, -2);
            
            gl.glVertex2d(-3.5, -4);
            gl.glVertex2d(-3.5, -2);
            
            gl.glVertex2d(-2.2, 1);
            gl.glVertex2d(-2.2, -2);
            
            gl.glVertex2d(2.2, 1);
            gl.glVertex2d(2.2, -2);
            
            gl.glVertex2d(0, -1);
            gl.glVertex2d(2.2, -1);
            
            gl.glVertex2d(2.7, -4);
            gl.glVertex2d(2.7, -2);
            
            gl.glVertex2d(4.3, -4);
            gl.glVertex2d(4.3, -2);
        gl.glEnd();
        
        double x = 0; 
        double y = 0;
        double radius = 2.2;
        double alpha = 0; 
        int segments = 18;
        double deltaAlpha = Math.PI / segments;
        gl.glBegin(GL.GL_LINE_STRIP);
            for (int i = 0; i <= segments; ++i)
            {
                x = radius * Math.cos(alpha);
                y = 1 + radius * Math.sin(alpha);
                gl.glVertex2d(x, y);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        radius = 1;
        alpha = 0; 
        segments = segments * 2;
        deltaAlpha = 2 * Math.PI / segments;
        gl.glBegin(GL.GL_LINE_STRIP);
            for (int i = 0; i <= segments; ++i)
            {
                x = radius * Math.cos(alpha);
                y = 0.8 + radius * Math.sin(alpha);
                gl.glVertex2d(x, y);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        // Линии чертежа
        gl.glLineWidth(1);
        gl.glLineStipple(2, (short)0x38FF);
        gl.glEnable(GL.GL_LINE_STIPPLE);
        gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(0, -4.5);
            gl.glVertex2d(0, 3.5);
            
            gl.glVertex2d(-3.54, -4.5);
            gl.glVertex2d(-3.54, -1.5);
            
            gl.glVertex2d(3.5, -4.5);
            gl.glVertex2d(3.5, -1.5);
            
            gl.glVertex2d(-2.5, 0.8);
            gl.glVertex2d(2.5, 0.8);
        gl.glEnd();
        
        
        gl.glLineWidth(2);
        gl.glLineStipple(1, (short)0xF00F);
        gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(-2.7, -4);
            gl.glVertex2d(-2.7, -2);
            
            gl.glVertex2d(-4.3, -4);
            gl.glVertex2d(-4.3, -2);
        gl.glEnd();
        gl.glDisable(GL.GL_LINE_STIPPLE);
        
        java.nio.ByteBuffer mask = java.nio.ByteBuffer.wrap(maskArray);
        gl.glPolygonStipple(mask);
        gl.glEnable(GL.GL_POLYGON_STIPPLE);
        gl.glBegin(GL.GL_QUADS);
            gl.glVertex2d(0, -1);
            gl.glVertex2d(2.2, -1);
            gl.glVertex2d(2.2, -4);
            gl.glVertex2d(0, -4);
            
            gl.glVertex2d(2.2, -2);
            gl.glVertex2d(2.7, -2);
            gl.glVertex2d(2.7, -4);
            gl.glVertex2d(2.2, -4);
            
            gl.glVertex2d(4.3, -2);
            gl.glVertex2d(5, -2);
            gl.glVertex2d(5, -4);
            gl.glVertex2d(4.3, -4);
        gl.glEnd();
        gl.glDisable(GL.GL_POLYGON_STIPPLE);
    }
    
    public void viewport2(GL gl)
    {
        gl.glViewport((int)(width / 2) + 3, (int)(height / 2) + 3, (int)(width / 2), (int)(height / 2));
        gl.glScissor((int)(width / 2) + 3, (int)(height / 2) + 3, (int)(width / 2), (int)(height / 2));
        
        gl.glClearColor(0.95f, 0.95f, 0.95f, 1);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        gl.glLoadIdentity();
        gl.glScaled(0.17, 0.17, 0.17);
        gl.glLineWidth(2);
        
        gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(-3.5, -4);
            gl.glVertex2d(3.5, -4);
            
            gl.glVertex2d(-3.5, -4);
            gl.glVertex2d(-3.5, -2);
            
            gl.glVertex2d(3.5, -4);
            gl.glVertex2d(3.5, -2);
            
            gl.glVertex2d(-3.5, -2);
            gl.glVertex2d(0, -2);
            
            gl.glVertex2d(-2.5, -2);
            gl.glVertex2d(-2.5, 3);
            
            gl.glVertex2d(-2.5, 3);
            gl.glVertex2d(-1, 3);
            
            gl.glVertex2d(-1, 3);
            gl.glVertex2d(-1, -1);
            
            gl.glVertex2d(2.5, -2);
            gl.glVertex2d(2.5, 3);
            
            gl.glVertex2d(2.5, 3);
            gl.glVertex2d(1, 3);
            
            gl.glVertex2d(1, 3);
            gl.glVertex2d(1, -1);
            
            gl.glVertex2d(-1, -1);
            gl.glVertex2d(1, -1);
            
            gl.glVertex2d(3.5, -2);
            gl.glVertex2d(2.5, -2);
            
            gl.glVertex2d(1, -0.2);
            gl.glVertex2d(2.5, -0.2);
            
            gl.glVertex2d(1, 1.8);
            gl.glVertex2d(2.5, 1.8);
        gl.glEnd();
        
        // Линии чертежа
        gl.glLineWidth(1);
        gl.glLineStipple(2, (short)0x38FF);
        gl.glEnable(GL.GL_LINE_STIPPLE);
        gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(0, -4.5);
            gl.glVertex2d(0, 3.5);
            
            gl.glVertex2d(-2.7, 0.8);
            gl.glVertex2d(2.7, 0.8);
        gl.glEnd();
        gl.glDisable(GL.GL_LINE_STIPPLE);
        
        java.nio.ByteBuffer mask = java.nio.ByteBuffer.wrap(maskArray);
        gl.glPolygonStipple(mask);
        gl.glEnable(GL.GL_POLYGON_STIPPLE);
        gl.glBegin(GL.GL_QUADS);
            gl.glVertex2d(0, -1);
            gl.glVertex2d(2.5, -1);
            gl.glVertex2d(2.5, -4);
            gl.glVertex2d(0, -4);
            
            gl.glVertex2d(2.5, -2);
            gl.glVertex2d(3.5, -2);
            gl.glVertex2d(3.5, -4);
            gl.glVertex2d(2.5, -4);
            
            gl.glVertex2d(1, -0.2);
            gl.glVertex2d(2.5, -0.2);
            gl.glVertex2d(2.5, -1);
            gl.glVertex2d(1, -1);
            
            gl.glVertex2d(1, 3);
            gl.glVertex2d(2.5, 3);
            gl.glVertex2d(2.5, 1.8);
            gl.glVertex2d(1, 1.8);
        gl.glEnd();
        gl.glDisable(GL.GL_POLYGON_STIPPLE);
    }
    
    public void viewport3(GL gl)
    {
        gl.glViewport(0, 0, (int)(width / 2) - 3, (int)(height / 2) - 3);
        gl.glScissor(0, 0, (int)(width / 2) - 3, (int)(height / 2) - 3);
        
        gl.glClearColor(0.95f, 0.95f, 0.95f, 1);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        gl.glLoadIdentity();
        gl.glScaled(0.17, 0.17, 0.17);
        gl.glLineWidth(2);
        
        gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(-2.2, -3);
            gl.glVertex2d(-2.2, 2);
            
            gl.glVertex2d(2.2, -3);
            gl.glVertex2d(2.2, 2);
            
            gl.glVertex2d(-2.2, -3);
            gl.glVertex2d(2.2, -3);
            
            gl.glVertex2d(2.2, 2);
            gl.glVertex2d(-2.2, 2);
            
            gl.glVertex2d(2.2, 0.5);
            gl.glVertex2d(-2.2, 0.5);
            
            gl.glVertex2d(2.2, -1.5);
            gl.glVertex2d(-2.2, -1.5);
        gl.glEnd();
        
        double x = 0; 
        double y = 0;
        double radius = 5;
        double alpha = 0; 
        int segments = 36;
        double deltaAlpha = Math.PI / segments;
        gl.glBegin(GL.GL_LINE_STRIP);
            while (alpha < 2 * Math.PI)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) - 0.5;
                if (x >= 3.5 || x < -3.5) gl.glVertex2d(x, y);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        radius = 0.8;
        alpha = 0; 
        segments = segments * 2;
        deltaAlpha = 2 * Math.PI / segments;
        gl.glBegin(GL.GL_LINE_STRIP);
            for (int i = 0; i <= segments; ++i)
            {
                x = radius * Math.cos(alpha) - 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                gl.glVertex2d(x, y);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        radius = 0.8;
        alpha = 0; 
        segments = segments * 2;
        deltaAlpha = 2 * Math.PI / segments;
        gl.glBegin(GL.GL_LINE_STRIP);
            for (int i = 0; i <= segments; ++i)
            {
                x = radius * Math.cos(alpha) + 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                gl.glVertex2d(x, y);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        // Линии чертежа
        gl.glLineWidth(1);
        gl.glLineStipple(2, (short)0x38FF);
        gl.glEnable(GL.GL_LINE_STIPPLE);
        gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(0, -4.5);
            gl.glVertex2d(0, 3.5);
            
            gl.glVertex2d(-5.5, -0.5);
            gl.glVertex2d(5.5, -0.5);
            
            gl.glVertex2d(3.5, -1.7);
            gl.glVertex2d(3.5, 0.7);
            
            gl.glVertex2d(-3.5, -1.7);
            gl.glVertex2d(-3.5, 0.7);
        gl.glEnd();
        gl.glDisable(GL.GL_LINE_STIPPLE);
    }
    
    public void viewport4(GL gl)
    {
        GLU glu = new GLU();
        
        gl.glViewport((int)(width / 2) + 3, 0, (int)(width / 2), (int)(height / 2) - 3);
        gl.glScissor((int)(width / 2) + 3, 0, (int)(width / 2), (int)(height / 2) - 3);
        
        gl.glClearColor(0.95f, 0.95f, 0.95f, 1);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        
        gl.glLoadIdentity();
        glu.gluPerspective(90, 1, 0.1, 50);
        glu.gluLookAt(-6, -6, 6, 0, 0, 2, 0, 0, 1);
        //glu.gluLookAt(-10, 0, 4, 0, 0, 2, 0, 0, 1);
        
        // Основание
        
        double x = 0; 
        double y = 0;
        double radius = 5;
        double alpha = Math.PI / 2; 
        int segments = 36;
        double deltaAlpha = Math.PI / segments;
        gl.glBegin(GL.GL_QUAD_STRIP);
            while (alpha < 2 * Math.PI + Math.PI / 2)
            {
                gl.glColor3d(Math.cos(alpha) / 6 + 0.4, Math.cos(alpha) / 6 + 0.4, Math.cos(alpha) / 6 + 0.4);
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) - 0.5;
                if (x >= 3.5)
                {
                    gl.glVertex3d(x, y, 0);
                    gl.glVertex3d(x, y, 2);
                }
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        alpha = 0;
        gl.glBegin(GL.GL_QUAD_STRIP);
            while (alpha < 2 * Math.PI)
            {
                gl.glColor3d(Math.cos(Math.PI + alpha) / 4 + 0.57, Math.cos(Math.PI + alpha) / 4 + 0.57, Math.cos(Math.PI + alpha) / 4 + 0.57);
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) - 0.5;
                if (x <= -3.5)
                {
                    gl.glVertex3d(x, y, 0);
                    gl.glVertex3d(x, y, 2);
                }
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        // Боковые стороны основания 
        
        gl.glBegin(GL.GL_QUADS);
            Color1(gl);
            gl.glVertex3d(3.5, -4, 0);
            gl.glVertex3d(3.5, -4, 2);
            gl.glVertex3d(-3.5, -4, 2);
            gl.glVertex3d(-3.5, -4, 0);
            
            Color4(gl);
            gl.glVertex3d(3.55, radius * Math.sin(Math.acos(3.5/radius)) - 0.58, 0);
            gl.glVertex3d(3.55, radius * Math.sin(Math.acos(3.5/radius)) - 0.58, 2);
            gl.glVertex3d(-3.55, radius * Math.sin(Math.acos(-3.5/radius)) - 0.58, 2);
            gl.glVertex3d(-3.55, radius * Math.sin(Math.acos(-3.5/radius)) - 0.58, 0);
        gl.glEnd();
        
        hole1(gl);
        hole2(gl);

        gl.glBegin(GL.GL_QUADS);        
            // Верхняя поверхность основания
            Color2(gl);
            gl.glVertex3d(3.5, -4, 2);
            gl.glVertex3d(2.2, -3, 2);
            gl.glVertex3d(-2.2, -3, 2);
            gl.glVertex3d(-3.5, -4, 2);
            
            gl.glVertex3d(3.5, 3, 2);
            gl.glVertex3d(2.2, 2, 2);
            gl.glVertex3d(-2.2, 2, 2);
            gl.glVertex3d(-3.5, 3, 2);
            
            // Верхняя поверхность по середине детали
            gl.glVertex3d(2.2, -1.5, 3);
            gl.glVertex3d(-2.2, -1.5, 3);
            gl.glVertex3d(-2.2, 0.5, 3);
            gl.glVertex3d(2.2, 0.5, 3);
                    
            // Нижняя поверхность основания
            Color3(gl);
            gl.glVertex3d(3.5, -4, 0);
            gl.glVertex3d(2.2, -3, 0);
            gl.glVertex3d(-2.2, -3, 0);
            gl.glVertex3d(-3.5, -4, 0);
            
            gl.glVertex3d(3.5, 3, 0);
            gl.glVertex3d(2.2, 2, 0);
            gl.glVertex3d(-2.2, 2, 0);
            gl.glVertex3d(-3.5, 3, 0);
            
            gl.glVertex3d(2.2, 2, 0);
            gl.glVertex3d(-2.2, 2, 0);
            gl.glVertex3d(-2.2, -3, 0);
            gl.glVertex3d(2.2, -3, 0);
            
            // Боковые грани у верхней части детали
            Color1(gl);
            gl.glVertex3d(2.2, -3, 2);
            gl.glVertex3d(2.2, -3, 3);
            gl.glVertex3d(-2.2, -3, 3);
            gl.glVertex3d(-2.2, -3, 2);
            
            Color4(gl);
            gl.glVertex3d(2.2, 2, 2);
            gl.glVertex3d(2.2, 2, 3);
            gl.glVertex3d(-2.2, 2, 3);
            gl.glVertex3d(-2.2, 2, 2);
            
            Color6(gl);
            gl.glVertex3d(2.2, 2, 2);
            gl.glVertex3d(2.2, 2, 3);
            gl.glVertex3d(2.2, -3, 3);
            gl.glVertex3d(2.2, -3, 2);
            
            Color5(gl);
            gl.glVertex3d(-2.2, 2, 2);
            gl.glVertex3d(-2.2, 2, 3);
            gl.glVertex3d(-2.2, -3, 3);
            gl.glVertex3d(-2.2, -3, 2);
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUAD_STRIP);
            radius = 2.2;
            alpha = 0; 
            segments = 18;
            deltaAlpha = Math.PI / segments;
            
            Color6(gl);
            gl.glVertex3d(2.2, -3, 3);
            gl.glVertex3d(2.2, -1.5, 3);
            gl.glColor3d(Math.cos(Math.PI - alpha) / 6 + 0.6, Math.cos(Math.PI - alpha) / 6 + 0.6, Math.cos(Math.PI - alpha) / 6 + 0.6);
            gl.glVertex3d(2.2, -3, 4.8);
            gl.glVertex3d(2.2, -1.5, 4.8);
            
            for (int i = 0; i <= segments; ++i)
            {
                gl.glColor3d(Math.cos(Math.PI - alpha) / 6 + 0.6, Math.cos(Math.PI - alpha) / 6 + 0.6, Math.cos(Math.PI - alpha) / 6 + 0.6);
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x, -3, y);
                gl.glVertex3d(x, -1.5, y);
                alpha += deltaAlpha;
            }
                
            Color5(gl);
            gl.glVertex3d(-2.2, -3, 3);
            gl.glVertex3d(-2.2, -1.5, 3);
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUAD_STRIP);
            radius = 2.2;
            alpha = 0; 
            segments = 36;
            deltaAlpha = Math.PI / segments;
            
            Color6(gl);
            gl.glVertex3d(2.2, 2, 3);
            gl.glVertex3d(2.2, 0.5, 3);
            gl.glColor3d(Math.cos(Math.PI - alpha) / 6 + 0.6, Math.cos(Math.PI - alpha) / 6 + 0.6, Math.cos(Math.PI - alpha) / 6 + 0.6);
            gl.glVertex3d(2.2, 2, 4.8);
            gl.glVertex3d(2.2, 0.5, 4.8);
            
            for (int i = 0; i <= segments; ++i)
            {
                gl.glColor3d(Math.cos(Math.PI - alpha) / 6 + 0.6, Math.cos(Math.PI - alpha) / 6 + 0.6, Math.cos(Math.PI - alpha) / 6 + 0.6);
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x, 2, y);
                gl.glVertex3d(x, 0.5, y);
                alpha += deltaAlpha;
            }
                
            Color5(gl);
            gl.glVertex3d(-2.2, 2, 3);
            gl.glVertex3d(-2.2, 0.5, 3);
        gl.glEnd();
        
        Color1(gl);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(2.2, -3, 3);
            gl.glVertex3d(-2.2, -3, 3);
            radius = 1;
            alpha = -Math.PI / 2;
            while (alpha < 0)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x, -3, y);
                alpha += deltaAlpha;
            }
            gl.glVertex3d(2.2, -3, 4.8);
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(-2.2, -3, 3);
            gl.glVertex3d(-2.2, -3, 4.8);
            radius = 1;
            alpha = -Math.PI;
            while (alpha < -Math.PI / 2)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x, -3, y);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUAD_STRIP);
            double radius1 = 1;
            double radius2 = 2.2;
            alpha = 0;
            while (alpha < Math.PI)
            {
                double x1 = radius1 * Math.cos(alpha);
                double y1 = radius1 * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x1, -3, y1);
                double x2 = radius2 * Math.cos(alpha);
                double y2 = radius2 * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x2, -3, y2);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        
        Color4(gl);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(2.2, -1.5, 3);
            gl.glVertex3d(-2.2, -1.5, 3);
            radius = 1;
            alpha = -Math.PI / 2;
            while (alpha < 0)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x, -1.5, y);
                alpha += deltaAlpha;
            }
            gl.glVertex3d(2.2, -1.5, 4.8);
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(-2.2, -1.5, 3);
            gl.glVertex3d(-2.2, -1.5, 4.8);
            radius = 1;
            alpha = -Math.PI;
            while (alpha < -Math.PI / 2)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x, -1.5, y);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUAD_STRIP);
            radius1 = 1;
            radius2 = 2.2;
            alpha = 0;
            while (alpha < Math.PI)
            {
                double x1 = radius1 * Math.cos(alpha);
                double y1 = radius1 * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x1, -1.5, y1);
                double x2 = radius2 * Math.cos(alpha);
                double y2 = radius2 * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x2, -1.5, y2);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        
        Color1(gl);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(2.2, 0.5, 3);
            gl.glVertex3d(-2.2, 0.5, 3);
            radius = 1;
            alpha = -Math.PI / 2;
            while (alpha < 0)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x, 0.5, y);
                alpha += deltaAlpha;
            }
            gl.glVertex3d(2.2, 0.5, 4.8);
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(-2.2, 0.5, 3);
            gl.glVertex3d(-2.2, 0.5, 4.8);
            radius = 1;
            alpha = -Math.PI;
            while (alpha < -Math.PI / 2)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x, 0.5, y);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUAD_STRIP);
            radius1 = 1;
            radius2 = 2.2;
            alpha = 0;
            while (alpha < Math.PI)
            {
                double x1 = radius1 * Math.cos(alpha);
                double y1 = radius1 * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x1, 0.5, y1);
                double x2 = radius2 * Math.cos(alpha);
                double y2 = radius2 * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x2, 0.5, y2);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        
        Color4(gl);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(2.2, 2, 3);
            gl.glVertex3d(-2.2, 2, 3);
            radius = 1;
            alpha = -Math.PI / 2;
            while (alpha < 0)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x, 2, y);
                alpha += deltaAlpha;
            }
            gl.glVertex3d(2.2, 2, 4.8);
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(-2.2, 2, 3);
            gl.glVertex3d(-2.2, 2, 4.8);
            radius = 1;
            alpha = -Math.PI;
            while (alpha < -Math.PI / 2)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x, 2, y);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUAD_STRIP);
            radius1 = 1;
            radius2 = 2.2;
            alpha = 0;
            while (alpha < Math.PI)
            {
                double x1 = radius1 * Math.cos(alpha);
                double y1 = radius1 * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x1, 2, y1);
                double x2 = radius2 * Math.cos(alpha);
                double y2 = radius2 * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x2, 2, y2);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUAD_STRIP);
            radius = 1;
            alpha = 0;
            while (alpha < 2 * Math.PI)
            {
                gl.glColor3d(Math.cos(alpha) / 12 + 0.68, Math.cos(alpha) / 12 + 0.68, Math.cos(alpha) / 12 + 0.68);
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x, -3, y);
                gl.glVertex3d(x, -1.5, y);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUAD_STRIP);
            radius = 1;
            alpha = 0;
            while (alpha < 2 * Math.PI)
            {
                gl.glColor3d(Math.cos(alpha) / 12 + 0.68, Math.cos(alpha) / 12 + 0.68, Math.cos(alpha) / 12 + 0.68);
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) + 4.8;
                gl.glVertex3d(x, 0.5, y);
                gl.glVertex3d(x, 2, y);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        gl.glDisable(GL.GL_DEPTH_TEST);
    }
    
    void hole1(GL gl)
    {
        Color2(gl);
        double x = 0; 
        double y = 0;
        double radius = 5;
        double alpha = 0; 
        int segments = 36;
        double deltaAlpha = Math.PI / segments;
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(-3.5, -4, 2);
            alpha = 2 * Math.PI;
            while (alpha > 0)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) - 0.5;
                if (x <= -3.5 && y <= -0.5)
                {
                    gl.glVertex3d(x, y, 2);
                }
                alpha -= deltaAlpha;
            }
            alpha = -Math.PI;
            radius = 0.8;
            while (alpha < -Math.PI / 2)
            {
                x = radius * Math.cos(alpha) - 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                gl.glVertex3d(x, y, 2);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(-3.5, 3, 2);
            alpha = 0;
            radius = 5;
            while (alpha < 2 * Math.PI)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) - 0.5;
                if (x <= -3.5 && y >= -0.5)
                {
                    gl.glVertex3d(x, y, 2);
                }
                alpha += deltaAlpha;
            }
            alpha = Math.PI;
            radius = 0.8;
            while (alpha > Math.PI / 2)
            {
                x = radius * Math.cos(alpha) - 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                if (true)
                {
                    gl.glVertex3d(x, y, 2);
                }
                alpha -= deltaAlpha;
            }
        gl.glEnd();
        
        //------------------------------------------------------
        
        Color3(gl);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(-3.5, -4, 0);
            radius = 5;
            alpha = 2 * Math.PI;
            while (alpha > 0)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) - 0.5;
                if (x <= -3.5 && y <= -0.5)
                {
                    gl.glVertex3d(x, y, 0);
                }
                alpha -= deltaAlpha;
            }
            alpha = -Math.PI;
            radius = 0.8;
            while (alpha < -Math.PI / 2)
            {
                x = radius * Math.cos(alpha) - 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                gl.glVertex3d(x, y, 0);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(-3.5, 3, 0);
            alpha = 0;
            radius = 5;
            while (alpha < 2 * Math.PI)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) - 0.5;
                if (x <= -3.5 && y >= -0.5)
                {
                    gl.glVertex3d(x, y, 0);
                }
                alpha += deltaAlpha;
            }
            alpha = Math.PI;
            radius = 0.8;
            while (alpha > Math.PI / 2)
            {
                x = radius * Math.cos(alpha) - 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                if (true)
                {
                    gl.glVertex3d(x, y, 0);
                }
                alpha -= deltaAlpha;
            }
        gl.glEnd();
        
        //-------------------------------------------------
        
        Color2(gl);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(-2.2, -3, 2);
            gl.glVertex3d(-3.5, -4, 2);
            alpha = -Math.PI / 2;
            radius = 0.8;
            while (alpha < 0)
            {
                x = radius * Math.cos(alpha) - 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                if (true)
                {
                    gl.glVertex3d(x, y, 2);
                }
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(-2.2, 2, 2);
            gl.glVertex3d(-3.5, 3, 2);
            alpha = Math.PI / 2;
            radius = 0.8;
            while (alpha > 0)
            {
                x = radius * Math.cos(alpha) - 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                if (true)
                {
                    gl.glVertex3d(x, y, 2);
                }
                alpha -= deltaAlpha;
            }
            gl.glVertex3d(-2.2, -3, 2);
        gl.glEnd();
        
        //------------------------------------------------------

        Color3(gl);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(-2.2, -3, 0);
            gl.glVertex3d(-3.5, -4, 0);
            alpha = -Math.PI / 2;
            radius = 0.8;
            while (alpha < 0)
            {
                x = radius * Math.cos(alpha) - 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                if (true)
                {
                    gl.glVertex3d(x, y, 0);
                }
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(-2.2, 2, 0);
            gl.glVertex3d(-3.5, 3, 0);
            alpha = Math.PI / 2;
            radius = 0.8;
            while (alpha > 0)
            {
                x = radius * Math.cos(alpha) - 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                if (true)
                {
                    gl.glVertex3d(x, y, 0);
                }
                alpha -= deltaAlpha;
            }
            gl.glVertex3d(-2.2, -3, 0);
        gl.glEnd();
        
        //--------------------------------------------------
        
        // Левое отверстие
        gl.glBegin(GL.GL_QUAD_STRIP);
            radius = 0.8;
            alpha = 0;
            while (alpha < 2 * Math.PI)
            {
                gl.glColor3d(Math.cos(alpha) / 12 + 0.66, Math.cos(alpha) / 12 + 0.66, Math.cos(alpha) / 12 + 0.66);
                x = radius * Math.cos(alpha) - 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                gl.glVertex3d(x, y, 0);
                gl.glVertex3d(x, y, 2);
                alpha += deltaAlpha;
            }
        gl.glEnd();
    }
    
    void hole2(GL gl)
    {
        Color2(gl);
        double x = 0; 
        double y = 0;
        double radius = 5;
        double alpha = 0; 
        int segments = 36;
        double deltaAlpha = Math.PI / segments;
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(3.5, -4, 2);
            alpha = 0;
            while (alpha < 2 * Math.PI)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) - 0.5;
                if (x >= 3.5 && y <= -0.5)
                {
                    gl.glVertex3d(x, y, 2);
                }
                alpha += deltaAlpha;
            }
            alpha = 0;
            radius = 0.8;
            while (alpha > -Math.PI / 2)
            {
                x = radius * Math.cos(alpha) + 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                if (true)
                {
                    gl.glVertex3d(x, y, 2);
                }
                alpha -= deltaAlpha;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(3.5, 3, 2);
            alpha = 2 * Math.PI;
            radius = 5;
            while (alpha > 0)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) - 0.5;
                if (x >= 3.5 && y >= -0.5)
                {
                    gl.glVertex3d(x, y, 2);
                }
                alpha -= deltaAlpha;
            }
            alpha = 0;
            radius = 0.8;
            while (alpha < Math.PI / 2)
            {
                x = radius * Math.cos(alpha) + 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                if (true)
                {
                    gl.glVertex3d(x, y, 2);
                }
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        //------------------------------------------------------
        
        Color3(gl);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(3.5, -4, 0);
            alpha = 0;
            radius = 5;
            while (alpha < 2 * Math.PI)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) - 0.5;
                if (x >= 3.5 && y <= -0.5)
                {
                    gl.glVertex3d(x, y, 0);
                }
                alpha += deltaAlpha;
            }
            alpha = 0;
            radius = 0.8;
            while (alpha > -Math.PI / 2)
            {
                x = radius * Math.cos(alpha) + 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                gl.glVertex3d(x, y, 0);
                alpha -= deltaAlpha;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(3.5, 3, 0);
            alpha = 2 * Math.PI;
            radius = 5;
            while (alpha > 0)
            {
                x = radius * Math.cos(alpha);
                y = radius * Math.sin(alpha) - 0.5;
                if (x >= 3.5 && y >= -0.5)
                {
                    gl.glVertex3d(x, y, 0);
                }
                alpha -= deltaAlpha;
            }
            alpha = 0;
            radius = 0.8;
            while (alpha < Math.PI / 2)
            {
                x = radius * Math.cos(alpha) + 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                gl.glVertex3d(x, y, 0);
                alpha += deltaAlpha;
            }
        gl.glEnd();
        
        //-------------------------------------------------
        
        Color2(gl);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(2.2, -3, 2);
            gl.glVertex3d(3.5, -4, 2);
            alpha = -Math.PI / 2;
            radius = 0.8;
            while (alpha > -Math.PI)
            {
                x = radius * Math.cos(alpha) + 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                if (true)
                {
                    gl.glVertex3d(x, y, 2);
                }
                alpha -= deltaAlpha;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(2.2, 2, 2);
            gl.glVertex3d(3.5, 3, 2);
            alpha = Math.PI / 2;
            radius = 0.8;
            while (alpha < Math.PI)
            {
                x = radius * Math.cos(alpha) + 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                if (true)
                {
                    gl.glVertex3d(x, y, 2);
                }
                alpha += deltaAlpha;
            }
            gl.glVertex3d(2.2, -3, 2);
        gl.glEnd();
        
        //------------------------------------------------------
        
        Color3(gl);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(2.2, -3, 0);
            gl.glVertex3d(3.5, -4, 0);
            alpha = -Math.PI / 2;
            radius = 0.8;
            while (alpha > -Math.PI)
            {
                x = radius * Math.cos(alpha) + 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                if (true)
                {
                    gl.glVertex3d(x, y, 0);
                }
                alpha -= deltaAlpha;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex3d(2.2, 2, 0);
            gl.glVertex3d(3.5, 3, 0);
            alpha = Math.PI / 2;
            radius = 0.8;
            while (alpha < Math.PI)
            {
                x = radius * Math.cos(alpha) + 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                if (true)
                {
                    gl.glVertex3d(x, y, 0);
                }
                alpha += deltaAlpha;
            }
            gl.glVertex3d(2.2, -3, 0);
        gl.glEnd();
        
        //--------------------------------------------------
        
        // Левое отверстие
        gl.glBegin(GL.GL_QUAD_STRIP);
            radius = 0.8;
            alpha = 0;
            while (alpha < 2 * Math.PI)
            {
                gl.glColor3d(Math.cos(alpha) / 12 + 0.66, Math.cos(alpha) / 12 + 0.66, Math.cos(alpha) / 12 + 0.66);
                x = radius * Math.cos(alpha) + 3.5;
                y = radius * Math.sin(alpha) - 0.5;
                gl.glVertex3d(x, y, 0);
                gl.glVertex3d(x, y, 2);
                alpha += deltaAlpha;
            }
        gl.glEnd();
    }
    
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        
        gl.glEnable(GL.GL_SCISSOR_TEST);
        gl.glColor3d(0.05, 0.05, 0.05);
        
        viewport1(gl);
        viewport2(gl);
        viewport3(gl);
        viewport4(gl);
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

