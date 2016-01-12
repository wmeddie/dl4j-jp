package com.example.parser

import org.apache.commons.io.FileUtils
import java.io.File
import scala.io.Source
import java.io.PrintWriter

object StringCleaner {
  
  def main(args: Array[String]): Unit = {
    val input = Source.fromFile(new File("src/main/resources/jawiki_CleanResult.txt")).getLines()
    val writer = new PrintWriter(new File("src/main/resources/jawiki_Cleaning.txt"))
    
    input.foreach { x => 
      if (x == null || x.isEmpty() || x == " ") println("null") else writer.write(x + "\n")
    }
    
    writer.close()
    println("finish")
  }
  
}