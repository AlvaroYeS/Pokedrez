import korlibs.time.*
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.korge.tween.*
import korlibs.korge.view.*
import korlibs.image.color.*
import korlibs.image.format.*
import korlibs.io.file.std.*
import korlibs.math.geom.*
import korlibs.math.interpolation.*

suspend fun main() = Korge(windowSize = Size(480, 307), backgroundColor = Colors["#2b2b2b"]) {
    val sceneContainer = sceneContainer()

    sceneContainer.changeTo { MyScene() }
}

class MyScene : Scene() {
    override suspend fun SContainer.sceneMain() {
        val image = image(resourcesVfs["tablero.png"].readBitmap()) {
            scale(1, 1)
        }

        while (true) {
            image.tween(image::rotation[(0).degrees], time = 99999.seconds, easing = Easing.SMOOTH)
        }
    }
}


