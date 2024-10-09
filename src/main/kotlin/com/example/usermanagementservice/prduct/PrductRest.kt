package com.example.usermanagementservice.prduct


import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pr")
class PrductRest {

    @PostMapping("/getDetails")
    fun register(): String {
        return "Api is Worked"
    }

}