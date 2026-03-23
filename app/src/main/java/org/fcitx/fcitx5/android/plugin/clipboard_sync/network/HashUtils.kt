package org.fcitx.fcitx5.android.plugin.clipboard_sync.network

import java.security.MessageDigest
import java.util.Locale

object HashUtils {
    fun sha256(input: String): String {
        return hash(input.toByteArray(Charsets.UTF_8))
    }
    
    fun sha256(bytes: ByteArray): String {
        return hash(bytes)
    }

    private fun hash(bytes: ByteArray): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(bytes)
        return hashBytes.joinToString("") { "%02X".format(it) }
    }
    
    fun calculateFileHash(fileName: String, content: ByteArray): String {
        val contentHash = sha256(content)
        // Format: FileName|CONTENT_HASH
        val combined = "$fileName|$contentHash"
        return sha256(combined)
    }
}
