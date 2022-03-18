/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Timer {
    double lastLoopTime=0;
    float timeCount;
    int fps;
    int fpsCount;
    int ups;
    int upsCount;
    
    
    
//    public double getTime() {
//        return glfwGetTime();
//    }
    
    public double getTime() {
        return System.nanoTime() / 1000000000.0;
    }
    
    public void init() {
        lastLoopTime = getTime();
    }
    
    public float getDelta() {
        double time = getTime();
        float delta = (float) (time - lastLoopTime);
        lastLoopTime = time;
        timeCount += delta;
        update();
        return delta;
    }
    
    public void updateFPS() {
        fpsCount++;
    }

    public void updateUPS() {
        upsCount++;
    }
    
    public void update() {
        if (timeCount > 1f) {
            fps = fpsCount;
            fpsCount = 0;

            ups = upsCount;
            upsCount = 0;

            timeCount -= 1f;
        }
    }
    
    public int getFPS() {
        return fps > 0 ? fps : fpsCount;
    }
    
    public int getUPS() {
        return ups > 0 ? ups : upsCount;
    }
}
