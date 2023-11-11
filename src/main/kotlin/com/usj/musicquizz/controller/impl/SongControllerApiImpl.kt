package com.usj.musicquizz.controller.impl

import com.usj.musicquizz.controller.SongControllerApi
import com.usj.musicquizz.model.dto.SongDTO
import com.usj.musicquizz.service.SongServiceApi
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.FileCopyUtils
import org.springframework.web.bind.annotation.RestController
import java.io.InputStream

@RestController
class SongControllerApiImpl : SongControllerApi {

    @Autowired
    lateinit var songServiceApi: SongServiceApi
    @Autowired
    lateinit var resourceLoader: ResourceLoader
    override fun createSong(songDTO: SongDTO): ResponseEntity<SongDTO> {
        return try {
            ResponseEntity.ok().body(songServiceApi.save(songDTO))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    override fun updateSong(songDTO: SongDTO): ResponseEntity<SongDTO> {
        return try {
            songServiceApi.edit(songDTO)
            ResponseEntity.ok().body(songDTO)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    override fun deleteSong(id: Long): ResponseEntity<SongDTO> {
        return try {
            ResponseEntity.ok().body(songServiceApi.delete(id))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    override fun getSongById(id: Long): ResponseEntity<SongDTO> {
        return try {
            ResponseEntity.ok().body(songServiceApi.find(id))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    override fun getSongs(limit: Int?, offset: Long?): ResponseEntity<List<SongDTO>> {
        return try {
            ResponseEntity.ok().body(songServiceApi.list(limit, offset))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    override fun download(request: HttpServletRequest?, response: HttpServletResponse, filename: String) {
        val resource = resourceLoader.getResource("classpath:music/$filename")
        if (resource.exists()) {
            response.contentType = MediaType.APPLICATION_OCTET_STREAM.toString()
            response.setHeader(
                "Content-Disposition", String.format(
                    "attachment; filename=" +
                            resource.filename
                )
            )
            response.setContentLength(resource.contentLength().toInt())
            val inputStream: InputStream = resource.inputStream
            FileCopyUtils.copy(inputStream, response.outputStream)
        }
    }
}