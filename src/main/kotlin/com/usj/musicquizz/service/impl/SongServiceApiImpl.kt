package com.usj.musicquizz.service.impl

import com.usj.musicquizz.model.dto.SongDTO
import com.usj.musicquizz.repository.SongRepository
import com.usj.musicquizz.service.SongServiceApi
import com.usj.musicquizz.utils.DataConverter
import com.usj.musicquizz.utils.OffsetBasedPageRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SongServiceApiImpl : SongServiceApi {

    @Autowired
    lateinit var songRepository: SongRepository

    override fun list(limit: Int?, offset: Long?): List<SongDTO> {
        if (limit != null && offset != null) {
            val pageable = OffsetBasedPageRequest(
                offset,
                limit
            )
            return songRepository.findAll(pageable).toList().map { DataConverter.songToDTO(it) }
        }
        return songRepository.findAll().map { DataConverter.songToDTO(it) }
    }

    override fun find(id: Long): SongDTO? {
        return DataConverter.songToDTO(songRepository.findById(id).get())
    }

    override fun delete(id: Long): SongDTO {
        val song = find(id)
        if (song != null) {
            songRepository.deleteById(id)
            return song
        } else {
            throw Exception("Song not found")
        }
    }

    override fun save(element: SongDTO): SongDTO {
        val item = DataConverter.songFromDTO(element)
        return DataConverter.songToDTO(songRepository.save(item))
    }

    override fun edit(element: SongDTO): Int {
        val item = DataConverter.songFromDTO(element)
        val song = item.id?.let { songRepository.findById(it).get() }
        song?.name = item.name
        song?.author = item.author
        song?.file = item.file
        songRepository.save(song!!)
        return 1
    }
}