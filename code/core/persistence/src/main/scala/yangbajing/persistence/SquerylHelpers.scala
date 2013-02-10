package yangbajing.persistence

import java.sql.{ DriverManager, Date, Timestamp }

import org.squeryl.adapters.{ PostgreSqlAdapter, H2Adapter, MySQLInnoDBAdapter, MySQLAdapter, OracleAdapter }
import org.squeryl.{ Session, SessionFactory }

object SquerylHelpers {

  def createSession(sqlAdapter: String, dbUrl: String, dbUsername: String, dbPassword: String): Session = {
    val adapter =
      sqlAdapter.toLowerCase() match {
        case "postgresql" =>
          registerPostgresql
          new PostgreSqlAdapter
        case "mysqlinnodb" =>
          registerMySQL
          new MySQLInnoDBAdapter
        case "mysql" =>
          registerMySQL
          new MySQLAdapter
        case "oracle" =>
          registerOracle
          new OracleAdapter
        case "h2" =>
          registerH2
          new H2Adapter
        case _ =>
          registerH2
          new H2Adapter
      }
    Session.create(DriverManager.getConnection(dbUrl, dbUsername, dbPassword), adapter)
  }

  def createSessionFactory(sqlAdapter: String, dbUrl: String, dbUsername: String, dbPassword: String) =
    new SessionFactory {
      def newSession = createSession(sqlAdapter, dbUrl, dbUsername, dbPassword)
    }

  lazy val registerPostgresql = Class.forName("org.postgresql.Driver")

  lazy val registerMySQL = Class.forName("")

  lazy val registerOracle = Class.forName("")

  lazy val registerH2 = Class.forName("")
}
