package cc.factorie.app.nlp.embeddings
import java.nio.charset.Charset
object WordVec {
  def main(args: Array[String]) {
    val opts = new EmbeddingOpts
    opts.parse(args)
    println("Default Charset of this JVM =" + Charset.defaultCharset());
    println("User Provided Charset for this project=" + opts.encoding.value)
    val wordEmbedding = if (opts.cbow.value == 1) new CBOWNegSamplingEmbeddingModel(opts) else new SkipGramNegSamplingEmbeddingModel(opts)
    val st1 = System.currentTimeMillis()
    wordEmbedding.buildVocab()
    val st = System.currentTimeMillis()
    println("time taken to build vocab : " + (st - st1) / 1000.0)
    wordEmbedding.learnEmbeddings()
    val en = System.currentTimeMillis() - st
    println("time taken to learn embedding : " + en / 1000.0)
    wordEmbedding.store()

  }
}
