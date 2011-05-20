// Import every thing for MongoDB.
import com.mongodb.{Mongo, DB, DBCollection, BasicDBObject, DBObject, DBCursor}

// Import JavaConversions for implicit comversion.
// Still those are NOT required in this code...
import scala.collection.JavaConversions._

// Create DBCollection.
val dbCollection: DBCollection = new Mongo("localhost").getDB("mydb").getCollection("persons")

// Function creates BasicDBObject by using parameter.
def createDoc(i: Int): BasicDBObject = {
  val doc = new BasicDBObject
  doc.put("name", "name_" + i)
  doc.put("num", i)
  doc
}

// Create documents by using function defined above.
val docs: Seq[BasicDBObject] = (0 to 9).map(i => createDoc(i))

// Insert all documents created above.
docs.foreach(doc => dbCollection.insert(doc))

// Print documents ritrieved above by self recursion.
def printCursor(cur: DBCursor): Unit = {
  if(cur.hasNext()) {
    println(cur.next)
    printCursor(cur)
  }
}

// Invoke function with query without any condition.
printCursor(dbCollection.find)


