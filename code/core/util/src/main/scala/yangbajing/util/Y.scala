package yangbajing.util

import net.liftweb.common.{ Box, Empty, Full, Failure }
import net.liftweb.util.Props

import yangbajing.common.BaseY

object Y extends BaseY {
  def dtrybox[R](msg: String)(func: => R) = try {
    Box !! func
  } catch {
    case e: Exception =>
      e.printStackTrace
      Failure(msg, Full(e), Empty)
  }

  def trybox[R](func: => R) = try {
    Box !! func
  } catch {
    case e: Exception =>
      e.printStackTrace
      Failure(e.getMessage, Full(e), Empty)
  }

}
