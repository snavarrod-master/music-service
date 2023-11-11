package com.usj.musicquizz.model

import jakarta.persistence.*
import java.io.Serializable


@Entity
@Table
class Song : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    var id: Long? = null

    @Basic(optional = false)
    @Column(name = "name")
    var name: String? = null

    @Basic(optional = false)
    @Column(name = "author")
    var author: String? = null

    @Basic(optional = false)
    @Column(name = "file")
    var file: String? = null

    constructor() {}

    constructor(id: Long?) {
        this.id = id
    }

    constructor(id: Long?, name: String, author: String, file: String) {
        this.id = id
        this.name = name
        this.author = author
        this.file = file
    }

    override fun hashCode(): Int {
        var hash = 0
        hash += if (id != null) id.hashCode() else 0
        return hash
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Song) {
            return false
        }
        return !(id == null && other.id != null || id != null && id != other.id)
    }

    override fun toString(): String {
        return "es.usj.musicquizz.model.entities.Song[ id=$id ]"
    }
}