import com.mongodb.{Mongo, DB, DBCollection, BasicDBObject, DBObject, DBCursor}

import scala.collection.JavaConversions._

val dbCollection: DBCollection = new Mongo("localhost").getDB("mydb").getCollection("persons")

def createDoc(i: Int): BasicDBObject = {
  val doc = new BasicDBObject
  doc.put("name", "name_" + i)
  doc.put("num", i)
  doc
}

val docs: Seq[BasicDBObject] = (0 to 9).map(i => createDoc(i))

docs.foreach(doc => dbCollection.insert(doc))

val cur: DBCursor = dbCollection.find()

def printCursor(cur: DBCursor): Unit = {
  if(cur.hasNext()) {
    println(cur.next)
    printCursor(cur)
  }
}

printCursor(dbCollection.find)


