public class YandexTranslatorService extends TranslatorService {

	private String[] languageStrings = {"en", "fr"};

	public String translate(Language from, Language to, String phrase) {

	}

	public abstract String languageString(Language lang) {
		return languageStrings[lang.ordinal()];
	}
}