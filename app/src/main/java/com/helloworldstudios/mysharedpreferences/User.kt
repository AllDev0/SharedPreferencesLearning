package com.helloworldstudios.mysharedpreferences

import java.util.UUID

class User{
    lateinit var uuid: UUID
    var name: String
    var surname: String
    var age: Int
    var occupation: String

    constructor(uuid: UUID, name: String, surname: String, age: Int, occupation: String){
        this.uuid = uuid
        this.name = name
        this.surname = surname
        this.age = age
        this.occupation = occupation
    }
    constructor(name: String, surname: String, age: Int, occupation: String){
        this.name = name
        this.surname = surname
        this.age = age
        this.occupation = occupation
    }
}