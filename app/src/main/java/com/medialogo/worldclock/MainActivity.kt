package com.medialogo.worldclock

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
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
        val addButton = findViewById<Button>(R.id.add)
        addButton.setOnClickListener {
            val intent = Intent(this, TimeZoneSelectActivity::class.java)
            startActivityForResult(intent, 1)
        }

        showWorldClocks()
    }

    private fun showWorldClocks() {
        // 保存しているタイムゾーンを取得
        val pref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
//        val timeZones = pref.getStringSet("time_zone", setOf()) // code from the book
        val timeZones = pref.getStringSet("time_zone", null) ?: setOf()

        val list = findViewById<ListView>(R.id.clockList)
        list.adapter = TimeZoneAdapter(this, timeZones.toTypedArray())
    }

    override fun onActivityResult(requestCode: Int, resultcode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultcode, data)
        // タイムゾーン選択画面からの戻り
        // 新たなタイムゾーンが追加された場合
        if (requestCode == 1
            && resultcode == Activity.RESULT_OK
            && data != null) {

            // 追加されたタイムゾーンを取得
            val timeZone = data.getStringExtra("timeZone")
            // 保存済みのタイムゾーンを取得
            val pref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
//            val timeZones = pref.getStringSet("time_zone", mutableSetOf()) // code from the book
            val timeZones = pref.getStringSet("time_zone", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

            // 追加されたタイムゾーンをタイムゾーン一覧に追加して保存
            timeZones.add(timeZone)
            pref.edit().putStringSet("time_zone", timeZones).apply()

            // 再表示
            showWorldClocks()
        }
    }

}
