package com.test.jfx

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.stage.Stage

fun main() {
    Application.launch(App::class.java)
}

class App : Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.run {
            title = "JFX-Test"
            scene = Scene(StackPane(Label("TEST")))
            minWidth = 400.0
            minHeight = 300.0
            show()
        }
    }
}