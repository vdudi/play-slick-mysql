package service

/**
 * Created by vdudi on 10/20/16.
 */
import javax.inject.Inject
import javax.inject.Singleton
import dao.UserDAO
import model.User
import scala.concurrent.Future

@Singleton
class UserServiceImpl @Inject()(userDAO: UserDAO) extends UserService {

  def addUser(user: User): Future[String] = {
    userDAO.add(user)
  }
  def deleteUser(id: Long): Future[Int] = {
    userDAO.delete(id)
  }

  def getUser(id: Long): Future[Option[User]] = {
    userDAO.get(id)
  }

  def listAllUsers: Future[Seq[User]] = {
    userDAO.listAll
  }
}
