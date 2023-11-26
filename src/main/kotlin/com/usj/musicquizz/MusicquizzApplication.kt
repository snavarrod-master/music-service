package com.usj.musicquizz

import com.usj.musicquizz.model.Song
import com.usj.musicquizz.model.dto.SongDTO
import com.usj.musicquizz.repository.SongRepository
import com.usj.musicquizz.service.SongServiceApi
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MusicquizzApplication {

    @Autowired
    lateinit var service: SongServiceApi

    @PostConstruct
    fun init() {
        val songs = listOf(
            SongDTO(1, "Tired", "Adele", "/songs/download/Tired.mp3"),
            SongDTO(2, "This is the life", "Amy Macdonald", "/songs/download/This_Is_The_Life.mp3"),
            SongDTO(3, "Harlem shake", "Baauer", "/songs/download/Harlem_Shake.mp3"),
            SongDTO(4, "Space Oddity", "David Bowie", "/songs/download/Space_Oddity.mp3"),
            SongDTO(5, "Sweet dreams", "Marilyn Manson", "/songs/download/Sweet_Dreams.mp3"),
            SongDTO(6, "Love and Marriage", "Frank Sinatra", "/songs/download/Love_And_Marriage.mp3"),
            SongDTO(7, "Royals", "Lorde", "/songs/download/Royals.mp3"),
            SongDTO(8, "Telacuti", "Lo blanquito", "/songs/download/Telacuti.mp3"),
            SongDTO(9, "Roxanne", "Police", "/songs/download/Roxanne.mp3"),
            SongDTO(10, "Norwegian wood", "The Beatles", "/songs/download/Norwegian_Wood.mp3")
        )
        songs.forEach { service.save(it) }
    }
}

fun main(args: Array<String>) {
    runApplication<MusicquizzApplication>(*args)
}
