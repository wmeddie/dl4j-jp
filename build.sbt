import AssemblyKeys._

name := """dl4j-jp"""

version := "1.0"

scalaVersion := "2.10.5"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "com.atilika.kuromoji" % "kuromoji-ipadic" % "0.9.0",
  //"com.atilika.kuromoji" % "kuromoji-unidic" % "0.9.0",
  "org.deeplearning4j" % "deeplearning4j-core" % "0.4-rc3.8",
  "org.deeplearning4j" % "deeplearning4j-nlp" % "0.4-rc3.8"  excludeAll(
    ExclusionRule(organization = "org.spark-project.akka")
    ),
  "org.nd4j" % "nd4j-x86" % "0.4-rc3.8",
  "info.bliki.wiki" % "bliki-core" % "3.0.19"
)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"


assemblySettings

// your assembly settings here
(mergeStrategy in assembly) <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("org", xs @ _*)                            => MergeStrategy.first
    case PathList("net", xs @ _*)                            => MergeStrategy.first
    case PathList("javax", "annotation", xs @ _*)            => MergeStrategy.first
    case PathList("edu", xs @ _*)                            => MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".properties" => MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".tooling"    => MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".html"       => MergeStrategy.discard
    case "application.conf"                                  => MergeStrategy.concat
    case "changelog.txt"                                     => MergeStrategy.discard
    case x => old(x)
  }
}

//mainClass in assembly := Some("com.example.parser.WikiParser")
mainClass in assembly := Some("com.example.dl4j.word2vec.train.Word2VecRawText")