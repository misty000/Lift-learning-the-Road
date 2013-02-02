package yangbajing.persistence.data

import java.util.Date

import scala.collection.mutable

import org.joda.time.DateTime

import yangbajing.util.Y

case class AnyMetadata(
  var name: String = "default",
  var data: mutable.Map[String, Any] = mutable.Map[String, Any](),
  val list: mutable.Map[String, MetaList[Any]] = mutable.Map[String, MetaList[Any]](),
  var metadata: Option[MutableMetadata[Any]] = None) {

  def dataString(key: String): Option[String] =
    Y orString data.get(key)

  def getDataString(key: String, deft: => String): String =
    dataString(key) getOrElse deft
}

case class MutableMetadata[V](
  var name: String = "default",
  val data: mutable.Map[String, V] = mutable.Map[String, V](),
  val list: mutable.Map[String, MetaList[V]] = mutable.Map[String, MetaList[V]](),
  var metadata: Option[MutableMetadata[V]] = None) {

  def getData(key: String): Option[V] =
    data.get(key)

  def getData(key: String, deft: Int): Int =
    data.get(key).map {
      case v: Int => v
      case _ => deft
    } getOrElse deft
}

case class Metadata[V](
  val name: String = "default",
  val data: Map[String, V] = Map[String, V](),
  val list: Map[String, MetaList[V]] = Map[String, MetaList[V]](),
  val metadata: Option[Metadata[V]] = None)

trait TMutableMetadata[V, Self] {
  def self: Self
  def metadata: MutableMetadata[V]

  def putMetadata(key: String, value: V): Option[V] =
    metadata.data.put(key, value)

  def getMetadata(key: String): Option[V] =
    metadata.data.get(key)

  def getOrElseMetadata[B >: V](key: String, deft: B) =
    metadata.data.getOrElse(key, deft)
}

trait TMetadata[K, V] {
  def metadata: Metadata[V]
}
