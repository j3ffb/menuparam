package code.lib

import net.liftweb._
import http._
import sitemap._
import common._
import sitemap.Loc.{TemplateBox, CalcValue}
import sitemap.Menu.{WithSlash, ParamMenuable, Menuable}
import util._
import Helpers._
import code.model._
import xml.NodeSeq
import code.snippet.CurrentSearch

object Routing {

  trait SimpleRoute {
    def menu: Menuable

    def insert = "* [href]" #> (menu.loc.createDefaultLink)
  }

  object Index extends SimpleRoute {
    lazy val menu = Menu.i("Home") / "index"
  }

  object Error extends SimpleRoute {
    lazy val menu = Menu.i("error") / "error"
  }

  object ContactUs extends SimpleRoute {
    lazy val menu = Menu.i("Contact Us") / "contact-us"
  }

  trait SearchRoute {
    def menu: ParamMenuable[Search]

    lazy val loc = menu.toLoc

    def insert(search: Search): NodeSeq => NodeSeq = {
      "* [href]" #> (loc.createLink(search))
    }
  }

  object Search extends SearchRoute {

    /* /search/[searchId] */
    lazy val menu = Menu.param[Search](
      "search", "Search",
      s => Full(s).map(_.trim).filter(_.length > 0).flatMap(CurrentSearch(_)),
      s => s.id.toString) / "search"
  }

  object Results extends SearchRoute {

    /* /search/[searchId]/results */
    lazy val searchMenu = Menu.param[Search](
      "results", "Results",
      s => Full(s).map(_.trim).filter(_.length > 0).flatMap{println("setting");CurrentSearch(_)},
      s => s.id.toString) / "search" / * / "results"
    lazy val menu = searchMenu >> CalcValue{() => println("getting"); CurrentSearch() } >> TemplateBox(() => Templates(List("results")))
  }

}