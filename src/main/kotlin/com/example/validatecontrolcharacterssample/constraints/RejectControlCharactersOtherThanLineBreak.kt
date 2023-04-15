package com.example.validatecontrolcharacterssample.constraints

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
@Pattern(regexp = "^$|[^[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]]+")
annotation class RejectControlCharactersOtherThanLineBreak(
    val message: String = "{com.example.validatecontrolcharacterssample.constraints.RejectControlCharactersOtherThanLineBreak.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
