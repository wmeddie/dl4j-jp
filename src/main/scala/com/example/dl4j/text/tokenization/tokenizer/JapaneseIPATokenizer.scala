package com.example.dl4j.text.tokenization.tokenizer

import java.util

import org.deeplearning4j.text.tokenization.tokenizer.{Tokenizer, TokenPreProcess}

import scala.collection.JavaConversions._
import java.util.List
import com.atilika.kuromoji.ipadic.{ Tokenizer => IPATokenizer }


class JapaneseIPATokenizer(private val tokenizer: IPATokenizer, private val toTokenize: String)
      extends Tokenizer {
  private var preProcess: TokenPreProcess = _

  private val tokens = tokenizer.tokenize(toTokenize).map(_.getSurface).toSeq
  private var index: Int = if (tokens.nonEmpty) 0 else -1
  
  override def hasMoreTokens: Boolean = {
    if (index < 0) false else index < tokens.size
  }
  
  override def countTokens(): Int = tokens.size
  
  override def nextToken(): String = {
    if (index < 0) {
      return null
    }

    val ret = tokens(index)
    index += 1
    if (preProcess != null) preProcess.preProcess(ret) else ret
  }
  
  override def getTokens: java.util.List[String] = {
    val ret = new util.ArrayList[String]()

    while (hasMoreTokens) {
      ret.add( nextToken())
    }

    ret
  }
  
  override def setTokenPreProcessor(tokenPreProcess: TokenPreProcess) = {
    this.preProcess = tokenPreProcess
  }
  
  def resetIterator() {
    index = if (countTokens() > 0) 0 else -1
  }
  
}