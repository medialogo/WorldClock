package com.medialogo.worldclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // デフォルトタイムゾーンを取得
        val timeZone = TimeZone.getDefault()
        // タイムゾーン名を表示
        val timeZoneView = findViewById<TextView>(R.id.timeZone)
        timeZoneView.text = timeZone.displayName

        // 「追加する」ボタンクリック時、タイムゾーン選択画面へ遷移
        addButton = findViewById<Button>(R.id.add)
        addButton.setOnClickListener {
            val intent = Intent(this, TimeZoneSelectActivity::class.java)
            startActivityForResult(intent, 1)
        }

    }
}