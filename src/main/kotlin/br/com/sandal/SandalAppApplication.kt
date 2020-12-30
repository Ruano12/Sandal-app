package br.com.sandal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing(modifyOnCreate = true)
class SandalAppApplication

fun main(args: Array<String>) {
	runApplication<SandalAppApplication>(*args)
}
