package com.mobile.zalukaev_lab3

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.View
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

        // Задерживаем распад стены на 1 секунду после запуска анимации пули
        Handler().postDelayed({
            // Создаем эффект распада стены
            val wallAnimator = ObjectAnimator.ofFloat(wall, View.ALPHA, 1.0f, 0f).apply {
                duration = 500 // продолжительность анимации распада

                // Добавляем слушателя, чтобы показать стену после окончания анимации
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        wall.visibility = View.VISIBLE // Показываем стену
                        // После показа стены, создаем анимацию исчезновения
                        val wallAnimator = ObjectAnimator.ofFloat(wall, View.ALPHA, 0f, 1.0f).apply {
                            duration = 500 // продолжительность анимации исчезновения
                        }
                        wallAnimator.start()
                    }
                })
            }
            wallAnimator.start()

        }, 300) // 1000 миллисекунд = 1 секунда задержки
    }
}
