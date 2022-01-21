package controller

import java.io.{File, PrintWriter}
import scala.io.Source
import scala.util.Try

object methods {
  def fibonacci( inputNumber : Int) : Int = inputNumber match {
    case 0 | 1 => inputNumber
    case _ => fibonacci( inputNumber-1 ) + fibonacci( inputNumber-2 )
  }


  //  Method to create file
  def file(file_name: String, file_content: String) = {
    // Creating a file
    val file_Object = new File(file_name)

    // Passing reference of file to the printwriter
    val print_Writer = new PrintWriter(file_Object)

    // Writing to the file
    print_Writer.write(file_content)

    // Closing printwriter
    print_Writer.close()
  }

  // Method to get READ the file:
  def getFileContent(filename: String) = {
    val fileContents = Source.fromFile(filename).getLines.mkString
    fileContents
  }

  // METHOD TO DELETE A FILE IF PRESENT
  def fileDelete(takeFileName: String): Unit = {
    val fileObject = new File(takeFileName)
    fileObject.delete()
  }

  // METHOD TO RENAME A FILE
  def renameFile(oldName: String, newName: String) = {
    Try(new File(oldName).renameTo(new File(newName))).getOrElse(false)

  }

}
