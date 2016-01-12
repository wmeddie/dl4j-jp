package com.example.dl4j.text.tokenization.tokenizer

import java.util.regex.Pattern

object StringCleaning {
  
  private val deletePattern = Pattern.compile("[\\d\\.:,\'\"\\(\\)\\[\\]|¥?!;><#\\$]+")
  private val JapaneseDeletePattern = Pattern.compile("[、。､｡（）「」；：？！\u301C\uFF5E・＋]+")
  
  def stripHalfDelete(base: String): String = {
    deletePattern.matcher(base).replaceAll("")
  }
  
  def stripJapaneseDelete(base: String): String = {
    JapaneseDeletePattern.matcher(base).replaceAll("")
  }
  
  def stripFullDelete(base: String): String ={
    val sub = deletePattern.matcher(base).replaceAll("")
    JapaneseDeletePattern.matcher(sub).replaceAll("")
  }
  
}