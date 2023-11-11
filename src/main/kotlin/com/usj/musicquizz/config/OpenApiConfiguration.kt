package com.usj.musicquizz.config

import org.springdoc.core.models.GroupedOpenApi

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OpenApiConfiguration {

    @Bean
    fun api(): GroupedOpenApi {
        return GroupedOpenApi.builder().group("MusicQuizz")
            .packagesToScan("com.usj.musicquizz")
            .build()
    }
}