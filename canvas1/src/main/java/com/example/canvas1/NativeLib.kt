package com.example.canvas1

class NativeLib {

    /**
     * A native method that is implemented by the 'canvas1' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'canvas1' library on application startup.
        init {
            System.loadLibrary("canvas1")
        }
    }
}