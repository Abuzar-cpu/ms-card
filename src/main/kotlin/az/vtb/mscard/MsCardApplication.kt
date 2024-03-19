package az.vtb.mscard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class MsCardApplication

fun main(args: Array<String>) {
    runApplication<MsCardApplication>(*args)
}
