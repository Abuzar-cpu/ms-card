package az.vtb.mscard.annotation

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

@Retention(RUNTIME)
@Target(CLASS)
annotation class Log
