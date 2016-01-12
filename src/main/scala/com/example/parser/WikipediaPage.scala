package com.example.parser

import info.bliki.wiki.filter.PlainTextConverter
import info.bliki.wiki.model.WikiModel
import java.util.regex.Pattern
import org.apache.commons.lang.StringEscapeUtils

class WikipediaPage(xml: String) {
  private var wikiModel = new WikiModel("", "")
  private var textConverter = new PlainTextConverter()

  val REF = Pattern.compile("<ref>.*?</ref>")
  val s = "\\[\\[[a-z\\-]*:[^\\]]+\\]\\]"
  val LANG_LINKS = Pattern.compile(s, Pattern.MULTILINE)
  val DOUBLE_CURLY = Pattern.compile("\\{\\{.*?\\}\\}")
  val URL = Pattern.compile("http://[^ <]+")
  val HTML_TAG = Pattern.compile("<[^!][^>]*>")
  val HTML_COMMENT = Pattern.compile("<!--.*?-->", Pattern.DOTALL)
  val PROG_COMMENT = Pattern.compile("/\\*[^\\*/]+\\*/")
  
  var page = xml
  
  def getContent(): String = {
    val str = LANG_LINKS.matcher(page).replaceAll(" ")
    
    wikiModel.setUp();
    val string = wikiModel.render(textConverter, str)
    wikiModel.tearDown();
    
    val s1 = StringEscapeUtils.unescapeHtml(StringEscapeUtils.unescapeHtml(string))
    val s2 = REF.matcher(s1).replaceAll(" ")
    val s3 = HTML_COMMENT.matcher(s2).replaceAll(" ")
    val s4 = URL.matcher(s3).replaceAll(" ")
    val s5 = DOUBLE_CURLY.matcher(s4).replaceAll(" ")
    val s6 = HTML_TAG.matcher(s5).replaceAll(" ")
    val s7 = PROG_COMMENT.matcher(s6).replaceAll(" ")
    //[[de:rioreiuy.jfierot|trutye.dhjf]]的なものが消えないらしいのでもう一度通している図　パイプが悪さをしているよう
    val s8 = LANG_LINKS.matcher(s7).replaceAll(" ")
    val s9 = s8.replaceAll("\\[\\[", "")
      .replaceAll("\\]\\]", "")
      .replaceAll("[ 　]+"," ")
      .replaceAll("\n ", "\n")
      .replaceAll("\n \n", "\n")
      .replaceAll("\n+", "\n")
      .replaceAll("\n \n", "\n")
    s9
  }
  
  
}