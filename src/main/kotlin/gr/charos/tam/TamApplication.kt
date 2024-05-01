package gr.charos.tam

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.shell.command.annotation.CommandScan

@SpringBootApplication
@CommandScan
class TamApplication

fun main(args: Array<String>) {
	runApplication<TamApplication>(*args)
}
