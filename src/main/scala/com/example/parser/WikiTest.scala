package com.example.parser

import scala.io.Source
import javax.xml.parsers.SAXParserFactory
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import scala.collection.JavaConversions._
import java.io.PrintWriter
import java.io.File

object WikiTest {
    def main(args: Array[String]): Unit = {
    val start = System.currentTimeMillis()
    val path = args(0)
    val writer = new PrintWriter(new File(args(1)))
  
    
    val factory = SAXParserFactory.newInstance()
	  val saxParser = factory.newSAXParser()

    val handler = new DefaultHandler() {
      var pagesFlag = false
      var textFlag = false
      var count = 0
      var resultText = new StringBuilder
    
      override def startElement(uri: String, localName: String, qName: String, attributes: Attributes): Unit = {
        //println("first: " + qName)
        if (qName.equalsIgnoreCase("page")) {
          count += 1
		  	  pagesFlag = true
		    }
        if (qName.equalsIgnoreCase("text")) {
		  	  textFlag = true
		    }
      }
    
      override def endElement(uri: String, localName: String, qName: String): Unit = {
        //println("end: " + qName)
        if (qName.equalsIgnoreCase("page")) {
          if (count%10000 == 0) println(count)
		  	  pagesFlag = false
		    }
        if (qName.equalsIgnoreCase("text")) {
		  	  textFlag = false
		    }
      }
    
      override def characters(ch: Array[Char], start: Int, length: Int): Unit = {
        if (pagesFlag && textFlag) {
          val res = new String(ch, start, length)
          resultText.append(res)
		    }
      }
    }
  
    saxParser.parse(path, handler)
    val result = handler.resultText.result()
    val res = new WikipediaPage(result).getContent()
    writer.write(res)
    writer.close()
    
    val end = System.currentTimeMillis()
    println("finish")
    println("Time: " + (end - start) + "msec")
  }
}