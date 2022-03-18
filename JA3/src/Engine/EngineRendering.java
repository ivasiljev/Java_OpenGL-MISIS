/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
 
import java.nio.IntBuffer;
import java.nio.FloatBuffer;
 
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

//import org.lwjgl.glfw.GLFW.*;
//import org.lwjgl.opengl.*;
//import org.lwjgl.system.MemoryStack;

public class EngineRendering {
    int width = 640;
    int height = 480;
    String title = "My program";
    
    int vao;
    
    public EngineRendering() {
        init();
    }
    
    public void init() {
        // Узнать подробнее про строки ниже
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        long window = glfwCreateWindow(width, height, title, 0, 0);
        
        vao = glGenVertexArrays();
        glBindVertexArray(vao);
        
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer vertices = stack.mallocFloat(3 * 6);
            vertices.put(-0.6f).put(-0.4f).put(0f).put(1f).put(0f).put(0f);
            vertices.put(0.6f).put(-0.4f).put(0f).put(0f).put(1f).put(0f);
            vertices.put(0f).put(0.6f).put(0f).put(0f).put(0f).put(1f);
            vertices.flip();

            int vbo = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vbo);
            glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        }
    }
}

//package Engine;
//
//import org.lwjgl.Version;
//import org.lwjgl.glfw.GLFWErrorCallback;
//import org.lwjgl.glfw.GLFWVidMode;
//import org.lwjgl.opengl.GL;
//import org.lwjgl.system.MemoryStack;
// 
//import java.nio.IntBuffer;
// 
//import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
//import static org.lwjgl.glfw.GLFW.*;
//import static org.lwjgl.opengl.GL11.*;
//import static org.lwjgl.opengl.GL15.*;
//import static org.lwjgl.opengl.GL20.*;
//import static org.lwjgl.opengl.GL30.*;
//import static org.lwjgl.system.MemoryStack.stackPush;
//import static org.lwjgl.system.MemoryUtil.NULL;
// 
///**
// * Created by RDM on 21/06/2017.
// */
//public class EngineRendering {
//    // The window handle
//    private long window;
//    // the vertex array
//    private int vao;
//    // a vertex buffer object
//    private int vbo;
//    // a vertex shader
//    private int vertexShader;
//    // a fragment shader
//    private int fragmentShader;
//    // a shader program
//    private int shaderProgram;
// 
//    private void createVBO() {
// 
//        try (MemoryStack stack = MemoryStack.stackPush()) {
//            float[] vertexData = {-.8f, -.8f, 0, 1, 1, 0, 0, 1,
//                    0, .8f, 0, 1, 0, 1, 0, 1,
//                    .8f, -.8f, 0, 1, 0, 0, 1, 1};
//            vbo = glGenBuffers();
//            glBindBuffer(GL_ARRAY_BUFFER, vbo);
////            glBufferData(GL_ARRAY_BUFFER, FloatBuffer.wrap(vertexData), GL_STATIC_DRAW);
//            glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
//            int positionLocation = glGetAttribLocation(shaderProgram, "position");
//            int colorLocation = glGetAttribLocation(shaderProgram, "color");
//            int floatSize = 4;
//            glVertexAttribPointer(positionLocation, 4, GL_FLOAT, false, 8 * floatSize, 0);
//            glVertexAttribPointer(colorLocation, 4, GL_FLOAT, false, 8 * floatSize, 4 * floatSize);
//            glEnableVertexAttribArray(positionLocation);
//            glEnableVertexAttribArray(colorLocation);
// 
//            int e = glGetError();
//            if (e != GL_NO_ERROR) {
//                throw new RuntimeException("Error creating VBO");
//            }
// 
//            System.out.println("Initialized VBO (pos=" + positionLocation + ", col=" + colorLocation + ")");
//        }
//    }
// 
//    private void createShaders() {
//        Shader vertexShader = Shader.loadShader(GL_VERTEX_SHADER, "triangle.vs");
//        this.vertexShader = vertexShader.getID();
//        System.out.println("Created vertex shader");
//        Shader fragmentShader = Shader.loadShader(GL_FRAGMENT_SHADER, "triangle.fs");
//        this.fragmentShader = fragmentShader.getID();
//        System.out.println("Created fragment shader");
// 
//        shaderProgram = glCreateProgram();
//        System.out.println("Created shader program");
//        glAttachShader(shaderProgram, this.vertexShader);
//        glAttachShader(shaderProgram, this.fragmentShader);
//        glLinkProgram(shaderProgram);
//        System.out.println("Linked shader program");
//        if (glGetProgrami(shaderProgram, GL_LINK_STATUS) != GL_TRUE) {
//            throw new RuntimeException(glGetProgramInfoLog(shaderProgram));
//        }
//        glUseProgram(shaderProgram);
//        System.out.println("Using shader program");
//    }
// 
//    private void destroyShaders() {
//        glUseProgram(0);
//        glDetachShader(shaderProgram, vertexShader);
//        glDetachShader(shaderProgram, fragmentShader);
//        glDeleteShader(vertexShader);
//        glDeleteProgram(shaderProgram);
//    }
// 
//    private void destroyVBO() {
//        // position
//        glDisableVertexAttribArray(0);
//        // color
//        glDisableVertexAttribArray(1);
// 
//        glBindBuffer(GL_ARRAY_BUFFER, 0);
//        glDeleteBuffers(vbo);
// 
//        glBindVertexArray(0);
//        glDeleteVertexArrays(vao);
//    }
//}
