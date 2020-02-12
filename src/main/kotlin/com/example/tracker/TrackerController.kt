package com.example.tracker

import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong
import com.example.tracker.*
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import com.auth0.jwt.algorithms.*
import com.auth0.jwt.*
import com.auth0.jwt.exceptions.*
import com.auth0.jwt.interfaces.DecodedJWT

import java.util.Date


val algorithm: Algorithm = Algorithm.HMAC256(readToken.read("src/main/kotlin/com/example/tracker/users.txt"));

@RestController
class GreetingController {

    @GetMapping("/")
    fun greeting( ) = "TrackerApi Version: v1.0"


}

@RestController
@RequestMapping("/login")
class UserController(var repository: UserRepository) {

    @PostMapping("/")
    fun login(@RequestBody user: User): ResponseEntity<String?> {
        if (repository.checkPassword(user)) {
            try {
                val token = JWT.create()
                        .withClaim("name", user.name)
                        .withExpiresAt(java.util.Date(System.currentTimeMillis() + 604800000))
                        .sign(algorithm)
                return ResponseEntity<String?>("token: " + token,HttpStatus.OK)
            } catch(exception: JWTVerificationException ) {
                return ResponseEntity<String?>(null,HttpStatus.UNAUTHORIZED)
            }

        } else {
            return ResponseEntity<String?>(null,HttpStatus.UNAUTHORIZED)
        }
    }

}

@RestController
@RequestMapping("/location")
class LocationController(var repository: LocationRepository) {

    @PutMapping("/")
    fun updateLoc(@RequestBody loc: Location, @RequestHeader("token") token: String): ResponseEntity<Location?> {
        try {
            val verifier: JWTVerifier = JWT.require(algorithm)
                    .withClaim("name", loc.name)
                    .build()
            val jwt: DecodedJWT = verifier.verify(token)
        } catch (exception: JWTVerificationException ) {
            return ResponseEntity<Location?>(null,HttpStatus.UNAUTHORIZED)
        }
        var ret = repository.updateLocation(loc)
        ret ?: return ResponseEntity<Location?>(null,HttpStatus.BAD_REQUEST)
        return ResponseEntity<Location?>(ret,HttpStatus.OK)

    }
    @GetMapping("/{id}")
    fun getLoc(@PathVariable id: String,@RequestHeader("Authorization") token: String): ResponseEntity<Location?> {
        try {
            val verifier: JWTVerifier = JWT.require(algorithm)
                    .withClaim("name",id )
                    .build()
            val jwt: DecodedJWT = verifier.verify(token)
        } catch (exception: JWTVerificationException ) {
            return ResponseEntity<Location?>(null,HttpStatus.UNAUTHORIZED)
        }
        var ret = repository.getLocation(id)
        ret ?: return ResponseEntity<Location?>(null,HttpStatus.NOT_FOUND)

        return ResponseEntity<Location?>(ret,HttpStatus.OK)
    }

}
