package gr.charos.tam

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TamApplication

fun main(args: Array<String>) {
	runApplication<TamApplication>(*args)
}
