package com.tomjerry.encrypt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var chars =
        charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@',
            '#', '$', '%', '^', '&', '(', ')', '+',
            '-', '*', '/', '[', ']', '{', '}', '=',
            '<', '>', '?', '_', '"', '.', ',', ' ')

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val plainTxt = plainText.text
        val txtKey = txtKey.text

        btnProses.setOnClickListener {
            val encrypt = txtKey.toString().toIntOrNull()?.let { it1 -> encrypt(plainTxt.toString(), it1) }
            val descrypt = encrypt?.let { it1 -> decrypt(it1, txtKey.toString().toIntOrNull()!!) }
            txtEncrypt.text = "Encrypt : $encrypt"
            txtDescrypt.text = "Descrypt : $descrypt"
            Toast.makeText(this, "$descrypt", Toast.LENGTH_SHORT).show()
        }


    }

    private fun encrypt(text : String, offset : Int) : String {
        val plain = text.toCharArray()
        for(i in plain.indices){
            for(j in chars.indices){
                if(j <= chars.size - offset){
                    if(plain[i] == chars[j]){
                        plain[i] = chars[j + offset]
                        break
                    }
                }else if(plain[i] == chars[j]){
                    plain[i] = chars[j - (chars.size - offset + 1)]
                }
            }
        }
        return String(plain)
    }

    fun decrypt(cip : String, offset : Int) : String {
        val cipher = cip.toCharArray()
        for(i in cipher.indices){
            for(j in chars.indices){
                if(j >= offset && cipher[i] == chars[j]){
                    cipher[i] = chars[j - offset]
                    break
                }
                if(cipher[i] == chars[j] && j < offset){
                    cipher[i] = chars[(chars.size - offset + 1) + j]
                    break
                }
            }
        }
        return String(cipher)
    }

//    private fun encrypt(plainTxt: String, txtKey: Int): String {
//        var result1 = ""
//
//        plainTxt.forEach {
//            result1 = it.toString()
//            if (it.isUpperCase()) {
//                val result = (it.toInt() + txtKey -65) % 26 + 65
//                result1 = charArrayOf(result.toChar()).toString()
//                Log.println(Log.ASSERT, "resilt1", result1)
//            } else {
//                val result = (it.toInt() + txtKey - 97) % 26 + 97
//                result1 = charArrayOf(result.toChar()).toString()
//                Log.println(Log.ASSERT, "resilt2", result1)
//            }
//        }
//
//        return result1
//
//    }

//    private fun desCrypt(chpper: String, txtKey: Int): String {
//        var result1 = ""
//
//        chpper.forEach {
//            result1 = it.toString()
//            if (it.isUpperCase()) {
//                val result = (it.toInt() - txtKey + 65) % 26 - 65
//                result1 = charArrayOf(result.toChar()).toString()
//                Log.println(Log.ASSERT, "resilt1", result1)
//            } else {
//                val result = (it.toInt() - txtKey + 97) % 26 - 97
//                result1 = charArrayOf(result.toChar()).toString()
//                Log.println(Log.ASSERT, "resilt2", result1)
//            }
//        }
//
//        return result1
//    }

}