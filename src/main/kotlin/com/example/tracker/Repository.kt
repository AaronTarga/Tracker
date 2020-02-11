package com.example.tracker

import java.io.File
import java.io.InputStream
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct
import com.example.tracker.*


@Repository
class UserRepository {
    fun checkPassword(user: User) :Boolean {
        val lineList = mutableListOf<String>()
        File("src/main/kotlin/com/example/tracker/users.txt").useLines { lines -> lines.forEach { lineList.add(it)} }
        var iterator = lineList.listIterator()
        var ret = false
        while(iterator.hasNext()) {
            var list = iterator.next().split(";")
            println(list.get(0))
            if (list.get(0).equals(user.name,false) && list.get(1).equals(user.password,false)) {
                return true
            }
        }
        return ret
    }
}

@Repository
class LocationRepository() {

    fun getLocation(loc: String) : Location? {
        val lineList = mutableListOf<String>()
        File("src/main/kotlin/com/example/tracker/users.txt").useLines { lines -> lines.forEach { lineList.add(it)} }
        var iterator = lineList.listIterator()
        while(iterator.hasNext()) {
            var list = iterator.next().split(";")
            if(list.get(0).equals(loc,false)) {
                return Location(list.get(0), Loc(list.get(2).toDouble(),list.get(3).toDouble()),list.get(4) ,list.get(5).toDouble(),list.get(6).toDouble(),list.get(7).toInt())
            }
        }
        return null
    }
    fun updateLocation(location: Location) : Location? {
        val test = getLocation(location.name)
        test ?: return null
        val lineList = mutableListOf<String>()
        File("src/main/kotlin/com/example/tracker/users.txt").useLines { lines -> lines.forEach { lineList.add(it)} }
        var iterator = lineList.listIterator()
        var text = ""
        while(iterator.hasNext()) {
            var entry = iterator.next()
            var list = entry.split(";")
            if(list.get(0).equals(location.name,false)) {
                var loc = location.loc
                text += location.name + ";" + list.get(1) + ";" + loc.longitude + ";" + loc.latitude + ";" + location.timestamp + ";" + location.speed + ";" + location.altitude + ";" + location.pulse  + "\n"
            } else {
                text += entry + "\n"
            }
        }
        File("src/main/kotlin/com/example/tracker/users.txt").printWriter().use { out -> out.println(text) }
        return location
    }


}