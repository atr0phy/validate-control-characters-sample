package com.example.validatecontrolcharacterssample

import com.example.validatecontrolcharacterssample.constraints.RejectControlCharactersOtherThanLineBreak
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
class RootController {
    data class IndexRequest(
        @field:RejectControlCharactersOtherThanLineBreak val value: String?
    )

    @PostMapping("/")
    fun index(
       @Valid @RequestBody requestBody: IndexRequest
    ): String {
        return "ok"
    }
}
