package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.*;


/**
 * GLRenderer.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class GLRenderer implements GLEventListener {
    double ex = 1,ey = 1,ez = 1,px = 0,py = 0,pz = 0.9,rad = 7,rot = 0,speed = 0.1,drot=0.1;

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU(); 
        
        
        gl.glLoadIdentity();
    }
    public void KeyPress(int code) { 
        System.out.println(code);
        switch (code){
            case 90:
                ez -= speed;
                break;
            case 65:
                ez += speed;
                break;
            case 38:
                ex+=speed*Math.cos(rot);
                ey+=speed*Math.sin(rot);
                break;
            case 40:
                ex-=speed*Math.cos(rot);
                ey-=speed*Math.sin(rot);
                break;
            case 37:
                rot+=drot;
                break;
            case 39:
                rot-=drot;
                break;

        }
        pz=ez-0.1;
        px=ex+rad*Math.cos(rot);
        py=ey+rad*Math.sin(rot);
    }
    public void display(GLAutoDrawable drawable) {  
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glLoadIdentity();
        GLU glu = new GLU();
        glu.gluPerspective(60, 1, 0.1, 200);
        glu.gluLookAt(ex, ey, ez, px, py, pz, 0, 0, 1);
        // Reset the current matrix to the "identity"
        
        //skybox
        int n = 32; //cylinder
        double r = 100, alpha = 0, da = 2 * Math.PI/n; //cylinder
        gl.glBegin(GL.GL_QUAD_STRIP);
            while(alpha <= Math.PI*2+da){
                gl.glColor3d(0,1,1);
                gl.glVertex3d(r*Math.cos(alpha),r*Math.sin(alpha),-0.1);
                gl.glColor3d(0,0,1);
                gl.glVertex3d(r*Math.cos(alpha),r*Math.sin(alpha),100);
                alpha+=da;
            }
        gl.glEnd();
        
        GLUquadric quad = glu.gluNewQuadric();
        gl.glColor3d(1,1,0);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
        glu.gluSphere(quad, 5, 36, 3);
        gl.glColor3d(0,0,0);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
        glu.gluSphere(quad, 5, 36, 3);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
        gl.glColor3d(0, 0.5, 0);
        gl.glBegin(GL.GL_QUADS);
            gl.glVertex3d(-100, -100, 0);            
            gl.glVertex3d(100, -100, 0);
            gl.glVertex3d(100, 100, 0);
            gl.glVertex3d(-100, 100, 0);
        gl.glEnd();

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

