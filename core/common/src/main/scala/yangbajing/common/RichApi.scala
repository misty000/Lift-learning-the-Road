package yangbajing.common

import java.util.Calendar
import java.util.Date

import org.bouncycastle.util.encoders.Hex
import org.joda.time.DateTime

sealed class DateTimeRichApi(datetime: DateTime) {
  def formatDatetimeWeak: String = BaseY.formaterDatetimeWeak.format(datetime.toDate)

  def formatDatetimeMillis: String = BaseY.formaterDatetimeMillis.format(datetime.toDate)

  def formatDatetime: String = BaseY.formaterDatetime.format(datetime.toDate)

  def formatDate: String = BaseY.formaterDate.format(datetime.toDate)

  def formatTime: String = BaseY.formaterTime.format(datetime.toDate)

  def formatTimeMillis: String = BaseY.formaterTimeMillis.format(datetime.toDate)
}

sealed class DateRichApi(date: Date) {
  def formatDatetimeWeak: String = BaseY.formaterDatetimeWeak.format(date)

  def formatDatetimeMillis: String = BaseY.formaterDatetimeMillis.format(date)

  def formatDatetime: String = BaseY.formaterDatetime.format(date)

  def formatDate: String = BaseY.formaterDate.format(date)

  def formatTime: String = BaseY.formaterTime.format(date)

  def formatTimeMillis: String = BaseY.formaterTimeMillis.format(date)

  def toCalender: Calendar = {
    val cal = Calendar.getInstance
    cal.setTime(date)
    cal
  }

  def toDateTime: DateTime = new DateTime(date)
}

sealed class ByteArray2String(data: Array[Byte]) {
  def __String = new String(data)

  def __HexString = new String(Hex.encode(data))
}

sealed class OptionRich[T](option: Option[T]) {

  def dmap[B](ret: => B)(f: T => B): B = {
    if (option.isDefined)
      f(option.get)
    else
      ret
  }

  def getOr[B >: T](deft: => B): B = option.getOrElse(deft)

}
