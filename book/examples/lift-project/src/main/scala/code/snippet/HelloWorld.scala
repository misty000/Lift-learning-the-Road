package code
package snippet

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import Helpers._

class HelloWorld {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date

  // 替换页面id为time的元素内容为当前时间
  def howdy: CssSel =
    "#time *" #> date.map(_.toString)

  def render: CssSel =
    "@msg" #> "您好，羊八井！"

  /*
   lazy val date: Date = DependencyFactory.time.vend // create the date via factory

   def howdy = "#time *" #> date.toString
   */
}

