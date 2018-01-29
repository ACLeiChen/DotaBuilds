package util;

import com.dotabuilds.data.Hero;
import com.dotabuilds.data.Match;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lei Chen on 2017/10/30.
 */

public class MatchFactory {

    public static List<Match> generateTestMatches() {
        List<Match> matches = new LinkedList<Match>();
        Hero Jugg = generateTestHero(8, "Juggernaut", "npc_dota_hero_juggernaut");
        Hero AM = generateTestHero(1, "Anti-Mage", "npc_dota_hero_antimage");
        Hero CM = generateTestHero(5, "Crystal Maiden", "npc_dota_hero_crystal_maiden");
        Match match1 = generateTestMatch(Jugg, "1111111111", 1, 1, 1, true, 111);
        Match match2 = generateTestMatch(AM, "2222222222", 2, 2, 2, true, 222);
        Match match3 = generateTestMatch(CM, "3333333333", 3, 3, 3, false, 333);
        matches.add(match1);
        matches.add(match2);
        matches.add(match3);

        return matches;
    }

    public static Match generateTestMatch(Hero hero, String matchId, int kill, int death, int assist, boolean isWon, int gpm){
        Match match = new Match();
        match.setMatchId(matchId);
        match.setKill(kill);
        match.setDeath(death);
        match.setAssist(assist);
        match.setWon(isWon);
        match.setGpm(gpm);
        match.setMyHero(hero);
        return match;
    }

    public static Hero generateTestHero(Integer id, String localizedName, String name){
        Hero hero = new Hero();
        hero.setId(id);
        hero.setLocalizedName(localizedName);
        hero.setName(name);
        return hero;
    }



    public static String serializeTestMatches() {
        List<Match> matches = generateTestMatches();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(matches);
    }
}
