// morphia with scala

// Mongo object id type.
import org.bson.types.ObjectId
// Mongo interface.
import com.mongodb.Mongo

// Morphia annotation.
import com.google.code.morphia.annotations.{Entity, Id}
// Morphoia main class.
import com.google.code.morphia.Morphia

// To handle Collection classes returned from MongoDB as
// Scala collection kind types.
import scala.collection.JavaConversions._

@Entity
class Person(var name: String, var age: Int) {

	// Default constractor.
	def this() = this("", -1)

	// For Object id of Mongo document.
	@Id var id: ObjectId = null;

}

val mongo = new Mongo("localhost")

val database = new Morphia().createDatastore(mongo, "Persons")

database.save[Person](new Person("t-iida", 45))

database.find(classOf[Person]).asList.foreach(elm => println(elm.name + ", " + elm.age))

// End of script.
