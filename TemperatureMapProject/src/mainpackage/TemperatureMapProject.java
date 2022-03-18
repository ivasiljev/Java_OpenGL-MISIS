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
 * TemperatureMapProject.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class TemperatureMapProject implements GLEventListener {
    final int MAX_TEMPERATURE = 80;
    final int MIN_TEMPERATURE = -80;
        
    public static void main(String[] args) {
        Frame frame = new Frame("Temperature Map");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new TemperatureMapProject());
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
    
    class HSV
    {
        public double H;
        public double S;
        public double V;
        
        public HSV(double h, double s, double v)
        {
            H = h;
            S = s;
            V = v;
        }
    }
    
    class RGB
    {
        public double R;
        public double G;
        public double B;
        
        public RGB(double r, double g, double b)
        {
            R = r;
            G = g;
            B = b;
        }
    }    
    
    public RGB HSVtoRGB(HSV hsv)
    {
        RGB rgb = new RGB(0, 0, 0);
        
        if (hsv.H == 360)
            hsv.H = 0;
        else
            hsv.H = hsv.H / 60;

        int i = (int)Math.floor(hsv.H);
        double d = hsv.H - i;
        
        double p = hsv.V * (1.0 - hsv.S);
        double q = hsv.V * (1.0 - (hsv.S * d));
        double t = hsv.V * (1.0 - (hsv.S * (1.0 - d)));

        switch (i)
        {
                case 0:
                        rgb.R = hsv.V;
                        rgb.G = t;
                        rgb.B = p;
                        break;

                case 1:
                        rgb.R = q;
                        rgb.G = hsv.V;
                        rgb.B = p;
                        break;

                case 2:
                        rgb.R = p;
                        rgb.G = hsv.V;
                        rgb.B = t;
                        break;

                case 3:
                        rgb.R = p;
                        rgb.G = q;
                        rgb.B = hsv.V;
                        break;

                case 4:
                        rgb.R = t;
                        rgb.G = p;
                        rgb.B = hsv.V;
                        break;

                default:
                        rgb.R = hsv.V;
                        rgb.G = p;
                        rgb.B = q;
                        break;
        }
        
        return rgb;
    }
    
    void setColor(GL gl, double koef, int arrayElement)
    {
        double currentColor = koef * Math.abs(arrayElement - MAX_TEMPERATURE);
        RGB rgb = HSVtoRGB(new HSV(currentColor, 1, 1));
        gl.glColor3d(rgb.R, rgb.G, rgb.B);
    }
    
    public void display(GLAutoDrawable drawable)
    {
        GL gl = drawable.getGL();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        int n = 8;
        int m = 5;
        int temperatureArray[] = 
        {
            30, 40, 70, 50, 70, 50, 50, 50,
            20, 40, 50, 40, 60, 40, 20, 10,
           -10, 00, 00, 10, 20, 30, 10, 00,
           -20,-50,-60,-50,-70,-20,-10, 00,
           -30,-60,-70,-80,-75,-50,-40,-20
        };
        
        double spectrumLimit = 240; // Color range: [0; spectrumLimit)
        
        double temperatureStep = spectrumLimit / (MAX_TEMPERATURE - MIN_TEMPERATURE);
        
        double xStep = 2.0 / (n - 1);
        double yStep = 2.0 / (m - 1);
        double y = 1;
        for (int i = 0; i < m - 1; ++i)
        {
            gl.glBegin(GL.GL_QUAD_STRIP);
                double x = -1;
                for (int j = 0; j < n; ++j)
                {
                    setColor(gl, temperatureStep, temperatureArray[i*n + j]);
                    gl.glVertex2d(x, y);
                    setColor(gl, temperatureStep, temperatureArray[i*n+n + j]);
                    gl.glVertex2d(x, y - yStep);
                    x += xStep;
                }
                y -= yStep;
            gl.glEnd();
        }
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

