package yangbajing.common

import java.text.SimpleDateFormat

object BaseY extends BaseY

trait BaseY extends TryUsingResource
  with BaseImplicitly
  with MathHelper
  with A {

  lazy val md5 = DigestHelpers("MD5")
  lazy val sha1 = DigestHelpers("SHA1")
  lazy val sha256 = DigestHelpers("SHA256")
  lazy val sha512 = DigestHelpers("SHA512")

  val emailer = java.util.regex.Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")

  lazy val formaterDatetimeWeak = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E")
  lazy val formaterDatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  lazy val formaterDatetimeMillis = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

  lazy val formaterDate = new SimpleDateFormat("yyyy-MM-dd")
  lazy val formaterMonth = new SimpleDateFormat("yyyy-MM")

  lazy val formaterMinute = new SimpleDateFormat("HH:mm")
  lazy val formaterTime = new SimpleDateFormat("HH:mm:ss")
  lazy val formaterTimeMillis = new SimpleDateFormat("HH:mm:ss.SSS")

  def emailValidate(email: String): Boolean = {
    emailer.matcher(email).matches
  }

  def NotImplement = throw new RuntimeException("未实现")

  def dsSha1(data: String): String =
    dsSha1(data.getBytes)

  def dsSha1(data: Array[Byte]): String =
    new ByteArray2String(sha1.digest(data)).__HexString

  def dsSha256(data: String): String =
    dsSha256(data.getBytes)

  def dsSha256(data: Array[Byte]): String =
    new ByteArray2String(sha256.digest(data)).__HexString

  def dsSha512(data: String): String =
    dsSha512(data.getBytes)

  def dsSha512(data: Array[Byte]): String =
    new ByteArray2String(sha512.digest(data)).__HexString

}
