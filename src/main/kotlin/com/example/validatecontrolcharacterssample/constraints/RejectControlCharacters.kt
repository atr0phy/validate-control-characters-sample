package com.example.validatecontrolcharacterssample.constraints

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
@Pattern(regexp = "^$|[^\\p{Cntrl}]+")
annotation class RejectControlCharacters(
    val message: String = "{com.example.validatecontrolcharacterssample.constraints.RejectControlCharacters.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
