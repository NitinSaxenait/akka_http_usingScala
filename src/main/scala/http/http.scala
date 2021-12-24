package http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import spray.json.enrichAny
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import controller.methods._
import marshalling.marshalling._
import scala.io.StdIn

// This application is going to perform following "CRUD" Operation:
//
// 1. Get fibonacci series:
// 2. Create a file:
// 3. Get file content:
// 4. Deletion of File:
// 5. Renaming a file:
object http {
  def main(args: Array[String]): Unit = {

    // Making Connections:
    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher


    // Routes for the CRUD Operation:
    val route = path("fibonacci" / IntNumber) { takeinput =>
      val output = fibonacci(takeinput)
      val fibonacci_series = Number(takeinput, output)
      // get request on the server
      get {
        complete(HttpEntity(ContentTypes.`application/json`, fibonacci_series.toJson.prettyPrint))
      }
    } ~
      path("file") {

        post {
          entity(as[FileRequest]) { user =>
            val output = FileResponse(true)
            file(user.filename, user.fileContent)
            complete(HttpEntity(
              ContentTypes.`application/json`,
              output.toJson.prettyPrint
            ))

          }
        }
      } ~
      path("getFileContent") {
        post {
          entity(as[FileContentRequest]) {
            takeFileName =>
              val file_content = getFileContent(takeFileName.filename)
              val get_content = FileContentResponse(takeFileName.filename, file_content)
              complete(HttpEntity(
                ContentTypes.`application/json`,
                get_content.toJson.prettyPrint
              ))
          }
        }
      } ~
      path("delete" / Segment) { fileToDelete =>
        val out = fileDelete(fileToDelete)
        val output = DeletionResponse(true)
        delete {
          complete(HttpEntity(ContentTypes.`application/json`, output.toJson.prettyPrint))
        }
      } ~
      path("Rename") {
        put {
          entity(as[RenameRequest]) {
            fileName =>
              val output = RenameResponse(true)
              renameFile(fileName.filename, fileName.newFileName)
              complete(HttpEntity(ContentTypes.`application/json`, output.toJson.prettyPrint))
          }
        }
      }

    // Binding the connections:
    val binding = Http().bindAndHandle(route, "localhost", 8000)
    println(s"The server is working at http://localhost:8000")
    println("Press RETURN if you want to stop the server.")
    StdIn.readLine()
    binding.flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
