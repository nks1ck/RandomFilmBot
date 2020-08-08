import com.truedev.kinoposk.api.model.film.FilmExt;
import com.truedev.kinoposk.api.service.KinopoiskApiService;

public class Kinopoisk {
    public static void main(String[] args) {
        System.out.println(formDescription(generateMovie()));
    }

    public static FilmExt generateMovie() {
        try {
            KinopoiskApiService kinopoiskApiService = new KinopoiskApiService();
            int id = generateID(1000, 1350000);
            FilmExt film = kinopoiskApiService.getFilmInfo(id);
            return film;
        } catch (NullPointerException e) {

        }
        return null;
    }

    public static String formDescription(FilmExt movie) {
        String result = "";

        result += movie.getData().getNameRU() + "\n";

        result += "Год выхода: " + movie.getData().getYear() + "\n";

        result += "Жанр: " + movie.getData().getGenre() + "\n";

        result += "Страна: " + movie.getData().getCountry() + "\n";

        result += "Продолжительность: " + movie.getData().getFilmLength() + "\n";

        result += "Ссылка: " + movie.getData().getWebURL() + "\n";

        return result;
    }

    private static int generateID (int a, int b) {
        return a + (int)(Math.random() * ((b-a) + 1));
    }
}
