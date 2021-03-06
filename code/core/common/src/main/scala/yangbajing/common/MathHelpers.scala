package yangbajing.common

import org.joda.time.DateTime

object MathHelpers extends MathHelper

trait MathHelper {
  def yMinSwap(value: Char, compare: Char): Char = {
    if (value < compare) value
    else compare
  }

  def yMinSwap(value: Int, compare: Int): Int = {
    if (value < compare) value
    else compare
  }

  def yMinSwap(value: Short, compare: Short): Short = {
    if (value < compare) value
    else compare
  }

  def yMinSwap(value: Double, compare: Double): Double = {
    if (value < compare) value
    else compare
  }

  def yMinSwap(value: Long, compare: Long): Long = {
    if (value < compare) value
    else compare
  }

  def yMinSwap(value: DateTime, compare: DateTime): DateTime = {
    if (value isBefore compare) value
    else compare
  }

  def yMinSwap[T <: Ordered[T]](value: T, compare: T): T = {
    if (value < compare) value
    else compare
  }

  def yMaxSwap(value: Char, compare: Char): Char = {
    if (value > compare) value
    else compare
  }

  def yMaxSwap(value: Int, compare: Int): Int = {
    if (value > compare) value
    else compare
  }

  def yMaxSwap(value: Short, compare: Short): Short = {
    if (value > compare) value
    else compare
  }

  def yMaxSwap(value: Double, compare: Double): Double = {
    if (value > compare) value
    else compare
  }

  def yMaxSwap(value: Long, compare: Long): Long = {
    if (value > compare) value
    else compare
  }

  def yMaxSwap(value: DateTime, compare: DateTime): DateTime = {
    if (value isAfter compare) value
    else compare
  }

  def yMaxSwap[T <: Ordered[T]](value: T, compare: T): T = {
    if (value > compare) value
    else compare
  }

}
