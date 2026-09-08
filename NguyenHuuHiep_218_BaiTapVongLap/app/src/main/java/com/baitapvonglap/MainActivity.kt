package com.baitapvonglap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.baitapvonglap.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBai1.setOnClickListener {
            binding.tvKetQua.text = bai1()
        }

        binding.btnBai2.setOnClickListener {
            binding.tvKetQua.text = bai2()
        }

        binding.btnBai3.setOnClickListener {
            binding.tvKetQua.text = bai3()
        }

        binding.btnXoa.setOnClickListener {
            binding.tvKetQua.text = ""
        }
    }

    // Bài 1: In các số từ 1 đến 10 (dùng for)
    private fun bai1(): String {
        val sb = StringBuilder("Bài 1: Các số từ 1 đến 10\n")
        for (i in 1..10) {
            sb.append(i)
            if (i < 10) sb.append(", ")
        }
        return sb.toString()
    }

    // Bài 2: Tính tổng 1 + 2 + 3 + ... + 100 (dùng for)
    private fun bai2(): String {
        var tong = 0
        for (i in 1..100) {
            tong += i
        }
        return "Bài 2: Tổng 1 + 2 + 3 + ... + 100 = $tong"
    }

    // Bài 3: In các số chẵn từ 1 đến 20 (dùng while)
    private fun bai3(): String {
        val sb = StringBuilder("Bài 3: Các số chẵn từ 1 đến 20\n")
        var i = 1
        while (i <= 20) {
            if (i % 2 == 0) {
                sb.append(i).append(" ")
            }
            i++
        }
        return sb.toString().trim()
    }
}
