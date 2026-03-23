package org.fcitx.fcitx5.android.plugin.clipboard_sync.service

import android.content.SharedPreferences
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import androidx.preference.PreferenceManager
import org.fcitx.fcitx5.android.plugin.clipboard_sync.R

class QuickSyncTileService : TileService() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onStartListening() {
        super.onStartListening()
        updateTileState()
    }

    override fun onClick() {
        super.onClick()
        val isActive = prefs.getBoolean("quick_sync", true)
        val newState = !isActive
        
        // Update preference
        prefs.edit().putBoolean("quick_sync", newState).apply()
        
        // Update tile
        updateTileState()
    }

    private fun updateTileState() {
        val isActive = prefs.getBoolean("quick_sync", true)
        val tile = qsTile ?: return
        
        tile.state = if (isActive) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        tile.label = getString(R.string.quick_sync_tile_label)
        
        tile.updateTile()
    }
}
