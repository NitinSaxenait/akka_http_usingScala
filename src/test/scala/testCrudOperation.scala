import controller.methods
import marshalling.marshalling.FileResponse
import org.scalatest.funsuite.AnyFunSuite
// This part of application will test all the CRUD operations
// 1. Check for fibonacci series output right and wrong.
// 2. Create a file.
// 3. Get the created file content right and wrong.
// 4.Rename the created file.
// 5. At last it will delete the renamed file.
class testCrudOperation extends  AnyFunSuite{

  // Test 1
  val fileName = "ABC"
  val newFileName = "Scala.txt"
  test("Positive Fibonacci Series") {
    assert(methods.fibonacci(10) == 55)
  }
  // Test 2
  test("Negative Fibonacci Result") {
    assert(methods.fibonacci(10) != 22)
  }
  // Test 3
  test("Create file test") {
    assert(methods.file(fileName, "I love Scala") == ())
  }
  // Test 4
  test("Test file content") {
    assert(methods.getFileContent(fileName) == "I love Scala")
  }
  // Test 5
  test("File content is not right") {
    assert(methods.getFileContent(fileName) != "I love Java")
  }

  // Test 6
  test("Rename a file") {
    assert(methods.renameFile(fileName, newFileName) == true)
  }
  // Test 7
  test("Delete a file") {
    assert(methods.fileDelete(newFileName) == ())
  }


}
