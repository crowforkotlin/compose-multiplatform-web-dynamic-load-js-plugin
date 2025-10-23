package org.example.project

import Zipline

interface Platform {
    val name: String

}

expect fun getPlatform(): Platform