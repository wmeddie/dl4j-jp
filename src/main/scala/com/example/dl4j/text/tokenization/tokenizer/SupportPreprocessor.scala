package com.example.dl4j.text.tokenization.tokenizer

import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess

class SupportPreprocessor extends TokenPreProcess {
  
  override def preProcess(token: String) = {
    StringCleaning.stripFullDelete(token).toLowerCase()
  }
  
}