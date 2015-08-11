public abstract class TranslatorService {	

	public abstract String translate(Language from, Language to, String phrase) throws Exception;

	public abstract String languageString(Language lang);
}