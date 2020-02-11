package com.example.tracker

data class User(var name: String, var password: String)

data class Loc(var longitude: Number, var latitude: Number)

data class Location(var name: String, var loc: Loc, var timestamp: String, var speed: Number, var altitude: Number, var pulse: Number)