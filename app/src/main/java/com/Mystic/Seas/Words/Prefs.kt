package com.Mystic.Seas.Words

object Prefs {
    private lateinit var sharedPrefs: android.content.SharedPreferences

    fun init(context: android.content.Context) {
        sharedPrefs = context.getSharedPreferences("mysterygame", android.content.Context.MODE_PRIVATE)
    }

    val level: Int
        get() = sharedPrefs.getInt("level", 1)


    fun passLevel() {
        if (level < levels.size) sharedPrefs.edit().putInt("level", level + 1).apply()
    }

    var musicVolume: Float
        get() = sharedPrefs.getFloat("musicVolume", 0.5f)
        set(value) = sharedPrefs.edit().putFloat("musicVolume", value).apply()
    var soundVolume: Float
        get() = sharedPrefs.getFloat("soundVolume", 0.5f)
        set(value) = sharedPrefs.edit().putFloat("soundVolume", value).apply()
}