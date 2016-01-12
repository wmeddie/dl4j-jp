package com.example.dl4j.text.tokenization.tokenizerfactory

import org.deeplearning4j.text.tokenization.tokenizer.{ Tokenizer, TokenPreProcess }
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory
import com.jbs.ao.dl4j.text.tokenization.tokenizer.JapaneseIPATokenizer
import com.atilika.kuromoji.ipadic.{ Tokenizer => IPATokenizer }
import java.io.InputStream

class JapaneseIPATokenizerFactory(private val tokenizer: IPATokenizer) extends TokenizerFactory{
  private var preProcess: TokenPreProcess = _
  
  def this() {
    this(new IPATokenizer())
  }
  
  override def create(toTokenize: String): Tokenizer = {
    if (toTokenize == null || toTokenize.isEmpty()) {
      throw new IllegalArgumentException("Unable to proceed; no sentence to tokenize")
    }
    val ret = new JapaneseIPATokenizer(tokenizer, toTokenize)
    ret.setTokenPreProcessor(preProcess)
    ret
  }
  
  override def create(toTokenize: InputStream): Tokenizer = {
    throw new UnsupportedOperationException()
  }
  
  override def setTokenPreProcessor(preProcessor: TokenPreProcess) {
    this.preProcess = preProcessor
  }
}