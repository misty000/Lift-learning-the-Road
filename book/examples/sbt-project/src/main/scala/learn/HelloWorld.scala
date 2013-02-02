package learn

object HelloWorld {
  def main(args: Array[String]) {
    val msg = "您好" :: "羊八井" :: Nil mkString ("", "，", "！")
    println(msg)
  }
}

