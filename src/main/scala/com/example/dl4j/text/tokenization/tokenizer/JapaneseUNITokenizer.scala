/*
package com.example.dl4j.text.tokenization.tokenizer

import scala.collection.JavaConversions._
import java.util.List
import java.util.ArrayList
import com.atilika.kuromoji.unidic.{ Tokenizer => UNITokenizer, Token => UNIToken }


class JapaneseUNITokenizer(private var tokenizer: UNITokenizer, toTokenize: String) 
      extends org.deeplearning4j.text.tokenization.tokenizer.Tokenizer  {
  
  private var tokens: Seq[String] = Seq()
  private var originalTokens: Seq[String] = Seq()
  private var preProcess: org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess = _
  
  for (token <- tokenizer.tokenize(toTokenize)) {
    tokens = tokens :+ token.getSurface
  }
  
  private var index: Int = if (tokens.size > 0) 0 else -1
  
  
  def this(toTokenize: String) {
    this(new UNITokenizer, toTokenize)
  }
  
  override def hasMoreTokens(): Boolean = {
    if (index < 0) false else index < tokens.size
  }
  
  override def countTokens(): Int = tokens.size
  
  override def nextToken(): String = {
    if (index < 0) {
      return null
    }
    val ret = tokens.get(index)
    index += 1
    if (preProcess != null) preProcess.preProcess(ret) else ret
  }
  
  override def getTokens(): List[String] = {
    val tokens = new ArrayList[String]()
    while (hasMoreTokens()) {
      tokens.add(nextToken())
    }
    tokens
  }
  
  override def setTokenPreProcessor(tokenPreProcess: org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess) = {
    this.preProcess = tokenPreProcess
  }
  
  def resetIterator() {
    index = if (countTokens() > 0) 0 else -1
  }
  
}
*/