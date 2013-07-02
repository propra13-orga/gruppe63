package game_eclipse;

public class Fight {

public static boolean fight() {

if (Math.random()*100<90) {System.out.println("Du verlierst! Das Spiel wird neu gestartet!" ); return false;}
else {System.out.println("Du gewinnst!"); return true;}
}

}