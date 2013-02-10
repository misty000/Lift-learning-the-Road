package yangbajing.persistence.data

import java.util.Date

import scala.collection.mutable

import org.joda.time.DateTime

import org.bson.types.ObjectId

import yangbajing.common.{ A, KV }

case class Line[V <: Any](var line: List[KV[String, V]] = Nil) {

  def orInt(key: String, deft: => Int): Int =
    A orInt (line.find(_.k == key), deft)

  def orLong(key: String, deft: => Long): Long =
    A orLong (line.find(_.k == key), deft)

  def orDateTime(key: String, deft: => DateTime): DateTime =
    A orDateTime (line.find(_.k == key), deft)

  def orString(key: String, deft: => String): String =
    A orString (line.find(_.k == key), deft)

  def orBoolean(key: String, deft: => Boolean): Boolean =
    A orBoolean (line.find(_.k == key), deft)

  def orObjectId(key: String, deft: => ObjectId): ObjectId =
    A orObjectId (line.find(_.k == key), deft)

  def orDouble(key: String, deft: => Double): Double =
    A orDouble (line.find(_.k == key), deft)

  def orDate(key: String, deft: => Date): Date =
    A orDate (line.find(_.k == key), deft)
}

case class AnyTable(
  var name: String = "default",
  val main: mutable.Map[String, Any] = mutable.Map(),
  val body: mutable.ArrayBuffer[Line[Any]] = mutable.ArrayBuffer(),
  var table: Option[AnyTable] = None)

case class MutableTable[V](
  var name: String = "default",
  val main: mutable.Map[String, V] = mutable.Map[String, V](),
  val body: mutable.ArrayBuffer[Line[V]] = mutable.ArrayBuffer[Line[V]](),
  var table: Option[MutableTable[V]] = None)

case class Table[V](
  val name: String = "default",
  val main: Map[String, V] = Map[String, V](),
  val body: List[Line[V]] = Nil,
  val table: Option[Table[V]] = None)

  