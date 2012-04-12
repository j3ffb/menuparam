package code.snippet


import net.liftweb._
import util._
import Helpers._
import net.liftweb.http.TransientRequestVar
import net.liftweb.common.Box
import java.util.UUID
import code.model.Search
import code.lib.Routing


trait CurrentSearch

object CurrentSearch extends TransientRequestVar[Box[Search]](None) with CurrentSearch {

  def apply() = get
  def apply(maybeId:String) = setIsUnset(Option(Search(UUID.fromString(maybeId))))

  /**
   * Gets the current search id
   */
  def maybeId:Box[UUID] = CurrentSearch.map(_.id)
}

object InsertResults{
  def render = {
    Routing.Results.insert(CurrentSearch().get)
  }
}

object SearchList{
  val searchIds = List (Search(UUID.randomUUID()), Search(UUID.randomUUID()), Search(UUID.randomUUID()))
  def render = {
    "div *" #> searchIds.map(Routing.Search.insert(_))
  }
}