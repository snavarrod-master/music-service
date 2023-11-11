package com.usj.musicquizz.model.dto

data class SongDTO(var id: Long = 0, var name: String = "", var author: String = "", var file : String = "") {
    init {
        name = name.trim()
        author = author.trim()
        file = file.trim()
    }
}