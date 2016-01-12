/*
package com.example.dl4j.text.tokenization.tokenizerfactory

import org.deeplearning4j.text.tokenization.tokenizer.{ Tokenizer, TokenPreProcess }
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory
import com.jbs.ao.dl4j.text.tokenization.tokenizer.JapaneseUNITokenizer
import com.atilika.kuromoji.unidic.{ Tokenizer => UNITokenizer }
import java.io.InputStream

class JapaneseUNITokenizerFactory(private var tokenizer: UNITokenizer) extends TokenizerFactory {
  private var preProcess: TokenPreProcess = _
  
  def this() {
    this(new UNITokenizer())
  }
  
  override def create(toTokenize: String): Tokenizer = {
    if (toTokenize == null || toTokenize.isEmpty()) {
      throw new IllegalArgumentException("Unable to proceed; no sentence to tokenize")
    }
    val ret = new JapaneseUNITokenizer(tokenizer, toTokenize)
    ret.setTokenPreProcessor(preProcess)
    ret
  }
  
  override def create(toTokenize: InputStream): Tokenizer = {
    throw new UnsupportedOperationException()
  }
  
  override def setTokenPreProcessor(preProcessor: TokenPreProcess) {
    this.preProcess = preProcessor
  }
}*/