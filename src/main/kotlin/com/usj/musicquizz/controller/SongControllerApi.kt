package com.usj.musicquizz.controller

import com.usj.musicquizz.model.dto.SongDTO
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/songs")
interface SongControllerApi {

    @RequestMapping(
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun createSong(@RequestBody @Valid songDTO: SongDTO): ResponseEntity<SongDTO>

    @RequestMapping(
        method = [RequestMethod.PUT],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateSong(@RequestBody @Valid songDTO: SongDTO): ResponseEntity<SongDTO>

    @RequestMapping(
        value = ["/{id}"],
        method = [RequestMethod.DELETE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun deleteSong(@PathVariable id: Long): ResponseEntity<SongDTO>


    @RequestMapping(
        path = ["/{id}"],
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getSongById(@PathVariable id: Long): ResponseEntity<SongDTO>



    @RequestMapping(
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getSongs(
        @RequestParam("limit", required = false) limit: Int? = 1000,
        @RequestParam("offset", required = false) offset: Long? = 0
    ): ResponseEntity<List<SongDTO>>

    @RequestMapping(
        path = ["/download/{filename}"],
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE]
    )
    fun download(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        @PathVariable("filename") filename: String
    )
}