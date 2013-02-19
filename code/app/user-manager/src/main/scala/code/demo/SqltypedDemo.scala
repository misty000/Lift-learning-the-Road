package code.demo

import scala.slick.driver.PostgresDriver
import PostgresDriver.simple.Database
import Database.{ threadLocalSession => session }
import scala.slick.direct._
import scala.slick.direct.AnnotationMapper._

@table(name = "coffees")
class Coffee {
  @column
  val name: String = ""

  @column
  val price: Double = 0.0
}

object SqltypedDemo {
  val driver = "org.postgresql.Driver"
  val url = "jdbc:postgresql:devdb"
  val user = "devuser"
  val password = "devpass"

  def main(args: Array[String]): Unit = {
    val db = Database.forURL(url, user, password, null, driver)

    val q1 = Queryable[Coffee]
    val q2 = q1.filter(_.price > 3.0).map{ c =>
      c.name
    }

    db.withSession {
      val backend = new SlickBackend(PostgresDriver, AnnotationMapper)

      println(backend.result(q2, session))
      println(backend.result(q2.length, session))
    }
  }

}
