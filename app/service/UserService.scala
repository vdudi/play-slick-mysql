package service

import model._
/**
 * Created by vdudi on 10/20/16.
 */
import com.google.inject.ImplementedBy
import model.User
import scala.concurrent.Future

@ImplementedBy(classOf[UserServiceImpl])
trait UserService {
  def addUser(user: User) : Future[String]
  def getUser(id : Long) : Future[Option[User]]
  def deleteUser(id : Long) : Future[Int]
  def listAllUsers : Future[Seq[User]]
}
