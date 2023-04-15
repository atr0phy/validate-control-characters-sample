package com.example.validatecontrolcharacterssample.constraints

import jakarta.validation.Validation
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

private class RejectControlCharactersOtherThanLineBreakTest {

    private data class TestData(
        @field:RejectControlCharactersOtherThanLineBreak
        val value: String
    )

    @Test
    fun `Allow empty string`() {
        val sut = TestData("")
        val validator = Validation.buildDefaultValidatorFactory().validator
        val violations = validator.validate(sut)
        assertTrue(violations.isEmpty())
    }

    @ParameterizedTest
    @ValueSource(strings=[
        "\n",
        "\r",
        "\r\n",
    ])
    fun `Allow linebreak`(value: String) {
        val sut = TestData(value)
        val validator = Validation.buildDefaultValidatorFactory().validator
        val violations = validator.validate(sut)
        assertTrue(violations.isEmpty())
    }

    @ParameterizedTest
    @ValueSource(strings=[
        "\nleft",
        "cen\nter",
        "right\n",
    ])
    fun `Allow string contain linebreak`(value: String) {
        val sut = TestData(value)
        val validator = Validation.buildDefaultValidatorFactory().validator
        val violations = validator.validate(sut)
        assertTrue(violations.isEmpty())
    }

    @ParameterizedTest
    @ValueSource(strings=[
        "\u0000",
        "\u0001",
        "\u0002",
        "\u0003",
        "\u0004",
        "\u0005",
        "\u0006",
        "\u0007",
        "\u0008",
        "\u0009",
        "\u000B",
        "\u000C",
        "\u000E",
        "\u000F",
        "\u0010",
        "\u0011",
        "\u0012",
        "\u0013",
        "\u0014",
        "\u0015",
        "\u0016",
        "\u0017",
        "\u0018",
        "\u0019",
        "\u001A",
        "\u001B",
        "\u001C",
        "\u001D",
        "\u001E",
        "\u001F",
        "\u007F",
    ])
    fun `Reject control characters other than linebreak`(value: String) {
        val sut = TestData(value)
        val validator = Validation.buildDefaultValidatorFactory().validator
        val violations = validator.validate(sut)
        assertTrue(violations.isNotEmpty())
    }

    @ParameterizedTest
    @ValueSource(strings=[
        "\u0000left",
        "cen\u0000ter",
        "right\u0000",
    ])
    fun `Reject string contain control characters other than linebreak`(value: String) {
        val sut = TestData(value)
        val validator = Validation.buildDefaultValidatorFactory().validator
        val violations = validator.validate(sut)
        assertTrue(violations.isNotEmpty())
    }
}
