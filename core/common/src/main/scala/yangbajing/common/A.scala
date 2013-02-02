package yangbajing.common

import java.util.Date
import java.util.UUID

import org.joda.time.DateTime
import org.bson.types.ObjectId

object A extends A

trait A {

  def eitherInt[R](d: Any)(func: => R): Either[Int, R] = d match {
    case v: Int => Left(v)
    case _ => Right(func)
  }

  def eitherString[R](d: Any)(func: => R): Either[String, R] = d match {
    case v: String => Left(v)
    case _ => Right(func)
  }

  def eitherBoolean[R](d: Any)(func: => R): Either[Boolean, R] = d match {
    case v: Boolean => Left(v)
    case _ => Right(func)
  }

  def eitherDouble[R](d: Any)(func: => R): Either[Double, R] = d match {
    case v: Double => Left(v)
    case _ => Right(func)
  }

  def eitherLong[R](d: Any)(func: => R): Either[Long, R] = d match {
    case v: Long => Left(v)
    case _ => Right(func)
  }

  def eitherObjectId[R](d: Any)(func: => R): Either[ObjectId, R] = d match {
    case v: ObjectId => Left(v)
    case _ => Right(func)
  }

  def eitherDate[R](d: Any)(func: => R): Either[Date, R] = d match {
    case v: Date => Left(v)
    case _ => Right(func)
  }

  def eitherUUID[R](d: Any)(func: => R): Either[UUID, R] = d match {
    case v: UUID => Left(v)
    case _ => Right(func)
  }

  def eitherDateTime[R](d: Any)(func: => R): Either[DateTime, R] = d match {
    case v: DateTime => Left(v)
    case _ => Right(func)
  }

  def getOrElse(d: Any, deft: => String): String = d match {
    case v: String => v
    case _ => deft
  }

  def getOrElse(d: Any, deft: => Boolean): Boolean = d match {
    case v: Boolean => v
    case v: Int => if (v < 1) false else true
    case _ => deft
  }

  def getOrElse(d: Any, deft: => Byte): Byte = d match {
    case v: Byte => v
    case _ => deft
  }

  def getOrElse(d: Any, deft: => Char): Char = d match {
    case v: Char => v
    case v: Byte => v.toChar
    case _ => deft
  }

  def getOrElse(d: Any, deft: => Short): Short = d match {
    case v: Short => v
    case v: Char => v.toShort
    case v: Byte => v.toShort
    case _ => deft
  }

  def getOrElse(d: Any, deft: => Int): Int = d match {
    case v: Int => v
    case v: Short => v.toInt
    case v: Char => v.toInt
    case v: Byte => v.toInt
    case _ => deft
  }

  def getOrElse(d: Any, deft: => Long): Long = d match {
    case v: Long => v
    case v: BigInt => v.toLong
    case v: Int => v.toLong
    case v: Short => v.toLong
    case v: Char => v.toLong
    case v: Byte => v.toLong
    case _ => deft
  }

  def getOrElse(d: Any, deft: => BigInt): BigInt = d match {
    case v: BigInt => v
    case v: Long => BigInt(v)
    case _ => deft
  }

  def getOrElse(d: Any, deft: => Float): Float = d match {
    case v: Float => v
    case _ => deft
  }

  def getOrElse(d: Any, deft: => Double): Double = d match {
    case v: Double => v
    case v: BigDecimal => v.toDouble
    case v: Float => v.toDouble
    case _ => deft
  }

  def getOrElse(d: Any, deft: => BigDecimal): BigDecimal = d match {
    case v: BigDecimal => v
    case v: Double => BigDecimal(v)
    case _ => deft
  }

  def getOrElse(d: Any, deft: => Date): Date = d match {
    case v: Date => v
    case _ => deft
  }

  def getOrElse(d: Any, deft: => DateTime): DateTime = d match {
    case v: DateTime => v
    case v: Date => new DateTime(v)
    case _ => deft
  }

  def getOrElse(d: Any, deft: => ObjectId): ObjectId = d match {
    case v: ObjectId => v
    case v: String => try { new ObjectId(v) } catch { case _: Exception => deft }
    case _ => deft
  }

  def getOrElse(d: Any, deft: => UUID): UUID = d match {
    case v: UUID => v
    case _ => deft
  }

  def orInt(d: Option[Any]): Option[Int] =
    d collect { case v if v.isInstanceOf[Int] => v.asInstanceOf[Int] }
  def orInt(d: Option[Any], deft: => Int): Int = orInt(d) getOrElse deft

  def orBoolean(d: Option[Any]): Option[Boolean] =
    d collect { case v if v.isInstanceOf[Boolean] => v.asInstanceOf[Boolean] }
  def orBoolean(d: Option[Any], deft: => Boolean): Boolean = orBoolean(d) getOrElse deft

  def orLong(d: Option[Any]): Option[Long] =
    d collect { case v if v.isInstanceOf[Long] => v.asInstanceOf[Long] }
  def orLong(d: Option[Any], deft: => Long): Long = orLong(d) getOrElse deft

  def orBigInt(d: Option[Any]): Option[BigInt] =
    d collect { case v if v.isInstanceOf[BigInt] => v.asInstanceOf[BigInt] }
  def orBigInt(d: Option[Any], deft: => BigInt): BigInt = orBigInt(d) getOrElse deft

  def orString(d: Option[Any]): Option[String] =
    d collect { case v if v.isInstanceOf[String] => v.asInstanceOf[String] }
  def orString(d: Option[Any], deft: => String): String = orString(d) getOrElse deft

  def orDouble(d: Option[Any]): Option[Double] =
    d collect { case v if v.isInstanceOf[Double] => v.asInstanceOf[Double] }
  def orDouble(d: Option[Any], deft: => Double): Double = orDouble(d) getOrElse deft

  def orDate(d: Option[Any]): Option[Date] =
    d collect { case v if v.isInstanceOf[Date] => v.asInstanceOf[Date] }
  def orDate(d: Option[Any], deft: => Date): Date = orDate(d) getOrElse deft

  def orDateTime(d: Option[Any]): Option[DateTime] = d collect {
    case v if v.isInstanceOf[DateTime] => v.asInstanceOf[DateTime]
    case v if v.isInstanceOf[Date] => new DateTime(v.asInstanceOf[Date])
  }
  def orDateTime(d: Option[Any], deft: => DateTime): DateTime = orDateTime(d) getOrElse deft

  def orObjectId(d: Option[Any]): Option[ObjectId] =
    d collect { case v if v.isInstanceOf[ObjectId] => v.asInstanceOf[ObjectId] }
  def orObjectId(d: Option[Any], deft: => ObjectId): ObjectId = orObjectId(d) getOrElse deft

  def orUUID(d: Option[Any]): Option[UUID] =
    d collect { case v if v.isInstanceOf[UUID] => v.asInstanceOf[UUID] }
  def orUUID(d: Option[Any], deft: => UUID): UUID = orUUID(d) getOrElse deft

}