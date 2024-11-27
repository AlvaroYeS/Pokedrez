import korlibs.time.*
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.korge.tween.*
import korlibs.korge.view.*
import korlibs.image.color.*
import korlibs.image.format.*
import korlibs.io.file.*
import korlibs.io.file.std.*
import korlibs.math.*
import korlibs.math.geom.*
import korlibs.math.interpolation.*
import kotlin.random.*

suspend fun main() = Korge(windowSize = Size(1024, 1024), backgroundColor = Colors["#2b2b2b"]) {
	val sceneContainer = sceneContainer()

	sceneContainer.changeTo { MyScene() }
}

class MyScene : PixelatedScene(128 * 12, 128 * 12, sceneSmoothing = true) {
	override suspend fun SContainer.sceneMain() {
        image(resourcesVfs["bgs/BattleBgForest.jpg"].readBitmap()).xy(0, 0).scale(3)
        val bgs = listOf(resourcesVfs["bgs/square brown dark.png"].readBitmap(), resourcesVfs["bgs/square brown light.png"].readBitmap())

        //Tablero
        for (x in 0 until 8) {
            for (y in 0 until 2) {
                image(bgs[(x + y).isOdd.toInt()]).xy(128 * x + 128 - (64 * y), 128 * y + (128 * 4))
            }
        }

        //Mano
        for (x in 0 until 8) {
            val image = resourcesVfs["bgs/square gray light.png"].readBitmap()
            image(image).xy(128 * x + 192, (7 * 128) + 192).anchor(0.50, 0.50)
        }

        //Fila de oponente
        for (n in 0 until 8) {
            val num = Random.nextInt(1, 151)
            val string = "pokemon/front/$num.png"
            val image = resourcesVfs[string].readBitmap()
            image(image).xy(128 * n + 192, 0 + 192 + (3 * 128)).scale(1.5).anchor(0.50, 0.50)
        }

        //Fila de usuario
        for (n in 0 until 8) {
            val num = Random.nextInt(1, 151)
            val string = "pokemon/back/$num.png"
            val image = resourcesVfs[string].readBitmap()
            image(image).xy(128 * n + 128, 0 + 192 + (4 * 128)).scale(1.5).anchor(0.50, 0.50)
        }

        //Fondo Tienda
        for (x in 0 until 8) {
            val image = resourcesVfs["bgs/square gray light.png"].readBitmap()
            image(image).xy(128 * x + 192, (9 * 128) + 192).anchor(0.50, 0.50)
        }

        //Tienda
        for (n in 0 until 8) {
            val num = Random.nextInt(1, 151)
            val string = "pokemon/icons/$num.png"
            val image = resourcesVfs[string].readBitmap()
            image(image).xy(128 * n + 192, (9 * 128) + 192).anchor(0.50, 0.50)
        }

        //Fondo Objetos
        for (x in 0 until 8) {
            val image = resourcesVfs["bgs/square gray dark.png"].readBitmap()
            image(image).xy((9 * 128) + 192, 128 * x + 192 + (2 * 128)).anchor(0.50, 0.50)
        }

        //Objetos
        for (n in 0 until 8) {
            val num = Random.nextInt(1, 151)
            val string = "pokemon/icons/$num.png"
            val image = resourcesVfs[string].readBitmap()
            image(image).xy((9 * 128) + 192, 128 * n + 192 + (2 * 128)).anchor(0.50, 0.50)
        }
	}
}
