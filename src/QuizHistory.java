import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.schema.ISqlJetIndexDef;
import org.tmatesoft.sqljet.core.schema.ISqlJetTableDef;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;
import org.tmatesoft.sqljet.core.table.ISqlJetTransaction;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

public class QuizHistory {
    // PURPOSE: store a database of results, hard words and phrases, etc.
    // TODO: use sqlite to store this information?
    // TODO: wrap SqlJetException in HistoryStoreException (so we can use different databases behind the scenes if needed)

    private String fileName;

    public QuizHistory(String fileName) {
    	this.fileName = fileName;
        try {
            init(fileName);
        } catch (Exception ex) {

        }
    }

    private SqlJetDb openDatabase(String filename) {
        SqlJetDb db = null;
        try {
            File dbFile = new File(filename);
            if (!dbFile.exists()) {
                init(filename); 
            }  
            db = SqlJetDb.open(dbFile, true);  
        } catch (Exception e) {
            // pass
        }
        
        return db;
    }

    private void init(String fileName) throws Exception {
        File dbFile = new File(fileName);
        if (!dbFile.exists()) {
            SqlJetDb db = SqlJetDb.open(dbFile, true);
            db.getOptions().setAutovacuum(true);
            db.runTransaction(new ISqlJetTransaction() {
                public Object run(SqlJetDb db) throws SqlJetException {
                    db.getOptions().setUserVersion(1);
                    return true;
                }
            }, SqlJetTransactionMode.WRITE);

            // Create the tables and indices
            createTablesAndIndices(db);    

            // Close
            db.close();
        }
    }

    private void createTablesAndIndices(SqlJetDb db) throws Exception {
        String createLanguageTable = "CREATE TABLE languages (language_abbr TEXT NOT NULL PRIMARY KEY, language_text TEXT NOT NULL)";
        String createWordsTable = "CREATE TABLE words (word TEXT NOT NULL PRIMARY KEY, language TEXT REFERENCES languages(language_abbr), proficiency INTEGER NOT NULL)";
        String createQuestionTable = "CREATE TABLE questions (question_id INTEGER NOT NULL PRIMARY KEY, phrase_input TEXT NOT NULL, phrase_output TEXT NOT NULL, distance INTEGER NOT NULL)";

        System.err.println("Queries:");
        System.err.println("\t" + createLanguageTable);
        System.err.println("\t" + createWordsTable);
        System.err.println("\t" + createQuestionTable);

        try {
            db.createTable(createLanguageTable);
            db.createTable(createWordsTable);
            db.createTable(createQuestionTable);
        } finally {
            db.commit(); // save the changes
        }
    }
}
