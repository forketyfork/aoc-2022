package year2024

import utils.*
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Day14 {

    companion object {
        val REGEX = "p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)".toRegex()
    }

    data class Robot(var pos: Point2D, val speed: Point2D, val width: Int, val height: Int) {
        fun move() {
            pos = Point2D((width + pos.x + speed.x) % width, (height + pos.y + speed.y) % height)
        }
    }

    fun part1(input: String): Long {
        val (width, height, robots) = parse(input)

        repeat(100) {
            robots.forEach { it.move() }
        }

        val q1 = robots.count { robot ->
            robot.pos.x < width / 2 && robot.pos.y < height / 2
        }

        val q2 = robots.count { robot ->
            robot.pos.x > width / 2 && robot.pos.y < height / 2
        }

        val q3 = robots.count { robot ->
            robot.pos.x < width / 2 && robot.pos.y > height / 2
        }

        val q4 = robots.count { robot ->
            robot.pos.x > width / 2 && robot.pos.y > height / 2
        }

        return q1.toLong() * q2 * q3 * q4
    }

    fun part2(input: String): Long {
        val (width, height, robots) = parse(input)

        val limit = robots.size / 12L

        for (iter in 1L..(width * height)) {

            val grid = CharGrid((" ".repeat(width) + "\n").repeat(height))

            robots.forEach {
                it.move()
                grid[it.pos] = '*'
            }

            var count = 0L
            for (y in (0..<height)) {
                for (x in 0..<width / 2) {
                    if (grid[Point2D(x, y)] == '*' && grid[Point2D(width - 1 - x, y)] == '*') {
                        count++
                    }
                }
            }
            if ((iter - 38) % 101 == 0L) {
                val size = Dimension(width, height)
                val img = BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB)
                for (x in 0 until size.width){
                    for (y in 0 until size.height) {
                        img.setRGB(x, y, if (grid[Point2D(x, y)] == '*') 0x00ff00 else 0xff0000)
                    }
                }
                ImageIO.write(img, "BMP", File("/Users/sergei.petunin/Downloads/test/$iter.bmp"))
            }
        }
        return 0L
    }

    fun parse(input: String): Triple<Int, Int, List<Robot>> {
        val lines = input.lines()
        val (width, height) = lines.first().split(',').map { it.toInt() }
        val robots = lines.drop(1).map {
            val (x, y, vx, vy) = REGEX.find(it)!!.groupValues.drop(1).map { it.toInt() }
            Robot(Point2D(x, y), Point2D(vx, vy), width, height)
        }
        return Triple(width, height, robots)
    }

}
