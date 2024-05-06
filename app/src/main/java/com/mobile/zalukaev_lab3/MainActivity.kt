package com.mobile.zalukaev_lab3
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var bullet: ImageView
    private lateinit var cannon: ImageView
    private lateinit var wall: ImageView
    private lateinit var buttonShoot: Button // Добавляем переменную для кнопки

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bullet = findViewById(R.id.bullet)
        cannon = findViewById(R.id.cannon)
        wall = findViewById(R.id.wall)
        buttonShoot = findViewById(R.id.buttonShoot) // Инициализируем кнопку

        // Назначаем слушателя на кнопку, чтобы запускать движение ядра
        buttonShoot.setOnClickListener {
            shootBullet()
        }
    }

    private fun shootBullet() {
        val bulletAnim = AnimationUtils.loadAnimation(this, R.anim.bullet_animation)
        bullet.startAnimation(bulletAnim)
    }
}