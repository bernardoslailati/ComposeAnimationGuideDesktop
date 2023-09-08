package domain

import java.net.URI

data class Link(
    val iconPath: String = "drawable/ic_open_in_new.svg",
    val description: String,
    val uri: URI,
)