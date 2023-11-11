package com.usj.musicquizz.repository

import com.usj.musicquizz.model.Song
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import org.springframework.stereotype.Repository

@Repository
interface SongRepository : JpaRepositoryImplementation<Song, Long> {}