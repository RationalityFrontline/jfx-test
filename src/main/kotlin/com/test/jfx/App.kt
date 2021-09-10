package com.test.jfx

import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.stage.Stage
import java.lang.ref.WeakReference

fun main() {
    Application.launch(App::class.java)
}

object AppContext {
    private val menuItems: MutableList<WeakReference<MenuItem>> = mutableListOf()

    private fun clearNullMenuItems() {
        menuItems.removeAll { it.get() == null }
    }

    fun launchNewStage(stage: Stage) {
        stage.run {
            title = "JFX-Test"
            scene = Scene(StackPane().apply {
                children.addAll(
                    VBox(
                        Label("ControlAcceleratorSupport Memory Leak Test"),
                        Button("Call GC and Print MenuItems").apply {
                            setOnAction {
                                System.gc()
                                clearNullMenuItems()
                                println(menuItems.joinToString { it.get()!!.toString() })
                            }
                        }
                    ).apply {
                        spacing = 25.0
                        alignment = Pos.CENTER
                    },
                    MenuBar(Menu("MENU").apply {
                        val menuItem = MenuItem("Restart Stage").apply {
                            setOnAction {
                                launchNewStage(Stage())
                                stage.close()
                            }
                        }
                        menuItems.add(WeakReference(menuItem))
                        items.add(menuItem)
                    }).apply { alignment = Pos.TOP_LEFT },
                )
            })
            minWidth = 400.0
            minHeight = 300.0
            show()
        }
    }
}

class App : Application() {
    override fun start(primaryStage: Stage) {
        AppContext.launchNewStage(primaryStage)
    }
}