package btl2770a.dream.tpflickr.model

class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: String,
    val title: String,
    val ispublic: Int,
    val isfirend: Int,
    val isfamily: Int
)