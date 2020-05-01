package gabotech.com.thronesapp

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

const val URL_API = "https://5eab59a2a280ac0016657478.mockapi.io/characters"

object CharactersRepo {
    private var characters: MutableList<Character> = mutableListOf()

    fun findCharacterByID(id: String): Character? {
        return characters.find { character ->
            character.id == id
        }
    }

    fun requestCharacters(context: Context,
                          success: ((MutableList<Character>) -> Unit),
                          error: (() -> Unit) ) {
        if (characters.isEmpty()){
            val request = JsonArrayRequest(Request.Method.GET, URL_API, null,
                { response ->
                    characters = parseCharacters(response)
                    success.invoke(characters)
                },
                { errorRequest ->
                    error.invoke()
                }
            )
            Volley.newRequestQueue(context).add(request)
        }else {
            success.invoke(characters)
        }

    }

    private fun intToCharacter(index: Int): Character {
        return Character(
            name = "Personaje ${index}",
            title = "Titulo ${index}",
            born = "Nacio en ${index}",
            actor = "Actor ${index}",
            quote = "Frase ${index}",
            father = "Padre ${index}",
            mother = "Madre${index}",
            spouse = "Espos@ ${index}",
            img = "random_img",
            house = dummyHouse()
        )
    }

    private fun parseCharacters(jsonArray: JSONArray): MutableList<Character>{
        val characters = mutableListOf<Character>()
        for (index in 0 until jsonArray.length()){
            val character = parseCharacter(jsonArray.getJSONObject(index))
            characters.add(character)
        }
        return characters
    }

    private fun parseCharacter(jsonCharacter: JSONObject): Character{
        return Character(
            jsonCharacter.getString("id"),
            jsonCharacter.getString("name"),
            jsonCharacter.getString("born"),
            jsonCharacter.getString("title"),
            jsonCharacter.getString("actor"),
            jsonCharacter.getString("quote"),
            jsonCharacter.getString("father"),
            jsonCharacter.getString("mother"),
            jsonCharacter.getString("spouse"),
            jsonCharacter.getString("img"),
            parseHouse(jsonCharacter.getJSONObject("house"))
        )
    }

    private fun parseHouse(jsonHouse: JSONObject): House{
        return House(
            jsonHouse.getString("name"),
            jsonHouse.getString("region"),
            jsonHouse.getString("words"),
            jsonHouse.getString("img")
        )
    }

    private fun dummyHouse(): House {
        val ids = arrayOf(
            "stark",
            "lannister",
            "tyrell",
            "arryn",
            "targaryen",
            "baratheon",
            "greyjoy",
            "frey",
            "tully"
        )
        val randomID = java.util.Random().nextInt(ids.size)

        return House(
            name = ids[randomID],
            region = "Region",
            words = "Lema",
            img = "random_img"
        )
    }

    private fun dummyCharacters(): MutableList<Character> {
        // En funciones lambdas (->) que reciben 1 argumento podes hacer referencia a ese argumento con el -it-
        // better
        return (1..10).map {
            intToCharacter(it)
        }.toMutableList()


        /* not bad
        val characters: MutableList<Character> =
            (1..10).map { index ->
                intToCharacter(index)
            }.toMutableList()
        return characters
        */

        /* old way
        val dummies: MutableList<Character> = mutableListOf()
        for (index in 1..10){
            val character: Character =  Character(
                name = "Personaje ${index}",
                title = "Titulo ${index}",
                born = "Nacio en ${index}",
                actor = "Actor ${index}",
                quote = "Frase en ${index}",
                father = "Padre ${index}",
                mother = "Madre${index}",
                spouse = "Espos@ ${index}",
                house = House(name = "Casa ${index}", region = "Region $index", words = "Lema $index")
            )
            dummies.add(character)
        }
        return dummies
         */
    }
}

