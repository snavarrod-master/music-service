package com.usj.musicquizz.utils

import com.usj.musicquizz.model.Song
import com.usj.musicquizz.model.dto.SongDTO


object DataConverter {

    fun songToDTO(song: Song): SongDTO {
        return SongDTO(
            song.id!!,
            song.name!!,
            song.author!!,
            song.file!!
        )
    }

    fun songFromDTO(songDTO: SongDTO): Song {
        return Song(
            songDTO.id,
            songDTO.name,
            songDTO.author,
            songDTO.file
        )
    }
}