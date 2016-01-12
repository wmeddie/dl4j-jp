package com.example.dl4j.word2vec.train

import java.util.ArrayList
import scala.collection.JavaConversions._
import org.canova.api.util.ClassPathResource
import org.deeplearning4j.models.embeddings.WeightLookupTable
import org.deeplearning4j.models.embeddings.inmemory.InMemoryLookupTable
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer
import org.deeplearning4j.models.word2vec.Word2Vec
import org.deeplearning4j.models.word2vec.wordstore.inmemory.InMemoryLookupCache
import org.deeplearning4j.text.sentenceiterator.{SentencePreProcessor, BasicLineIterator, SentenceIterator, UimaSentenceIterator}
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory
import com.jbs.ao.dl4j.text.tokenization.tokenizerfactory.{ JapaneseIPATokenizerFactory }
import com.jbs.ao.dl4j.text.tokenization.tokenizer.{ JapaneseIPATokenizer, SupportPreprocessor }


object Word2VecRawText {

  def main(args: Array[String]): Unit = {
    val start = System.currentTimeMillis()
    val filePath = args(0)
    val iter: SentenceIterator = new BasicLineIterator(filePath)
    iter.setPreProcessor(new SentencePreProcessor {
      override def preProcess(s: String): String = if (s.length == 0) "あ" else s
    })
    
    println("Load & Vectorize sentences")
    
    val tIPA = new JapaneseIPATokenizerFactory()
    tIPA.setTokenPreProcessor(new SupportPreprocessor())
 //   val tUNI = new JapaneseUNITokenizerFactory()
 //   tUNI.setTokenPreProcessor(new SupportPreprocessor())
    
    //model building: vectorSize=100
    println("Start Building model ipadic: 100")
    val IPAvec100 = new Word2Vec.Builder().minWordFrequency(5).iterations(1)
      .layerSize(100)
      .seed(42)
      .windowSize(5)
      .iterate(iter)
      .tokenizerFactory(tIPA)
      .workers(10)
      .build()
    println("ipadic - Complete")
//    iter.reset()
//    println("Start Building model unidic: 100")
//    val UNIvec100 = new Word2Vec.Builder().minWordFrequency(5).iterations(1)
//      .layerSize(100)
//      .seed(42)
//      .windowSize(5)
//      .iterate(iter)
//      .tokenizerFactory(tUNI)
//      .build()
//    println("unidic - Complete")
    
    //model fitting: 100
    println("fitting ipadic: 100")
    IPAvec100.fit()
//    iter.reset()
//    println("fitting unidic: 100")
//    UNIvec100.fit()
//    iter.reset()
    
    //write
    println("writing vectors to txt")
    WordVectorSerializer.writeWordVectors(IPAvec100, args(1) + "IPAvector100.txt")
//    WordVectorSerializer.writeWordVectors(UNIvec100, "UNIvector100.txt")
  
    //test
    val IPAlist100 = IPAvec100.wordsNearest("日本", 10)
//    val UNIlist100 = UNIvec100.wordsNearest("日本", 10)
    println(IPAlist100)
//    println(UNIlist100)
    val pos = List("日本","ロンドン")
    val neg = List("東京")
    val IPA100 = IPAvec100.wordsNearest(pos, neg, 10)
//    val UNI100 = UNIvec100.wordsNearest(pos, neg, 10)
    println(IPA100)
//    println(UNI100)
    println("-----")

    
    println("Time: " + (System.currentTimeMillis() - start) + "msec")
  }
  
}