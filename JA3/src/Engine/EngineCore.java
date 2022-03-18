/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

public class EngineCore {
    boolean running  = true;
    long targetFPS = 60L;
    Timer timer;
    EngineRendering ER;
    
    void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    
    public EngineCore() {
        timer = new Timer();
        ER = new EngineRendering();
    }
    
    public void start()
    {
        init();
        gameLoop();
    }
    
    public void init() {
        timer.init();
    }
    
    public void gameLoop() {
        long targetTime = 1000L / targetFPS;
        ER.init();
        while (running) {
            long startTime = (long)(timer.getTime() * 1000);
            float delta = timer.getDelta();
            input();
            update(delta);
            timer.updateUPS();
            render();
            timer.updateFPS();

            //window.update();
            long endTime = (long)(timer.getTime() * 1000);
            sleep(startTime + targetTime - endTime);
        }
    }
    
    public void input() {}
    
    public void update(float delta) {
        
        System.out.println(timer.fps);
    }
    
    public void render() {}
}
