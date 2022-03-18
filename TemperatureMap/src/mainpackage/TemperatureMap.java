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
 * TemperatureMap.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class TemperatureMap implements GLEventListener {
    boolean grayscale = false;
    boolean smoothColors = true;
    boolean grid = true;
    
    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new TemperatureMap());
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
        animator.stop();
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
        if (grayscale)
        {
            double currentColor = koef * arrayElement;
            gl.glColor3d(currentColor, currentColor, currentColor);
        }
        else
        {
            double currentColor = koef * arrayElement;
            RGB rgb = HSVtoRGB(new HSV(currentColor, 1, 1));
            gl.glColor3d(rgb.R, rgb.G, rgb.B);
        }
    }
    
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        int n;
        int arrayInput[] = { 1, 5, 7, 9, 11, 14, 12, 4, 2, 3, 16, 1 };
        n = arrayInput.length;
        
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) if (arrayInput[i] > maxValue) maxValue = arrayInput[i];
        
        double koef = 0;
        if (!grayscale)
            koef = 359.0 / maxValue;
        else
            koef = 1.0 / maxValue;
        
        double width = 1.8;
        double dx = width / n; 
        gl.glBegin(GL.GL_QUADS);              
            for (int i = 0; i < n; ++i)
            {
                double x = width * i / n - 1 + (2 - width) / 2; // "- 1" смещаем х в левые четверти экрана, 
                                                                // "+ (2 - width) / 2" - отступ от левой границы экрана, так чтобы отступ справа был такой же
                
                setColor(gl, koef, arrayInput[i]);
                
                gl.glVertex2d(x, 0);
                gl.glVertex2d(x, dx);
                
                if (smoothColors)
                {
                    if (i != n - 1) // При сглаживании цветов должно отрисовываться на 1 сегмент меньше
                    {
                        setColor(gl, koef, arrayInput[i + 1]);
                        gl.glVertex2d(x + dx, dx);
                        gl.glVertex2d(x + dx, 0);
                    }
                }
                else
                {
                    gl.glVertex2d(x + dx, dx);
                    gl.glVertex2d(x + dx, 0);
                }
            }
        gl.glEnd();
        
        // Границы палитры
        gl.glColor3d(1, 1, 1);
        gl.glBegin(GL.GL_LINES);                
            // граница слева
            gl.glVertex2d(- 1 + (2.0 - width) / 2, 0);
            gl.glVertex2d(- 1 + (2.0 - width) / 2, dx);
            
            // граница справа
            gl.glVertex2d(width - 1 + (2.0 - width) / 2 - (smoothColors ? dx : 0), dx);
            gl.glVertex2d(width - 1 + (2.0 - width) / 2 - (smoothColors ? dx : 0), 0);
            for (int i = 0; i < n - (smoothColors ? 1 : 0); ++i) // Если сглаживаем цвета, то должно быть на 1 сегмент меньше
            {
                double x = width * i / n - 1 + (2.0 - width) / 2; // -1 смещаем х в левые четверти экрана, 
                                                                // (2 - width) / 2 - отступ от левой границы экрана, так чтобы отступ справа был такой же
                        
                if (grid)   // разделения между ячейками массива
                {
                    gl.glVertex2d(x, 0);
                    gl.glVertex2d(x, dx);

                    gl.glVertex2d(x + dx, dx);
                    gl.glVertex2d(x + dx, 0);
                }
                
                // Нижняя граница
                gl.glVertex2d(x, 0);
                gl.glVertex2d(x + dx, 0);
                
                // Верхняя граница
                gl.glVertex2d(x, dx);
                gl.glVertex2d(x + dx, dx);
            }
        gl.glEnd();
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

