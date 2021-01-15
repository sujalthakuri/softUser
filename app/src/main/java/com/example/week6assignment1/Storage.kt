package com.example.week6assignment1

import com.example.week6assignment1.models.User
var users = mutableMapOf<Int, User>()
var active = false
class Storage {
    public fun appendUsers(user: User){
        val id = users.size + 1
        user.id = id.toInt()
        user.id?.let { users.put(it, user) }
    }
    public fun getUsers(): MutableMap<Int, User> {
        return users
    }

    public fun deleteUser(user: User){
        users.remove(user.id)
    }

    public fun loadDefault(){
        if(!active){
            this.appendUsers(User(null, "Humpty Sharma", 21, 'M', "Ratopool"))
            this.appendUsers(User(null, "Saamir Khan", 17, 'M', "Swoyambhu"))
            this.appendUsers(User(null, "Sujal Singh Thakuri", 20, 'M', "Ratopool"))
        }
        active = true
    }
}