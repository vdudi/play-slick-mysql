package dao


import javax.inject.Inject
import model.User
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.concurrent.Execution.Implicits._
import javax.inject.Inject
import javax.inject.Singleton
import model.User
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.Future


/**
 * Created by vdudi on 10/20/16.
 */

@Singleton
class UserDAOImpl @Inject()(dbConfigProvider: DatabaseConfigProvider)(ec: ExecutionContext) extends UserDAO {

private val dbConfig  = dbConfigProvider.get[JdbcProfile]


  import dbConfig._
  import driver.api._

  private class UserTable(tag:Tag)
    extends Table[User](tag, "user") {
    def id = column[Long]("id", O.PrimaryKey,O.AutoInc)
    def firstName = column[String]("first_name")
    def lastName = column[String]("last_name")
    def mobile = column[Long]("mobile")
    def email = column[String]("email")

    override def * =
      (id, firstName,lastName,mobile,email) <> (User.tupled, User.unapply)
  }

  private val users = TableQuery[UserTable]

  override def add(user: User): Future[String] = {
    db.run(users += user).map(res => "User successfully added").recover {
      case ex : Exception => ex.getCause.getMessage
    }
  }

  override def delete(id: Long): Future[Int] = {
    db.run(users.filter(_.id === id).delete)
  }

  override def get(id: Long): Future[Option[User]] = {
    db.run(users.filter(_.id === id).result.headOption)
  }

  override def listAll: Future[Seq[User]] = {
    db.run(users.result)
  }

}
