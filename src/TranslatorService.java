public abstract class TranslatorService {

	public enum Language {
		LanguageEnglish, LanguageFrench
	};
	

	public abstract String translate(Language from, Language to, String phrase);

	public abstract String languageString(Language lang);
}