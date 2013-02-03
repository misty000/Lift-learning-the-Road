package code.filter

import javax.servlet.annotation.WebFilter
import javax.servlet.{ Filter, FilterChain, FilterConfig }
import javax.servlet.{ ServletRequest, ServletResponse }
import javax.servlet.http.{ HttpServletRequest, HttpServletResponse }

//@WebFilter(urlPatterns = Array("/*"))
class DefaultFilter extends Filter {
  def doFilter(req: ServletRequest, resp: ServletResponse, chain: FilterChain) {
    val request = req.asInstanceOf[HttpServletRequest]
    val response = resp.asInstanceOf[HttpServletResponse]

    request.setCharacterEncoding("UTF-8")
    response.setCharacterEncoding("UTF-8")

    chain.doFilter(request, response)
  }

  def init(config: FilterConfig) {
  }

  def destroy() {
  }

}

