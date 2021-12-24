package marshalling

import spray.json.DefaultJsonProtocol._

object marshalling {
  // CASE FOR FIBONACCI:
  final case class Number(number: Int, result: Int)

  implicit val numberFormat = jsonFormat2(Number)

  // CASE FOR FILE CREATION:
  final case class FileRequest(filename: String, fileContent: String)

  implicit val FileRequestFormat = jsonFormat2(FileRequest)

  final case class FileResponse(status: Boolean)

  implicit val FileResponseFormat = jsonFormat1(FileResponse)

  // CASE OF GETTING THE CONTENT FROM THE FILE:
  final case class FileContentRequest(filename: String)

  implicit val fileContentFormat = jsonFormat1(FileContentRequest)

  final case class FileContentResponse(filename: String, fileContent: String)

  implicit val fileContentrFormat = jsonFormat2(FileContentResponse)

  // CASE FOR DELETION
  final case class DeletionResponse(status: Boolean)

  implicit val DeletionResponseFormat = jsonFormat1(DeletionResponse)

  // CASE FOR RENAMING A FILE
  final case class RenameRequest(filename: String, newFileName: String)

  implicit val RenameRequestFormat = jsonFormat2(RenameRequest)

  final case class RenameResponse(status: Boolean)

  implicit val RenameResponseFormat = jsonFormat1(RenameResponse)
}
