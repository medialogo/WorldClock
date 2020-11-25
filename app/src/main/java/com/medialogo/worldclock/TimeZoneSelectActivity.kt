package com.medialogo.worldclock

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class TimeZoneSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_zone_select)

        // キャンセルされた場合用(戻るボタンが押された場合など)
        setResult(Activity.RESULT_CANCELED)

        // タイムゾーンリストを取得してアダプターを設定
        val list = findViewById<ListView>(R.id.clockList)
        val adapter = TimeZoneAdapter(this)
        list.adapter = adapter

        list.setOnItemClickListener { _, _, position, _ ->
            // タップされた位置のタイムゾーンを得る
            val timeZone = adapter.getItem(position)

            // 繊維元の画面に結果を返す
            val result = Intent()
            result.putExtra("timeZone", timeZone)
            setResult(Activity.RESULT_OK, result)

            // この画面を閉じる
            finish()
        }
    }
}