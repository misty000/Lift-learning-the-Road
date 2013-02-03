package code.servlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.{ HttpServlet, HttpServletRequest, HttpServletResponse }

//@WebServlet(urlPatterns = Array("/HelloWorld"))
class HelloWorld extends HttpServlet {
  override protected def doPost(request: HttpServletRequest, response: HttpServletResponse) {
      val out = response.getWriter()

    out println "你好，羊八井！"
  }

  override protected def doGet(request: HttpServletRequest, response: HttpServletResponse) {
    doPost(request, response)
  }

}
