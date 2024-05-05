package it.polimi.ingsw.utils;

import com.google.gson.*;
import it.polimi.ingsw.model.Game;

import java.lang.reflect.Type;

/**
 * A custom type adapter for the Game class.
 *
 * @author Marcelo S. Hernandez
 */
public class GameTypeAdapter implements JsonSerializer<Game>, JsonDeserializer<Game> {
    @Override
    public JsonElement serialize(Game game, Type type, JsonSerializationContext jsonSerializationContext) {
        Gson gson = new Gson();
        String json = gson.toJson(game, Game.class);
        String regex = "(it\\.polimi\\.ingsw\\.model\\.commongoalcard\\.CommonGoalCard)(\\d+)@\\w+";
        String replacement = "$2";
        String output = json.replaceAll(regex, replacement);
        JsonElement element = JsonParser.parseString(output);

        return element;
    }

    @Override
    public Game deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new Game(jsonElement.getAsJsonObject());
    }
}
